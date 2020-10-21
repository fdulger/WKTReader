package me.fdulger.wkt.geometry.wkt

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

import me.fdulger.wkt.WKTReader
import me.fdulger.wkt.WKTWriter
import me.fdulger.wkt.geometry.Point
import me.fdulger.wkt.geometry.LineString
import me.fdulger.wkt.geometry.Polygon

class TestWKTReader {

    @Test
    fun testReadPoint() {
        val p = Point(30.5, 40.0)
        val p2: Point = WKTReader.read(WKTWriter.write(p))!! as Point
        assertTrue(!p2.isEmpty())
        assertEquals(p2.x, p.x, 0.0)
        assertEquals(p2.y, p.y, 0.0)
    }

    @Test
    fun testReadPolygon() {
        val pg = Polygon(outer = LineString(listOf(
                Point(0.0, 0.0),
                Point(0.0, 10.1),
                Point(10.0, 10.0),
                Point(10.0, 0.0),
                Point(0.0, 0.0))), holes = emptyList())
        val pg2: Polygon = WKTReader.read(WKTWriter.write(pg)) as Polygon
        assertTrue(!pg2.isEmpty())
        assertEquals(pg.numHoles(), pg2.numHoles())
        assertEquals(pg.outer.size(), pg2.outer.size())
        assertEquals(pg.outer.getX(pg.outer.size() - 1), pg2.outer.getX(pg2.outer.size() - 1), 0.0)
    }
}