package rd.parking.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.vividsolutions.jts.io.ParseException;

import rd.parking.dto.UserDTO;
import rd.parking.entity.User;
import rd.parking.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

	@MockBean
	private UserRepository userRepo;
	
	@Autowired
	UserController userController;
	
	@Captor 
	private ArgumentCaptor<User> userCaptor;	
	
	@Test
	public void getAllUsers_test() throws ParseException {
		
		List<User> users = Arrays.asList(new User(1, "abc"), new User(2, "pqr"));
		
		Mockito.when(userRepo.findAll()).thenReturn(users);
		
		List<UserDTO> userDTOs = userController.getAllUsers();
		
		UserDTO dto1 = userDTOs.get(0);
		UserDTO dto2 = userDTOs.get(1);
		Assert.assertEquals(1, dto1.getId());
		Assert.assertEquals("abc", dto1.getName());
		Assert.assertEquals(2, dto2.getId());
		Assert.assertEquals("pqr", dto2.getName());
		
	}

	
	@Test
	public void createUser_test() throws ParseException {
		UserDTO userDTO = new UserDTO(2l, "abc");
		
		userController.createUser(userDTO);
		Mockito.verify(userRepo, Mockito.times(1)).save(userCaptor.capture());
		Assert.assertEquals(2l, userCaptor.getValue().getId());
		Assert.assertEquals("abc", userCaptor.getValue().getName());

	}
	
	@Test
	public void getUser_test_empty_result() {
		Mockito.when(userRepo.findById(1l)).thenReturn(Optional.empty());
		UserDTO rd = userController.getUser(1l);
		Assert.assertNull(rd);
	}
	
}
