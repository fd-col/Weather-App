/**
 * 
 */
package it.univpm.progetto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.progetto.configuration.Scheduler;

/**
 * @author colleluori
 * @author camplese
 *
 */
@RestController 
public class Controller {

	@Autowired 
	Scheduler schedule;
	
	@GetMapping (value= "/data")
	public  String saluto() {
		return "say Hi, mommy. Questa è una provaa";
	}
	
	
	@GetMapping ("/prova")
	public void prova() {
		schedule.callOpenWeather();
		
	}
	
	
	
}
