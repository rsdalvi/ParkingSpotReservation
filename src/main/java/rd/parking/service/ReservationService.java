package rd.parking.service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vividsolutions.jts.io.ParseException;

import rd.parking.Exception.InvalidDataException;
import rd.parking.dto.ReservationDTO;
import rd.parking.entity.Reservation;
import rd.parking.repository.ReservationRepository;
import rd.parking.util.ReservationConversionUtil;

/**
 * An Service for Reservation business logic.
 * @author User1
 *
 */
@Service
public class ReservationService {

	// 10Rs per hour
	public static final float MINUTE_RATE = 10f/60f;

	@Autowired
	private ReservationRepository reservationRepo;
	
	/**
	 * Get all reservations in the system
	 * @return
	 */
	public List<ReservationDTO> getAllReservations(){
		return convertList(reservationRepo.findAll());
	}
	
	/**
	 * Convert list of Reservation to ReservationDTO.
	 * @param entities
	 * @return
	 */
	private List<ReservationDTO> convertList(Iterable<Reservation> entities) {
		List<ReservationDTO> reservations = new ArrayList<ReservationDTO>();
		entities.forEach(reservation->{reservations.add(ReservationConversionUtil.convertReservation(reservation));});
		return reservations;
	}
	
	/**
	 * Get Reservation by Id.
	 * @param id
	 * @return
	 */
	public ReservationDTO getReservation(long id) {
		Optional<Reservation> optional = reservationRepo.findById(id);
		if(optional.isPresent()) {
			return ReservationConversionUtil.convertReservation(optional.get());
		}
		return null;
	}
	
	/**
	 * Create Reservation.
	 * 
	 * @param reservationDTO
	 * @throws ParseException
	 */
	public void createReservation(ReservationDTO reservationDTO) throws ParseException {
		if(isParkingSpotReserved(reservationDTO)) {
			throw new InvalidDataException("Parking Spot is already reserved.");
		}
		reservationRepo.save(ReservationConversionUtil.convertReservationDTO(reservationDTO));
	}
	
	/**
	 * Returns true if given ParkingSpot is already reserved.
	 * @param reservationDTO
	 * @return
	 */
	private boolean isParkingSpotReserved(ReservationDTO reservationDTO) {
		Iterable<Reservation> reservationsByParkingSpot = reservationRepo.findByParkingSpotId(reservationDTO.getParkingSpotDTO().getId());
		return reservationsByParkingSpot.iterator().hasNext();
	}

	/**
	 * Update Reservation.
	 * @param reservationDTO
	 * @throws ParseException
	 */
	public void updateReservation(ReservationDTO reservationDTO) throws ParseException {
		reservationRepo.save(ReservationConversionUtil.convertReservationDTO(reservationDTO));
	}
	
	/**
	 * Delete Reservation
	 * @param id
	 */
	public void deleteReservation(long id) {		
		reservationRepo.deleteById(id);
	}

	/**
	 * Get Reservation by UserId
	 * @param userId
	 * @return
	 */
	public List<ReservationDTO> getReservationsByUser(long userId) {
		return convertList(reservationRepo.findByUserId(userId));
	}

	/**
	 * Get the cost of reservation.
	 * @param id
	 * @return
	 */
	public double getReservationCost(long id) {
		double cost = 0d;
		Optional<Reservation> optional = reservationRepo.findById(id);
		if(optional.isPresent()) {
			cost = getReservationCost(optional.get());
		}
		return cost;
	}

	/**
	 * Calculate reservation cost by minute rate.
	 * @param reservation
	 * @return
	 */
	private double getReservationCost(Reservation reservation) {
		Duration duration = Duration.between(reservation.getReservationStartTime(), reservation.getReservationEndTime());
		return duration.toMinutes() * MINUTE_RATE;
	}

}
