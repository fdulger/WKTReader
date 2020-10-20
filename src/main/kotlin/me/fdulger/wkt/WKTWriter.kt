package me.fdulger.wkt

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale
import me.fdulger.wkt.geometry.Geometry
import me.fdulger.wkt.geometry.GeometryCollection
import me.fdulger.wkt.geometry.LineString
import me.fdulger.wkt.geometry.MultiLineString
import me.fdulger.wkt.geometry.MultiPoint
import me.fdulger.wkt.geometry.MultiPolygon
import me.fdulger.wkt.geometry.Point
import me.fdulger.wkt.geometry.Polygon

class WKTWriter {

    private val df: DecimalFormat
    init {
        val otherSymbols = DecimalFormatSymbols(Locale.getDefault())
        otherSymbols.decimalSeparator = '.'
        df = DecimalFormat("#.#", otherSymbols)
    }

    private fun writePoint(p: Point, printName: Boolean): String {
        val result = StringBuilder(if (printName) "POINT " else "")
        if (p.isEmpty()) {
            result.append("EMPTY")
            return result.toString()
        }
        result.append("(")
                .append(df.format(p.x))
                .append(" ")
                .append(df.format(p.y))
                .append(")")
        return result.toString()
    }

    private fun writePoint(p: Point): String {
        return writePoint(p, true)
    }

    private fun writeLineString(ls: LineString, printName: Boolean): String {
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

    private fun writeLineString(ls: LineString): String {
        return writeLineString(ls, true)
    }

    private fun writePolygon(p: Polygon, printName: Boolean): String {
        val result = StringBuilder(if (printName) "POLYGON " else "")
        if (p.isEmpty()) {
            result.append("EMPTY")
            return result.toString()
        }
        result.append("(")
        result.append(writeLineString(p.outer, false))
                .append(", ")
        for (i in 0 until p.numHoles()) {
            result.append(writeLineString(p.getHole(i)!!, false))
                    .append(", ")
        }
        result.replace(result.length - 2, result.length, "")
        result.append(")")
        return result.toString()
    }

    private fun writePolygon(p: Polygon): String {
        return writePolygon(p, true)
    }

    private fun writeGeometryCollection(gc: GeometryCollection): String {
        val result = StringBuilder("GEOMETRYCOLLECTION ")
        if (gc.isEmpty()) {
            result.append("EMPTY")
            return result.toString()
        }
        result.append("(")
        for (i in 0 until gc.size()) {
            result.append(write(gc[i]))
                    .append(", ")
        }
        result.replace(result.length - 2, result.length, "")
        result.append(")")
        return result.toString()
    }

    private fun writeMultiPoint(mp: MultiPoint): String {
        val result = StringBuilder("MULTIPOINT ")
        if (mp.isEmpty()) {
            result.append("EMPTY")
            return result.toString()
        }
        result.append("(")
        for (i in 0 until mp.size()) {
            result.append(writePoint(mp[i] as Point, false))
                    .append(", ")
        }
        result.replace(result.length - 2, result.length, "")
        result.append(")")
        return result.toString()
    }

    private fun writeMultiLineString(mls: MultiLineString): String {
        val result = StringBuilder("MULTILINESTRING ")
        if (mls.isEmpty()) {
            result.append("EMPTY")
            return result.toString()
        }
        result.append("(")
        for (i in 0 until mls.size()) {
            result.append(writeLineString(mls[i] as LineString, false))
                    .append(", ")
        }
        result.replace(result.length - 2, result.length, "")
        result.append(")")
        return result.toString()
    }

    private fun writeMultiPolygon(mls: MultiPolygon): String {
        val result = StringBuilder("MULTIPOLYGON ")
        if (mls.isEmpty()) {
            result.append("EMPTY")
            return result.toString()
        }
        result.append("(")
        for (i in 0 until mls.size()) {
            result.append(writePolygon(mls[i] as Polygon, false))
                    .append(", ")
        }
        result.replace(result.length - 2, result.length, "")
        result.append(")")
        return result.toString()
    }

    /**
     * Transforms the input me.fdulger.wkt.geometry.Geometry object into WKT-formatted String. e.g.
     * <pre>`
     * new WKTWriter().write(new LineString(new double[]{30, 10, 10, 30, 40, 40}));
     * //returns "LINESTRING (30 10, 10 30, 40 40)"
    `</pre> *
     */
    fun write(geom: Geometry): String {
        return when (geom) {
            is Point -> writePoint(geom)
            is LineString -> writeLineString(geom)
            is Polygon -> writePolygon(geom)
            is MultiPoint -> writeMultiPoint(geom)
            is MultiLineString -> writeMultiLineString(geom)
            is MultiPolygon -> writeMultiPolygon(geom)
            else ->
                if (geom is GeometryCollection) writeGeometryCollection(geom) else "Unknown geometry type."
        }
    }
}