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
	
	private String date;
	private String cityName;
	private Long timeUNIX;
	private Long visibility;
	private Double speed;
	
	public WeatherParser(String cityName) {
		this.cityName = cityName;
	}
	
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
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
	 * assegna il valore agli attributi della classe madre WeatherParser tramite il jsonObject passato come parametro
	 */
	public void setAll(JSONObject obj) {
		this.setTimeUNIX((Long) obj.get("dt"));	
		this.setDate(formatDate());
		this.setVisibility((Long) obj.get("visibility"));
		JSONObject wind = (JSONObject) obj.get("wind");
		this.setSpeed((Double) Double.parseDouble( wind.get("speed").toString() ));
	}
	
	/**
	 * formattatore generale del json restituito
	 * @return JSONObject
	 */
	@SuppressWarnings("unchecked")
	public JSONObject formatter() {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("cityname", this.cityName);
		jsonObj.put("timeUNIX", this.timeUNIX);
		jsonObj.put("date", this.date);
		jsonObj.put("visibility", this.visibility);
		Map<String, Double> m1 = new LinkedHashMap<String, Double>(1);	// www.educba.com/json-in-java
		m1.put("speed", this.speed);  
		jsonObj.put("wind", m1);
		
		return jsonObj;
	}
	
	/**
	 * formatta una data a partire dal tempo UNIX 
	 * @return dateFromTimeUnix
	 */
	public String formatDate() {
		Date date = new Date(this.timeUNIX*1000L);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		String dateFromTimeUnix = simpleDateFormat.format(date);
		return dateFromTimeUnix;
	}

}

