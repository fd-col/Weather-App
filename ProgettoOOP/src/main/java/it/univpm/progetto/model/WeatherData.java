/**
 * 
 */
package it.univpm.progetto.model;

import java.net.URISyntaxException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * @author camplese
 * @author colleluori
 *
 */
public class WeatherData {
	
	JSONObject jsonObjFormatted = new JSONObject();

	/**
	 * primo costruttore
	 * @param cityName
	 * @param date
	 * @param visibility
	 * @param speed
	 * @throws URISyntaxException 
	 */
	public WeatherData(String cityName) {
		CurrentWeatherParser currentWeatherParser = new CurrentWeatherParser(cityName);
		currentWeatherParser.parsing();
		jsonObjFormatted = currentWeatherParser.formatter(); //accedo al metodo formatter() della superclasse
	}

	/**
	 * @return the jsonObj
	 */
	public JSONObject getJsonObj() {
		return jsonObjFormatted;
	}
	
}
