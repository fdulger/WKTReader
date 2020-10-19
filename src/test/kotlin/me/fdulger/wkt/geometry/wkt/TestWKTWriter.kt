package me.fdulger.wkt.geometry.wkt

import org.junit.Assert.assertEquals
import me.fdulger.wkt.geometry.LineString
import me.fdulger.wkt.geometry.Point
import org.junit.Before
import org.junit.Test
import me.fdulger.wkt.geometry.Geometry
import me.fdulger.wkt.geometry.GeometryCollection
import me.fdulger.wkt.geometry.MultiLineString
import me.fdulger.wkt.geometry.MultiPoint
import me.fdulger.wkt.geometry.MultiPolygon
import me.fdulger.wkt.geometry.Polygon
import java.util.*

class TestWKTWriter {
    val writer: WKTWriter = WKTWriter()

    @Test
    fun testWritePoint() {
        val p = Point(30.5, 40.0)
        assertEquals("POINT (30.5 40)", writer.write(p))
    }

    @Test
    fun testWriteEmptyPoint() {
        val p = Point()
        assertEquals("POINT EMPTY", writer.write(p))
    }

    @Test
    fun testWriteLineString() {
        val ls = LineString(doubleArrayOf(30.0, 40.0, 60.1, 60.0))
        assertEquals("LINESTRING (30 40, 60.1 60)", writer.write(ls))
    }

    @Test
    fun testWriteEmptyLineString() {
        val ls0 = LineString()
        val ls1 = LineString(doubleArrayOf())
        assertEquals("LINESTRING EMPTY", writer.write(ls0))
        assertEquals("LINESTRING EMPTY", writer.write(ls1))
    }

    @Test
    fun testWriteEmptyPolygon() {
        val pg = Polygon()
        assertEquals("POLYGON EMPTY", writer.write(pg))
    }

    @Test
    fun testWritePolygon() {
        val pg = Polygon(LineString(doubleArrayOf(0.0, 0.0, 0.0, 10.1, 10.0, 10.0, 10.0, 0.0, 0.0, 0.0)), listOf<LineString>())
        assertEquals("POLYGON ((0 0, 0 10.1, 10 10, 10 0, 0 0))", writer.write(pg))
    }

    @Test
    fun testWriteEmptyGeometryCollection() {
        val gc: GeometryCollection = GeometryCollection(emptyList())
        assertEquals("GEOMETRYCOLLECTION EMPTY", writer.write(gc))
    }

    @Test
    fun testWriteGeometryCollection() {
        val p = Point(30.5, 40.0)
        val gc: GeometryCollection = GeometryCollection(listOf(p, p))
        assertEquals("GEOMETRYCOLLECTION (POINT (30.5 40), POINT (30.5 40))", writer.write(gc))
    }

    @Test
    fun testWriteGeometryCollectionWithEmptyMembers() {
        val p = Point(30.5, 40.0)
        val p2 = Point()
        val ls = LineString(doubleArrayOf(30.0, 40.0, 60.1, 60.0))
        val ls2 = LineString(doubleArrayOf())
        val gc: GeometryCollection = GeometryCollection(listOf(p, p2, ls, ls2))
        assertEquals("GEOMETRYCOLLECTION (POINT (30.5 40), POINT EMPTY, LINESTRING (30 40, 60.1 60), LINESTRING EMPTY)", writer.write(gc))
    }

    @Test
    fun testWriteMultiPoint() {
        val p = Point(30.5, 40.0)
        val mp = MultiPoint(listOf(p, p))
        assertEquals("MULTIPOINT ((30.5 40), (30.5 40))", writer.write(mp))
    }

    @Test
    fun testWriteMultiLineString() {
        val ls = LineString(doubleArrayOf(30.0, 40.0, 60.1, 60.0))
        val mls = MultiLineString(listOf(ls, ls))
        assertEquals("MULTILINESTRING ((30 40, 60.1 60), (30 40, 60.1 60))", writer.write(mls))
    }

    @Test
    fun testWriteMultiPolygon() {
        val pg = Polygon(LineString(doubleArrayOf(0.0, 0.0, 0.0, 10.1, 10.0, 10.0, 10.0, 0.0, 0.0, 0.0)), listOf())
        val mpg = MultiPolygon(listOf(pg, pg))
        assertEquals("MULTIPOLYGON (((0 0, 0 10.1, 10 10, 10 0, 0 0)), ((0 0, 0 10.1, 10 10, 10 0, 0 0)))", writer.write(mpg))
    }
}