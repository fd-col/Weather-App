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
	
	/**
	 * costruttore che chiama il costruttore della superclasse con i parametri inseriti
	 * @param cityName1
	 * @param cityName2
	 * @param cityName3
	 * @param flag
	 */
	public StatsHistorical(String allCityName, boolean flag1, boolean flag2, int primaCitta, int ultimaCitta,
																int giornoIniziale, int giornoFinale) {
		
		super(allCityName, flag1, flag2, primaCitta, ultimaCitta, giornoIniziale, giornoFinale);
	}

	/**
	 * calcola la minima visibilità fra i giorni passati come parametri
	 * @param i
	 * @param j
	 * @param k
	 * @param l
	 * @return the visibilityMin
	 */
	public Double visibilityMin() {
		ArrayList<JSONArray> arrayLoaded = getArrayTemp();
		JSONObject jObject= (JSONObject) arrayLoaded.get(primaCitta).get(giornoIniziale-1);
		Double visibilityMin = (Double) jObject.get("visibility");
		for(int m = giornoIniziale-1; m < giornoFinale; m++) {
			JSONObject jObj= (JSONObject) arrayLoaded.get(primaCitta).get(m);
			if(visibilityMin > (Double) jObj.get("visibility")) {
				visibilityMin = (Double) jObj.get("visibility");
			}
		}
		return visibilityMin;
	}
	
	/**
	 * calcola la massima visibilità del vento fra i giorni passati come parametri
	 * @param i
	 * @param j
	 * @param k
	 * @param l
	 * @return the visibilityMax
	 */
	public Double visibilityMax() {
		ArrayList<JSONArray> arrayLoaded = getArrayTemp();
		JSONObject jObject= (JSONObject) arrayLoaded.get(primaCitta).get(giornoIniziale-1);
		
		Double visibilityMax = (Double) jObject.get("visibility");
		for(int m = giornoIniziale-1; m < giornoFinale; m++) {
			JSONObject jObj= (JSONObject) arrayLoaded.get(primaCitta).get(m);
			if(visibilityMax < (Double) jObj.get("visibility")) {
				visibilityMax = (Double) jObj.get("visibility");
			}
		}
		return visibilityMax;
	}
	
	/**
	 * calcola la minima velocità del vento fra i giorni passati come parametri
	 * @param i
	 * @param j
	 * @param k
	 * @param l
	 * @return the speedMin
	 */
	public Double speedMin() {
		JSONObject jsonObject= (JSONObject) getDatiStorici().get(giornoIniziale-1);
		JSONObject wind1 = (JSONObject) jsonObject.get("wind");
		Double speedMin = (Double) wind1.get("speed");
		for(int m = giornoIniziale-1; m < giornoFinale; m++) {
			JSONObject object= (JSONObject) getDatiStorici().get(m);
			JSONObject wind2 = (JSONObject) object.get("wind");
			if(speedMin > (Double) Double.parseDouble(wind2.get("speed").toString())) {
				speedMin = (Double) Double.parseDouble(wind2.get("speed").toString());
			}
		}
		return speedMin;
	}
	
	/**
	 * calcola la massima velocità del vento fra i giorni passati come parametri
	 * @param i
	 * @param j
	 * @param k
	 * @param l
	 * @return the speedMax
	 */
	public Double speedMax() {
		JSONObject jsonObject = (JSONObject) getDatiStorici().get(giornoIniziale-1);
		JSONObject wind1 = (JSONObject) jsonObject.get("wind");
		Double speedMax = (Double) Double.parseDouble(wind1.get("speed").toString());
		for(int m = giornoIniziale-1; m < giornoFinale; m++) {
			JSONObject object= (JSONObject) getDatiStorici().get(m);
			JSONObject wind2 = (JSONObject) object.get("wind");
			if(speedMax < (Double) wind2.get("speed")) {
				speedMax = (Double) wind2.get("speed");
			}
		}
		return speedMax;
	}
	
	/**
	 * calcola la visibilità media fra i giorni passati come parametri
	 * @param visibilityAverage the visibilityAverage to set
	 * @return visibilityAverage
	 */
	public Double visibilityAverage()  {
		Double sum = (double) 0;
		for(int m = giornoIniziale-1; m < giornoFinale; m++) {
			JSONObject jObject= (JSONObject) getDatiStorici().get(m);
			 sum += (double) jObject.get("visibility");
		}
		Double visibilityAverage = (Double) sum/(giornoFinale-giornoIniziale);
		return visibilityAverage;
	}

	/**
	 * calcola la velocità media del vento fra i giorni passati come parametri
	 * @param speedAverage the speedAverage to set
	 * @return speedAverage
	 */
	public Double speedAverage() {
		Double sum = (double) 0;
		for(int m = giornoIniziale-1; m < giornoFinale; m++) {
			JSONObject jObject= (JSONObject) getDatiStorici().get(m);
			JSONObject wind = (JSONObject) jObject.get("wind");
			Double rawSpeed = (Double) Double.parseDouble(wind.get("speed").toString());
			sum += (Double) rawSpeed;
		}
		Double speedAverage = sum/(giornoFinale-giornoIniziale);
		return speedAverage;
		
	}

	/**
	 * calcola la varianza della visibilità fra i giorni passati come parametri
	 * @param visibilitiVariance the visibilitiVariance to set
	 * @return 
	 * @throws Exception 
	 */
	public Double visibilityVariance() {
		Double media = visibilityAverage();
		Double sommaScartiQuad = (double) 0;
		for(int m = giornoIniziale-1; m < giornoFinale; m++) {
			JSONObject jObject= (JSONObject) getDatiStorici().get(m);
			sommaScartiQuad += ((Double) jObject.get("visibility") - media)
								*((Double) jObject.get("visibility") - media);
		}
		Double visibilityVariance = sommaScartiQuad/(giornoFinale-giornoIniziale);
		return visibilityVariance;
	}

	/**
	 * calcola la varianza della velocità del vento fra i giorni passati come parametri
	 * @param speedVariance the speedVariance to set
	 * @throws Exception 
	 */
	public Double speedVariance()  {
		Double media = speedAverage();
		Double sommaScartiQuad = (double) 0;
		for(int m = giornoIniziale-1; m < giornoFinale; m++) {
			JSONObject jObject= (JSONObject) getDatiStorici().get(m);
			JSONObject wind = (JSONObject) jObject.get("wind");
			Double rawSpeed = (Double) Double.parseDouble(wind.get("speed").toString());
			sommaScartiQuad += (rawSpeed - media)*(rawSpeed - media);
		}
		Double speedVariance = sommaScartiQuad/(giornoFinale-giornoIniziale);
		return speedVariance;
	}
	
	/**
	 * formatta la risposta json dei valori calcolati dalla classe
	 * @param i
	 * @param j
	 * @param k
	 * @param l
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public JSONObject formatter() throws InvalidParameterException {
		JSONObject jsonObject = new JSONObject();
		if(primaCitta != ultimaCitta) 
			throw new InvalidParameterException("Errore di inserimento parametri: "
										+ " \'primaCitta\' deve essere uguale a \'ultimaCitta\' ");
		else {
			Map<String, Double> m1 = new LinkedHashMap<String, Double>(4);
			m1.put("visibilityMax", visibilityMax());
			m1.put("visibilityMin", visibilityMin());
			m1.put("visibilityAverage", visibilityAverage());
			m1.put("visibilityVariance", visibilityVariance());
			jsonObject.put("visibilityStats", m1);	
			
			Map<String, Double> m2 = new LinkedHashMap<String, Double>(4);
			m2.put("speedMax", speedMax());
			m2.put("speedMin", speedMin());
			m2.put("speedAverage", speedAverage());
			m2.put("speedVariance", speedVariance());
			jsonObject.put("speedStats", m2);
		}
		return jsonObject;
	}
	
}
