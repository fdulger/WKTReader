package me.fdulger.wkt.geometry.writer

import me.fdulger.wkt.geometry.MultiPoint

object MultiPointWriter {
    fun write(mp: MultiPoint): String {
        return if (mp.isEmpty()) "MULTIPOINT EMPTY" else {
            val points = mp.points.joinToString(", ") { PointWriter.write(it, false) }
            "MULTIPOINT ($points)"
        }
    }
}
