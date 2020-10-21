package me.fdulger.wkt.geometry.wkt

import me.fdulger.wkt.WKTReader
import me.fdulger.wkt.WKTWriter
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import me.fdulger.wkt.geometry.Point
import org.junit.Test
import me.fdulger.wkt.geometry.LineString
import me.fdulger.wkt.geometry.Polygon

class TestWKTReader {

    @Test
    fun testReadPoint() {
        val writer = WKTWriter()
        val p = Point(30.5, 40.0)
        val p2: Point = WKTReader.read(writer.write(p))!! as Point
        assertTrue(!p2.isEmpty())
        assertEquals(p2.x, p.x, 0.0)
        assertEquals(p2.y, p.y, 0.0)
    }

    @Test
    fun testReadPolygon() {
        val writer = WKTWriter()
        val pg = Polygon(LineString(listOf(Point(0.0, 0.0), Point(0.0, 10.1), Point(10.0, 10.0), Point(10.0, 0.0), Point(0.0, 0.0))), emptyList())
        val pg2: Polygon = WKTReader.read(writer.write(pg)) as Polygon
        assertTrue(!pg2.isEmpty())
        assertEquals(pg.numHoles(), pg2.numHoles())
        assertEquals(pg.outer.size(), pg2.outer.size())
        assertEquals(pg.outer.getX(pg.outer.size() - 1), pg2.outer.getX(pg2.outer.size() - 1), 0.0)
    }
}