package edu.gimt.domain;

import java.util.ArrayList;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.impl.CoordinateArraySequence;

public class JTStest {

  public static void main(final String[] args) {

    final GeometryFactory gf = new GeometryFactory();
    final ArrayList<Coordinate> limits = new ArrayList<Coordinate>();
    limits.add(new Coordinate(-3.444589, 43.233229));
    limits.add(new Coordinate(-2.946085, 43.432011));
    limits.add(new Coordinate(-2.753824, 43.453947));
    limits.add(new Coordinate(-2.428354, 43.320214));
    limits.add(new Coordinate(-2.513498, 43.099009));
    limits.add(new Coordinate(-2.663187, 43.077948));
    limits.add(new Coordinate(-2.898019, 43.054873));
    limits.add(new Coordinate(-2.969430, 43.101015));
    limits.add(new Coordinate(-2.935029, 43.140636));
    limits.add(new Coordinate(-2.980669, 43.203665));
    limits.add(new Coordinate(-3.022425, 43.195879));
    limits.add(new Coordinate(-3.032136, 43.164722));
    limits.add(new Coordinate(-3.110879, 43.148124));
    limits.add(new Coordinate(-3.249582, 43.200202));
    limits.add(new Coordinate(-3.417123, 43.140108));
    limits.add(new Coordinate(-3.444589, 43.233229));
    
    final Polygon polygon = gf.createPolygon(new LinearRing(new CoordinateArraySequence(limits
        .toArray(new Coordinate[limits.size()])), gf), null);

    // Point OK to verify
    Coordinate coord4pointIn = new Coordinate(-2.833475, 43.209211);
    Point pointIn = gf.createPoint(coord4pointIn);
    System.out.println("OK Coordenadas " + pointIn.toText() + " dentro de la región? : " + pointIn.within(polygon));

    // Point KO to verify
    Coordinate coord4pointOut = new Coordinate(-2.350076, 43.170162);
    Point pointOut = gf.createPoint(coord4pointOut);
    System.out.println("KO Coordenadas " + pointOut.toText() + " dentro de la región? : " + pointOut.within(polygon));

    // Point OK to verify
    coord4pointIn = new Coordinate(-2.762064, 43.440986);
    pointIn = gf.createPoint(coord4pointIn);
    System.out.println("OK Coordenadas " + pointIn.toText() + " dentro de la región? : " + pointIn.within(polygon));

    // Point KO to verify
    coord4pointOut = new Coordinate(-2.990030, 43.169161);
    pointOut = gf.createPoint(coord4pointOut);
    System.out.println("KO Coordenadas " + pointOut.toText() + " dentro de la región? : " + pointOut.within(polygon));
  }

}
