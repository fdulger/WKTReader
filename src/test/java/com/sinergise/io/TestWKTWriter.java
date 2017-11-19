package com.sinergise.io;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.GeometryCollection;
import com.sinergise.geometry.LineString;
import com.sinergise.geometry.MultiLineString;
import com.sinergise.geometry.MultiPoint;
import com.sinergise.geometry.MultiPolygon;
import com.sinergise.geometry.Point;
import com.sinergise.geometry.Polygon;

public class TestWKTWriter {
	
	WKTWriter writer;
	
	@Before
    public void initialize() {
		writer = new WKTWriter();
    }
	
	@Test
    public void testWritePoint() {
		Point p = new Point(30.5, 40.0);
		assertEquals("POINT (30.5 40)", writer.write(p));
	}
	
	@Test
    public void testWriteEmptyPoint() {
		Point p = new Point();
		assertEquals("POINT EMPTY", writer.write(p));
	}
	
	@Test
    public void testWriteLineString() {
		LineString ls = new LineString( new double[] {30,40,60.1,60} );
		assertEquals("LINESTRING (30 40, 60.1 60)", writer.write(ls));
	}
	
	@Test
    public void testWriteEmptyLineString() {
		LineString ls0 = new LineString();
		LineString ls1 = new LineString( new double[] {} );
		
		assertEquals("LINESTRING EMPTY", writer.write(ls0));
		assertEquals("LINESTRING EMPTY", writer.write(ls1));
	}
	
	@Test
    public void testWriteEmptyPolygon() {
		Polygon pg = new Polygon();
		assertEquals("POLYGON EMPTY", writer.write(pg));
	}
	
	@Test
    public void testWritePolygon() {
		Polygon pg = new Polygon(new LineString(new double[] {0,0,0,10.1,10,10,10,0,0,0}), new LineString[] {});
		assertEquals("POLYGON ((0 0, 0 10.1, 10 10, 10 0, 0 0))", writer.write(pg));
	}
	
	@Test
    public void testWriteEmptyGeometryCollection() {
		GeometryCollection<Geometry> gc = new GeometryCollection<Geometry>(new Geometry[] {});
		assertEquals("GEOMETRYCOLLECTION EMPTY", writer.write(gc));
	}
	
	@Test
    public void testWriteGeometryCollection() {
		Point p = new Point(30.5, 40.0);
		GeometryCollection<Geometry> gc = new GeometryCollection<Geometry>(new Geometry[] {p,p});
		assertEquals("GEOMETRYCOLLECTION (POINT (30.5 40), POINT (30.5 40))", writer.write(gc));
	}
	
	@Test
    public void testWriteGeometryCollectionWithEmptyMembers() {
		Point p = new Point(30.5, 40.0);
		Point p2 = new Point();
		LineString ls = new LineString( new double[] {30,40,60.1,60} );
		LineString ls2 = new LineString( new double[] {} );
		GeometryCollection<Geometry> gc = new GeometryCollection<Geometry>(new Geometry[] {p,p2,ls,ls2});
		assertEquals("GEOMETRYCOLLECTION (POINT (30.5 40), POINT EMPTY, LINESTRING (30 40, 60.1 60), LINESTRING EMPTY)", writer.write(gc));
	}
	
	@Test
    public void testWriteMultiPoint() {
		Point p = new Point(30.5, 40.0);	
		MultiPoint mp = new MultiPoint(new Point[] {p,p});
		assertEquals("MULTIPOINT ((30.5 40), (30.5 40))", writer.write(mp));
	}
	
	@Test
    public void testWriteMultiLineString() {
		LineString ls = new LineString( new double[] {30,40,60.1,60} );
		MultiLineString mls = new MultiLineString(new LineString[] {ls,ls});
		assertEquals("MULTILINESTRING ((30 40, 60.1 60), (30 40, 60.1 60))", writer.write(mls));
	}
	
	@Test
    public void testWriteMultiPolygon() {
		Polygon pg = new Polygon(new LineString(new double[] {0,0,0,10.1,10,10,10,0,0,0}), new LineString[] {});
		MultiPolygon mpg = new MultiPolygon(new Polygon[] {pg,pg});
		assertEquals("MULTIPOLYGON (((0 0, 0 10.1, 10 10, 10 0, 0 0)), ((0 0, 0 10.1, 10 10, 10 0, 0 0)))", writer.write(mpg));
	}

}
