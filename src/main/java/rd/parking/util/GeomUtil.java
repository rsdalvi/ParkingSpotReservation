package rd.parking.util;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.PrecisionModel;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

public class GeomUtil {
	
	public static Point latlonToPoint(double lat, double lon) throws ParseException{
		GeometryFactory geomFactory = new GeometryFactory(new PrecisionModel(), 4326);
		WKTReader wktr = new WKTReader(geomFactory);
		String wkt = String.format("Point(%s %s)", lon, lat);
		Geometry geom = wktr.read(wkt);
		return (Point) geom;
	}
}
