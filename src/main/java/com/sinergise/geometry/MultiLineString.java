package com.sinergise.geometry;


public final class MultiLineString extends GeometryCollection<LineString> {

	public MultiLineString() {
		super();
	}

	public MultiLineString(LineString[] lines) {
		super(lines);
	}
}
