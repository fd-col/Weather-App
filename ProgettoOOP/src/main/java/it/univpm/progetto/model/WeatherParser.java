/**
 * 
 */
package it.univpm.progetto.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TimeZone;

import org.json.simple.JSONObject;

/**
 * @author colleluori
 * @author camplese
 */
public class WeatherParser {
	
	private String cityName;
	private Long timeUNIX;
	private Long visibility;
	private Double speed;
	
	public WeatherParser(String cityName) {
		this.cityName = cityName;
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
	
	/**
	 * formattatore generale del json restituito
	 * @return JSONObject
	 */
	@SuppressWarnings("unchecked")
	public JSONObject formatter() {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("cityname", this.cityName);
		jsonObj.put("timeUNIX", this.timeUNIX);
		jsonObj.put("date", formatDate());
		jsonObj.put("visibility", this.visibility);
		Map<String, Double> m1 = new LinkedHashMap<String, Double>(1);	// www.educba.com/json-in-java
		m1.put("speed", this.speed);  
		jsonObj.put("wind", m1);
		
		return jsonObj;
	}
	
	public String formatDate() {
		Date date = new Date(this.timeUNIX*1000L);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		String java_date = simpleDateFormat.format(date);
		return java_date;
	}

}

