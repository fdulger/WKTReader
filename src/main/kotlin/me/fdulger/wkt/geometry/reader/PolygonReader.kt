package me.fdulger.wkt.geometry.reader

import me.fdulger.wkt.geometry.LineString
import me.fdulger.wkt.geometry.Polygon
import java.util.StringTokenizer

object PolygonReader {
    fun read(st: StringTokenizer): Polygon {
        val outer: LineString = LineStringReader.read(st)
        if (outer.isEmpty()) {
            return Polygon()
        }
        val holes: ArrayList<LineString> = ArrayList()
        var token: String = st.nextToken()
        while (token != ")") {
            holes.add(LineStringReader.read(st))
            token = st.nextToken()
        }
        return Polygon(outer, holes)
    }
}