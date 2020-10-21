package me.fdulger.wkt.geometry.writer

import me.fdulger.wkt.geometry.LineString
import me.fdulger.wkt.geometry.MultiLineString

object MultiLineStringWriter {
    fun write(mls: MultiLineString): String {
        val result = StringBuilder("MULTILINESTRING ")
        if (mls.isEmpty()) {
            result.append("EMPTY")
            return result.toString()
        }
        result.append("(")
        for (i in 0 until mls.size()) {
            result.append(LineStringWriter.write(mls[i] as LineString, false))
                    .append(", ")
        }
        result.replace(result.length - 2, result.length, "")
        result.append(")")
        return result.toString()
    }
}
