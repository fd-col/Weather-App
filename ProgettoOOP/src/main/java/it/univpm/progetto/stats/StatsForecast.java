/**
 * 
 */
package it.univpm.progetto.stats;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * @author fedju
 *
 */
public class StatsForecast extends Stats {

	private int numeroGiorni = getGiornoFinale() - getGiornoIniziale();
	private JSONArray datiAttuali = new JSONArray();
	
	/**
	 * costruttore
	 * @param cityName
	 * @param giornoIniziale
	 * @param giornoFinale
	 */
	public StatsForecast(String allCityName, boolean flag,boolean flag2, 
							String cityName, int giornoIniziale, int giornoFinale) {
		
		super(allCityName, flag, flag2, cityName, giornoIniziale, giornoFinale);
	
		datiAttuali = getDatiAttuali(1,1);
	}
	
	
	
	//NOTA: i dati attuali contengono "visibility" di tipo Double
	
	public boolean confronta(double soglia_errore) { 
		double sogliaErroreDecimale = soglia_errore/100;
		
		boolean flag = true;
		
		//il for serve per confrontare i corrispondenti giorni riguardo i dati attuali e le previsioni future
		for(int i=0; i<numeroGiorni; i++) {
			
			JSONObject jObjAttuali = (JSONObject) datiAttuali.get(i);
			Double visibilityAttuali = (Double) jObjAttuali.get("visibility");
			
			Map<String, Double> m = new LinkedHashMap<String, Double>(1);
			Double speedAttuali =  m.getOrDefault("speed", 0.0);
			
			
			
			JSONObject jObjFuturi = (JSONObject) getDatiFuturi().get(i);
			Long visibilityFuturi = (Long) jObjFuturi.get("visibility");
			
			Map<String, Double> m1 = new LinkedHashMap<String, Double>(1);
			Double speedFuturi =  m1.getOrDefault("speed", 0.0);
			
			
			
			//prendo la differenza in  valore assoluto tra la visibilità dei dati attuali e quella delle previsioni future 
			Double visibilityDifference = Math.abs( (Double) (visibilityAttuali - visibilityFuturi) );
			Double speedDifference = speedAttuali - speedFuturi;
			
			//durante il ciclo for se un solo valore di "visibilityDifference sfora la soglia_errore percentuale
			//calcolata rispetto la visibilità dei dati attuali --> le previsioni sono errate
			if( visibilityDifference >= (visibilityAttuali*sogliaErroreDecimale)  ) 
				flag = false; 
		}
		return flag;	
	}
			

	/**
	 * @return the datiAttuali
	 */
	public JSONArray getDatiAttuali() {
		return datiAttuali;
	}

	
}
