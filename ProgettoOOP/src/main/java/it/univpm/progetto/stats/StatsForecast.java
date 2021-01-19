/**
 * 
 */
package it.univpm.progetto.stats;

import java.security.InvalidParameterException;

import org.json.simple.JSONObject;

/**
 * @author fedju
 *
 */
public class StatsForecast extends Stats {
	
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
	}
	
	/**
	 * confronta i dati attuali con le previsioni riguardanti la "visibility"
	 * @param soglia_errore
	 * @return se le previsioni sono attendibili
	 */
	public boolean confronta(double soglia_errore, boolean vis_speed) { 
		if(primaCitta != ultimaCitta) 
			throw new InvalidParameterException("Errore di inserimento parametri: "
												+ "\'primaCitta\' deve essere uguale a \'ultimaCitta\' ");
		double sogliaErroreDecimale = soglia_errore/100;
		boolean flag = true;
		
		Double visibilityAttuali = null;
		Double visibilityFuturi = null;
		Double speedAttuali = null;
		Double speedFuturi = 5.5;
		
		//il for serve per confrontare i corrispondenti giorni riguardo i dati attuali e le previsioni future
		for(int i=0; i<numeroGiorni; i++) {
			
			JSONObject jObjAttuali = (JSONObject) getDatiAttuali().get(0).get(i);
			JSONObject jObjFuturi = (JSONObject) getDatiFuturi().get(0).get(i);
			
			if(vis_speed) {
				visibilityAttuali = (Double) jObjAttuali.get("visibility");
				visibilityFuturi = (Double) jObjFuturi.get("visibility");
				//prendo la differenza in  valore assoluto tra la visibilità dei dati attuali e quella delle previsioni future 
				Double visibilityDifference = Math.abs( (Double) (visibilityAttuali - visibilityFuturi) );
				
				//durante il ciclo for se un solo valore di "visibilityDifference sfora la soglia_errore percentuale
				//calcolata rispetto la visibilità dei dati attuali --> le previsioni sono errate
				if( visibilityDifference >= (visibilityAttuali*sogliaErroreDecimale)  ) 
					flag = false; 
			}
			else {
				JSONObject wind1 = (JSONObject) jObjAttuali.get("wind");
				speedAttuali = (Double) Double.parseDouble( wind1.get("speed").toString() );
				JSONObject wind2 = (JSONObject) jObjAttuali.get("wind");
				speedFuturi = (Double) Double.parseDouble( wind2.get("speed").toString() );
				//
				Double speedDifference = Math.abs( (Double) (speedAttuali - speedFuturi) );
				
				if( speedDifference >= (speedAttuali*sogliaErroreDecimale)  ) 
					flag = false; 
			}
		}
		return flag;	
	}
	
	/**
	 * confronta i dati attuali con le previsioni riguardanti la "speed"
	 * @param soglia_errore
	 * @return
	 */
/*	public boolean confrontaSpeed(double soglia_errore) { 
		if(primaCitta != ultimaCitta) 
			throw new InvalidParameterException("Errore di inserimento parametri: "
												+ "\'primaCitta\' deve essere uguale a \'ultimaCitta\' ");
		double sogliaErroreDecimale = soglia_errore/100;
		boolean flag = true;
		
		for(int i=0; i<numeroGiorni; i++) {
			JSONObject jObjAttuali = (JSONObject) getDatiAttuali().get(0).get(i);
			
			
		}
*/		
		
	
}
