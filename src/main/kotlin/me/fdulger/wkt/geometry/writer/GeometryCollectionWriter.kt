package me.fdulger.wkt.geometry.writer

import me.fdulger.wkt.geometry.GeometryCollection
import me.fdulger.wkt.geometry.LineString
import me.fdulger.wkt.geometry.Point
import me.fdulger.wkt.geometry.Polygon

object GeometryCollectionWriter {
    fun write(gc: GeometryCollection): String {
        return if (gc.isEmpty()) "GEOMETRYCOLLECTION EMPTY" else {
            val geometries = gc.elements.joinToString(", ") { when (it) {
                    is Point -> PointWriter.write(it)
                    is Polygon -> PolygonWriter.write(it)
                    is LineString -> LineStringWriter.write(it)
                    else -> "UNKNOWN GEOMETRY"
                }
            }
            "GEOMETRYCOLLECTION ($geometries)"
        }
    }
}
