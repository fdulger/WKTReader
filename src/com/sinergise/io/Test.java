package com.sinergise.io;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.GeometryCollection;
import com.sinergise.geometry.LineString;
import com.sinergise.geometry.MultiLineString;
import com.sinergise.geometry.MultiPoint;
import com.sinergise.geometry.MultiPolygon;
import com.sinergise.geometry.Point;
import com.sinergise.geometry.Polygon;

public class Test {

	public static void main(String[] args) {
		WKTWriter writer = new WKTWriter();
		Point p = new Point(30.5,40);
		System.out.println(writer.write(p));
		
		Point p2 = new Point();
		System.out.println(writer.write(p2));
		
		LineString ls = new LineString( new double[] {30,40,60.1,60} );
		System.out.println(writer.write(ls));

		LineString ls2 = new LineString( new double[] {30,40,60.1,60,120.2,135} );
		System.out.println(writer.write(ls2));

		LineString ls3 = new LineString( new double[] {} );
		System.out.println(writer.write(ls3));
		
		Polygon pg0 = new Polygon();
		System.out.println(writer.write(pg0));
		
		Polygon pg = new Polygon(new LineString(new double[] {0,0,0,10.1,10,10,10,0,0,0}), new LineString[] {});
		System.out.println(writer.write(pg));
		
		GeometryCollection<Geometry> gc0 = new GeometryCollection<Geometry>(new Geometry[] {});
		System.out.println(writer.write(gc0));
		
		GeometryCollection<Geometry> gc2 = new GeometryCollection<Geometry>(new Geometry[] {p,p});
		System.out.println(writer.write(gc2));
		
		MultiPoint mp = new MultiPoint(new Point[] {p,p2});
		System.out.println(writer.write(mp));
		
		MultiPoint mp2 = new MultiPoint(new Point[] {p,p});
		System.out.println(writer.write(mp2));
		
		MultiLineString mls = new MultiLineString(new LineString[] {ls,ls2});
		System.out.println(writer.write(mls));
		
		MultiPolygon mpg = new MultiPolygon(new Polygon[] {pg,pg});
		System.out.println(writer.write(mpg));
		
		GeometryCollection<Geometry> gc = new GeometryCollection<Geometry>(new Geometry[] {pg0,p,ls,ls2,ls3,ls3,pg0,pg,mpg,mp2,mls});
		System.out.println(writer.write(gc));
		
		System.out.println("*********************************");
		
		WKTReader reader = new WKTReader();
		
		System.out.println(writer.write(reader.read(writer.write(p))));
		
		System.out.println(writer.write(reader.read(writer.write(ls))));
		
		System.out.println(writer.write(reader.read(writer.write(pg))));
		
		System.out.println(writer.write(reader.read(writer.write(mp2))));
		
		System.out.println(writer.write(reader.read(writer.write(mls))));
		
		System.out.println(writer.write(reader.read(writer.write(mpg))));
		
		System.out.println(writer.write(reader.read(writer.write(gc))));
		
		System.out.println(writer.write(reader.read("bla bla")));
	}

}
