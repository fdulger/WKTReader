package me.fdulger.wkt.geometry.writer

import me.fdulger.wkt.geometry.MultiPolygon
import me.fdulger.wkt.geometry.Polygon

object MultiPolygonWriter {
    fun write(multiPolygon: MultiPolygon): String {
        val result = StringBuilder("MULTIPOLYGON ")
        if (multiPolygon.isEmpty()) {
            result.append("EMPTY")
            return result.toString()
        }
        result.append("(")
        for (i in 0 until multiPolygon.size()) {
            result.append(PolygonWriter.write(multiPolygon[i] as Polygon, false))
                    .append(", ")
        }
        result.replace(result.length - 2, result.length, "")
        result.append(")")
        return result.toString()
    }
}
