package me.fdulger.wkt.geometry

class Point(val x: Double = Double.NaN, val y: Double = Double.NaN) : Geometry {

    override fun isEmpty(): Boolean = x.isNaN() && y.isNaN()

    override fun toString(): String = "PT($x $y)"

    override fun equals(other: Any?) = other is Point && other.x == x && other.y == y

    override fun hashCode(): Int = x.hashCode() + y.hashCode()

}

