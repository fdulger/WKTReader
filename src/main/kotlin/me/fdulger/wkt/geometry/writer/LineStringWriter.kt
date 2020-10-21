package me.fdulger.wkt.geometry.writer

import me.fdulger.wkt.geometry.LineString
import java.text.DecimalFormat

object LineStringWriter {

    private val df = DecimalFormat();

    fun write(ls: LineString, printName: Boolean = true): String {
        val result = StringBuilder(if (printName) "LINESTRING " else "")
        if (ls.isEmpty()) {
            result.append("EMPTY")
            return result.toString()
        }
        result.append("(")
        for (i in 0 until ls.size()) {
            result.append(df.format(ls.getX(i)))
                    .append(" ")
                    .append(df.format(ls.getY(i)))
                    .append(", ")
        }
        result.replace(result.length - 2, result.length, "")
        result.append(")")
        return result.toString()
    }
}