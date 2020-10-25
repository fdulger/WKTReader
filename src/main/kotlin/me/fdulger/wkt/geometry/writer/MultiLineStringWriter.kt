package me.fdulger.wkt.geometry.writer

import me.fdulger.wkt.geometry.MultiLineString

object MultiLineStringWriter {
    fun write(mls: MultiLineString): String {
        return if (mls.isEmpty()) "MULTILINESTRING EMPTY" else {
            val lineStrings = mls.lines.joinToString(", ") { LineStringWriter.write(it, false) }
            "MULTILINESTRING ($lineStrings)"
        }
    }
}
