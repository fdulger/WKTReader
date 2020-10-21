package me.fdulger.wkt.geometry

class LineString (var points: List<Point> = emptyList()) : Geometry {

    override fun isEmpty(): Boolean = points.isEmpty()

    fun isClosed(): Boolean = isEmpty() || (points.size > 1 && points.first() == points.last())

    fun size(): Int = points.size

    fun getX(i: Int): Double = points[i].x

    fun getY(i: Int): Double = points[i].y

    override fun toString(): String = points.joinToString(",", "LINESTRING (", ")") { "${it.x} ${it.y}" }

}


