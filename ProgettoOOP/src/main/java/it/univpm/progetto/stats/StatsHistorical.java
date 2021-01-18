/**
 * 
 */
package it.univpm.progetto.stats;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * @author falco
 *
 */
public class StatsHistorical extends Stats {
	
	ArrayList<JSONArray> arrayLoaded = getArrayTemp();
	/**
	 * costruttore che chiama il costruttore della superclasse con i parametri inseriti
	 * @param allCityName
	 * @param flag1 
	 * @param flag2
	 * @param primaCitta
	 * @param ultimaCitta
	 * @param int giornoIniziale
	 * @param int giornoFinale
	 */
	public StatsHistorical(String allCityName, boolean flag1, boolean flag2, int primaCitta, int ultimaCitta,
																int giornoIniziale, int giornoFinale) {
		
		super(allCityName, flag1, flag2, primaCitta, ultimaCitta, giornoIniziale, giornoFinale);
	}

	/**
	 * unico metodo che calcola i valori minimi e massimo degli attributi "visibility" e "speed"
	 * @param flag1 seleziona l'attributo su cui fare i calcoli: "visibility" o "speed"
	 * (flag1=true per "visibility", flag1=false per "speed")
	 * @param flag2 determina se calcolare val. min o max dell'attributo selezionato tramite flag1
	 * (flag2=true calcola il valore minimo, flag2=false calcola il valore massimo)
	 * @return the visibilityMin
	 */
	public Double valueMinMax(boolean flag1, boolean flag2) {
		JSONObject jObject = (JSONObject) arrayLoaded.get(primaCitta).get(giornoIniziale-1);
		
		Double visibility;
		Double speed;
		if(flag1) { 	//scelgo se calcolare visibility oppure speed (flag=true --> visibility)
			visibility = (Double) jObject.get("visibility");
			for(int i = giornoIniziale-1; i < giornoFinale; i++) {
				JSONObject jsonObj= (JSONObject) arrayLoaded.get(primaCitta).get(i);
				if(flag2) // voglio calcolare il valore minimo o massimo (flag=true --> valore minimo)
					if(visibility >= (Double) jsonObj.get("visibility")) 
						visibility = (Double) jsonObj.get("visibility");
				
				else 
					visibility = (Double) jsonObj.get("visibility");
			}
			return visibility;
		}
		else {
			JSONObject wind = (JSONObject) jObject.get("wind");
			speed = (Double) wind.get("speed");
			for(int i = giornoIniziale-1; i < giornoFinale; i++) {
				JSONObject jsonObj= (JSONObject) arrayLoaded.get(primaCitta).get(i);
				JSONObject wind2 = (JSONObject) jsonObj.get("wind");
				if(flag2)
					if(speed >= (Double) wind2.get("speed")) 
						speed = (Double) wind2.get("speed");
				else 
					speed = (Double) wind2.get("speed");
			}
			return speed; 
		}
		
	}
	
	/**
	 * calcola la media (fra i giorni passati nel costruttore) della visibilità oppure della velocità del vento 
	 * @param flag determina se il calcolo viene effettuato su "visibility" oppure su "speed"
	 * @return the average
	 */
	public Double average(boolean flag)  {
		Double sum = (double) 0;
		for(int m = giornoIniziale-1; m < giornoFinale; m++) {
			JSONObject jObject= (JSONObject) arrayLoaded.get(primaCitta).get(m);
			
			if(flag) {
				sum += (double) jObject.get("visibility");
			}
			else {
				JSONObject wind = (JSONObject) jObject.get("wind");
				Double rawSpeed = (Double) Double.parseDouble(wind.get("speed").toString());
				sum += (Double) rawSpeed;
			}
		}
		Double average = (Double) sum/(giornoFinale-giornoIniziale);	
		return average;
	}

	/**
	 * calcola la varianza (fra i giorni passati nel costruttore) della visibilità oppure della velocità del vento
	 * @param flag permette di decide se calcolare la varianza della "visibility" oppure della "speed"
	 * @return the variance
	 */
	public Double variance(boolean flag) {
		Double media = average(true);
		Double sommaScartiQuad = (double) 0;
		for(int m = giornoIniziale-1; m < giornoFinale; m++) {
			JSONObject jObject= (JSONObject) arrayLoaded.get(primaCitta).get(m);
			
			if(flag)
				sommaScartiQuad += ((Double) jObject.get("visibility") - media)
									*((Double) jObject.get("visibility") - media);
			else {
				JSONObject wind = (JSONObject) jObject.get("wind");
				Double rawSpeed = (Double) Double.parseDouble(wind.get("speed").toString());
				sommaScartiQuad += (rawSpeed - media)*(rawSpeed - media);
			}
		}
		Double variance = sommaScartiQuad/(giornoFinale-giornoIniziale);
		return variance;
	}
	
	/**
	 * formatta le statistiche (calcolate tramite i metodi della classe stessa) in un oggetto JSON 
	 * @return the jsonObject
	 * @throws InvalidParameterException
	 */
	@SuppressWarnings("unchecked")
	public JSONObject formatter() throws InvalidParameterException {
		JSONObject jsonObject = new JSONObject();
		if(primaCitta != ultimaCitta) 
			throw new InvalidParameterException("Errore di inserimento parametri: "
										+ " \'primaCitta\' deve essere uguale a \'ultimaCitta\' ");
		else {
			Map<String, Double> m1 = new LinkedHashMap<String, Double>(4);
			m1.put("visibilityMax", valueMinMax(true, false));
			m1.put("visibilityMin", valueMinMax(true, true));
			m1.put("visibilityAverage", average(true));
			m1.put("visibilityVariance", variance(true));
			jsonObject.put("visibilityStats", m1);	
			
			Map<String, Double> m2 = new LinkedHashMap<String, Double>(4);
			m2.put("speedMax", valueMinMax(false, false));
			m2.put("speedMin", valueMinMax(false, true));
			m2.put("speedAverage", average(false));
			m2.put("speedVariance", variance(false));
			jsonObject.put("speedStats", m2);
		}
		return jsonObject;
	}
	
}