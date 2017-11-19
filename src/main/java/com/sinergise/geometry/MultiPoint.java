package com.sinergise.geometry;


public final class MultiPoint extends GeometryCollection<Point> {

	public MultiPoint() {
		super();
	}

	public MultiPoint(Point[] points) {
		super(points);
	}
}
