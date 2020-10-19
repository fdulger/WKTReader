package me.fdulger.wkt.geometry.wkt

import me.fdulger.wkt.WKTReader
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import me.fdulger.wkt.geometry.Point
import org.junit.Before
import org.junit.Test
import me.fdulger.wkt.geometry.LineString
import me.fdulger.wkt.geometry.Polygon

class TestWKTReader {
    var writer: WKTWriter = WKTWriter()
    var reader: WKTReader = WKTReader()

    @Test
    fun testReadPoint() {
        val p = Point(30.5, 40.0)
        val p2: Point = reader.read(writer.write(p)) as Point
        assertTrue(!p2.isEmpty())
        assertEquals(p2.x, p.x, 0.0)
        assertEquals(p2.y, p.y, 0.0)
    }

    @Test
    fun testReadPolygon() {
        val pg = Polygon(LineString(doubleArrayOf(0.0, 0.0, 0.0, 10.1, 10.0, 10.0, 10.0, 0.0, 0.0, 0.0)), emptyList())
        println(writer.write(pg))
        val pg2: Polygon = reader.read(writer.write(pg)) as Polygon
        assertTrue(!pg2.isEmpty())
        assertEquals(pg.numHoles, pg2.numHoles)
        assertEquals(pg.outer!!.numCoords, pg2.outer!!.numCoords)
        assertEquals(pg.outer!!.getX(pg.outer!!.numCoords - 1), pg2.outer!!.getX(pg2.outer!!.numCoords - 1), 0.0)
    }
}