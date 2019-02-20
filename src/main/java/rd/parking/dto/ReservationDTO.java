package rd.parking.dto;

import java.time.LocalDateTime;

/**
 * Reservation Data Transfer Object.
 * @author User1
 *
 */
public class ReservationDTO {

	private long id;
	
	private UserDTO userDTO;
	
	private ParkingSpotDTO parkingSpotDTO;
	
	private LocalDateTime reservationStartTime;
	private LocalDateTime reservationEndTime;
	
	public ReservationDTO(long id, UserDTO userDTO, ParkingSpotDTO parkingSpotDTO, LocalDateTime reservationStartTime,
			LocalDateTime reservationEndTime) {
		super();
		this.id = id;
		this.userDTO = userDTO;
		this.parkingSpotDTO = parkingSpotDTO;
		this.reservationStartTime = reservationStartTime;
		this.reservationEndTime = reservationEndTime;
	}
	
	public ReservationDTO() {}
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the userDTO
	 */
	public UserDTO getUserDTO() {
		return userDTO;
	}
	/**
	 * @param userDTO the userDTO to set
	 */
	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	/**
	 * @return the parkingSpotDTO
	 */
	public ParkingSpotDTO getParkingSpotDTO() {
		return parkingSpotDTO;
	}
	/**
	 * @param parkingSpotDTO the parkingSpotDTO to set
	 */
	public void setParkingSpotDTO(ParkingSpotDTO parkingSpotDTO) {
		this.parkingSpotDTO = parkingSpotDTO;
	}
	/**
	 * @return the reservationStartTime
	 */
	public LocalDateTime getReservationStartTime() {
		return reservationStartTime;
	}
	/**
	 * @param reservationStartTime the reservationStartTime to set
	 */
	public void setReservationStartTime(LocalDateTime reservationStartTime) {
		this.reservationStartTime = reservationStartTime;
	}
	/**
	 * @return the reservationEndTime
	 */
	public LocalDateTime getReservationEndTime() {
		return reservationEndTime;
	}
	/**
	 * @param reservationEndTime the reservationEndTime to set
	 */
	public void setReservationEndTime(LocalDateTime reservationEndTime) {
		this.reservationEndTime = reservationEndTime;
	}
	
}
