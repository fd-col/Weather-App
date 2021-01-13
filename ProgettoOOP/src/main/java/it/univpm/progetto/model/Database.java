/** 
 * 
 */
package it.univpm.progetto.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author colleluori
 * @author camplese
 *
 */
public class Database {
	
	private ArrayList<JSONArray> datiAttuali;
	private ArrayList<JSONArray> datiStorici;
	private ArrayList<JSONArray> datiFuturi;	
	/**
	 * @return the arrayJson
	 */
	@SuppressWarnings("unchecked")
	public JSONArray getDatiAttuali(int inizio, int fine) {
		JSONArray jsonArrayTemp = new JSONArray();
		for(int i=inizio; i <= fine; i++)
			jsonArrayTemp.addAll( datiAttuali.get(i-1) ); 
		return (JSONArray)jsonArrayTemp;
	}
	/**
	 * @return the dati_storici
	 */
	public ArrayList<JSONArray> getDatiStorici() {
		return datiStorici;
	}
	/**
	 * @return the datiFuturi
	 */
	public ArrayList<JSONArray> getDatiFuturi() {
		return datiFuturi;
	}
	/**
	 * flag=true per i dati attuali, flag=false per i dati storici
	 * costruttore della classe
	 */
	public Database(String cityName1, String cityName2, String cityName3,  boolean flag) {
		datiAttuali = new ArrayList<JSONArray>();
		datiStorici = new ArrayList<JSONArray>();
		
		JSONArray jsonArray1 = reader(nomeFile(cityName1, flag));
		JSONArray jsonArray2 = reader(nomeFile(cityName2, flag));
		JSONArray jsonArray3 = reader(nomeFile(cityName3, flag));
		
		if(flag) {
			datiAttuali.add(jsonArray1); 
			datiAttuali.add(jsonArray2); 
			datiAttuali.add(jsonArray3); } 
		else {
			datiStorici.add(jsonArray1); 
			datiStorici.add(jsonArray2); 
			datiStorici.add(jsonArray3); 
		}
	}
	
	/**
	 * secondo costruttore
	 */
	public Database() {
		WeatherParser wp = new WeatherParser();
		wp.parsingForecast("previsioni-future-Trieste");

		JSONArray jsonArray1 = new JSONArray();
		//prima formattare il jsonObject preso da file
		jsonArray1.add(		wp.getObj1()	);
		
		
		
//		JSONArray jsonArray2 = new JSONArray();
//		JSONArray jsonArray3 = new JSONArray();
	}

	public String nomeFile(String cityName, boolean flag) {
		if(flag)
			return "database/dati-attuali-"+cityName+".json";
		else
			return "database/dati-storici-"+cityName+".json";
	}
	
	public JSONArray reader(String nome_file) {
		JSONParser parser = new JSONParser();
		JSONArray jsonArray = new JSONArray();
		//JSONObject jsonObj = new JSONObject();
		try {
			BufferedReader buffRead = new BufferedReader(new FileReader(nome_file));
			String line = buffRead.readLine();
			jsonArray = (JSONArray) parser.parse(line);
			//jsonObj = (JSONObject) parser.parse(line);

			buffRead.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return jsonArray;
	}
	
	
}
