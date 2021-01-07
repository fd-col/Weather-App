/**
 * 
 */
package it.univpm.progetto.controller;

import java.net.URISyntaxException;

import org.json.simple.JSONObject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping(value= "/save")
	public void prova() {
		Database db = new Database(); //OPPURE UNA @COMPONENT PER LA CLASSE DATABASE,E PROVARE A CHIAMARE DA LI salveFile
		db.printData("Trieste", false);
		db.printData("Ortona", false);
		db.printData("Venezia", false);
	}
	
}
