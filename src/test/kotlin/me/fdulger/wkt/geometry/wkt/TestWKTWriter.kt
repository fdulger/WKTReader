package me.fdulger.wkt.geometry.wkt

import me.fdulger.wkt.WKTWriter
import me.fdulger.wkt.geometry.*
import org.junit.Assert.assertEquals
import org.junit.Test

class TestWKTWriter {

    @Test
    fun testWritePoint() {
        val p = Point(30.5, 40.0)
        assertEquals("POINT (30.5 40)", WKTWriter.write(p))
    }

    @Test
    fun testWriteEmptyPoint() {
        val p = Point()
        assertEquals("POINT EMPTY", WKTWriter.write(p))
    }

    @Test
    fun testWriteLineString() {
        val ls = LineString(listOf(Point(30.0, 40.0), Point(60.1, 60.0)))
        assertEquals("LINESTRING (30 40, 60.1 60)", WKTWriter.write(ls))
    }

    @Test
    fun testWriteEmptyLineString() {
        val ls0 = LineString()
        val ls1 = LineString(listOf())
        assertEquals("LINESTRING EMPTY", WKTWriter.write(ls0))
        assertEquals("LINESTRING EMPTY", WKTWriter.write(ls1))
    }

    @Test
    fun testWriteEmptyPolygon() {
        val pg = Polygon()
        assertEquals("POLYGON EMPTY", WKTWriter.write(pg))
    }

    @Test
    fun testWritePolygon() {
        val pg = Polygon(outer = LineString(listOf(
                Point(0.0, 0.0),
                Point(0.0, 10.1),
                Point(10.0, 10.0),
                Point(10.0, 0.0),
                Point(0.0, 0.0))), holes = listOf())
        assertEquals("POLYGON ((0 0, 0 10.1, 10 10, 10 0, 0 0))", WKTWriter.write(pg))
    }

    @Test
    fun testWriteEmptyGeometryCollection() {
        val gc = GeometryCollection(emptyList())
        assertEquals("GEOMETRYCOLLECTION EMPTY", WKTWriter.write(gc))
    }

    @Test
    fun testWriteGeometryCollection() {
        val p = Point(30.5, 40.0)
        val gc = GeometryCollection(listOf(p, p))
        assertEquals("GEOMETRYCOLLECTION (POINT (30.5 40), POINT (30.5 40))", WKTWriter.write(gc))
    }

    @Test
    fun testWriteGeometryCollectionWithEmptyMembers() {
        val p = Point(30.5, 40.0)
        val p2 = Point()
        val ls = LineString(listOf(Point(30.0, 40.0), Point(60.1, 60.0)))
        val ls2 = LineString(emptyList())
        val gc = GeometryCollection(listOf(p, p2, ls, ls2))
        val expect = "GEOMETRYCOLLECTION (POINT (30.5 40), POINT EMPTY, LINESTRING (30 40, 60.1 60), LINESTRING EMPTY)"
        assertEquals(expect, WKTWriter.write(gc))
    }

    @Test
    fun testWriteMultiPoint() {
        val p = Point(30.5, 40.0)
        val mp = MultiPoint(listOf(p, p))
        assertEquals("MULTIPOINT ((30.5 40), (30.5 40))", WKTWriter.write(mp))
    }

    @Test
    fun testWriteMultiLineString() {
        val ls = LineString(listOf(Point(30.0, 40.0), Point(60.1, 60.0)))
        val mls = MultiLineString(listOf(ls, ls))
        assertEquals("MULTILINESTRING ((30 40, 60.1 60), (30 40, 60.1 60))", WKTWriter.write(mls))
    }

    @Test
    fun testWriteMultiPolygon() {
        val pg = Polygon(outer = LineString(listOf(
                Point(0.0, 0.0),
                Point(0.0, 10.1),
                Point(10.0, 10.0),
                Point(10.0, 0.0),
                Point(0.0, 0.0))), holes = emptyList())
        val mpg = MultiPolygon(listOf(pg, pg))
        val expect = "MULTIPOLYGON (((0 0, 0 10.1, 10 10, 10 0, 0 0)), ((0 0, 0 10.1, 10 10, 10 0, 0 0)))"
        assertEquals(expect, WKTWriter.write(mpg))
    }
}