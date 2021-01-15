/**
 * 
 */
package it.univpm.progetto.configuration;

import java.io.BufferedReader;
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
public class ReaderFromFile implements Reader {
	
	/**
	 *	stabilisce il nome del file in base al cityName passato come parametro
	 *  e alla scelta effettuata tramite il boolean "flag"
	 *  @return nome del file 
	 */
	public String nomeFile(String cityName, boolean flag) {
		if(flag)
			return "dataset/dati-attuali-"+cityName+".json";
		else
			return "dataset/dati-storici-"+cityName+".json";
	}
	
	/**
	 * stabilisce il nome del file in base al cityName
	 * @param cityName
	 * @return nome del file
	 */
	public String nomeFile(String cityName) {
		return "dataset/previsioni-future-"+cityName+".json";
	}
	
	//NOTA BENE: QUANDO LO USO PER LEGGERE UN JSON-OBJECT PROVARE A INSERIRLO 
	//IN UN JSONARRAY E FAR RESTITUIRE IL JSONOBJECT IN PRIMA POSIZIONE
	//---> pare non funzionare.
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
			
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		} 
		return jsonArrayFromFile;
	}
	
	public JSONObject readFileToJsonObject(String nome_file) {
		JSONParser parser = new JSONParser();
		JSONObject jsonObjFromFile = new JSONObject();
		try {
			BufferedReader buffRead = new BufferedReader(new FileReader(nome_file));
			String line = buffRead.readLine();
			jsonObjFromFile = (JSONObject) parser.parse(line);

			buffRead.close();
			
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		} 
		return jsonObjFromFile;
	}

}
