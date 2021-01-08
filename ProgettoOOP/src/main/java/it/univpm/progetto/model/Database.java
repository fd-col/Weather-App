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
 * @author fedju
 *
 */
public class Database {

	private ArrayList<JSONArray> datiAttuali;
	private ArrayList<JSONArray> datiStorici;
	/**
	 * @return the arrayJson
	 */
	public ArrayList<JSONArray> getDatiAttuali() {
		return datiAttuali;
	}
	/**
	 * @return the dati_storici
	 */
	public ArrayList<JSONArray> getDatiStorici() {
		return datiStorici;
	}
	/**
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

	public String nomeFile(String cityName, boolean flag) {
		if(flag)
			return "database/dati-attuali-"+cityName+".json";
		else
			return "database/dati-storici-"+cityName+".json";
	}
	
	public JSONArray reader(String nome_file) {
		JSONParser parser = new JSONParser();
		JSONArray jsonArray = new JSONArray();
		try {
			BufferedReader buffRead = new BufferedReader(new FileReader(nome_file));
			String line = buffRead.readLine();
			jsonArray = (JSONArray) parser.parse(line);

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
