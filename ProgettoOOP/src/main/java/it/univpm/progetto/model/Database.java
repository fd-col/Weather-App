/**
 * 
 */
package it.univpm.progetto.model;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;


/**
 * accumula dati su file locale dalle API di OpenWeather
 * @author fedju
 *
 */

public class Database {
	/**
	 * Metodo per salvare un oggetto in un file di testo .json.
	 * Posso scegliere se salvare un JSONObject oppure un JSONArray.
	 * 
	 * @param nome_file Nome del file in cui salvare l'oggetto.
	 * @param isObject Specifica se l'oggetto da salvare è un JSONObject oppure un JSONArray.
	 */
	public void salvaFile(String cityName, String nf) {
		String nome_file = nf;
		try {
			WeatherData wd = new WeatherData(cityName);
			PrintWriter file_output = new PrintWriter(
										new BufferedWriter(
											new FileWriter(nome_file, true)));
			file_output.println(wd.print());
			file_output.close();
			System.out.println("File salvato!"); //PER VERIFICA
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

	

}
