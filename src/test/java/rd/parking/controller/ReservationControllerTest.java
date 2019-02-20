package rd.parking.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

import rd.parking.Exception.InvalidDataException;
import rd.parking.dto.ParkingSpotDTO;
import rd.parking.dto.ReservationDTO;
import rd.parking.dto.UserDTO;
import rd.parking.entity.ParkingSpot;
import rd.parking.entity.Reservation;
import rd.parking.entity.User;
import rd.parking.repository.ReservationRepository;
import rd.parking.util.GeomUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationControllerTest {

	@MockBean
	private ReservationRepository reservationRepo;

	@Autowired
	private ReservationController reservationController;

	@Captor 
	private ArgumentCaptor<Reservation> reservationCaptor;
	
	@Test
	public void getAllReservations_test() throws ParseException {

		List<Reservation> reservations = Arrays.asList(new Reservation(1, new User(1, "abc"), new ParkingSpot(1, GeomUtil.latlonToPoint(19.1, 72.1)), null, null), 
				new Reservation(2, new User(2, "pqr"), new ParkingSpot(2, GeomUtil.latlonToPoint(19.2, 72.2)), null, null));

		Mockito.when(reservationRepo.findByUserId(1)).thenReturn(reservations);

		List<ReservationDTO> reservationDTOs = reservationController.getAllReservations(1);

		ReservationDTO dto1 = reservationDTOs.get(0);
		ReservationDTO dto2 = reservationDTOs.get(1);

		Assert.assertEquals(1, dto1.getId());
		Assert.assertEquals(1, dto1.getUserDTO().getId());
		Assert.assertEquals("abc", dto1.getUserDTO().getName());
		Assert.assertEquals(1, dto1.getParkingSpotDTO().getId());
		Assert.assertEquals(19.1d, dto1.getParkingSpotDTO().getLat(), 0.01d);
		Assert.assertEquals(72.1d, dto1.getParkingSpotDTO().getLon(), 0.01d);

		Assert.assertEquals(2, dto2.getId());
		Assert.assertEquals(2, dto2.getUserDTO().getId());
		Assert.assertEquals("pqr", dto2.getUserDTO().getName());
		Assert.assertEquals(2, dto2.getParkingSpotDTO().getId());
		Assert.assertEquals(19.2d, dto2.getParkingSpotDTO().getLat(), 0.01d);
		Assert.assertEquals(72.2d, dto2.getParkingSpotDTO().getLon(), 0.01d);

	}

	@Test
	public void getReservationCost_test() throws ParseException {

		LocalDateTime starttime = convertLocalDateTime("2019-02-20 11:30");
		LocalDateTime endtime = convertLocalDateTime("2019-02-20 12:30");
		Optional<Reservation> reservationOptional = Optional.of(new Reservation(1, new User(1, "abc"), new ParkingSpot(1, GeomUtil.latlonToPoint(19.1, 72.1)), starttime, endtime));

		Mockito.when(reservationRepo.findById(1l)).thenReturn(reservationOptional);

		double cost = reservationController.getReservationCost(1);
		Assert.assertEquals(10d, cost, 0.001);
	}

	@Test
	public void getReservationCost_test_invalid_id() throws ParseException {

		Optional<Reservation> reservationOptional = Optional.empty();

		Mockito.when(reservationRepo.findById(1l)).thenReturn(reservationOptional);

		double cost = reservationController.getReservationCost(1);
		Assert.assertEquals(0d, cost, 0.001);
	}
	
	private LocalDateTime convertLocalDateTime(String str) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
		LocalDateTime datetime = LocalDateTime.parse(str, formatter);
		return datetime;
	}
	
	@Test
	public void createReservation_test_happy_path() throws ParseException {
		long userId = 1;
		LocalDateTime starttime = convertLocalDateTime("2019-02-20 11:30");
		LocalDateTime endtime = convertLocalDateTime("2019-02-20 12:30");
		ReservationDTO reservationDTO = new ReservationDTO(2l, new UserDTO(1l, "abc"), new ParkingSpotDTO(1l, 19.1d, 72.1d), starttime, endtime);

		List<Reservation> reservations = Arrays.asList();
		Mockito.when(reservationRepo.findByParkingSpotId(1l)).thenReturn(reservations);

		reservationController.createReservation(reservationDTO, userId);
		
		Mockito.verify(reservationRepo, Mockito.times(1)).save(reservationCaptor.capture());
		Assert.assertEquals(2l, reservationCaptor.getValue().getId());
		Assert.assertEquals(1l, reservationCaptor.getValue().getUser().getId());
		Assert.assertEquals(1l, reservationCaptor.getValue().getParkingSpot().getId());
	}


	@Test(expected = InvalidDataException.class)
	public void createReservation_test_duplicate_reservation() throws ParseException {
		long userId = 1;
		LocalDateTime starttime = convertLocalDateTime("2019-02-20 11:30");
		LocalDateTime endtime = convertLocalDateTime("2019-02-20 12:30");
		ReservationDTO reservationDTO = new ReservationDTO(1, new UserDTO(1l, "abc"), new ParkingSpotDTO(1l, 19.1d, 72.1d), starttime, endtime);

		ParkingSpot parkingSpot = new ParkingSpot(1l, GeomUtil.latlonToPoint(19.1, 72.1));
		List<Reservation> reservations = Arrays.asList(new Reservation(1, new User(1, "abc"), parkingSpot, null, null));
		Mockito.when(reservationRepo.findByParkingSpotId(1l)).thenReturn(reservations);

		try {
			reservationController.createReservation(reservationDTO, userId);
		} catch(Exception e) {
			Mockito.verify(reservationRepo, Mockito.never()).save(Mockito.any());
			throw e;
		}
	}
	
	@Test
	public void getReservation_test_empty_result() {
		Mockito.when(reservationRepo.findById(1l)).thenReturn(Optional.empty());
		ReservationDTO rd = reservationController.getReservation(1l);
		Assert.assertNull(rd);
	}
	

}
