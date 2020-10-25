package me.fdulger.wkt.geometry.writer

import me.fdulger.wkt.geometry.MultiPolygon

object MultiPolygonWriter {
    fun write(multiPolygon: MultiPolygon): String {
        return if (multiPolygon.isEmpty()) "MULTIPOLYGON EMPTY" else {
            val polygons = multiPolygon.polys.joinToString(", ") {
                PolygonWriter.write(it, false)
            }
            "MULTIPOLYGON ($polygons)"
        }
    }
}
