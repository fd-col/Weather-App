/**
 * 
 */
package it.univpm.progetto.controller;

import java.net.URISyntaxException;
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
 * @author colleluori
 * @author camplese
 *
 */
@RestController 
public class Controller {
	
	/**
	 * effettua una chiamata alle previsioni attuali tramite API di Postman 
	 * ritornando lo stesso formato dei metadati contenuti nel database
	 * @return dati meteo attuali in formato JSON
	 * @throws URISyntaxException
	 */
	@GetMapping (value= "/metadata")
	public  JSONObject giveMeData() {
		WeatherData wd = new WeatherData("London");
		return wd.getJsonObj();
	}
	
	/**
	 * dati attuali (dal database) delle città selezionate: 1=Trieste, 2=Ortona, 3=Venezia ;
	 * indicare nella chiamata su Postman l'indice d'inizio e di fine selezione tramite un body
	 *
	 * @param attributi della classe Index.java: "inizio" e "fine" 
	 * @return i dati attuali delle città scelte   
	 * @throws URISyntaxException
	 */
	@PostMapping (value= "/weather/current")
	public  ArrayList<JSONArray> current(@RequestBody Index i) {
		Dataset ds = new Dataset("Trieste,Ortona,Venezia",true, false, i.primaCitta, i.ultimaCitta,
																		i.giornoIniziale, i.giornoFinale);
		return ds.getDatiAttuali();				// 1=Trieste, 2=Ortona, 3=Venezia
	}															
	
	
	@PostMapping (value= "/weather/historical")
	public  ArrayList<JSONArray> historical(@RequestBody Index i) {
		Dataset ds = new Dataset("Trieste,Ortona,Venezia",false, false, i.primaCitta, i.ultimaCitta, 
																		i.giornoIniziale, i.giornoFinale);
		return ds.getDatiStorici();
	}
	
	
	@PostMapping (value= "/weather/forecast")
	public ArrayList<JSONArray> forecast(@RequestBody Index i) {
		Dataset ds = new Dataset("Trieste,Ortona,Venezia",true, true, i.primaCitta, i.ultimaCitta,
																		i.giornoIniziale, i.giornoFinale);
		return ds.getDatiFuturi();
	}
	
	/**
	 * statistiche riguardanti dati storici
	 * @param i
	 * @return
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
	 * @return
	 */
	@PostMapping (value= "/stats/forecast")
	public String statsForecast(@RequestBody Index i) {
		StatsForecast statsForecast = new StatsForecast("Trieste,Ortona,Venezia",true, true, 
													i.primaCitta, i.ultimaCitta, i.giornoIniziale, i.giornoFinale);
																			
		if( statsForecast.confronta(i.soglia_errore, false) )
			
			return "OK, PREVISIONI AZZECCATE";
		else 
			return "NO, PREVISIONI ERRATE";
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
		saveToFile.printData("London", true);
	}
	
}
