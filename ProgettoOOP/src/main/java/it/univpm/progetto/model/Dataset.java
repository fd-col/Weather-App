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
	
	private String[] allCityName;
	protected int primaCitta, ultimaCitta;
	
	private ArrayList<JSONArray> arrayDatiAttualiStorici;
	private ArrayList<JSONArray> arrayDatiFuturi;
	private JSONArray datiFuturi;

	/**
	 * costruttore (dati meteo attuali, dati meteo storici)
	 * flag=true per i dati attuali, flag=false per i dati storici
	 * @param allCityName
	 * @param flag1 
	 * @param flag2
	 * @param primaCitta
	 * @param ultimaCitta
	 * @param int giornoIniziale
	 * @param int giornoFinale
	 */
	@SuppressWarnings("unchecked")
	public Dataset(String allCityName, boolean flag1, boolean flag2, 
					int primaCitta, int ultimaCitta, int giornoIniziale, int giornoFinale) {
		
	    this.allCityName = allCityName.split(",");
		this.primaCitta = primaCitta;
		this.ultimaCitta = ultimaCitta;
	    
		ReaderFromFile rff = new ReaderFromFile();
		JSONArray jsonArray1 = rff.readFile(rff.nomeFile(this.allCityName[0], flag1, flag2) , 	false);
		JSONArray jsonArray2 = rff.readFile(rff.nomeFile(this.allCityName[1], flag1, flag2) ,	false);
		JSONArray jsonArray3 = rff.readFile(rff.nomeFile(this.allCityName[2], flag1, flag2) , 	false);
		
		arrayDatiAttualiStorici = new ArrayList<JSONArray>();
		arrayDatiAttualiStorici.add(jsonArray1);
		arrayDatiAttualiStorici.add(jsonArray2);
		arrayDatiAttualiStorici.add(jsonArray3);
		
		if(flag2) {
			arrayDatiFuturi = new ArrayList<JSONArray>();
			for(int i=primaCitta; i <= ultimaCitta; i++) {
				datiFuturi = new JSONArray();
				for(int j=giornoIniziale; j<=giornoFinale; j++) {
				
					ForecastWeatherParser forecastWeatherParser = new ForecastWeatherParser(this.allCityName[i-1], j-1);
					forecastWeatherParser.parsing();
					datiFuturi.add(forecastWeatherParser.formatter());
				}
			arrayDatiFuturi.add(datiFuturi);
			}
		}
	}
		
	/**
	 * @return the arrayTemp
	 */
	public ArrayList<JSONArray> getArrayDatiAttualiStorici() {
		return arrayDatiAttualiStorici;
	}

	/**
	 * @param arrayTemp the arrayTemp to set
	 */
	public void setArrayDatiAttualiStorici(ArrayList<JSONArray> arrayDatiAttualiStorici) {
		this.arrayDatiAttualiStorici = arrayDatiAttualiStorici;
	}

	/**
	 * @return the jsonArrayTemp
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<JSONArray> getDatiAttuali() {
		ArrayList<JSONArray> arrayTemp = new JSONArray();
		for(int i=primaCitta; i <= ultimaCitta; i++) 
			 arrayTemp.add( arrayDatiAttualiStorici.get(i-1) );
		return arrayTemp;
	}

	/**
	 * @return the jsonArrayTemp
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<JSONArray> getDatiStorici() {
		ArrayList<JSONArray> arrayTemp = new JSONArray();
		for(int i=primaCitta; i <= ultimaCitta; i++) 
			 arrayTemp.add( arrayDatiAttualiStorici.get(i-1) );
		return arrayTemp;
	}
	
	/**
	 * @return the datiFuturi
	 */
	public ArrayList<JSONArray> getDatiFuturi() {
		return arrayDatiFuturi;
	}
	
}
