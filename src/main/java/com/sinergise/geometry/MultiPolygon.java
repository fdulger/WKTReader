package com.sinergise.geometry;


public final class MultiPolygon extends GeometryCollection<Polygon> {

	public MultiPolygon() {
		super();
	}

	public MultiPolygon(Polygon[] polys) {
		super(polys);
	}
}
