package me.fdulger.wkt.geometry

class MultiPolygon : GeometryCollection {
    constructor() : super() {}
    constructor(polys: List<Polygon>) : super(polys) {}
}