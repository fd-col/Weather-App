/** 
 * 
 */
package it.univpm.progetto.model;

import java.util.ArrayList;

import org.json.simple.JSONArray;

import it.univpm.progetto.configuration.ReaderFromFile;
import it.univpm.progetto.parser.ForecastWeatherParser;

/**
 * questa classe inizializza i dati attuali / dati storici / previsioni future contenuti nel dataset , 
 * e ne permette la restituzione 
 * @author colleluori
 * @author camplese
 */
public class Dataset {
	
	private String[] allCityName;
	protected int primaCitta, ultimaCitta;
	
	private ArrayList<JSONArray> arrayDatiAttualiStorici;
	private JSONArray datiAttualiStorici;
	private ArrayList<JSONArray> arrayDatiFuturi;
	private JSONArray datiFuturi;

	/**
	 * costruttore (dati meteo attuali, dati meteo storici, previsioni meteo future)
	 * @param allCityName nomi delle città contenute nel dataset
	 * @param flag1 true per i dati attuali, false per le previsioni future oppure i dati storici
	 * @param flag2 true per le previsioni future, false per i dati storici
	 * @param primaCitta variabile intera corrispondente all'indice della prima citta'
	 * @param ultimaCitta variabile intera corrispondente all'indice dell'ultima citta'
	 * @param giornoIniziale primo giorno di cui si vogliono i dati meteo
	 * @param giornoFinale ultimo giorno di cui si vogliono i dati meteo
	 */
	@SuppressWarnings("unchecked")
	public Dataset(String allCityName, boolean flag1, boolean flag2, 
					int primaCitta, int ultimaCitta, int giornoIniziale, int giornoFinale) {
		
	    this.allCityName = allCityName.split(",");
		this.primaCitta = primaCitta;
		this.ultimaCitta = ultimaCitta;
	    
		ReaderFromFile rff = new ReaderFromFile();
		
		arrayDatiAttualiStorici = new ArrayList<JSONArray>();
		for(int i=primaCitta; i <= ultimaCitta; i++) {
			JSONArray jsonArrayTemp = new JSONArray();
			datiAttualiStorici = rff.readFile(rff.nomeFile(this.allCityName[i-1], flag1, flag2) , 	false);
			for(int j=giornoIniziale; j<=giornoFinale; j++) {
				jsonArrayTemp.add(datiAttualiStorici.get(j-1));
			}
			arrayDatiAttualiStorici.add(jsonArrayTemp);
		}
				
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
	 * @return the arrayDatiAttualiStorici
	 */
	public ArrayList<JSONArray> getArrayDatiAttualiStorici() {
		return arrayDatiAttualiStorici;
	}

	/**
	 * @param arrayDatiAttualiStorici the arrayDatiAttualiStorici to set
	 */
	public void setArrayDatiAttualiStorici(ArrayList<JSONArray> arrayDatiAttualiStorici) {
		this.arrayDatiAttualiStorici = arrayDatiAttualiStorici;
	}
	
	/**
	 * @return the arrayDatiFuturi
	 */
	public ArrayList<JSONArray> getDatiFuturi() {
		return arrayDatiFuturi;
	}
	
}
