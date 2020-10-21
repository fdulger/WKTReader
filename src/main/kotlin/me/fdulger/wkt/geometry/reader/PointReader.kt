package me.fdulger.wkt.geometry.reader

import me.fdulger.wkt.geometry.Point
import java.util.StringTokenizer

object PointReader {
    fun read(st: StringTokenizer): Point = readPoints(st)[0]
}

fun readPoints(st: StringTokenizer): List<Point> {
    val result: MutableList<Point> = mutableListOf()
    val skip = listOf("(", ",", " ")

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