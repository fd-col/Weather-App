/**
 * 
 */
package it.univpm.progetto.configuration;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * @author fedju
 *
 */
public interface Reader {

	public String nomeFile(String s, boolean f);
	
	public JSONArray readFile(String nome_file);
	
	public JSONObject readFileToJsonObject(String nome_file);
}
