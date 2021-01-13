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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.progetto.configuration.SaveToFile;
import it.univpm.progetto.model.Database;
import it.univpm.progetto.model.WeatherData;
/**
 * @author colleluori
 * @author camplese
 *
 */
@RestController 
public class Controller {

	
	@GetMapping (value= "/data")
	public  JSONObject givaMeData() throws URISyntaxException {
		WeatherData wd = new WeatherData("Trieste");
		return wd.formatter();
	}
	/**
	 * dati attuali (dal database) delle città selezionate: 1=Trieste, 2=Ortona, 3=Venezia ;
	 * indicare nella chiamata su Postman l'indice d'inizio e di fine selezione tramite un body
	 *
	 * @param attributi della classe Index.java: "inizio" e "fine" 
	 * @return i dati attuali delle città scelte
	 * 		   
	 * @throws URISyntaxException
	 */
	@PostMapping (value= "/current_weather")
	public  JSONArray current(@RequestBody Index i) throws URISyntaxException {
		Database db = new Database("Trieste","Ortona","Venezia",true);
		return db.getDatiAttuali(i.inizio, i.fine); 
	}															// 1=Trieste, 2=Ortona, 3=Venezia
	
	
	
	@GetMapping (value= "/historical_weather")
	public  ArrayList<JSONArray> historical() throws URISyntaxException {
		Database db = new Database("Trieste","Ortona","Venezia",false);
		return db.getDatiStorici();
	}
	
	
	
	
	
	
	
	
	
	@GetMapping(value= "/save")
	public void prova() {
		SaveToFile saveToFile = new SaveToFile(); 
/*		saveToFile.printData("Trieste", false);
		saveToFile.printData("Ortona", false);
		saveToFile.printData("Venezia", false);
*/		
		saveToFile.printData("London", true);
	}
	
}
