/**
 * 
 */
package it.univpm.progetto.configuration;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
/**
 * @author colleluori
 * @author camplese
 *
 */
@Component
public class Scheduler {
	
	/**
	 * effettua una chiamata ad OpenWeather periodicamente ogni 10 sec
	 */
	@Scheduled(cron="*10/*****") //ogni 10 secondi
	public void callOpenWeather() {
		
		System.out.print("baby");
		
	}
	
}
