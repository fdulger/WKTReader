package me.fdulger.wkt.geometry.reader

import me.fdulger.wkt.geometry.MultiPoint
import me.fdulger.wkt.geometry.Point
import java.util.StringTokenizer

object MultiPointReader {
    fun read(st: StringTokenizer): MultiPoint {
        var token: String = st.nextToken()
        val points: ArrayList<Point> = ArrayList()
        while (token != ")") {
            points.add(PointReader.read(st))
            token = st.nextToken()
        }
        return MultiPoint(points)
    }
}