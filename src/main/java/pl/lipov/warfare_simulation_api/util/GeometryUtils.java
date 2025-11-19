package pl.lipov.warfare_simulation_api.util;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.MultiLineString;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.locationtech.jts.io.WKTWriter;

public class GeometryUtils {

    private static final int EXPECTED_SRID = 2180;

    private GeometryUtils() {
    }

    public static MultiLineString parseMultiLineWkt(String wkt) {
        try {
            Geometry geometry = new WKTReader().read(wkt);
            if (geometry instanceof MultiLineString) {
                return (MultiLineString) geometry;
            } else {
                throw new IllegalArgumentException("Provided WKT is not a MultiLineString");
            }
        } catch (ParseException exception) {
            throw new IllegalArgumentException("Invalid WKT format: " + exception.getMessage(), exception);
        }
    }

    public static Polygon parsePolygonWkt(String wkt) {
        try {
            Geometry geometry = new WKTReader().read(wkt);
            if (geometry instanceof Polygon polygon) {
                polygon.setSRID(EXPECTED_SRID);
                return polygon;
            } else {
                throw new IllegalArgumentException("Provided WKT is not a Polygon");
            }
        } catch (ParseException exception) {
            throw new IllegalArgumentException("Invalid WKT format: " + exception.getMessage(), exception);
        }
    }

    public static String toWkt(MultiLineString multiLineString) {
        return new WKTWriter().write(multiLineString);
    }
}
