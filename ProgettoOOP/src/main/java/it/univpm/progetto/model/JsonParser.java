/**
 * 
 */
package it.univpm.progetto.model;

/**
 * @author colleluori
 * @author camplese
 */
public class JsonParser {
	private String cityName;
	private Long timeUNIX;
	private Long visibility;
	private Double speed;
	
	public JsonParser(String cityName) {
		this.setCityName(cityName);
	}
	
	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * @return the timeUNIX
	 */
	public Long getTimeUNIX() {
		return timeUNIX;
	}

	/**
	 * @param timeUNIX the timeUNIX to set
	 */
	public void setTimeUNIX(Long timeUNIX) {
		this.timeUNIX = timeUNIX;
	}

	/**
	 * @return the visibility
	 */
	public Long getVisibility() {
		return visibility;
	}

	/**
	 * @param visibility the visibility to set
	 */
	public void setVisibility(Long visibility) {
		this.visibility = visibility;
	}

	/**
	 * @return the speed
	 */
	public Double getSpeed() {
		return speed;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(Double speed) {
		this.speed = speed;
	}

	public void parsing() {}

}

