/**
 * 
 */
package it.univpm.progetto.stats;

import java.security.InvalidParameterException;
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
	 * @param allCityName
	 * @param flag1 
	 * @param flag2
	 * @param primaCitta
	 * @param ultimaCitta
	 * @param int giornoIniziale
	 * @param int giornoFinale
	 */
	public StatsForecast(String allCityName, boolean flag1 , boolean flag2, int primaCitta, int ultimaCitta,
																		int giornoIniziale, int giornoFinale) {
		
		super(allCityName, flag1, flag2, primaCitta, ultimaCitta, giornoIniziale, giornoFinale);
	
		datiAttuali = (JSONArray) getDatiAttuali().get(0);
	}
	
	
	
	//NOTA: i dati attuali contengono "visibility" di tipo Double
	
	public boolean confronta(double soglia_errore) { 
		if(primaCitta != ultimaCitta) 
			throw new InvalidParameterException("Errore di inserimento parametri: "
												+ "\'primaCitta\' deve essere uguale a \'ultimaCitta\' ");
		double sogliaErroreDecimale = soglia_errore/100;
		
		boolean flag = true;
		
		//il for serve per confrontare i corrispondenti giorni riguardo i dati attuali e le previsioni future
		for(int i=0; i<numeroGiorni; i++) {
			
			JSONObject jObjAttuali = (JSONObject) datiAttuali.get(primaCitta-1);
			Double visibilityAttuali = (Double) jObjAttuali.get("visibility");
			
			Map<String, Double> m = new LinkedHashMap<String, Double>(1);
			Double speedAttuali =  m.getOrDefault("speed", 0.0);
			
			
			
			JSONObject jObjFuturi = (JSONObject) getDatiFuturi().get(i);
			Double visibilityFuturi = (Double) jObjFuturi.get("visibility");
			
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
	
}
