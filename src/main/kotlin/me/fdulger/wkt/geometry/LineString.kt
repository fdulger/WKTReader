package me.fdulger.wkt.geometry

import java.util.*

class LineString : Geometry {
    protected var coords: DoubleArray?

    /**
     * Creates an empty linestring
     */
    constructor() {
        coords = DoubleArray(0)
    }

    /**
     * @param coords interlaced array of ordinates: {x0, y0, x1, y1, .. ,xn, yn}
     */
    constructor(coords: DoubleArray?) {
        this.coords = coords
    }

    override fun isEmpty(): Boolean {
        return coords!!.size == 0
    }

    val isClosed: Boolean
        get() {
            if (isEmpty()) return true
            val len = coords!!.size
            return if (len < 4) false else coords!![0] == coords!![len - 2] && coords!![1] == coords!![len - 1]
        }
    val numCoords: Int
        get() = if (coords == null) 0 else coords!!.size ushr 1

    fun getX(i: Int): Double {
        return coords!![i shl 1]
    }

    fun getY(i: Int): Double {
        return coords!![(i shl 1) + 1]
    }

    override fun toString(): String {
        return javaClass.simpleName + "(" + Arrays.toString(coords) + ")"
    }

    override fun hashCode(): Int {
        val prime = 31
        var result = super.hashCode()
        result = prime * result + Arrays.hashCode(coords)
        return result
    }

    override fun equals(obj: Any?): Boolean {
        if (obj == null || obj !is LineString) {
            return false
        }
        val cmp = obj
        if (cmp.coords!!.size != coords!!.size) {
            return false
        }
        for (i in coords!!.indices) {
            if (cmp.coords!![i] != coords!![i]) {
                return false
            }
        }
        return true
    }
}