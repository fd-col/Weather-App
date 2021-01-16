/** 
 * 
 */
package it.univpm.progetto.model;

import java.util.ArrayList;

import org.json.simple.JSONArray;

import it.univpm.progetto.configuration.ReaderFromFile;

/**
 * @author colleluori
 * @author camplese
 */
public class Dataset {
	
	private ArrayList<JSONArray> datiAttuali;
	private ArrayList<JSONArray> datiStorici;
	private JSONArray datiFuturi;	
	/**
	 * costruttore (dati meteo attuali, dati meteo storici)
	 * flag=true per i dati attuali, flag=false per i dati storici
	 */
	public Dataset(String cityName1, String cityName2, String cityName3, boolean flag) {
		
		ReaderFromFile rff = new ReaderFromFile();
		
		JSONArray jsonArray1 = rff.readFile(rff.nomeFile(cityName1, flag));
		JSONArray jsonArray2 = rff.readFile(rff.nomeFile(cityName2, flag));
		JSONArray jsonArray3 = rff.readFile(rff.nomeFile(cityName3, flag));
		
		if(flag) {
			datiAttuali = new ArrayList<JSONArray>();
			datiAttuali.add(jsonArray1); 
			datiAttuali.add(jsonArray2); 
			datiAttuali.add(jsonArray3); } 
		else {
			datiStorici = new ArrayList<JSONArray>();
			datiStorici.add(jsonArray1); 
			datiStorici.add(jsonArray2); 
			datiStorici.add(jsonArray3); 
		}
	}
	
	/**
	 * secondo costruttore (dati futuri)
	 * @param cityName
	 * @param giornoIniziale
	 * @param giornoFinale
	 */
	public Dataset(String cityName, int giornoIniziale, int giornoFinale) {
		datiFuturi = new JSONArray();
		for(int i=giornoIniziale; i<=giornoFinale; i++) {
			ForecastWeatherParser forecastWeatherParser = new ForecastWeatherParser(cityName, i-1);
			forecastWeatherParser.parsing();
			datiFuturi.add(forecastWeatherParser.formatter());
		}
	}
	
	/**
	 * @return the datiAttuali
	 */
	public JSONArray getDatiAttuali(int primaCitta, int ultimaCitta) {
		JSONArray jsonArrayTemp = new JSONArray();
		for(int i=primaCitta; i <= ultimaCitta; i++)
			jsonArrayTemp.addAll( datiAttuali.get(i-1) ); 

		return (JSONArray)jsonArrayTemp;
	}
	
	/**
	 * @return the datiStorici
	 */
	public JSONArray getDatiStorici(int primaCitta, int ultimaCitta) {
		JSONArray jsonArrayTemp = new JSONArray();
		try {
			for(int i=primaCitta; i <= ultimaCitta; i++)
				jsonArrayTemp.addAll( datiStorici.get(i-1) ); 
		}catch(UnsupportedOperationException e) {
			e.printStackTrace();
		}
		catch(ClassCastException e) {
			e.printStackTrace();
		}
		catch(NullPointerException e) {
			e.printStackTrace();
		}
		return (JSONArray)jsonArrayTemp;
	}
	
	/**
	 * @return the datiFuturi
	 */
	public JSONArray getDatiFuturi() {
		return datiFuturi;
	}	
}
