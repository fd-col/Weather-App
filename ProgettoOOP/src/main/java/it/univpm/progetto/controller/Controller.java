/**
 * 
 */
package it.univpm.progetto.controller;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.progetto.configuration.SaveToFile;
import it.univpm.progetto.model.Dataset;
import it.univpm.progetto.model.WeatherData;
import it.univpm.progetto.stats.StatsForecast;
import it.univpm.progetto.stats.StatsHistorical;

/**
 * contiene le rotte che posso essere richiamate tramite un client per richieste di tipo HTTP
 * @author colleluori
 * @author camplese
 * 
 */
@RestController 
public class Controller {
	
	/**
	 * effettua una chiamata alle previsioni attuali tramite API di Postman 
	 * ritornando lo stesso formato dei metadati contenuti nel dataset
	 * @return the current weather
	 */
	@GetMapping (value= "/metadata/weather")
	public  JSONObject giveMeData() {
		WeatherData wd = new WeatherData("London");
		return wd.getJsonObjectFormatted();
		
	}
	
	/**
	 * metodo che ritorno un esempio di metadati ritornati da una chiamata alle statistiche 
	 * sui dati storici e sulle previsioni future
	 * @return the metadata of a stats request
	 */
	@GetMapping (value= "/metadata/stats")
	public JSONObject giveMeStatsForecast() {
		WeatherData wd = new WeatherData();
		return wd.getJsonObjectFormatted();
	}
	
	/**
	 * dati attuali (dal database) delle città selezionate: 1=Trieste, 2=Ortona, 3=Venezia ;
	 * indicare nella chiamata su Postman l'indice d'inizio e di fine selezione tramite un body
	 * @param i
	 * @return the current weather of the dataset 
	 */
	@PostMapping (value= "/weather/current")
	public  ArrayList<JSONArray> current(@RequestBody Index i) {
		Dataset ds = new Dataset("Trieste,Ortona,Venezia",true, false, i.primaCitta, i.ultimaCitta,
																		i.giornoIniziale, i.giornoFinale);
		return ds.getArrayDatiAttualiStorici();				// 1=Trieste, 2=Ortona, 3=Venezia
	}															
	
	
	/**
	 * dati storici (dal database) delle città selezionate: 1=Trieste, 2=Ortona, 3=Venezia ;
	 * indicare nella chiamata su Postman l'indice d'inizio e di fine selezione tramite un body
	 * @param i
	 * @return the historical weather of the dataset
	 */
	@PostMapping (value= "/weather/historical")
	public  ArrayList<JSONArray> historical(@RequestBody Index i) {
		Dataset ds = new Dataset("Trieste,Ortona,Venezia",false, false, i.primaCitta, i.ultimaCitta, 
																		i.giornoIniziale, i.giornoFinale);
		return ds.getArrayDatiAttualiStorici();
	}
	
	
	/**
	 * previsioni future (dal database) delle città selezionate: 1=Trieste, 2=Ortona, 3=Venezia ;
	 * indicare nella chiamata su Postman l'indice d'inizio e di fine selezione tramite un body
	 * @param i
	 * @return the forecast weather of the dataset
	 */
	@PostMapping (value= "/weather/forecast")
	public ArrayList<JSONArray> forecast(@RequestBody Index i) {
		Dataset ds = new Dataset("Trieste,Ortona,Venezia",true, true, i.primaCitta, i.ultimaCitta,
																		i.giornoIniziale, i.giornoFinale);
		return ds.getDatiFuturi();
	}
	
	/**
	 * statistiche riguardanti dati storici
	 * @param i
	 * @return the historical stats 
	 */
	@PostMapping (value= "/stats/historical")
	public JSONObject stats(@RequestBody Index i) {
		StatsHistorical statsHistorical = new StatsHistorical("Trieste,Ortona,Venezia", false, false, 
													i.primaCitta, i.ultimaCitta, i.giornoIniziale, i.giornoFinale ); 											
		return statsHistorical.formatter();
	}
	
	/**
	 * statistiche riguardanti previsioni future
	 * @param i
	 * @return the forecast stats
	 */
	@PostMapping (value= "/stats/forecast")
	public JSONObject statsForecast(@RequestBody Index i) {
		StatsForecast statsForecast = new StatsForecast("Trieste,Ortona,Venezia",true, true, 
													i.primaCitta, i.ultimaCitta, i.giornoIniziale, i.giornoFinale);
		return statsForecast.formatter(i.soglia_errore);													
	}
	
	
	


	
	/**
	 * metodo per gestire il salvataggio dei dati forniti da OpenWeather tramite chiamate alle relative API
	 */
	@GetMapping(value= "/save")
	public void save() {
	
		SaveToFile saveToFile = new SaveToFile(); 
/*		
		saveToFile.printData("Trieste", false);
		saveToFile.printData("Ortona", false);
		saveToFile.printData("Venezia", false);	
*/
		saveToFile.printData("London", true); //è stata lasciata per effettuare una prova
	}										  //di creazione file all'interno del dataset
	
}
