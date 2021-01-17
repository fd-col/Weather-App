/**
 * 
 */
package it.univpm.progetto.configuration;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import it.univpm.progetto.model.WeatherData;

/**
 * accumula dati su file locale dalle API di OpenWeather
 * @author colleluori
 * @author camplese
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
			WeatherData weatherData = new WeatherData(cityName); //inizializzo gli attributi del JSONObject
			PrintWriter file_output = new PrintWriter(
										new BufferedWriter(
											new FileWriter(nome_file, true)));
			file_output.print(weatherData.getJsonObj()+","); //stampo su file il JSONObject
			file_output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * richiama il metodo appendToFile, ed effettua l'alternativa di salvataggio,
	 * tramite il "flag" (true=dati attuali, false=dati storici).
	 * @param cityName
	 * @param flag
	 */
	public void printData(String cityName, boolean flag) {
		if(flag)
			appendToFile(cityName, "dataset/dati-attuali-"+cityName+".json" );
		else
			appendToFile(cityName, "dataset/dati-storici-"+cityName+".json");
			
	}

}
