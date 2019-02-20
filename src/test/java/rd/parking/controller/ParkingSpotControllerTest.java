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

import rd.parking.dto.ParkingSpotDTO;
import rd.parking.entity.ParkingSpot;
import rd.parking.repository.ParkingSpotRepository;
import rd.parking.util.GeomUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParkingSpotControllerTest {

	@MockBean
	private ParkingSpotRepository parkingSpotRepo;
	
	@Autowired
	ParkingSpotController parkingSpotController;
	
	@Captor 
	private ArgumentCaptor<ParkingSpot> parkingSpotCaptor;	
	
	@Test
	public void getAllParkingSpots_test() throws ParseException {
		
		List<ParkingSpot> parkingSpots = Arrays.asList(new ParkingSpot(1, GeomUtil.latlonToPoint(19.1, 72.1)), new ParkingSpot(2, GeomUtil.latlonToPoint(19.2, 72.2)));
		
		Mockito.when(parkingSpotRepo.findAll()).thenReturn(parkingSpots);
		
		List<ParkingSpotDTO> parkingSpotDTOs = parkingSpotController.getAllParkingSpots();
		
		ParkingSpotDTO dto1 = parkingSpotDTOs.get(0);
		ParkingSpotDTO dto2 = parkingSpotDTOs.get(1);
		Assert.assertEquals(1, dto1.getId());
		Assert.assertEquals(19.1d, dto1.getLat(), 0.01d);
		Assert.assertEquals(72.1d, dto1.getLon(), 0.01d);
		Assert.assertEquals(2, dto2.getId());
		Assert.assertEquals(19.2d, dto2.getLat(), 0.01d);
		Assert.assertEquals(72.2d, dto2.getLon(), 0.01d);
		
	}

	
	@Test
	public void createParkingSpot_test() throws ParseException {
		ParkingSpotDTO parkingSpotDTO = new ParkingSpotDTO(2l, 19.1, 72.1);
		
		parkingSpotController.createParkingSpot(parkingSpotDTO);
		Mockito.verify(parkingSpotRepo, Mockito.times(1)).save(parkingSpotCaptor.capture());
		Assert.assertEquals(2l, parkingSpotCaptor.getValue().getId());
		Assert.assertEquals(72.1d, parkingSpotCaptor.getValue().getLocation().getX(), 0.001);
		Assert.assertEquals(19.1d, parkingSpotCaptor.getValue().getLocation().getY(), 0.001);

	}
	
	@Test
	public void getParkingSpot_test_empty_result() {
		Mockito.when(parkingSpotRepo.findById(1l)).thenReturn(Optional.empty());
		ParkingSpotDTO psd = parkingSpotController.getParkingSpot(1l);
		Assert.assertNull(psd);
	}
	
}
