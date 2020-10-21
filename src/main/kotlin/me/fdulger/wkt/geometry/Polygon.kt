package me.fdulger.wkt.geometry

class Polygon constructor(
        val outer: LineString = LineString(),
        private val holes: List<LineString> = emptyList()): Geometry {

    override fun isEmpty(): Boolean = outer.isEmpty()

    fun numHoles(): Int = holes.size

    fun getHole(index: Int): LineString? = holes[index]
    override fun toString(): String = if (outer.isEmpty()) "Poly ()" else "Poly($outer, $holes)"

    init {
        if (outer.isEmpty()) {
            require(holes.isEmpty()) { "Empty polygon cannot have holes" }
        } else require(outer.isClosed()) { "Outer ring must be closed" }
        for (hole in holes) {
            require(hole.isClosed()) { "Hole ring not closed" }
        }
    }
}

