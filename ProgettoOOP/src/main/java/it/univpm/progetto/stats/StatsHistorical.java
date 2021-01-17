/**
 * 
 */
package it.univpm.progetto.stats;

import java.security.InvalidParameterException;
import java.util.LinkedHashMap;
import java.util.Map;

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
	public StatsHistorical(String allCityName, boolean flag, boolean flag2, 
							String cityName, int giornoIniziale, int giornoFinale) {
		
		super(allCityName, flag, flag2, cityName, giornoIniziale, giornoFinale);
	}

	/**
	 * calcola la minima visibilità fra i giorni passati come parametri
	 * @param i
	 * @param j
	 * @param k
	 * @param l
	 * @return the visibilityMin
	 */
	public Double visibilityMin(int i, int j) {
		JSONObject jObject= (JSONObject) getDatiStorici(i,j).get(giornoIniziale-1);
		Double visibilityMin = (Double) jObject.get("visibility");
		for(int m = giornoIniziale-1; m < giornoFinale; m++) {
			JSONObject jObj= (JSONObject) getDatiStorici(i,j).get(m);
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
	public Double visibilityMax(int i, int j) {
		JSONObject jObject= (JSONObject) getDatiStorici(i,j).get(giornoIniziale-1);
		Double visibilityMax = (Double) jObject.get("visibility");
		for(int m = giornoIniziale-1; m < giornoFinale; m++) {
			JSONObject jObj= (JSONObject) getDatiStorici(i,j).get(m);
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
	public Double speedMin(int i, int j) {
		JSONObject jsonObject= (JSONObject) getDatiStorici(i,j).get(giornoIniziale-1);
		JSONObject wind1 = (JSONObject) jsonObject.get("wind");
		Double speedMin = (Double) wind1.get("speed");
		for(int m = giornoIniziale-1; m < giornoFinale; m++) {
			JSONObject object= (JSONObject) getDatiStorici(i,j).get(m);
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
	public Double speedMax(int i, int j) {
		JSONObject jsonObject = (JSONObject) getDatiStorici(i,j).get(giornoIniziale-1);
		JSONObject wind1 = (JSONObject) jsonObject.get("wind");
		Double speedMax = (Double) Double.parseDouble(wind1.get("speed").toString());
		for(int m = giornoIniziale-1; m < giornoFinale; m++) {
			JSONObject object= (JSONObject) getDatiStorici(i,j).get(m);
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
	public Double visibilityAverage(int i, int j)  {
		Double sum = (double) 0;
		for(int m = giornoIniziale-1; m < giornoFinale; m++) {
			JSONObject jObject= (JSONObject) getDatiStorici(i,j).get(m);
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
	public Double speedAverage(int i, int j) {
		Double sum = (double) 0;
		for(int m = giornoIniziale-1; m < giornoFinale; m++) {
			JSONObject jObject= (JSONObject) getDatiStorici(i,j).get(m);
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
	public Double visibilityVariance(int i, int j) {
		Double media = visibilityAverage(i,j);
		Double sommaScartiQuad = (double) 0;
		for(int m = giornoIniziale-1; m < giornoFinale; m++) {
			JSONObject jObject= (JSONObject) getDatiStorici(i,j).get(m);
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
	public Double speedVariance(int i, int j)  {
		Double media = speedAverage(i,j);
		Double sommaScartiQuad = (double) 0;
		for(int m = giornoIniziale-1; m < giornoFinale; m++) {
			JSONObject jObject= (JSONObject) getDatiStorici(i,j).get(m);
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
	public JSONObject formatter(int i, int j) throws InvalidParameterException {
		JSONObject jsonObject = new JSONObject();
		if(i != j) 
			throw new InvalidParameterException("Errore di inserimento parametri: "
										+ " \'primaCitta\' deve essere uguale a \'ultimaCitta\' ");
		else {
			Map<String, Double> m1 = new LinkedHashMap<String, Double>(4);
			m1.put("visibilityMax", visibilityMax( i,  j));
			m1.put("visibilityMin", visibilityMin( i,  j));
			m1.put("visibilityAverage", visibilityAverage( i,  j));
			m1.put("visibilityVariance", visibilityVariance( i,  j));
			jsonObject.put("visibilityStats", m1);	
			
			Map<String, Double> m2 = new LinkedHashMap<String, Double>(4);
			m2.put("speedMax", speedMax( i,  j));
			m2.put("speedMin", speedMin( i,  j));
			m2.put("speedAverage", speedAverage( i,  j));
			m2.put("speedVariance", speedVariance( i,  j));
			jsonObject.put("speedStats", m2);
		}
		return jsonObject;
	}
	
}
