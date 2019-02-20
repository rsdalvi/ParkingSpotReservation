package rd.parking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vividsolutions.jts.io.ParseException;

import rd.parking.dto.ParkingSpotDTO;
import rd.parking.service.ParkingSpotService;

/**
 * Parking Spot Rest Controller.
 * @author User1
 *
 */
@RestController
public class ParkingSpotController {

	@Autowired
	private ParkingSpotService parkingSpotService;
	
	/**
	 * Get all Parking Spots.
	 * @return
	 */
	@RequestMapping("/parkingSpots")
	public List<ParkingSpotDTO> getAllParkingSpots(){
		return parkingSpotService.getAllParkingSpots();
	}
	
	/**
	 * Get Parking Spot by Id.
	 * @param id
	 * @return
	 */
	@RequestMapping("/parkingSpots/{id}")
	public ParkingSpotDTO getParkingSpot(@PathVariable long id) {
		return parkingSpotService.getParkingSpot(id);
	}

	/**
	 * Request handler for "/parkingSpots/?lat={lat}&lon={lon}&rad={rad}"
	 * 
	 * Get all Parking Spot near given latlon within given radius in meters.
	 * 
	 * @param lat
	 * @param lon
	 * @param rad
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/parkingSpots/near")
	public List<ParkingSpotDTO> getParkingSpot(@RequestParam("lat") double lat, @RequestParam("lon") double lon, @RequestParam("rad") double rad) throws ParseException {
		return parkingSpotService.getNearbyParkingSpot(lat, lon, rad);
	}
	
	/**
	 * Create given Parking Spot
	 * @param parkingSpot
	 * @throws ParseException
	 */
	@RequestMapping(method=RequestMethod.POST, value="/parkingSpots")
	public void createParkingSpot(@RequestBody ParkingSpotDTO parkingSpot) throws ParseException {
		parkingSpotService.createParkingSpot(parkingSpot);
	}

	/**
	 * Update given Parking Spot
	 * @param parkingSpot
	 * @throws ParseException
	 */
	@RequestMapping(method=RequestMethod.PUT, value="/parkingSpots/{id}")
	public void updateParkingSpot(@RequestBody ParkingSpotDTO parkingSpot) throws ParseException {
		parkingSpotService.updateParkingSpot(parkingSpot);
	}

	/**
	 * Delete Parking Spot by Id.
	 * @param id
	 */
	@RequestMapping(method=RequestMethod.DELETE, value="/parkingSpots/{id}")
	public void deleteParkingSpot(@PathVariable long id) {
		parkingSpotService.deleteParkingSpot(id);
	}
	
	
}
