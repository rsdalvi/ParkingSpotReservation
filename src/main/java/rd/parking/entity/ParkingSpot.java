package rd.parking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.vividsolutions.jts.geom.Point;

/**
 * Represents  a Parking Spot.
 * Parking spot is identified by its geo location.
 * 
 * @author User1
 *
 */
@Entity
public class ParkingSpot {

	@Id
	private long id;
	
    @Column(columnDefinition = "geometry(Point,4326)")
    private Point location;
        
    public ParkingSpot(long id, Point location) {
    	this.id = id;
    	this.location = location;
    }
    
    public ParkingSpot() {}
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Point getLocation() {
		return location;
	}
	public void setLocation(Point location) {
		this.location = location;
	}	
	
}
