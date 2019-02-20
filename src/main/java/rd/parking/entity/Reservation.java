package rd.parking.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Entity class for making parking spot reservation by an User.
 * Parking spot can not be reserved by multiple users at the same time, 
 * hence adding unique constraint on parking_spot_id.
 * 
 * @author User1
 *
 */
@Entity
@Table(name = "reservation",
	uniqueConstraints = @UniqueConstraint(columnNames = {"parking_spot_id"})
)
public class Reservation implements Serializable {

	private static final long serialVersionUID = 134457684696176445L;

	@Id
	private long id;

	@ManyToOne
	@JoinColumn
	private User user;

	@ManyToOne    	
	@JoinColumn
	private ParkingSpot parkingSpot;

	private LocalDateTime reservationStartTime;
	private LocalDateTime reservationEndTime;
	
	public Reservation(long id, User user, ParkingSpot parkingSpot, LocalDateTime reservationStartTime,
			LocalDateTime reservationEndTime) {
		super();
		this.id = id;
		this.user = user;
		this.parkingSpot = parkingSpot;
		this.reservationStartTime = reservationStartTime;
		this.reservationEndTime = reservationEndTime;
	}

	public Reservation() {}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public ParkingSpot getParkingSpot() {
		return parkingSpot;
	}
	public void setParkingSpot(ParkingSpot parkingSpot) {
		this.parkingSpot = parkingSpot;
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
