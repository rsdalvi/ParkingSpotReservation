package rd.parking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vividsolutions.jts.io.ParseException;

import rd.parking.dto.ParkingSpotDTO;
import rd.parking.entity.ParkingSpot;
import rd.parking.repository.ParkingSpotRepository;
import rd.parking.util.ParkingSpotConversionUtil;

/***
 * Service class for ParkingSpot business logic.
 * @author User1
 *
 */
@Service
public class ParkingSpotService {

	@Autowired
	private ParkingSpotRepository parkingSpotRepo;
	
	/**
	 * Return a list of all parking spots in the system.
	 * @return
	 */
	public List<ParkingSpotDTO> getAllParkingSpots() {
		return convertList(parkingSpotRepo.findAll());
	}

	/**
	 * convert list of ParkingSpot to ParkingSpotDTO
	 * @param entities
	 * @return
	 */
	private List<ParkingSpotDTO> convertList(Iterable<ParkingSpot> entities) {
		List<ParkingSpotDTO> parkingSpots = new ArrayList<ParkingSpotDTO>();
		entities.forEach(parkingSpot->{parkingSpots.add(ParkingSpotConversionUtil.convertParkingSpot(parkingSpot));});
		return parkingSpots;
	}

	/**
	 * Find Parking Spot by its Id.
	 * @param id
	 * @return
	 */
	public ParkingSpotDTO getParkingSpot(long id) {
		Optional<ParkingSpot> optional = parkingSpotRepo.findById(id);
		if(optional.isPresent()) {
			return ParkingSpotConversionUtil.convertParkingSpot(optional.get());
		}
		return null;
	}

	/**
	 * Create new ParkingSpot.
	 * @param parkingSpotDTO
	 * @throws ParseException
	 */
	public void createParkingSpot(ParkingSpotDTO parkingSpotDTO) throws ParseException {
		parkingSpotRepo.save(ParkingSpotConversionUtil.convertParkingSpotDTO(parkingSpotDTO));
	}

	/**
	 * Update ParkingSpot
	 * @param parkingSpotDTO
	 * @throws ParseException
	 */
	public void updateParkingSpot(ParkingSpotDTO parkingSpotDTO) throws ParseException {
		parkingSpotRepo.save(ParkingSpotConversionUtil.convertParkingSpotDTO(parkingSpotDTO));
	}

	/**
	 * Delete Parking Spot by id.
	 * @param id
	 */
	public void deleteParkingSpot(long id) {
		parkingSpotRepo.deleteById(id);
	}

	/**
	 * Return a list of nearby parking spot from given position within the radius given in meters.
	 * @param lat
	 * @param lon
	 * @param rad
	 * @return
	 * @throws ParseException
	 */
	public List<ParkingSpotDTO> getNearbyParkingSpot(double lat, double lon, double rad) throws ParseException {
		return convertList(parkingSpotRepo.findNearbyParkingSpot(lat, lon, rad));
	}

}
