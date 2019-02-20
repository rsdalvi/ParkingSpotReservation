package rd.parking.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity class for User who would be doing parking reservation.
 * @author User1
 *
 */
@Entity
@Table(name="Users")
public class User {

	@Id
	private long id;
	private String name;

	public User() {
	}
	
	public User(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
