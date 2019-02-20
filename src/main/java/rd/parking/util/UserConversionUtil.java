package rd.parking.util;

import rd.parking.dto.UserDTO;
import rd.parking.entity.User;

public class UserConversionUtil {

	public static User convertUserDTO(UserDTO userDTO) {
		User user = new User();
		user.setId(userDTO.getId());
		user.setName(userDTO.getName());
		return user;
	}
	
	public static UserDTO convertUser(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setName(user.getName());
		return userDTO;
	}
}
