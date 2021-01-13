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
public class ForecastWeatherParser extends JsonParser {
	
	JSONArray jsonArrayLoaded = new JSONArray();
	
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
	public ForecastWeatherParser(String cityName) {
		super(cityName);
	}
	
	/**
	 * effettua il parsing dell'oggetto JSON, che viene prelevato dal
	 * file di testo delle previsioni-future (tramite il metodo readFile) 
	 * @param nome_file
	 */
	@Override
	public void parsing() {  
		ReaderFromFile rff = new ReaderFromFile();
		JSONObject obj = new JSONObject();			  //il cityName lo prende dalla superclasse
		obj = (JSONObject) rff.readFileToJSONObject(	rff.nomeFile(  getCityName()	)		);    
		JSONArray jsonArray = (JSONArray) obj.get("list");
		jsonArrayLoaded.add( (JSONObject) jsonArray.get(0) );	//previsioni meteo 06/01/2021 12.00.00
		jsonArrayLoaded.add( (JSONObject) jsonArray.get(8) );	//previsioni meteo 07/01/2021 12.00.00
		jsonArrayLoaded.add( (JSONObject) jsonArray.get(16) );	//previsioni meteo 08/01/2021 12.00.00
		jsonArrayLoaded.add( (JSONObject) jsonArray.get(24) );	//previsioni meteo 09/01/2021 12.00.00
		jsonArrayLoaded.add( (JSONObject) jsonArray.get(32) );	//previsioni meteo 10/01/2021 12.00.00	
	}

}
