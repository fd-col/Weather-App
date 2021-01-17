/**
 * 
 */
package it.univpm.progetto.model;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * @author fedju
 *
 */
public class StatsForecast extends Dataset {

	private int numeroGiorni;
	private JSONArray datiAttuali = new JSONArray();
	
	/**
	 * costruttore
	 * @param cityName
	 * @param giornoIniziale
	 * @param giornoFinale
	 */
	public StatsForecast(String cityName1, String cityName2, String cityName3, boolean flag,boolean flag2, 
							String cityName, int giornoIniziale, int giornoFinale) {
		super(cityName1, cityName2, cityName3, flag, flag2, cityName, giornoIniziale, giornoFinale );
		this.numeroGiorni = giornoFinale - giornoIniziale;
		datiAttuali = getDatiAttuali(3,3);
		//datiFuturi = getDatiFuturi();
	}
	
	
	
	public boolean confronta(double soglia_errore) { 
		double sogliaErroreDecimale = soglia_errore/100;
		
		boolean flag = true;
		//il for serve per confrontare i corrispondenti giorni riguardo i dati attuali e le previsioni future
		for(int i=0; i<=numeroGiorni; i++) {
			
			JSONObject jObjAttuali = (JSONObject) datiAttuali.get(i);
			Long visibilityAttuali = (Long) jObjAttuali.get("visibility");
			Map<String, Double> m = new LinkedHashMap<String, Double>(1);
			Double speedAttuali =  m.getOrDefault("speed", 0.0);
			
			JSONObject jObjFuturi = (JSONObject) getDatiFuturi().get(i);
			Long visibilityFuturi = (Long) jObjFuturi.get("visibility");
			Map<String, Double> m1 = new LinkedHashMap<String, Double>(1);
			Double speedFuturi =  m1.getOrDefault("speed", 0.0);
			
			//prendo la differenza in  valore assoluto tra la visibilità dei dati attuali e quella delle previsioni future 
			Double visibilityDifference = Math.abs( (double) (visibilityAttuali - visibilityFuturi) );
			Double speedDifference = speedAttuali - speedFuturi;
			
			//durante il ciclo for se un solo valore di "visibilityDifference sfora la soglia_errore percentuale
			//calcolata rispetto la visibilità dei dati attuali --> le previsioni sono errate
			if( visibilityDifference >= (visibilityAttuali*sogliaErroreDecimale)  ) 
				flag = false; 
		}
		return flag;	
	}
				//la differenza tra le due visbilità non può discostarsi oltre il margine consetito 
				//dalla soglia d'errore sulla visibilità dei dati attuali

	/**
	 * @return the datiAttuali
	 */
	public JSONArray getDatiAttuali() {
		return datiAttuali;
	}

	
}
