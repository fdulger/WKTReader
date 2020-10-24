package me.fdulger.wkt.geometry.writer

import me.fdulger.wkt.geometry.Point
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

val decimalFormat = DecimalFormat("#.#", DecimalFormatSymbols(Locale.getDefault()))

object PointWriter {
    fun write(point: Point, printName: Boolean = true): String {
        return if (!point.isEmpty()) {
            val coords = "(${decimalFormat.format(point.x)} ${decimalFormat.format(point.y)})"
            if (printName) "POINT $coords" else coords
        } else "POINT EMPTY"
    }
}
