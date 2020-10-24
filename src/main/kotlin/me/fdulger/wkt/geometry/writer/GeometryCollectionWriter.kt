package me.fdulger.wkt.geometry.writer

import me.fdulger.wkt.geometry.GeometryCollection
import me.fdulger.wkt.geometry.LineString
import me.fdulger.wkt.geometry.Point
import me.fdulger.wkt.geometry.Polygon

object GeometryCollectionWriter {
    fun write(gc: GeometryCollection): String {
        val result = StringBuilder("GEOMETRYCOLLECTION ")
        if (gc.isEmpty()) {
            result.append("EMPTY")
            return result.toString()
        }
        result.append("(")
        for (i in 0 until gc.size()) {
            val g = gc[i]
            result.append(when (g) {
                is Point -> PointWriter.write(g)
                is Polygon -> PolygonWriter.write(g)
                is LineString -> LineStringWriter.write(g)
                else -> "unknown geometry"
            }).append(", ")
        }
        result.replace(result.length - 2, result.length, "")
        result.append(")")
        return result.toString()
    }
}
