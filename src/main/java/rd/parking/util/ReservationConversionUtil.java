package rd.parking.util;

import com.vividsolutions.jts.io.ParseException;

import rd.parking.dto.ReservationDTO;
import rd.parking.entity.Reservation;

public class ReservationConversionUtil {

	public static Reservation convertReservationDTO(ReservationDTO reservationDTO) throws ParseException {
		Reservation reservation = new Reservation();
		reservation.setId(reservationDTO.getId());
		reservation.setParkingSpot(ParkingSpotConversionUtil.convertParkingSpotDTO(reservationDTO.getParkingSpotDTO()));
		reservation.setUser(UserConversionUtil.convertUserDTO(reservationDTO.getUserDTO()));
		reservation.setReservationStartTime(reservationDTO.getReservationStartTime());
		reservation.setReservationEndTime(reservationDTO.getReservationEndTime());
		return reservation;
	}

	
	public static ReservationDTO convertReservation(Reservation reservation) {
		ReservationDTO reservationDTO = new ReservationDTO();
		reservationDTO.setId(reservation.getId());
		reservationDTO.setParkingSpotDTO(ParkingSpotConversionUtil.convertParkingSpot(reservation.getParkingSpot()));
		reservationDTO.setUserDTO(UserConversionUtil.convertUser(reservation.getUser()));
		reservationDTO.setReservationStartTime(reservation.getReservationStartTime());
		reservationDTO.setReservationEndTime(reservation.getReservationEndTime());
		return reservationDTO;
	}
}
