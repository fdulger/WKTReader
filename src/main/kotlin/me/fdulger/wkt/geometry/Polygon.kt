package me.fdulger.wkt.geometry

data class Polygon constructor(
    val outer: LineString = LineString(),
    val holes: List<LineString> = emptyList()
) : Geometry {

    override fun isEmpty(): Boolean = outer.isEmpty()

    fun numHoles(): Int = holes.size

    init {
        if (outer.isEmpty()) {
            require(holes.isEmpty()) { "Empty polygon cannot have holes" }
        } else require(outer.isClosed()) { "Outer ring must be closed" }
        for (hole in holes) {
            require(hole.isClosed()) { "Hole ring not closed" }
        }
    }
}
