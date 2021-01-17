/**
 * 
 */
package it.univpm.progetto.configuration;

import org.json.simple.JSONArray;

/**
 * @author colleluori
 * @author camplese
 *
 */
public interface Reader {

	public String nomeFile(String s, boolean flag1, boolean flag2);
	
	public JSONArray readFile(String nome_file, boolean flag);
	
	//public JSONObject readFileToJsonObject(String nome_file);
}
