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
		return wd.print();
	}
	
	@GetMapping(value= "/salva")
	public void prova() {
		Database db1 = new Database(); //OPPURE UNA @COMPONENT PER LA CLASSE DATABASE,E PROVARE A CHIAMARE DA LI salveFile
		db1.salvaFile("Trieste", "dati_storici.json");
/*		Database db2 = new Database();
		Database db3 = new Database();
		
		db2.salvaFile("Ortona", "dati_storici.json");
		db3.salvaFile("Venezia", "dati_storici.json");
*/	
	}
	
}
