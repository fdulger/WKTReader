package me.fdulger.wkt.geometry.writer

import me.fdulger.wkt.geometry.LineString

object LineStringWriter {
    fun write(ls: LineString, printName: Boolean = true): String {
        return if (ls.isEmpty()) "LINESTRING EMPTY" else {
            val points = ls.points.joinToString(", ") { PointWriter.write(it, false, false) }
            if (printName) "LINESTRING ($points)" else "($points)"
        }
    }
}
