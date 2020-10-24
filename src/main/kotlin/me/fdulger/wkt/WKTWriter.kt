package me.fdulger.wkt

import me.fdulger.wkt.geometry.Geometry
import me.fdulger.wkt.geometry.GeometryCollection
import me.fdulger.wkt.geometry.LineString
import me.fdulger.wkt.geometry.MultiLineString
import me.fdulger.wkt.geometry.MultiPoint
import me.fdulger.wkt.geometry.MultiPolygon
import me.fdulger.wkt.geometry.Point
import me.fdulger.wkt.geometry.Polygon
import me.fdulger.wkt.geometry.writer.GeometryCollectionWriter
import me.fdulger.wkt.geometry.writer.LineStringWriter
import me.fdulger.wkt.geometry.writer.MultiLineStringWriter
import me.fdulger.wkt.geometry.writer.MultiPointWriter
import me.fdulger.wkt.geometry.writer.MultiPolygonWriter
import me.fdulger.wkt.geometry.writer.PointWriter
import me.fdulger.wkt.geometry.writer.PolygonWriter

object WKTWriter {
    fun write(geometry: Geometry): String {
        return when (geometry) {
            is Point -> PointWriter.write(geometry)
            is Polygon -> PolygonWriter.write(geometry)
            is LineString -> LineStringWriter.write(geometry)
            is MultiPoint -> MultiPointWriter.write(geometry)
            is MultiPolygon -> MultiPolygonWriter.write(geometry)
            is MultiLineString -> MultiLineStringWriter.write(geometry)
            is GeometryCollection -> GeometryCollectionWriter.write(geometry)
            else -> "Unknown geometry type."
        }
    }
}
