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
 * classe che si occupa della lettura da file
 * @author colleluori
 * @author camplese
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
	 * @param nome_file nome del file da leggere
	 * @param flag sceglie se prendere un JSONOBJECT o un JSONArray (true=JSONObject, false=JSONArray)
	 * @return jsonArrayFromFile
	 */
	@SuppressWarnings("unchecked")
	public JSONArray readFile(String nome_file, boolean flag ) {
		JSONParser parser = new JSONParser();
		JSONArray jsonArrayFromFile = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		try {
			BufferedReader buffRead = new BufferedReader(new FileReader(nome_file));
			String line = buffRead.readLine();
			//se è un JSONOBJECT 
			if(flag){
				jsonObject = (JSONObject) parser.parse(line);
				jsonArrayFromFile.add(jsonObject);
			}
			//altrimenti è un JSONArray 
			else 
				jsonArrayFromFile = (JSONArray) parser.parse(line);
			buffRead.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		return jsonArrayFromFile;
	}

}
