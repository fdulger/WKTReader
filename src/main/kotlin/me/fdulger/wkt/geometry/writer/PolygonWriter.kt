package me.fdulger.wkt.geometry.writer

import me.fdulger.wkt.geometry.Polygon

object PolygonWriter {
    fun write(p: Polygon, printName: Boolean = true): String {
        val result = StringBuilder(if (printName) "POLYGON " else "")
        if (p.isEmpty()) {
            result.append("EMPTY")
            return result.toString()
        }
        result.append("(")
        result.append(LineStringWriter.write(p.outer, false))
                .append(", ")
        for (i in 0 until p.numHoles()) {
            result.append(LineStringWriter.write(p.getHole(i)!!, false))
                    .append(", ")
        }
        result.replace(result.length - 2, result.length, "")
        result.append(")")
        return result.toString()
    }
}