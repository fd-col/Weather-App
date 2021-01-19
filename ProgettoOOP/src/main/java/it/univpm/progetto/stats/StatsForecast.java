/**
 * 
 */
package it.univpm.progetto.stats;

import java.security.InvalidParameterException;
import java.util.LinkedHashMap;
import java.util.Map;

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
		Double speedFuturi = null;
		
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
				//prendo la differenza in  valore assoluto tra la velocità del vento dei dati attuali e quella delle previsioni future 
				Double speedDifference = Math.abs( (Double) (speedAttuali - speedFuturi) );
				
				if( speedDifference >= (speedAttuali*sogliaErroreDecimale)  ) 
					flag = false; 
			}
		}
		return flag;	
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject formatter(double soglia_errore) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("cityId", this.primaCitta);
		jsonObject.put("numeroGiorni", this.numeroGiorni);
		jsonObject.put("sogliaDiErrore", soglia_errore);
		Map<String, Boolean> m1 = new LinkedHashMap<String, Boolean>(2);
		m1.put("speed", confronta(soglia_errore,false));
		m1.put("visibility", confronta(soglia_errore,true));
		jsonObject.put("attendibilità", m1);
		
		return jsonObject;
	}
			
}
