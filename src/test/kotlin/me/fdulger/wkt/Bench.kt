package me.fdulger.wkt

import org.junit.jupiter.api.Test

class Bench {
    @Test
    fun readBigPolygon() {
        val multipolygonStr = javaClass.getResource("multipolygon.wkt").readText()
        val start = System.currentTimeMillis()
        WKTReader.read(multipolygonStr)
        val duration = System.currentTimeMillis() - start
        println("Took $duration")
    }
}
