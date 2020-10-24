package me.fdulger.wkt.geometry.reader

import me.fdulger.wkt.geometry.MultiPolygon
import me.fdulger.wkt.geometry.Polygon
import java.util.StringTokenizer

object MultiPolygonReader {
    fun read(st: StringTokenizer): MultiPolygon {
        var token: String = st.nextToken()
        val polygons: ArrayList<Polygon> = ArrayList()
        while (token != ")") {
            polygons.add(PolygonReader.read(st))
            if (!st.hasMoreTokens()) break
            token = st.nextToken()
        }
        return MultiPolygon(polygons)
    }
}
