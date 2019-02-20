package rd.parking.repository;

import org.springframework.data.repository.CrudRepository;

import rd.parking.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
