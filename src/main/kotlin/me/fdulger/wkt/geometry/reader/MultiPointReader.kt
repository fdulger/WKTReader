package me.fdulger.wkt.geometry.reader

import me.fdulger.wkt.geometry.MultiPoint
import me.fdulger.wkt.geometry.Point
import java.util.StringTokenizer

object MultiPointReader {
    fun read(st: StringTokenizer): MultiPoint {
        var token: String = st.nextToken()
        val points: ArrayList<Point> = ArrayList()
        while (token != ")") {
            val point = readPoints(st)
            if (point.isNotEmpty()) points += point
            if (!st.hasMoreTokens()) break
            token = st.nextToken()
        }
        return MultiPoint(points)
    }
}
