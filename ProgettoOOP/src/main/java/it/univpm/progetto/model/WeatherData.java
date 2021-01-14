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
	JSONArray jsonArrayFormatted = new JSONArray();
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
	 * secondo costruttore
	 * @param cityName
	 * @param p
	 */
	public WeatherData(String cityName, int p) {
		for(int i=0; i<5; i++) {
			ForecastWeatherParser forecastWeatherParser = new ForecastWeatherParser(cityName, i);
			forecastWeatherParser.parsing();
			jsonArrayFormatted.add(forecastWeatherParser.formatter());
		}
	}

	/**
	 * @return the jsonObj
	 */
	public JSONObject getJsonObj() {
		return jsonObjFormatted;
	}
	
	/**
	 * @return the jsonArrayFormatted
	 */
	public JSONArray getJsonArrayFormatted() {
		return jsonArrayFormatted;
	}
	
	
	//SI POTREBBE FARE UN ALTRO COSTRUTTORE ANALOGO AL PRECEDENTE, 
	//con il costruttore ForecastWeatherParser e i medesimi comandi
	
	
}
