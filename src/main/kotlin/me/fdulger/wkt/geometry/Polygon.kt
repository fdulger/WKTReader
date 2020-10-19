package me.fdulger.wkt.geometry

import java.util.*

class Polygon @JvmOverloads constructor(val outer: LineString? = null, private val holes: List<LineString?>? = null) : Geometry {
    override fun isEmpty(): Boolean {
        return outer == null
    }

    val numHoles: Int
        get() = holes?.size ?: 0

    fun getHole(index: Int): LineString? {
        return holes!![index]
    }

    private fun validateRings() {
        if (outer == null) {
            require(holes == null || holes!!.isEmpty()) { "Empty polygon cannot have holes" }
        } else require(outer.isClosed) { "Outer ring not closed" }
        if(holes != null) {
            for (hole in holes) {
                require(hole!!.isClosed) { "Hole ring not closed" }
            }
        }
    }

    override fun toString(): String {
        return if (outer == null) {
            "Poly ()"
        } else "Poly($outer, $holes)"
    }

    /**
     * Creates an empty polygon
     */
    init {
        validateRings()
    }
}