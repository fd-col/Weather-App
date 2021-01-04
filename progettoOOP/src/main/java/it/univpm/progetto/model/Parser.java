/**
 * 
 */
package it.univpm.progetto.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


/**
 * @author colleluori
 * @author camplese
 */
public class Parser {

	private String cityName;
	private Long date;
	private Long visibility;
	private Double speed;
	
	//AppidFromFile appid = new AppidFromFile(); // non va bene????
	
	/**
	 * Costruttore della classe Parser
	 * @param cityName
	 */
	public Parser(String cityName) {
		this.cityName = cityName;
	}
	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}
	/**
	 * @return the date
	 */
	public Long getDate() {
		return date;
	}
	/**
	 * @return the visibility
	 */
	public Long getVisibility() {
		return visibility;
	}
	/**
	 * @return the speed
	 */
	public Double getSpeed() {
		return speed;
	}
	
	
	
	public String appidFromFile() throws FileNotFoundException {

		char[] appidChar = new char[35];
		try {
			int next;
			int i=0;
			BufferedReader reader = new BufferedReader(new FileReader("appid.txt"));
			do {
				next = reader.read();
				if(next!=-1) {
					char c = (char) next;
					appidChar[i] = c;
					i++;
				}
			}while(next!=-1);
			//converto appidChar da char[] ad una String

			reader.close();
		}catch(IOException e) {
			System.out.println(e);
		}
		return new String(appidChar);
	}
	

	/**
	 * metodo che effettua il parsing dell'JSON ritornato dalla chiamata a OpenWeather
	 * @throws ParseException
	 * @throws FileNotFoundException 
	 * @throws RestClientException 
	 * @throws URISyntaxException 
	 */
	public void parseMethod() throws ParseException, RestClientException, FileNotFoundException, URISyntaxException {
		JSONParser parser = new JSONParser();
		JSONObject obj = new JSONObject();
		
	
		URI uri = new URI("http://api.openweathermap.org/data/2.5/weather?q=Trieste&appid=edf5872114c84e16c695b5644567722e");
		
		RestTemplate restTemplate = new RestTemplate();
		String jsonResponse = restTemplate.getForObject(uri, String.class);
		
		System.out.println(jsonResponse);
		//IL PROBLEMA POTREBBE ESSERE NELLE DUE RIGHE PRECEDENTI, provare con HttpRequeste...
		
		try {
			obj = (JSONObject) parser.parse(jsonResponse);
			this.cityName = (String) obj.get("name");
			this.date = (Long) obj.get("dt");	
			this.visibility = (Long) obj.get("visibility");
			
			JSONObject wind = (JSONObject) obj.get("wind");
			this.speed = (Double) Double.parseDouble( wind.get("speed").toString() );
		
		} catch(ParseException e){
			e.printStackTrace();		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
