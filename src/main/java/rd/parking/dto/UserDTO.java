package rd.parking.dto;

/**
 * User Data Transfer Object.
 * @author User1
 *
 */
public class UserDTO {

	private long id;
	private String name;
	
	public UserDTO(long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public UserDTO() {}
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
