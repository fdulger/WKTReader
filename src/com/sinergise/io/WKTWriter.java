package com.sinergise.io;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.GeometryCollection;
import com.sinergise.geometry.LineString;
import com.sinergise.geometry.MultiLineString;
import com.sinergise.geometry.MultiPoint;
import com.sinergise.geometry.MultiPolygon;
import com.sinergise.geometry.Point;
import com.sinergise.geometry.Polygon;

public class WKTWriter {
	
	DecimalFormat df;
	
	WKTWriter() {
		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
		otherSymbols.setDecimalSeparator('.');
		df = new DecimalFormat("#.#", otherSymbols);
	}
	
	public String writePoint(Point p, boolean printName) {
		StringBuilder result = new StringBuilder(printName ? "POINT " : "");
		if(p.isEmpty()) {
			result.append("EMPTY");
			return result.toString();
		}
		result.append("(")
		.append(df.format(p.getX()))
		.append(" ")
		.append(df.format(p.getY()))
		.append(")");
		
		return result.toString(); 
	}
	
	public String writePoint(Point p) {
		return writePoint(p, true);
	}
	
	public String writeLineString(LineString ls, boolean printName) {
		StringBuilder result = new StringBuilder(printName ? "LINESTRING " : "");
		if(ls.isEmpty()) {
			result.append("EMPTY");
			return result.toString();
		}
		result.append("(");
		for(int i=0;i<ls.getNumCoords();i++) {
			result.append(df.format(ls.getX(i)))
			.append(" ")
			.append(df.format(ls.getY(i)))
			.append(", ");
		}
		result.replace(result.length()-2, result.length(), "");
		result.append(")");
		return result.toString(); 
	}
	
	public String writeLineString(LineString ls) {
		return writeLineString(ls, true);
	}
	
	public String writePolygon(Polygon p, boolean printName) {
		StringBuilder result = new StringBuilder(printName ? "POLYGON " : "");
		if(p.isEmpty()) {
			result.append("EMPTY");
			return result.toString();
		}
		result.append("(");
		result.append(writeLineString(p.getOuter(),false))
		  .append(", ");
		for(int i=0;i<p.getNumHoles();i++) {
			result.append(writeLineString(p.getHole(i),false))
				  .append(", ");
		}
		result.replace(result.length()-2, result.length(), "");
		result.append(")");
		return result.toString(); 
	}
	
	public String writePolygon(Polygon p) {
		return writePolygon(p,true);
	}
	
	public String writeGeometryCollection(GeometryCollection gc) {
		StringBuilder result = new StringBuilder("GEOMETRYCOLLECTION ");
		if(gc.isEmpty()) {
			result.append("EMPTY");
			return result.toString();
		}
		result.append("(");
		for(int i=0;i<gc.size();i++) {
			result.append(write(gc.get(i)))
					.append(", ");
		}
		result.replace(result.length()-2, result.length(), "");
		result.append(")");
		return result.toString(); 
	}
	
	public String writeMultiPoint(MultiPoint mp) {
		StringBuilder result = new StringBuilder("MULTIPOINT ");
		if(mp.isEmpty()) {
			result.append("EMPTY");
			return result.toString();
		}
		result.append("(");
		for(int i=0;i<mp.size();i++) {
			result.append(writePoint(mp.get(i),false))
			.append(", ");
		}
		result.replace(result.length()-2, result.length(), "");
		result.append(")");
		return result.toString(); 
	}
	
	public String writeMultiLineString(MultiLineString mls) {
		StringBuilder result = new StringBuilder("MULTILINESTRING ");
		if(mls.isEmpty()) {
			result.append("EMPTY");
			return result.toString();
		}
		result.append("(");
		for(int i=0;i<mls.size();i++) {
			result.append(writeLineString(mls.get(i),false))
			.append(", ");
		}
		result.replace(result.length()-2, result.length(), "");
		result.append(")");
		return result.toString(); 
	}
	
	public String writeMultiPolygon(MultiPolygon mls) {
		StringBuilder result = new StringBuilder("MULTIPOLYGON ");
		if(mls.isEmpty()) {
			result.append("EMPTY");
			return result.toString();
		}
		result.append("(");
		for(int i=0;i<mls.size();i++) {
			result.append(writePolygon(mls.get(i),false))
			.append(", ");
		}
		result.replace(result.length()-2, result.length(), "");
		result.append(")");
		return result.toString(); 
	}
	
	/**
	 * Transforms the input Geometry object into WKT-formatted String. e.g.
	 * <pre><code>
	 * new WKTWriter().write(new LineString(new double[]{30, 10, 10, 30, 40, 40}));
	 * //returns "LINESTRING (30 10, 10 30, 40 40)"
	 * </code></pre>
	 */
	public String write(Geometry geom) {
		if(geom instanceof Point) {
			return writePoint((Point)geom);
		}
		if(geom instanceof LineString) {
			return writeLineString((LineString) geom);
		}
		if(geom instanceof Polygon) {
			return writePolygon((Polygon) geom);
		}
		if(geom instanceof MultiPoint) {
			return writeMultiPoint((MultiPoint) geom);
		}
		if(geom instanceof MultiLineString) {
			return writeMultiLineString((MultiLineString) geom);
		}
		if(geom instanceof MultiPolygon) {
			return writeMultiPolygon((MultiPolygon) geom);
		}
		if(geom instanceof GeometryCollection) {
			return writeGeometryCollection((GeometryCollection)geom);
		}
		return "Unknown geometry type.";
	}
}
