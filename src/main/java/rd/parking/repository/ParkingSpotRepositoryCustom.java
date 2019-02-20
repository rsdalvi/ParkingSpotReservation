package rd.parking.repository;

import java.util.List;

import com.vividsolutions.jts.io.ParseException;

import rd.parking.entity.ParkingSpot;

/**
 * An interface for Custom methods in ParkingSpotRepository
 * @author User1
 *
 */
public interface ParkingSpotRepositoryCustom {

	public List<ParkingSpot> findNearbyParkingSpot(double lat, double lon, double rad) throws ParseException;
	
}
