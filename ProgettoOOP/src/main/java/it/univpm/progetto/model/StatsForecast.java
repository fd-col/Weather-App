/**
 * 
 */
package it.univpm.progetto.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * @author fedju
 *
 */
public class StatsForecast extends Dataset {

	int numeroGiorni;
	private JSONArray datiAttuali = new JSONArray();
	private JSONArray datiFuturi = new JSONArray();
	/**
	 * costruttore
	 * @param cityName
	 * @param giornoIniziale
	 * @param giornoFinale
	 */
	public StatsForecast(String cityName, int giornoIniziale, int giornoFinale) {
		super(cityName, giornoIniziale, giornoFinale);
		
		numeroGiorni = giornoFinale - giornoIniziale;
		datiAttuali = getDatiAttuali(1,1);
		datiFuturi = getDatiFuturi();
	}
	
	public boolean confronta(double soglia_errore) { //esempio con soglia_errore 1.00
		boolean flag = false;
		for(int i=0; i<=numeroGiorni; i++) {
			
			JSONObject jObjAttuali = (JSONObject) datiAttuali.get(i);
			Long visibilityAttuali = (Long) jObjAttuali.get("visibility");
			Double speedAttuali = (Double) jObjAttuali.get("speed");
			
			JSONObject jObjFuturi = (JSONObject) datiFuturi.get(i);
			Long visibilityFuturi = (Long) jObjFuturi.get("visibility");
			Double speedFuturi = (Double) jObjFuturi.get("speed");
			
			Double visibilityDifference =  (double) (visibilityAttuali - visibilityFuturi);
			Double speedDifference = speedAttuali - speedFuturi;
			
			if( visibilityDifference < soglia_errore && speedDifference < soglia_errore  )
				flag = true;
		}
		return flag;	
	}
	
	

}
