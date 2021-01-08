/**
 * 
 */
package it.univpm.progetto.configuration;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;

import it.univpm.progetto.model.WeatherData;
/**
 * accumula dati su file locale dalle API di OpenWeather
 * @author fedju
 *
 */
public class SaveToFile {
	/**
	 * Metodo per salvare un oggetto in un file di testo .json
	 * @param nome_file Nome del file in cui salvare l'oggetto.
	 * @param isObject Specifica se l'oggetto da salvare è un JSONObject oppure un JSONArray.
	 */
	public void appendToFile(String cityName, String nome_file) {
		try {
			WeatherData wd = new WeatherData(cityName); //inizializzo gli attributi del JSONObject
			PrintWriter file_output = new PrintWriter(
										new BufferedWriter(
											new FileWriter(nome_file, true)));
			file_output.print(wd.formatter()+","); //stampo su file il JSONObject
			file_output.close();
			System.out.println("File salvato!"); //PER VERIFICA
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
	/**
	 * richiama il metodo appendToFile e tramite il "flag" si effettua la scelta 
	 * di salvataggio (true=dati attuali, false=dati storici)
	 * @param cityName
	 * @param flag
	 */
	public void printData(String cityName, boolean flag) {
		if(flag)
			appendToFile(cityName, "dati-attuali-"+cityName+".json" );
		else
			appendToFile(cityName, "dati-storici-"+cityName+".json");
			
	}

}
