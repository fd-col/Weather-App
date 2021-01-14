/**
 * 
 */
package it.univpm.progetto.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.progetto.configuration.ReaderFromFile;

/**
 * @author fedju
 *
 */
public class ForecastWeatherParser extends WeatherParser {
	
	int i;
	JSONArray jsonArrayLoaded = new JSONArray();
	JSONArray jsonArrayParsed = new JSONArray();
	
	/**
	 * @return the jsonArrayLoaded
	 */
	public JSONArray getJsonArrayLoaded() {
		return jsonArrayLoaded;
	}

	/**
	 * costruttore della classe che inizializza il nome della città
	 * @param cityName
	 */
	public ForecastWeatherParser(String cityName, int i) {
		super(cityName);
		this.i = i;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void parsing() {  
		ReaderFromFile rff = new ReaderFromFile();
		JSONObject obj = new JSONObject();			  //il cityName lo prende dalla superclasse
		obj = (JSONObject) rff.readFileToJsonObject(	rff.nomeFile(  getCityName()	)		);    
		JSONArray jsonArray = (JSONArray) obj.get("list");
		jsonArrayLoaded.add( jsonArray.get(0) );	//previsioni meteo 06/01/2021 12.00.00
		jsonArrayLoaded.add( jsonArray.get(8) );	//previsioni meteo 07/01/2021 12.00.00
		jsonArrayLoaded.add( jsonArray.get(16) );	//previsioni meteo 08/01/2021 12.00.00
		jsonArrayLoaded.add( jsonArray.get(24) );	//previsioni meteo 09/01/2021 12.00.00
		jsonArrayLoaded.add( jsonArray.get(32) );	//previsioni meteo 10/01/2021 12.00.00	
		
		JSONObject jsonObj = (JSONObject) jsonArrayLoaded.get(i) ; //analizzo ogni singolo giorno delle previsioni meteo
		this.setTimeUNIX((Long) jsonObj.get("dt"));					//inizializzo gli attributi della superclasse
		this.setVisibility((Long) jsonObj.get("visibility"));
		JSONObject wind = (JSONObject) jsonObj.get("wind");
		this.setSpeed((Double) Double.parseDouble( wind.get("speed").toString() ));
	}
	


}
