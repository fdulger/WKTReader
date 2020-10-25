package me.fdulger.wkt.geometry

data class LineString(var points: List<Point> = emptyList()) : Geometry {

    override fun isEmpty(): Boolean = points.isEmpty()

    fun isClosed(): Boolean = isEmpty() || (points.size > 1 && points.first() == points.last())

    fun size(): Int = points.size
}
