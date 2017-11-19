package com.sinergise.geometry;

import java.util.Arrays;


public class Polygon implements Geometry {

	private LineString outer;
	private LineString[] holes;

	/**
	 * Creates an empty polygon
	 */
	public Polygon() {
		this(null, null);
	}

	public Polygon(LineString outer, LineString[] holes) {
		this.outer = outer;
		this.holes = holes == null ? new LineString[0] : holes;
		validateRings();
	}

	@Override
	public boolean isEmpty() {
		return outer == null;
	}

	public LineString getOuter() {
		return outer;
	}

	public int getNumHoles() {
		return holes == null ? 0 : holes.length;
	}

	public LineString getHole(int index) {
		return holes[index];
	}

	private void validateRings() {
		if (outer == null) {
			if (holes.length > 0) {
				throw new IllegalArgumentException("Empty polygon cannot have holes");
			}
			
		} else if (!outer.isClosed()) {
			throw new IllegalArgumentException("Outer ring not closed");
		}
		for (LineString hole : holes) {
			if (!hole.isClosed()) {
				throw new IllegalArgumentException("Hole ring not closed");
			}
		}
	}

	@Override
	public String toString() {
		if (outer == null) {
			return "Poly ()";
		}
		return "Poly(" + outer + ", " + Arrays.toString(holes) + ")";
	}

	@Override
	public int hashCode() {
		int result = 31 + Arrays.hashCode(holes);
		result = 31 * result + ((outer == null) ? 0 : outer.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || !obj.getClass().equals(getClass())) {
			return false;
		}
		Polygon other = (Polygon)obj;
		if (!Arrays.equals(holes, other.holes)) {
			return false;
		}
		if (outer == null) {
			if (other.outer != null) {
				return false;
			}
		} else if (!outer.equals(other.outer)) {
			return false;
		}
		return true;
	}
}
