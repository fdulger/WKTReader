package me.fdulger.wkt.geometry

open class GeometryCollection(private var elements: List<Geometry> = mutableListOf()) : Geometry, Iterable<Geometry> {

    fun size(): Int = elements.size

    operator fun get(index: Int): Geometry = elements[index]

    override fun iterator(): Iterator<Geometry> = elements.iterator()

    override fun isEmpty(): Boolean = elements.isEmpty()

    override fun toString(): String {
        val sb = StringBuilder(javaClass.simpleName)
        for (g in this) {
            sb.append(g)
        }
        return sb.toString()
    }
}
