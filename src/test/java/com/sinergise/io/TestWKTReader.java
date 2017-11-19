package com.sinergise.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.sinergise.geometry.LineString;
import com.sinergise.geometry.Point;
import com.sinergise.geometry.Polygon;

public class TestWKTReader {
	
	WKTWriter writer;
	WKTReader reader;
    
	@Before
    public void initialize() {
		writer = new WKTWriter();
		reader = new WKTReader();
    }
	
	@Test
    public void testReadPoint() {
		
		Point p = new Point(30.5, 40.0);
		Point p2 = (Point) reader.read(writer.write(p));
		
		assertTrue(! p2.isEmpty());
		assertEquals(p2.getX(), p.getX(), 0);
		assertEquals(p2.getY(), p.getY(), 0);
	}
	
	@Test
	public void testReadPolygon() {
		Polygon pg = new Polygon(new LineString(new double[] {0,0,0,10.1,10,10,10,0,0,0}), new LineString[] {});
		Polygon pg2 = (Polygon) reader.read(writer.write(pg));
		
		assertTrue(! pg2.isEmpty());
		assertEquals(pg.getNumHoles(), pg2.getNumHoles());
		assertEquals(pg.getOuter().getNumCoords(), pg2.getOuter().getNumCoords());
		assertEquals(pg.getOuter().getX(pg.getOuter().getNumCoords() - 1), pg2.getOuter().getX(pg2.getOuter().getNumCoords() - 1), 0);
	}
 
}
