package me.fdulger.wkt.geometry

data class Point(val x: Double = Double.NaN, val y: Double = Double.NaN) : Geometry {

    override fun isEmpty(): Boolean = x.isNaN() && y.isNaN()

    override fun equals(other: Any?) = other is Point && other.x == x && other.y == y

    override fun hashCode(): Int = x.hashCode() + y.hashCode()
}
