package me.fdulger.wkt

import me.fdulger.wkt.geometry.GeometryCollection
import me.fdulger.wkt.geometry.LineString
import me.fdulger.wkt.geometry.MultiLineString
import me.fdulger.wkt.geometry.MultiPoint
import me.fdulger.wkt.geometry.MultiPolygon
import me.fdulger.wkt.geometry.Point
import me.fdulger.wkt.geometry.Polygon
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class TestWKTReader {

    @Test
    fun testReadPoint() {
        val pointStr = "POINT (30.5, 40.0)"
        val point = WKTReader.read(pointStr) as Point
        assertTrue(!point.isEmpty())
        assertEquals(point.x, 30.5, 0.0)
        assertEquals(point.y, 40.0, 0.0)
    }

    @Test
    fun testReadEmptyPoint() {
        val pointStr = "POINT ()"
        val point = WKTReader.read(pointStr) as Point
        assertTrue(point.isEmpty())
    }

    @Test
    fun testReadEmptyPoint2() {
        val pointStr = "POINT EMPTY"
        val point = WKTReader.read(pointStr) as Point
        assertTrue(point.isEmpty())
    }

    @Test
    fun testReadPolygon() {
        val polygonStr = "POLYGON ((35 10, 45 45, 15 40, 10 20, 35 10),(20 30, 35 35, 30 20, 20 30))"
        val polygon = WKTReader.read(polygonStr) as Polygon
        assertTrue(!polygon.isEmpty())
        assertEquals(1, polygon.numHoles())
        assertEquals(5, polygon.outer.size())
        assertEquals(true, polygon.outer.isClosed())
    }

    @Test
    fun testReadEmptyPolygon() {
        println(WKTWriter.write(Polygon()))
        val polygonStr = "POLYGON EMPTY"
        val polygon = WKTReader.read(polygonStr) as Polygon
        assertTrue(polygon.isEmpty())
        assertEquals(0, polygon.numHoles())
    }

    @Test
    fun testReadEmptyPolygon2() {
        val polygonStr = "POLYGON ((),())"
        val polygon = WKTReader.read(polygonStr) as Polygon
        assertTrue(polygon.isEmpty())
        assertEquals(0, polygon.numHoles())
    }

    @Test
    fun testReadEmptyPolygonCanNotHaveHoles() {
        val polygonStr = "POLYGON ((),(10.0 20.0, 15.0 20.0, 10.0 20.0))"
        val polygon = WKTReader.read(polygonStr) as Polygon
        assertTrue(polygon.isEmpty())
        assertEquals(0, polygon.numHoles())
    }

    @Test
    fun testHolesMustBeClosed() {
        val polygonStr = "POLYGON ((35 10, 45 45, 15 40, 10 20, 35 10),(20 30, 35 35, 30 20, 20 35))"
        assertThrows<IllegalArgumentException> { WKTReader.read(polygonStr) as Polygon }
    }

    @Test
    fun testOuterStringMustBeClosed() {
        val polygonStr = "POLYGON ((35 10, 45 45, 15 40, 10 20, 10 35),(20 30, 35 35, 30 20, 20 35))"
        assertThrows<IllegalArgumentException> { WKTReader.read(polygonStr) as Polygon }
    }

    @Test
    fun testReadLineString() {
        val lineStringStr = "LINESTRING (30 10, 10 30, 40 40)"
        val expected = LineString(listOf(Point(30.0, 10.0), Point(10.0, 30.0), Point(40.0, 40.0)))
        assertEquals(expected, WKTReader.read(lineStringStr))
    }

    @Test
    fun testReadEmptyLineString() {
        val lineStringStr = "LINESTRING ()"
        val expected = LineString()
        val got = WKTReader.read(lineStringStr) as LineString
        assertEquals(expected, got)
        assertTrue(got.isEmpty())
    }

    @Test
    fun readEmptyMultiPoint() {
        val multiPointStr = "MULTIPOINT EMPTY"
        val multiPoint = WKTReader.read(multiPointStr) as MultiPoint
        assertTrue(multiPoint.isEmpty())
    }

    @Test
    fun readEmptyMultiPoint2() {
        val multiPointStr = "MULTIPOINT ()"
        val multiPoint = WKTReader.read(multiPointStr) as MultiPoint
        assertTrue(multiPoint.isEmpty())
    }

    @Test
    fun readMultiPoint() {
        val multiPointStr = "MULTIPOINT ((10.1 20.1), (15 25), (45 40), (90 75))"
        val multiPoint = WKTReader.read(multiPointStr) as MultiPoint
        val expected = MultiPoint(listOf(
                Point(10.1, 20.1),
                Point(15.0, 25.0),
                Point(45.0, 40.0),
                Point(90.0, 75.0)))
        assertEquals(expected, multiPoint)
    }

    @Test
    fun readMultiPoint2() {
        val multiPointStr = "MULTIPOINT (10.1 20.1, 15 25, 15 40, 90 75)"
        val multiPoint = WKTReader.read(multiPointStr) as MultiPoint
        val expected = MultiPoint(listOf(
                Point(10.1, 20.1),
                Point(15.0, 25.0),
                Point(15.0, 40.0),
                Point(90.0, 75.0)))
        assertEquals(expected, multiPoint)
    }

    @Test
    fun testReadMultiPolygon() {
        val multiPolygonStr = "MULTIPOLYGON (((40 40, 20 45, 45 30, 40 40))," +
                "((20 35, 10 30, 10 10, 30 5, 45 20, 20 35),(30 20, 20 15, 20 25, 30 20)))"
        println(multiPolygonStr)
        val multiPolygon = WKTReader.read(multiPolygonStr) as MultiPolygon
        val expected = MultiPolygon(listOf(
            Polygon(LineString(listOf(
                Point(40.0, 40.0),
                Point(20.0, 45.0),
                Point(45.0, 30.0),
                Point(40.0, 40.0))), emptyList()),
            Polygon(LineString(listOf(
                Point(20.0, 35.0),
                Point(10.0, 30.0),
                Point(10.0, 10.0),
                Point(30.0, 5.0),
                Point(45.0, 20.0),
                Point(20.0, 35.0))), listOf(
                    LineString(listOf(
                        Point(30.0, 20.0),
                        Point(20.0, 15.0),
                        Point(20.0, 25.0),
                        Point(30.0, 20.0)))
                    )
                )
            )
        )
        assertEquals(expected, multiPolygon)
    }

    @Test
    fun testReadMultiLineString() {
        val multiLineStringStr = "MULTILINESTRING ((10 10, 20 20, 10 40)," +
                "(40 40, 30 30, 40 20, 30 10))"
        var multiLineString = WKTReader.read(multiLineStringStr) as MultiLineString
        var expected = MultiLineString(listOf(
                LineString(listOf(
                    Point(10.0, 10.0),
                    Point(20.0, 20.0),
                    Point(10.0, 40.0))),
                LineString(listOf(
                    Point(40.0, 40.0),
                    Point(30.0, 30.0),
                    Point(40.0, 20.0),
                    Point(30.0, 10.0)))
                ))
        assertEquals(expected, multiLineString)
    }

    @Test
    fun testReadGeometryCollection() {
        val geometryCollectionStr = "GEOMETRYCOLLECTION (POINT (40 10)," +
                "LINESTRING (10 10, 20 20, 10 40)," +
                "POLYGON ((40 40, 20 45, 45 30, 40 40)))"
        val geometryCollection = WKTReader.read(geometryCollectionStr) as GeometryCollection
        val expected = GeometryCollection(listOf(
                Point(40.0, 10.0),
                LineString(listOf(
                        Point(10.0, 10.0),
                        Point(20.0, 20.0),
                        Point(10.0, 40.0))
                ),
                Polygon(LineString(listOf(
                        Point(40.0, 40.0),
                        Point(20.0, 45.0),
                        Point(45.0, 30.0),
                        Point(40.0, 40.0))
                    )
                )
            )
        )
        assertEquals(expected, geometryCollection)
    }

    @Test
    fun testUnknownType() {
        val polygonStr = "XGON (35 10, 45 45, 15 40, 10 20, 10 35)"
        assertThrows<IllegalArgumentException> { WKTReader.read(polygonStr) }
    }

}
