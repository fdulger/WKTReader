package me.fdulger.wkt.geometry.reader

import me.fdulger.wkt.geometry.Point
import java.util.StringTokenizer

object PointReader {
    fun read(st: StringTokenizer): Point = readPoints(st).getOrElse(0) { Point() }
}

fun readPoints(st: StringTokenizer): List<Point> {
    val result: MutableList<Point> = mutableListOf()
    val skip = "(), "

    var token: String
    var x = Double.NaN
    while (true) {
        token = st.nextToken()
        if (token in skip) {
            if (token == ")") break
        } else {
            if (token == "EMPTY") {
                return emptyList()
            }
            if (x.isNaN()) {
                x = token.toDouble()
            } else {
                result += Point(x, token.toDouble())
                x = Double.NaN
            }
        }
    }
    return result
}
