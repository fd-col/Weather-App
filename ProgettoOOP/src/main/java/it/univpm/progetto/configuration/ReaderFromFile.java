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
	public String nomeFile(String cityName, boolean flag1, boolean flag2) {
		if(flag1)
			return "dataset/dati-attuali-"+cityName+".json";
		else if(flag2)
			return "dataset/previsioni-future-"+cityName+".json";
		else
			return "dataset/dati-storici-"+cityName+".json";
	}
	
	/**
	 * legge da file il JSONObject o il JSONArray, e ritorna il JSONObject in prima posizione del JSONArray,
	 * altrimenti direttamente il JSONArray
	 * @param nome_file
	 * @param flag sceglie se prendere un JSONOBJECT o un JSONArray (true=JSONObject, false=JSONArray)
	 * @return jsonArrayFromFile
	 */
	public JSONArray readFile(String nome_file, boolean flag ) {
		JSONParser parser = new JSONParser();
		JSONArray jsonArrayFromFile = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		try {
			BufferedReader buffRead = new BufferedReader(new FileReader(nome_file));
			String line = buffRead.readLine();
			//se è un JSONOBJECT 
			if(flag){
				jsonObj = (JSONObject) parser.parse(line);
				jsonArrayFromFile.add(jsonObj);
			}
			//altrimenti è un JSONArray 
			else 
				jsonArrayFromFile = (JSONArray) parser.parse(line);
			
			buffRead.close();
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		} 
		return jsonArrayFromFile;
	}

}
