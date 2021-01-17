/** 
 * 
 */
package it.univpm.progetto.model;

import java.util.ArrayList;

import org.json.simple.JSONArray;

import it.univpm.progetto.configuration.ReaderFromFile;
import it.univpm.progetto.parser.ForecastWeatherParser;

/**
 * @author colleluori
 * @author camplese
 */
public class Dataset {
	
	private ArrayList<JSONArray> datiAttuali;
	private ArrayList<JSONArray> datiStorici;
	private JSONArray datiFuturi;
	private String[] allCityName;
	/**
	 * costruttore (dati meteo attuali, dati meteo storici)
	 * flag=true per i dati attuali, flag=false per i dati storici
	 */
	public Dataset(String allCityName, boolean flag1, boolean flag2, 
					String cityName, int giornoIniziale, int giornoFinale) {
		
	    this.allCityName = allCityName.split(",");
		
		ReaderFromFile rff = new ReaderFromFile();
		
		JSONArray jsonArray1 = rff.readFile(rff.nomeFile(this.allCityName[0], flag1, flag2) , false);
		JSONArray jsonArray2 = rff.readFile(rff.nomeFile(this.allCityName[1], flag1, flag2) , false);
		JSONArray jsonArray3 = rff.readFile(rff.nomeFile(this.allCityName[2], flag1, flag2) , false);
		
		if(flag1) {
			datiAttuali = new ArrayList<JSONArray>();
			datiAttuali.add(jsonArray1); 
			datiAttuali.add(jsonArray2); 
			datiAttuali.add(jsonArray3); 
			if(flag2) {
				datiFuturi = new JSONArray();
				for(int i=giornoIniziale; i<=giornoFinale; i++) {
					ForecastWeatherParser forecastWeatherParser = new ForecastWeatherParser(cityName, i-1);
					forecastWeatherParser.parsing();
					datiFuturi.add(forecastWeatherParser.formatter());
				} 
			}
		}
		else {
			datiStorici = new ArrayList<JSONArray>();
			datiStorici.add(jsonArray1); 
			datiStorici.add(jsonArray2); 
			datiStorici.add(jsonArray3); 
		}
	}
		
	/**
	 * @return the datiAttuali
	 */
	public JSONArray getDatiAttuali(int primaCitta, int ultimaCitta) {
		JSONArray jsonArrayTemp = new JSONArray();
		for(int i=primaCitta; i <= ultimaCitta; i++)
			jsonArrayTemp.add( datiAttuali.get(i-1) ); 
		
		return (JSONArray)jsonArrayTemp;
	}
	
	/**
	 * @return the datiStorici
	 */
	public JSONArray getDatiStorici(int primaCitta, int ultimaCitta) {
		JSONArray jsonArrayTemp = new JSONArray();
		for(int i=primaCitta; i <= ultimaCitta; i++)
			jsonArrayTemp.add( datiStorici.get(i-1) ); 

		return (JSONArray)jsonArrayTemp;
	}
	
	/**
	 * @return the datiFuturi
	 */
	public JSONArray getDatiFuturi() {
		return datiFuturi;
	}
	
}
