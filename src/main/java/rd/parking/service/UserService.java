package rd.parking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rd.parking.dto.UserDTO;
import rd.parking.entity.User;
import rd.parking.repository.UserRepository;
import rd.parking.util.UserConversionUtil;

/**
 * An Service for User business logic.
 * @author User1
 *
 */
@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	/**
	 * Get all Users in the System.
	 * @return
	 */
	public List<UserDTO> getAllUsers(){
		return convertList(userRepo.findAll());
	}
	
	/**
	 * Convert list of User to UserDTO
	 * @param entities
	 * @return
	 */
	private List<UserDTO> convertList(Iterable<User> entities) {
		List<UserDTO> users = new ArrayList<UserDTO>();
		entities.forEach(user->{users.add(UserConversionUtil.convertUser(user));});
		return users;
	}

	/**
	 * Get User by Id
	 * @param id
	 * @return
	 */
	public UserDTO getUser(long id) {
		Optional<User> optional = userRepo.findById(id);
		if(optional.isPresent()) {
			return UserConversionUtil.convertUser(optional.get());
		}
		return null;
	}
	
	/**
	 * Create User
	 * @param userDTO
	 */
	public void createUser(UserDTO userDTO) {
		userRepo.save(UserConversionUtil.convertUserDTO(userDTO));
	}
	
	/**
	 * Update User
	 * @param userDTO
	 */
	public void updateUser(UserDTO userDTO) {
		userRepo.save(UserConversionUtil.convertUserDTO(userDTO));
	}
	
	/**
	 * Delete User by Id
	 * @param id
	 */
	public void deleteUser(long id) {		
		userRepo.deleteById(id);
	}

}
