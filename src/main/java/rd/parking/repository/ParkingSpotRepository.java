package rd.parking.repository;

import org.springframework.data.repository.CrudRepository;

import rd.parking.entity.ParkingSpot;

public interface ParkingSpotRepository extends CrudRepository<ParkingSpot, Long>, ParkingSpotRepositoryCustom {

}
