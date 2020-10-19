package me.fdulger.wkt

import java.util.ArrayList
import java.util.Arrays
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
    fun readDoubleArray(st: StringTokenizer): DoubleArray? {
        val list: ArrayList<Double> = ArrayList<Double>()
        val skip = listOf("(", ",", " ")
        var token: String = st.nextToken()
        while (!token.equals(")")) {
            if (token.equals("EMPTY")) {
                return null
            }
            if (!skip.contains(token)) {
                list.add(token.toDouble())
            }
            token = st.nextToken()
        }
        val result = DoubleArray(list.size)
        for (i in 0 until list.size) {
            result[i] = list.get(i)
        }
        return result
    }

    fun readPoint(st: StringTokenizer): Point {
        val coords = readDoubleArray(st)
        return if (coords == null) Point() else Point(coords[0], coords[1])
    }

    fun readLineString(st: StringTokenizer): LineString {
        val coords = readDoubleArray(st)
        return coords?.let { LineString(it) } ?: LineString()
    }

    fun readPolygon(st: StringTokenizer): Polygon {
        val outer: LineString = readLineString(st)
        if (outer.isEmpty()) {
            return Polygon()
        }
        val holes: ArrayList<LineString> = ArrayList<LineString>()
        var token: String = st.nextToken()
        while (!token.equals(")")) {
            holes.add(readLineString(st))
            token = st.nextToken()
        }
        return Polygon(outer, holes)
    }

    fun readMultiPoint(st: StringTokenizer): MultiPoint {
        var token: String = st.nextToken()
        val points: ArrayList<Point> = ArrayList<Point>()
        while (!token.equals(")")) {
            points.add(readPoint(st))
            token = st.nextToken()
        }
        return MultiPoint(points)
    }

    fun readMultiLineString(st: StringTokenizer): MultiLineString {
        var token: String = st.nextToken()
        val lineStrings: ArrayList<LineString> = ArrayList<LineString>()
        while (!token.equals(")")) {
            lineStrings.add(readLineString(st))
            token = st.nextToken()
        }
        return MultiLineString(lineStrings)
    }

    fun readMultiPolygon(st: StringTokenizer): MultiPolygon {
        var token: String = st.nextToken()
        val polygons: ArrayList<Polygon> = ArrayList<Polygon>()
        while (!token.equals(")")) {
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
        val name = st.nextElement() as String
        var result: Geometry? = Point()
        if (name.equals("POINT")) {
            result = readPoint(st)
        } else if (name.equals("LINESTRING")) {
            result = readLineString(st)
        } else if (name.equals("POLYGON")) {
            result = readPolygon(st)
        } else if (name.equals("MULTIPOINT")) {
            result = readMultiPoint(st)
        } else if (name.equals("MULTILINESTRING")) {
            result = readMultiLineString(st)
        } else if (name.equals("MULTIPOLYGON")) {
            result = readMultiPolygon(st)
        } else if (name.equals("GEOMETRYCOLLECTION")) {
            val geometries: ArrayList<Geometry> = ArrayList<Geometry>()
            var token: String = st.nextToken()
            while (!token.equals(")")) {
                if (token.equals("POINT")) {
                    geometries.add(readPoint(st))
                } else if (token.equals("LINESTRING")) {
                    geometries.add(readLineString(st))
                } else if (token.equals("POLYGON")) {
                    geometries.add(readPolygon(st))
                } else if (token.equals("MULTIPOINT")) {
                    geometries.add(readMultiPoint(st))
                } else if (token.equals("MULTILINESTRING")) {
                    geometries.add(readMultiLineString(st))
                } else if (token.equals("MULTIPOLYGON")) {
                    geometries.add(readMultiPolygon(st))
                }
                token = st.nextToken()
            }
            result = GeometryCollection(geometries)
        } else {
            System.err.println("Unknown geometry type: $name")
            return null
        }
        return result
    }
}