/**
 * 
 */
package it.univpm.progetto.configuration;

import org.json.simple.JSONArray;

/**
 * interfaccia che viene implementata nella classe ReaderFromFile.java
 * @author colleluori
 * @author camplese
 *
 */
public interface Reader {

	/**
	 * determina il nome di un file, scegliendo tra 3 opzioni, tramite due booleani
	 * @param s indica il cityName
	 * @param flag1
	 * @param flag2
	 * @return nome del file
	 */
	public String nomeFile(String s, boolean flag1, boolean flag2);
	
	/**
	 * effettua la lettura di un file nel dataset e assegna il contenuto in un json array
	 * @param nome_file
	 * @param flag determina se salvare un json object dentro un json array, o altrimenti un json array per intero
	 * @return json array
	 */
	public JSONArray readFile(String nome_file, boolean flag);
	
}
