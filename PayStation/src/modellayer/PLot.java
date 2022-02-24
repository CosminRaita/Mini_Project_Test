package modellayer;

public class PLot {

	private int id;
	private String name;
	private String zipCode;
	private PZone zone;
	
	public PLot() {
		
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
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
	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}
	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	/**
	 * @return the zone
	 */
	public PZone getZone() {
		return zone;
	}
	/**
	 * @param zone the zone to set
	 */
	public void setZone(PZone zone) {
		this.zone = zone;
	}
	
}
