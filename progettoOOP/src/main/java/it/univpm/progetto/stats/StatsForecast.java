/**
 * 
 */
package it.univpm.progetto.stats;

import java.security.InvalidParameterException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONObject;

/**
 * @author colleluori
 * @author camplese
 */
public class StatsForecast extends Stats {
	
	/**
	 * costruttore che chiama il costruttore della superclasse
	 * @param allCityName nomi delle città contenute nel dataset
	 * @param flag1 true per i dati attuali, false per le previsioni future oppure i dati storici
	 * @param flag2 true per le previsioni future, false per i dati storici
	 * @param primaCitta variabile intera corrispondente all'indice della prima citta'
	 * @param ultimaCitta variabile intera corrispondente all'indice dell'ultima citta'
	 * @param giornoIniziale primo giorno di cui si vogliono i dati meteo
	 * @param giornoFinale ultimo giorno di cui si vogliono i dati meteo
	 */
	public StatsForecast(String allCityName, boolean flag1 , boolean flag2, int primaCitta, int ultimaCitta,
																		int giornoIniziale, int giornoFinale) {
		super(allCityName, flag1, flag2, primaCitta, ultimaCitta, giornoIniziale, giornoFinale);
	}
	
	/**
	 * confronta i dati attuali con le previsioni future
	 * @param soglia_errore passata in %
	 * @param visibility_speed booleano che determina se calcolare la "visibility" oppure la "speed"
	 * @return se le previsioni sono attendibili
	 */
	@SuppressWarnings("unchecked")
	public boolean confronta(double soglia_errore, boolean visibility_speed) { 
		if(primaCitta != ultimaCitta) 
			throw new InvalidParameterException("Errore di inserimento parametri: "
												+ "\'primaCitta\' deve essere uguale a \'ultimaCitta\' ");
		double sogliaErroreDecimale = soglia_errore/100;
		boolean flag = true;
		
		double visibilityAttuali = 0;
		double visibilityFuturi = 0;
		double speedAttuali = 0;
		double speedFuturi = 0;
		
		//il for serve per confrontare i corrispondenti giorni riguardo i dati attuali e le previsioni future
		for(int i=0; i<numeroGiorni-1; i++) {
			
			JSONObject jsonObjectAttuali = (JSONObject) getArrayDatiAttualiStorici().get(0).get(i);
			JSONObject jsonObjectFuturi = (JSONObject) getDatiFuturi().get(0).get(i);
			
			if(visibility_speed) {
				visibilityAttuali += (Double) jsonObjectAttuali.get("visibility");
				visibilityFuturi += (Double) jsonObjectFuturi.get("visibility");
				
				//prendo la differenza in  valore assoluto tra la visibilità dei dati attuali e quella delle previsioni future 
				Double visibilityDifference = Math.abs( (Double) (visibilityAttuali - visibilityFuturi) );
				
				//durante il ciclo for se un solo valore di "visibilityDifference sfora la soglia_errore percentuale
				//calcolata rispetto la visibilità dei dati attuali --> le previsioni sono errate
				if( visibilityDifference >= (visibilityAttuali*sogliaErroreDecimale)  ) 
					flag = false; 
			}
			else {
				JSONObject wind1 = (JSONObject) jsonObjectAttuali.get("wind");
				speedAttuali += (Double) Double.parseDouble( wind1.get("speed").toString() );
				
				LinkedHashMap<String,Double> wind2 = (LinkedHashMap<String, Double>) jsonObjectFuturi.get("wind");
				speedFuturi += (Double) Double.parseDouble( wind2.get("speed").toString() );
				
				//prendo la differenza in  valore assoluto tra la velocità del vento dei dati attuali e quella delle previsioni future 
				Double speedDifference = Math.abs( (Double) (speedAttuali - speedFuturi) );
				
				if( speedDifference >= (speedAttuali*sogliaErroreDecimale)  ) 
					flag = false; 
			}
		}
		return flag;	
	}
	
	/**
	 * formatta la risposta json delle statistiche sulle previsioni future
	 * @param soglia_errore soglia di errore percentuale
	 * @return the jsonObject formatted
	 */
	@SuppressWarnings("unchecked")
	public JSONObject formatter(double soglia_errore) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("cityId", this.primaCitta);
		jsonObject.put("numeroGiorni", this.numeroGiorni);
		jsonObject.put("soglia_errore", soglia_errore+"%");
		Map<String, Boolean> m1 = new LinkedHashMap<String, Boolean>(2);
		m1.put("speed", confronta(soglia_errore,false));
		m1.put("visibility", confronta(soglia_errore,true));
		jsonObject.put("attendibilità", m1);
		
		return jsonObject;
	}
			
}
