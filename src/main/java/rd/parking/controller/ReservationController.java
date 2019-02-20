package rd.parking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vividsolutions.jts.io.ParseException;

import rd.parking.dto.ReservationDTO;
import rd.parking.dto.UserDTO;
import rd.parking.service.ReservationService;

/**
 * Reservation Rest Controller
 * @author User1
 *
 */
@RestController
public class ReservationController {

	@Autowired
	private ReservationService reservationService;
	
	/**
	 * Get all Reservations for given User.
	 * @param userId
	 * @return
	 */
	@RequestMapping("/users/{userId}/reservations")
	public List<ReservationDTO> getAllReservations(@PathVariable long userId){
		return reservationService.getReservationsByUser(userId);
	}
	
	/**
	 * Get Reservation by Id.
	 * @param id
	 * @return
	 */
	@RequestMapping("/users/{userId}/reservations/{id}")
	public ReservationDTO getReservation(@PathVariable long id) {
		return reservationService.getReservation(id);
	}
	
	/**
	 * Create new Reservation for given User.
	 * @param reservationDTO
	 * @param userId
	 * @throws ParseException
	 */
	@RequestMapping(method=RequestMethod.POST, value="/users/{userId}/reservations")
	public void createReservation(@RequestBody ReservationDTO reservationDTO, @PathVariable long userId) throws ParseException {
		UserDTO userDTO = new UserDTO(userId, "");
		reservationDTO.setUserDTO(userDTO);
		reservationService.createReservation(reservationDTO);
	}

	/**
	 * Update given Reservation.
	 * 
	 * @param reservationDTO
	 * @throws ParseException
	 */
	@RequestMapping(method=RequestMethod.PUT, value="/users/{userId}/reservations/{id}")
	public void updateReservation(@RequestBody ReservationDTO reservationDTO) throws ParseException {
		reservationService.updateReservation(reservationDTO);
	}

	/**
	 * Delete Reservation by Id.
	 * @param id
	 */
	@RequestMapping(method=RequestMethod.DELETE, value="/users/{userId}/reservations/{id}")
	public void deleteReservation(@PathVariable long id) {
		reservationService.deleteReservation(id);
	}

	/**
	 * Get the cost of reservation by reservationId
	 * @param id
	 * @return
	 */
	@RequestMapping("/users/{userId}/reservations/{id}/cost")
	public double getReservationCost(@PathVariable long id) {
		return reservationService.getReservationCost(id);
	}
	
}
