package me.fdulger.wkt.geometry.writer

import me.fdulger.wkt.geometry.Point
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

object PointWriter {

    private val decimalFormat = DecimalFormat("#.#", DecimalFormatSymbols(Locale.getDefault()))

    fun write(point: Point, name: Boolean = true, parentheses: Boolean = true): String {
        return if (!point.isEmpty()) {
            val coords = "${decimalFormat.format(point.x)} ${decimalFormat.format(point.y)}"
            if (name) "POINT ($coords)" else if (parentheses) "($coords)" else coords
        } else "POINT EMPTY"
    }
}
