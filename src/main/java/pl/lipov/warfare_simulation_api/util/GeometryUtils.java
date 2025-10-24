package pl.lipov.warfare_simulation_api.util;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.MultiLineString;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.locationtech.jts.io.WKTWriter;

public class GeometryUtils {

    private GeometryUtils() {
    }

    public static MultiLineString parseWkt(String wkt) {
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

    public static String toWkt(MultiLineString multiLineString) {
        return new WKTWriter().write(multiLineString);
    }
}
