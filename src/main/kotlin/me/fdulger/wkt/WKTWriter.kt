package me.fdulger.wkt

import me.fdulger.wkt.geometry.*
import me.fdulger.wkt.geometry.writer.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

class WKTWriter {
    fun write(geom: Geometry): String {
        return when (geom) {
            is Point -> PointWriter.write(geom)
            is Polygon -> PolygonWriter.write(geom)
            is LineString -> LineStringWriter.write(geom)
            is MultiPoint -> MultiPointWriter.write(geom)
            is MultiPolygon -> MultiPolygonWriter.write(geom)
            is MultiLineString -> MultiLineStringWriter.write(geom)
            is GeometryCollection -> GeometryCollectionWriter.write(geom)
            else -> "Unknown geometry type."
        }
    }
}