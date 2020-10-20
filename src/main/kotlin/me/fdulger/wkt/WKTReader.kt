package me.fdulger.wkt

import java.util.ArrayList
import java.util.StringTokenizer
import me.fdulger.wkt.geometry.Geometry
import me.fdulger.wkt.geometry.GeometryCollection
import me.fdulger.wkt.geometry.LineString
import me.fdulger.wkt.geometry.MultiLineString
import me.fdulger.wkt.geometry.MultiPoint
import me.fdulger.wkt.geometry.MultiPolygon
import me.fdulger.wkt.geometry.Point
import me.fdulger.wkt.geometry.Polygon

class WKTReader {
    private fun readPoints(st: StringTokenizer): List<Point> {
        val result: MutableList<Point> = mutableListOf()
        val skip = listOf("(",","," ")

        var token: String = st.nextToken()
        if (token == "EMPTY") {
            return emptyList()
        }
        var x = Double.NaN
        while (token != ")") {
            if(token in skip) {
                token = st.nextToken()
                continue
            }
            if (x.isNaN()) {
                x = token.toDouble()
            } else {
                result += Point(x, token.toDouble())
                x = Double.NaN
            }
            token = st.nextToken()
        }
        return result
    }

    private fun readPoint(st: StringTokenizer): Point = readPoints(st)[0]

    private fun readLineString(st: StringTokenizer): LineString = LineString(readPoints(st))

    private fun readPolygon(st: StringTokenizer): Polygon {
        val outer: LineString = readLineString(st)
        if (outer.isEmpty()) {
            return Polygon()
        }
        val holes: ArrayList<LineString> = ArrayList<LineString>()
        var token: String = st.nextToken()
        while (token != ")") {
            holes.add(readLineString(st))
            token = st.nextToken()
        }
        return Polygon(outer, holes)
    }

    private fun readMultiPoint(st: StringTokenizer): MultiPoint {
        var token: String = st.nextToken()
        val points: ArrayList<Point> = ArrayList<Point>()
        while (token != ")") {
            points.add(readPoint(st))
            token = st.nextToken()
        }
        return MultiPoint(points)
    }

    private fun readMultiLineString(st: StringTokenizer): MultiLineString {
        var token: String = st.nextToken()
        val lineStrings: ArrayList<LineString> = ArrayList<LineString>()
        while (token != ")") {
            lineStrings.add(readLineString(st))
            token = st.nextToken()
        }
        return MultiLineString(lineStrings)
    }

    private fun readMultiPolygon(st: StringTokenizer): MultiPolygon {
        var token: String = st.nextToken()
        val polygons: ArrayList<Polygon> = ArrayList<Polygon>()
        while (token != ")") {
            polygons.add(readPolygon(st))
            token = st.nextToken()
        }
        return MultiPolygon(polygons)
    }

    /**
     * Transforms the input WKT-formatted String into me.fdulger.wkt.geometry.Geometry object
     */
    fun read(wktString: String?): Geometry? {
        val st = StringTokenizer(wktString, "(), ", true)
        if (!st.hasMoreElements()) {
            return null
        }
        return when (val name = st.nextElement()) {
            "POINT" -> readPoint(st)
            "LINESTRING" -> readLineString(st)
            "POLYGON" -> readPolygon(st)
            "MULTIPOINT" -> readMultiPoint(st)
            "MULTILINESTRING" -> readMultiLineString(st)
            "MULTIPOLYGON" -> readMultiPolygon(st)
            "GEOMETRYCOLLECTION" -> readGeometryCollection(st)
            else -> {
                System.err.println("Unknown geometry type: $name")
                null
            }
        }
    }

    private fun readGeometryCollection(st: StringTokenizer): GeometryCollection {
        val geometries: ArrayList<Geometry> = ArrayList<Geometry>()
        var token: String = st.nextToken()
        while (token != ")") {
            when (token) {
                "POINT" -> {
                    geometries.add(readPoint(st))
                }
                "LINESTRING" -> {
                    geometries.add(readLineString(st))
                }
                "POLYGON" -> {
                    geometries.add(readPolygon(st))
                }
                "MULTIPOINT" -> {
                    geometries.add(readMultiPoint(st))
                }
                "MULTILINESTRING" -> {
                    geometries.add(readMultiLineString(st))
                }
                "MULTIPOLYGON" -> {
                    geometries.add(readMultiPolygon(st))
                }
            }
            token = st.nextToken()
        }
        return GeometryCollection(geometries)
    }
}