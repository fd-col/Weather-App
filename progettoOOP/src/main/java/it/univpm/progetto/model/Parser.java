/**
 * 
 */
package it.univpm.progetto.model;

import java.io.FileNotFoundException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import it.univpm.progetto.configuration.AppidFromFile;

/**
 * @author colleluori
 * @author camplese
 */
public class Parser {

	private String cityName;
	private Long date;
	private Double visibility;
	private Double speed;
	
	AppidFromFile appid;
	
	/**
	 * Costruttore della classe Parser
	 * @param cityName
	 */
	public Parser(String cityName) {
		this.cityName = cityName;
	}
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
	/**
	 * metodo che effettua il parsing dell'JSON ritornato dalla chiamata a OpenWeather
	 * @throws ParseException
	 * @throws FileNotFoundException 
	 * @throws RestClientException 
	 */
	public void parseMethod() throws ParseException, RestClientException, FileNotFoundException {
		JSONParser parser = new JSONParser();
		JSONObject obj = new JSONObject();
		
		RestTemplate restTemplate = new RestTemplate();
		String jsonResponse = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weahter?q="+this.cityName+
														"&appid="+appid.appidFromFile(), String.class);
		try {
			obj = (JSONObject) parser.parse(jsonResponse);
			this.cityName = (String) obj.get("name");
			this.date = (Long) obj.get("dt");	
			this.visibility = (Double) obj.get("visibility");
			
			JSONObject wind = (JSONObject) obj.get("wind");
			this.speed = (Double) Double.parseDouble( wind.get("speed").toString() );
		
		} catch(ParseException e){
			e.printStackTrace();		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
