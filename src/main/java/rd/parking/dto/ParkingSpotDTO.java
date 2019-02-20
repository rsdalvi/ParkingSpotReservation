package rd.parking.dto;

/**
 * Represents ParkingSpot Data Transfer Object.
 * 
 * @author User1
 *
 */
public class ParkingSpotDTO {

	private long id;
	private double lat;
	private double lon;
	
	public ParkingSpotDTO() {}
	
	public ParkingSpotDTO(long id, double lat, double lon) {
		super();
		this.id = id;
		this.lat = lat;
		this.lon = lon;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	
	
}
