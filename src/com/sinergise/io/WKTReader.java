package com.sinergise.io;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.GeometryCollection;
import com.sinergise.geometry.LineString;
import com.sinergise.geometry.MultiLineString;
import com.sinergise.geometry.MultiPoint;
import com.sinergise.geometry.MultiPolygon;
import com.sinergise.geometry.Point;
import com.sinergise.geometry.Polygon;

public class WKTReader {

	double[] readDoubleArray(StringTokenizer st) {
		ArrayList<Double> list = new ArrayList<Double>();
		String[] skip = new String[] {"(", ",", " "};

		String token = st.nextToken();
		while(!token.equals(")")) {
			if(token.equals("EMPTY")) {
				return null;
			}
			if(!Arrays.asList(skip).contains(token)) {
				list.add(Double.parseDouble(token));
			}
			token = st.nextToken();
		}
		double[] result = new double[list.size()];
		for(int i=0; i<list.size(); i++) {
			result[i] = list.get(i);
		}
		return result;
	}

	Point readPoint(StringTokenizer st) {
		double[] coords = readDoubleArray(st);
		return coords == null ? new Point() : new Point(coords[0], coords[1]);
	}
	
	LineString readLineString(StringTokenizer st) {
		double[] coords = readDoubleArray(st);
		return coords == null ? new LineString() : new LineString(coords);
	}
	
	Polygon readPolygon(StringTokenizer st) {
		LineString outer = readLineString(st);
		if(outer.isEmpty()) {
			return new Polygon();
		}
		ArrayList<LineString> holes = new ArrayList<LineString>();
		String token = st.nextToken();
		while(!token.equals(")")) {
			holes.add(readLineString(st));
			token = st.nextToken();
		}
		LineString[] hole_arr = new LineString[holes.size()];
		return new Polygon(outer, holes.toArray(hole_arr));
	}
	
	MultiPoint readMultiPoint(StringTokenizer st) {
		String token = st.nextToken();
		ArrayList<Point> points = new ArrayList<Point>();
		while(!token.equals(")")) {
			points.add(readPoint(st));
			token = st.nextToken();
		}
		Point[] points_arr = new Point[points.size()];
		return new MultiPoint(points.toArray(points_arr));
	}
	
	MultiLineString readMultiLineString(StringTokenizer st) {
		String token = st.nextToken();
		ArrayList<LineString> lineStrings = new ArrayList<LineString>();
		while(!token.equals(")")) {
			lineStrings.add(readLineString(st));
			token = st.nextToken();
		}
		LineString[] lineStrings_arr = new LineString[lineStrings.size()];
		return new MultiLineString(lineStrings.toArray(lineStrings_arr));
	}
	
	MultiPolygon readMultiPolygon(StringTokenizer st) {
		String token = st.nextToken();
		ArrayList<Polygon> polygons = new ArrayList<Polygon>();
		while(!token.equals(")")) {
			polygons.add(readPolygon(st));
			token = st.nextToken();
		}
		Polygon[] polygon_arr = new Polygon[polygons.size()];
		return new MultiPolygon(polygons.toArray(polygon_arr));
	}
	
	/**
	 * Transforms the input WKT-formatted String into Geometry object
	 */
	public Geometry read(String wktString) {
		StringTokenizer st = new StringTokenizer(wktString,"(), ",true);
		if(!st.hasMoreElements()) {
			return null;
		}
		String name = (String) st.nextElement();
		Geometry result = new Point();
		if(name.equals("POINT")) {
			result = readPoint(st);
		}
		else if(name.equals("LINESTRING")) {
			result = readLineString(st);
		}
		else if(name.equals("POLYGON")) {
			result = readPolygon(st);
		}
		else if(name.equals("MULTIPOINT")) {
			result = readMultiPoint(st);
		}
		else if(name.equals("MULTILINESTRING")) {
			result = readMultiLineString(st);
		}
		else if(name.equals("MULTIPOLYGON")) {
			result = readMultiPolygon(st);
		}
		else if(name.equals("GEOMETRYCOLLECTION")) {
			ArrayList<Geometry> geometries = new ArrayList<Geometry>();
			String token = st.nextToken();
			while(!token.equals(")")) {
				if(token.equals("POINT")) {
					geometries.add(readPoint(st));
				}
				else if(token.equals("LINESTRING")) {
					geometries.add(readLineString(st));
				}
				else if(token.equals("POLYGON")) {
					geometries.add(readPolygon(st));
				}
				else if(token.equals("MULTIPOINT")) {
					geometries.add(readMultiPoint(st));
				}
				else if(token.equals("MULTILINESTRING")) {
					geometries.add(readMultiLineString(st));
				}
				else if(token.equals("MULTIPOLYGON")) {
					geometries.add(readMultiPolygon(st));
				}
				token = st.nextToken();
			}
			Geometry[] geometry_arr = new Geometry[geometries.size()];
			result = new GeometryCollection<Geometry>(geometries.toArray(geometry_arr));
		}
		else {
			System.err.println("Unknown geometry type: " + name);
			return null;
		}
		return result;
	}

}
