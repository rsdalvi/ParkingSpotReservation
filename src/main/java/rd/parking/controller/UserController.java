package rd.parking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rd.parking.dto.UserDTO;
import rd.parking.service.UserService;

/**
 * User Rest Controller
 * @author User1
 *
 */
@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	/**
	 * Get all Users list.
	 * @return
	 */
	@RequestMapping("/users")
	public List<UserDTO> getAllUsers(){
		return userService.getAllUsers();
	}
	
	/**
	 * Get User by Id
	 * @param id
	 * @return
	 */
	@RequestMapping("/users/{id}")
	public UserDTO getUser(@PathVariable long id) {
		return userService.getUser(id);
	}
	
	/**
	 * Create new User.
	 * @param userDTO
	 */
	@RequestMapping(method=RequestMethod.POST, value="/users")
	public void createUser(@RequestBody UserDTO userDTO) {
		userService.createUser(userDTO);
	}

	/**
	 * Update an User.
	 * @param userDTO
	 */
	@RequestMapping(method=RequestMethod.PUT, value="/users/{id}")
	public void updateUser(@RequestBody UserDTO userDTO) {
		userService.updateUser(userDTO);
	}

	/**
	 * Delete User by Id.
	 * @param id
	 */
	@RequestMapping(method=RequestMethod.DELETE, value="/users/{id}")
	public void deleteUser(@PathVariable long id) {
		userService.deleteUser(id);
	}
	
}
