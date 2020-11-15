package me.fdulger.wkt

import java.lang.IllegalArgumentException
import java.util.StringTokenizer
import me.fdulger.wkt.geometry.Geometry
import me.fdulger.wkt.geometry.reader.GeometryCollectionReader
import me.fdulger.wkt.geometry.reader.LineStringReader
import me.fdulger.wkt.geometry.reader.MultiLineStringReader
import me.fdulger.wkt.geometry.reader.MultiPointReader
import me.fdulger.wkt.geometry.reader.MultiPolygonReader
import me.fdulger.wkt.geometry.reader.PointReader
import me.fdulger.wkt.geometry.reader.PolygonReader

object WKTReader {
    fun read(wktString: String?): Geometry? {
        val st = StringTokenizer(wktString, "(), ", true)
        if (!st.hasMoreTokens()) throw IllegalArgumentException("invalid input")
        return when (val name = st.nextElement()) {
            "POINT" -> PointReader.read(st)
            "POLYGON" -> PolygonReader.read(st)
            "LINESTRING" -> LineStringReader.read(st)
            "MULTIPOINT" -> MultiPointReader.read(st)
            "MULTIPOLYGON" -> MultiPolygonReader.read(st)
            "MULTILINESTRING" -> MultiLineStringReader.read(st)
            "GEOMETRYCOLLECTION" -> GeometryCollectionReader.read(st)
            else -> throw IllegalArgumentException("Unknown geometry type: $name")
        }
    }
}
