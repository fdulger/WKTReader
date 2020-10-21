package me.fdulger.wkt.geometry.writer

import me.fdulger.wkt.geometry.MultiPoint
import me.fdulger.wkt.geometry.Point

object MultiPointWriter {
    fun write(mp: MultiPoint): String {
        val result = StringBuilder("MULTIPOINT ")
        if (mp.isEmpty()) {
            result.append("EMPTY")
            return result.toString()
        }
        result.append("(")
        for (i in 0 until mp.size()) {
            result.append(PointWriter.write(mp[i] as Point, false))
                    .append(", ")
        }
        result.replace(result.length - 2, result.length, "")
        result.append(")")
        return result.toString()
    }
}
