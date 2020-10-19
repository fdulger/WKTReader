package me.fdulger.wkt.geometry

class Point : Geometry {
    var x = Double.NaN
        private set
    var y = Double.NaN
        private set

    /**
     * Creates an empty point
     */
    constructor() : super() {}
    constructor(x: Double, y: Double) {
        this.x = x
        this.y = y
    }

    override fun isEmpty(): Boolean {
        return java.lang.Double.isNaN(x) && java.lang.Double.isNaN(y)
    }

    override fun toString(): String {
        return "PT($x $y)"
    }

    override fun hashCode(): Int {
        var temp = java.lang.Double.doubleToLongBits(x)
        var result = 31 + (temp xor (temp ushr 32)).toInt()
        temp = java.lang.Double.doubleToLongBits(y)
        result = 31 * result + (temp xor (temp ushr 32)).toInt()
        return result
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj) {
            return true
        }
        if (obj == null) {
            return false
        }
        if (obj !is Point) {
            return false
        }
        val other = obj
        if (java.lang.Double.doubleToLongBits(x) != java.lang.Double.doubleToLongBits(other.x)) {
            return false
        }
        return if (java.lang.Double.doubleToLongBits(y) != java.lang.Double.doubleToLongBits(other.y)) {
            false
        } else true
    }
}