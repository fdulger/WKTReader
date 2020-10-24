package me.fdulger.wkt.geometry.reader

import me.fdulger.wkt.geometry.LineString
import me.fdulger.wkt.geometry.MultiLineString
import java.util.StringTokenizer

object MultiLineStringReader {
    fun read(st: StringTokenizer): MultiLineString {
        var token: String = st.nextToken()
        val lineStrings: ArrayList<LineString> = ArrayList()
        while (token != ")") {
            lineStrings.add(LineStringReader.read(st))
            token = st.nextToken()
        }
        return MultiLineString(lineStrings)
    }
}
