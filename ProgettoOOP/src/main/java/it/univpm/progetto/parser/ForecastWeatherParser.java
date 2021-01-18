/**
 * 
 */
package it.univpm.progetto.parser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.progetto.configuration.ReaderFromFile;

/**
 * @author fedju
 *
 */
public class ForecastWeatherParser extends WeatherParser {
	
	private int i;
	
	JSONArray jsonArrayLoaded = new JSONArray();

	/**
	 * costruttore della classe 
	 * @param cityName
	 */
	public ForecastWeatherParser(int i) {
		super();
		this.i = i;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void parsing() {  
		ReaderFromFile rff = new ReaderFromFile();
		JSONObject obj = new JSONObject();			  //il cityName lo prende dalla superclasse
		obj = (JSONObject) rff.readFile( rff.nomeFile( getCityName(), false, true), 		true ).get(0);    
		
		JSONObject city = (JSONObject) obj.get("city");
		this.setCityName((String) (city.get("name")));
		
		JSONArray jsonArray = (JSONArray) obj.get("list");
		jsonArrayLoaded.add( jsonArray.get(0) );	//previsioni meteo 06/01/2021 12.00.00
		jsonArrayLoaded.add( jsonArray.get(8) );	//previsioni meteo 07/01/2021 12.00.00
		jsonArrayLoaded.add( jsonArray.get(16) );	//previsioni meteo 08/01/2021 12.00.00
		jsonArrayLoaded.add( jsonArray.get(24) );	//previsioni meteo 09/01/2021 12.00.00
		jsonArrayLoaded.add( jsonArray.get(32) );	//previsioni meteo 10/01/2021 12.00.00	
		
		JSONObject jsonObject = (JSONObject) jsonArrayLoaded.get(i) ; //analizzo ogni singolo giorno delle previsioni meteo
																	//inizializzo gli attributi della superclasse
		setAll(jsonObject);
	}

	/**
	 * @return the jsonArrayLoaded
	 */
	public JSONArray getJsonArrayLoaded() {
		return jsonArrayLoaded;
	}
}
