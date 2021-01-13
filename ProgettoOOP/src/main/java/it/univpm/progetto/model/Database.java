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
 *
 */
public class Database {
	
	private ArrayList<JSONArray> datiAttuali;
	private ArrayList<JSONArray> datiStorici;
	private ArrayList<JSONArray> datiFuturi;	
	/**
	 * costruttore (dati meteo attuali, dati meteo storici)
	 * flag=true per i dati attuali, flag=false per i dati storici
	 */
	public Database(String cityName1, String cityName2, String cityName3,  boolean flag) {
		
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
	 * secondo costruttore (dati meteo futuri)
	 */
	public Database(String cityName) {
		ForecastWeatherParser forecastParser = new ForecastWeatherParser(cityName);
		forecastParser.parsing();

		JSONArray jsonArray1 = new JSONArray();
		//prima formattare il jsonObject preso da file
		jsonArray1.add(		forecastParser.getObj1()	);
		
		
		
//		JSONArray jsonArray2 = new JSONArray();
//		JSONArray jsonArray3 = new JSONArray();
	}
	
	/**
	 * @return the datiAttuali
	 */
	public JSONArray getDatiAttuali(int inizio, int fine) {
		JSONArray jsonArrayTemp = new JSONArray();
		try {
			for(int i=inizio; i <= fine; i++)
				jsonArrayTemp.addAll( datiAttuali.get(i-1) ); 
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
	 * @return the datiStorici
	 */
	public JSONArray getDatiStorici(int inizio, int fine) {
		JSONArray jsonArrayTemp = new JSONArray();
		try {
			for(int i=inizio; i <= fine; i++)
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
	public ArrayList<JSONArray> getDatiFuturi() {
		return datiFuturi;
	}
	

	
	
	
}
