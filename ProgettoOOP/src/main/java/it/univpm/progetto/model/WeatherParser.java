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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
/**
 * @author colleluori
 * @author camplese
 */
public class WeatherParser {

	private String cityName;
	private Long timeUNIX;
	private Long visibility;
	private Double speed;
	
	JSONObject obj1 = new JSONObject();
	JSONObject obj2 = new JSONObject();
	JSONObject obj3 = new JSONObject();
	JSONObject obj4 = new JSONObject();
	JSONObject obj5 = new JSONObject();
	
	public WeatherParser() {}
	
	/**
	 * Costruttore della classe Parser
	 * @param cityName
	 */
	public WeatherParser(String cityName) {
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
	public Long getTimeUNIX() {
		return timeUNIX;
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
	/**
	 * @return the obj1
	 */
	public JSONObject getObj1() {
		return obj1;
	}
	/**
	 * @return the obj2
	 */
	public JSONObject getObj2() {
		return obj2;
	}
	/**
	 * @return the obj3
	 */
	public JSONObject getObj3() {
		return obj3;
	}
	/**
	 * @return the obj4
	 */
	public JSONObject getObj4() {
		return obj4;
	}
	/**
	 * @return the obj5
	 */
	public JSONObject getObj5() {
		return obj5;
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
	 * @throws ParseException
	 * @throws FileNotFoundException 
	 * @throws RestClientException 
	 * @throws URISyntaxException 
	 */
	public void parseMethod() throws ParseException, RestClientException, FileNotFoundException, URISyntaxException {
		JSONParser parser = new JSONParser();
		JSONObject obj = new JSONObject();
		
		URI uri = new URI("http://api.openweathermap.org/data/2.5/weather?q="+this.cityName+
						  "&appid="+appidFromFile());
		RestTemplate restTemplate = new RestTemplate();
		String jsonResponse = restTemplate.getForObject(uri, String.class);
		try {
			obj = (JSONObject) parser.parse(jsonResponse);
			this.cityName = (String) obj.get("name");
			this.timeUNIX = (Long) obj.get("dt");	
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
	
	public JSONObject reader(String nome_file) {
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = new JSONObject();
		try {
			BufferedReader buffRead = new BufferedReader(new FileReader(nome_file));
			String line = buffRead.readLine();
			jsonObj = (JSONObject) parser.parse(line);

			buffRead.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return jsonObj;
	}
	
	public int indexFilter() {
		return 0;
	}
	
	public void parsingForecast(String nome_file) {
	
		JSONObject obj = new JSONObject();
		obj = (JSONObject) reader(nome_file);
		JSONArray jsonArray = (JSONArray) obj.get("list");
		obj1 = (JSONObject) jsonArray.get(0);
		obj2 = (JSONObject) jsonArray.get(8);
		obj3 = (JSONObject) jsonArray.get(16);
		obj4 = (JSONObject) jsonArray.get(24);
		obj5 = (JSONObject) jsonArray.get(32);
	}
}