package me.fdulger.wkt.geometry.reader

import me.fdulger.wkt.geometry.Geometry
import me.fdulger.wkt.geometry.GeometryCollection
import java.util.StringTokenizer

object GeometryCollectionReader {
    fun read(st: StringTokenizer): GeometryCollection {
        val geometries: ArrayList<Geometry> = ArrayList()
        var token: String = st.nextToken()
        while (token != ")") {
            when (token) {
                "POINT" -> geometries.add(PointReader.read(st))
                "POLYGON" -> geometries.add(PolygonReader.read(st))
                "LINESTRING" -> geometries.add(LineStringReader.read(st))
            }
            token = st.nextToken()
        }
        return GeometryCollection(geometries)
    }
}
