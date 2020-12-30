/**
 * 
 */
package it.univpm.progetto.model;

import java.io.FileNotFoundException;

import org.json.simple.parser.ParseException;
import org.springframework.web.client.RestClientException;

/**
 * @author camplese
 * @author colleluori
 *
 */
public class WeatherData {
	private String cityName;
	private Long date;
	private Double visibility;
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
	public Long getDate() {
		return date;
	}
	/**
	 * @return the visibility
	 */
	public Double getVisibility() {
		return visibility;
	}
	/**
	 * @return the speed
	 */
	public Double getSpeed() {
		return speed;
	}
	
	public void setWeatherData(String cityName) throws RestClientException, FileNotFoundException, ParseException {
		Parser parser = new Parser(cityName);
		parser.parseMethod();
		this.cityName = parser.getCityName();
		this.date = parser.getDate();
		this.speed = parser.getSpeed();
		this.visibility = parser.getVisibility();
	}
}
