/**
 * 
 */
package it.univpm.progetto.controller;

import java.net.URISyntaxException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.progetto.configuration.SaveToFile;
import it.univpm.progetto.model.Dataset;
import it.univpm.progetto.model.Stats;
import it.univpm.progetto.model.StatsForecast;
import it.univpm.progetto.model.WeatherData;

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
	public  JSONObject givaMeData() {
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
	@PostMapping (value= "/current_weather")
	public  JSONArray current(@RequestBody Index i) {
		Dataset ds = new Dataset("Trieste","Ortona","Venezia",true, false, "Trieste", i.giornoIniziale, i.giornoFinale);
		return ds.getDatiAttuali(i.primaCitta, i.ultimaCitta);				// 1=Trieste, 2=Ortona, 3=Venezia
	}															
	
	
	@PostMapping (value= "/historical_weather")
	public  JSONArray historical(@RequestBody Index i) {
		Dataset ds = new Dataset("Trieste","Ortona","Venezia",false, false, "Trieste", i.giornoIniziale, i.giornoFinale);
		return ds.getDatiStorici(i.primaCitta, i.ultimaCitta);
	}
	
	
	@PostMapping (value= "/forecast_weather")
	public JSONArray forecast(@RequestBody Index i) {
		Dataset ds = new Dataset("Trieste","Ortona","Venezia",true, true, i.cityName, i.giornoIniziale, i.giornoFinale);
		return ds.getDatiFuturi();
	}
	
	@PostMapping (value= "/stats")
	public JSONObject stats(@RequestBody Index i) {
		Stats stats = new Stats("Trieste","Ortona","Venezia", false, false, "Trieste", i.giornoIniziale, i.giornoFinale ); 		//statistiche riguardanti dati storici
		return stats.formatter(i.primaCitta, i.ultimaCitta, i.giornoIniziale, i.giornoFinale);
	}
	
	/**
	 * statistiche riguardanti dati storici
	 * @param i
	 * @return
	 */
	@PostMapping (value= "/stats_forecast")
	public String statsForecast(@RequestBody Index i) {
		StatsForecast statsForecast = new StatsForecast("Trieste","Ortona","Venezia",true, true, 
														i.cityName, i.giornoIniziale, i.giornoFinale);
		if( statsForecast.confronta(i.soglia_errore) )
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
