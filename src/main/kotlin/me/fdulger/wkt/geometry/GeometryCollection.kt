package me.fdulger.wkt.geometry

open class GeometryCollection(private var elements: List<Geometry> = mutableListOf()) : Geometry {

    fun size(): Int = elements.size

    operator fun get(index: Int): Geometry = elements[index]

    override fun isEmpty(): Boolean = elements.isEmpty()

    override fun equals(other: Any?): Boolean {
        return this === other || (other is GeometryCollection && elements == other.elements)
    }

    override fun hashCode(): Int = elements.hashCode()
}

data class MultiLineString(val lines: List<LineString> = emptyList()) : GeometryCollection(lines)

data class MultiPoint(val points: List<Point>) : GeometryCollection(points)

data class MultiPolygon(val polys: List<Polygon> = emptyList()) : GeometryCollection(polys)
