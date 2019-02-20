package rd.parking.util;

import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;

import rd.parking.dto.ParkingSpotDTO;
import rd.parking.entity.ParkingSpot;

public class ParkingSpotConversionUtil {

	public static ParkingSpotDTO convertParkingSpot(ParkingSpot parkingSpot){
		ParkingSpotDTO parkingSpotDTO = new ParkingSpotDTO();
		parkingSpotDTO.setId(parkingSpot.getId());

		//parkingSpot.getLocation()
		//WKTWriter wktw = new WKTWriter();
		//String locationWKT = wktw.write(parkingSpot.getLocation());
		//parkingSpotDTO.setLocationWKT(locationWKT);
		
		double lon = parkingSpot.getLocation().getX();
		double lat = parkingSpot.getLocation().getY();
		
		parkingSpotDTO.setLat(lat);
		parkingSpotDTO.setLon(lon);
		
		return parkingSpotDTO;
	}
	
	public static ParkingSpot convertParkingSpotDTO(ParkingSpotDTO parkingSpotDTO) throws ParseException{
		ParkingSpot parkingSpot = new ParkingSpot();
		parkingSpot.setId(parkingSpotDTO.getId());
		Point point = GeomUtil.latlonToPoint(parkingSpotDTO.getLat(), parkingSpotDTO.getLon());
		parkingSpot.setLocation(point);
		
		return parkingSpot;
	}
	
}
