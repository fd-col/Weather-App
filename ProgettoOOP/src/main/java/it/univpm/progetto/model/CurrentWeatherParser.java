/**
 * 
 */
package it.univpm.progetto.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * @author fedju
 *
 */
public class CurrentWeatherParser extends WeatherParser {
	
	/**
	 * costruttore della classe che inizializza il nome della città
	 * @param cityName
	 */
	public CurrentWeatherParser(String cityName) {
		super(cityName);
	}

	/**
	 * prende l'API key da file
	 * @return una stringa contenente l'API key presa da file
	 * @throws FileNotFoundException
	 */
	public String appidFromFile() throws FileNotFoundException {

		char[] appidChar = new char[32];
		int i=0;
		try {
			int next;
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
	 */
	@Override
	public void parsing() {
		JSONParser parser = new JSONParser();
		JSONObject obj = new JSONObject();
		String jsonResponse = null;
		
		RestTemplate restTemplate = new RestTemplate();
		try {
			jsonResponse = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?q="+this.getCityName()+
															"&appid="+appidFromFile(), String.class);
			obj = (JSONObject) parser.parse(jsonResponse);
			
		} catch (RestClientException | FileNotFoundException | ParseException e) {
			e.printStackTrace();
		}
		
		this.setCityName((String) obj.get("name"));
		this.setTimeUNIX((Long) obj.get("dt"));	
		this.setVisibility((Long) obj.get("visibility"));
		JSONObject wind = (JSONObject) obj.get("wind");
		this.setSpeed((Double) Double.parseDouble( wind.get("speed").toString() ));
		
	}

}
