/**
 * 
 */
package it.univpm.progetto.controller;

import org.json.simple.JSONObject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.progetto.model.WeatherData;

/**
 * @author colleluori
 * @author camplese
 *
 */
@RestController 
public class Controller {

	
	@GetMapping (value= "/data")
	public  JSONObject givaMeData() {
		WeatherData wd = new WeatherData("Trieste");
		return wd.print();
	}

	
}
