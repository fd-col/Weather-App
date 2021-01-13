/**
 * 
 */
package it.univpm.progetto.configuration;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author fedju
 *
 */
public class ReaderFromFile {

	public String nomeFile(String cityName, boolean flag) {
		if(flag)
			return "database/dati-attuali-"+cityName+".json";
		else
			return "database/dati-storici-"+cityName+".json";
	}
	
	public String nomeFile(String cityName) {
		return "database/previsioni-future-"+cityName+".json";
	}
	
	
	//NOTA BENE: QUANDO LO USO PER LEGGERE UN JSON-OBJECT PROVARE A INSERIRLO 
	//IN UN JSONARRAY E FAR RESTITUIRE IL JSONOBJECT IN PRIMA POSIZIONE
	//---> pare non funzionare
	
	// cercare Generics come tipo di ritorno
	public JSONArray readFile(String nome_file) {
		JSONParser parser = new JSONParser();
		JSONArray jsonArrayFromFile = new JSONArray();
		//JSONObject jsonObj = new JSONObject();
		try {
			BufferedReader buffRead = new BufferedReader(new FileReader(nome_file));
			String line = buffRead.readLine();
			jsonArrayFromFile = (JSONArray) parser.parse(line);
			//jsonObj = (JSONObject) parser.parse(line);

			buffRead.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return jsonArrayFromFile;
	}
	
	public JSONObject readFileToJSONObject(String nome_file) {
		JSONParser parser = new JSONParser();
		JSONObject jsonObjFromFile = new JSONObject();
		try {
			BufferedReader buffRead = new BufferedReader(new FileReader(nome_file));
			String line = buffRead.readLine();
			jsonObjFromFile = (JSONObject) parser.parse(line);

			buffRead.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return jsonObjFromFile;
	}
}
