/**
 * 
 */
package it.univpm.progetto.model;

import org.json.simple.JSONObject;

import it.univpm.progetto.parser.CurrentWeatherParser;
import it.univpm.progetto.stats.StatsForecast;
import it.univpm.progetto.stats.StatsHistorical;

/**
 * questa classe si occupa di inizializzare i dati relativi a: dati attuali contenuti nel dataset
 * o anche dati relativi alle statistiche calcolate su dati storici e preivisioni future
 * (per lo scopo del progetto è stata usata in SaveToFile.java per salvare i dati attuali e creare
 * lo storico dei dati, ed inoltre viene usata nel controller per restituire i metadati)
 * @author camplese
 * @author colleluori
 *
 */
public class WeatherData {
	
	JSONObject jsonObjectFormatted = new JSONObject();

	/**
	 * costruttore che inizializza i dati restituiti da una chiamata tipo 
	 * all'API di OpenWeather sulle previsioni attuali, già parsati tramite
	 * l'uso della classe CurrentWeatherParser.java
	 * @param cityName
	 */
	public WeatherData(String cityName) {
		CurrentWeatherParser currentWeatherParser = new CurrentWeatherParser(cityName);
		currentWeatherParser.parsing();
		jsonObjectFormatted = currentWeatherParser.formatter(); //accedo al metodo formatter() della superclasse
	}

	/**
	 * costruttore che inizializza i dati restituiti da una chiamata tipo
	 * alle statistiche fatte sui dati storici del dataset 
	 * (come esempio viene presa la città di Trieste, e le statistiche 
	 * calcolate  dal primo fino all'ultimo giorno)
	 */
	@SuppressWarnings("unchecked")
	public WeatherData() {
		StatsHistorical statsHistorical = new StatsHistorical("Trieste,Ortona,Venezia", false, false, 1, 1, 1, 5);
		StatsForecast statsForecast = new StatsForecast("Trieste,Ortona,Venezia", true, true, 1, 1, 1, 5);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("statsHistorical", statsHistorical.formatter());
		jsonObject.put("statsForecast", statsForecast.formatter(50));
		
		jsonObjectFormatted = jsonObject;
	}
	
	/**
	 * @return the jsonObj
	 */
	public JSONObject getJsonObjectFormatted() {
		return jsonObjectFormatted;
	}
	
}
