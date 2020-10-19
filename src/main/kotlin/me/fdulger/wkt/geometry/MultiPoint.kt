package me.fdulger.wkt.geometry

class MultiPoint : GeometryCollection {
    constructor() : super() {}
    constructor(points: List<Point>) : super(points) {}
}