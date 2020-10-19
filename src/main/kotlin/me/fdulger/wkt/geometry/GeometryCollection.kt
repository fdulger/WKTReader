package me.fdulger.wkt.geometry

import java.util.*
import kotlin.collections.ArrayList

open class GeometryCollection : Geometry, Iterable<Geometry> {
    protected var elements: MutableList<Geometry>

    /**
     * Creates an empty collection
     */
    constructor() {
        elements = ArrayList()
    }

    constructor(elements: List<Geometry>?) {
        this.elements = Collections.unmodifiableList(elements)
    }

    fun size(): Int {
        return elements.size
    }

    operator fun get(index: Int): Geometry {
        return elements[index]
    }

    override fun iterator(): MutableIterator<Geometry> {
        return elements.iterator()
    }

    override fun isEmpty(): Boolean {
        return elements.isEmpty()
    }

    override fun toString(): String {
        val sb = StringBuilder(javaClass.simpleName)
        for (g in this) {
            sb.append(g)
        }
        return sb.toString()
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj) {
            return true
        }
        return if (obj == null || javaClass != obj.javaClass) {
            false
        } else elements == (obj as GeometryCollection).elements
    }

    override fun hashCode(): Int {
        return elements.hashCode()
    }
}