package me.fdulger.wkt.geometry.reader

import me.fdulger.wkt.geometry.LineString
import java.util.StringTokenizer

object LineStringReader {
    fun read(st: StringTokenizer): LineString = LineString(readPoints(st))
}