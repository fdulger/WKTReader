package me.fdulger.wkt.geometry.writer

import me.fdulger.wkt.geometry.Point
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

object PointWriter {

    private val df: DecimalFormat
    init {
        val otherSymbols = DecimalFormatSymbols(Locale.getDefault())
        otherSymbols.decimalSeparator = '.'
        df = DecimalFormat("#.#", otherSymbols)
    }

    fun write(point: Point, printName: Boolean = true): String {
        return if (!point.isEmpty()) {
            val coords = "(${df.format(point.x)} ${df.format(point.y)})"
            if (printName) "POINT $coords" else coords
        } else "POINT EMPTY"
    }
}