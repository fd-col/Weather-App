/**
 * 
 */
package it.univpm.progetto.controller;

import java.net.URISyntaxException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping (value= "/current_weather")
	public  ArrayList<JSONArray> current() throws URISyntaxException {
		Database db = new Database("Trieste","Ortona","Venezia",true);
		return db.getDatiAttuali();
	}
	
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
