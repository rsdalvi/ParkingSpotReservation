package rd.parking.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import rd.parking.entity.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

	public List<Reservation> findByUserId(long userId);
	
	public List<Reservation> findByParkingSpotId(long parkingSpotId);
	
}
