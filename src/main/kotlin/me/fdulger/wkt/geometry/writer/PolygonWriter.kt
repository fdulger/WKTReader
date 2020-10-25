package me.fdulger.wkt.geometry.writer

import me.fdulger.wkt.geometry.Polygon

object PolygonWriter {
    fun write(p: Polygon, printName: Boolean = true): String {
        return if (p.isEmpty()) "POLYGON EMPTY" else {
            val outer = LineStringWriter.write(p.outer, false)
            return if (p.holes.isEmpty()) {
                if (printName) "POLYGON ($outer)" else "($outer)"
            } else {
                val holes = if (p.holes.isEmpty()) "" else p.holes.joinToString(", ") {
                    LineStringWriter.write(it, false)
                }
                if (printName) "POLYGON ($outer, $holes)" else "($outer, $holes)"
            }
        }
    }
}
