package me.fdulger.wkt.geometry

class MultiLineString : GeometryCollection {
    constructor() : super() {}
    constructor(lines: List<LineString>) : super(lines) {}
}