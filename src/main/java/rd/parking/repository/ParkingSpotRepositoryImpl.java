package rd.parking.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;

import rd.parking.entity.ParkingSpot;
import rd.parking.util.GeomUtil;

/**
 * Implementation class for ParkingSpotRepository custom methods
 * @author User1
 *
 */
public class ParkingSpotRepositoryImpl implements ParkingSpotRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;
    
    private static final double DEGREE_IN_METERS = 111195d;
    
	@Override
	public List<ParkingSpot> findNearbyParkingSpot(double lat, double lon, double rad) throws ParseException {
		// convert radius in meter to degrees approximately by dividing it by 111195 
		// https://stackoverflow.com/questions/12204834/get-distance-in-meters-instead-of-degrees-in-spatialite
		
		double rad_degree = rad / DEGREE_IN_METERS;
		
        Point location = GeomUtil.latlonToPoint(lat, lon);
        Geometry filter = location.buffer(rad_degree);
		
	    Query query = entityManager.createQuery("select ps from ParkingSpot ps where within(ps.location, :filter) = true", ParkingSpot.class);
	    query.setParameter("filter", filter);
	    return query.getResultList();        
	}

}
