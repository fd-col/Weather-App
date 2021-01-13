/**
 * 
 */
package it.univpm.progetto.model;

import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONObject;

/**
 * @author camplese
 * @author colleluori
 *
 */
public class WeatherData {
	private String cityName;
	private Long timeUNIX;
	private Long visibility;
	private Double speed;
	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}
	/**
	 * @return the date
	 */
	public Long getTimeUNIX() {
		return timeUNIX;
	}
	/**
	 * @return the visibility
	 */
	public Long getVisibility() {
		return visibility;
	}
	/**
	 * @return the speed
	 */
	public Double getSpeed() {
		return speed;
	}
	/**
	 * @param cityName
	 * @param date
	 * @param visibility
	 * @param speed
	 * @throws URISyntaxException 
	 */
	public WeatherData(String cityName) {
		CurrentWeatherParser parser = new CurrentWeatherParser(cityName);
		parser.parsing();
		this.cityName = parser.getCityName();
		timeUNIX = parser.getTimeUNIX();
		visibility = parser.getVisibility();
		speed = parser.getSpeed();
	}
	/**
	 * formattatore del json restituito
	 * @return JSONObject
	 */
	@SuppressWarnings ("unchecked")
	public JSONObject formatter() {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("cityname", this.cityName);
		jsonObj.put("timeUNIX", this.timeUNIX);
		jsonObj.put("visibility", this.visibility);
		Map<String, Double> m1 = new LinkedHashMap<String, Double>(1);	// www.educba.com/json-in-java
		m1.put("speed", this.speed);  
		jsonObj.put("wind", m1);
		
		return jsonObj;
	}
}
