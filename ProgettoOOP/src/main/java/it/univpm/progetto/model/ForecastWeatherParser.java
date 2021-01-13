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
	JSONObject obj1 = new JSONObject();
	JSONObject obj2 = new JSONObject();
	JSONObject obj3 = new JSONObject();
	JSONObject obj4 = new JSONObject();
	JSONObject obj5 = new JSONObject();	
	
	/**
	 * costruttore della classe che inizializza il nome della città
	 * @param cityName
	 */
	public ForecastWeatherParser(String cityName) {
		super(cityName);
	}
	/**
	 * @return the obj1
	 */
	public JSONObject getObj1() {
		return obj1;
	}
	/**
	 * @return the obj2
	 */
	public JSONObject getObj2() {
		return obj2;
	}
	/**
	 * @return the obj3
	 */
	public JSONObject getObj3() {
		return obj3;
	}
	/**
	 * @return the obj4
	 */
	public JSONObject getObj4() {
		return obj4;
	}
	/**
	 * @return the obj5
	 */
	public JSONObject getObj5() {
		return obj5;
	}
	
	/**
	 * effettua il parsing dell'oggetto JSON prelevato dal file di tempo delle previsioni-future
	 * @param nome_file
	 */
	@Override
	public void parsing() {  
		ReaderFromFile rff = new ReaderFromFile();
		JSONObject obj = new JSONObject();			  //il cityName lo prende dalla superclasse
		obj = (JSONObject) rff.readFile(	rff.nomeFile(  getCityName()	)		).get(0);    
		JSONArray jsonArray = (JSONArray) obj.get("list");
		obj1 = (JSONObject) jsonArray.get(0);	//previsioni meteo 06/01/2021 12.00.00
		obj2 = (JSONObject) jsonArray.get(8);	//previsioni meteo 07/01/2021 12.00.00
		obj3 = (JSONObject) jsonArray.get(16);	//previsioni meteo 08/01/2021 12.00.00
		obj4 = (JSONObject) jsonArray.get(24);	//previsioni meteo 09/01/2021 12.00.00
		obj5 = (JSONObject) jsonArray.get(32);	//previsioni meteo 10/01/2021 12.00.00
	}

}
