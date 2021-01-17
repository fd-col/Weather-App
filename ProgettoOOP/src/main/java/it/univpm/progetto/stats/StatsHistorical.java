/**
 * 
 */
package it.univpm.progetto.stats;

import java.security.InvalidParameterException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import it.univpm.progetto.model.Dataset;
/**
 * @author falco
 *
 */
public class StatsHistorical extends Dataset {
	
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
	 * calcola la minima visibilità tra i giorni passati come parametri
	 * @param i
	 * @param j
	 * @param k
	 * @param l
	 * @return the visibilityMin
	 */
	public Double visibilityMin(int i, int j, int giornoIniziale, int giornoFinale) {
		JSONObject jObject= (JSONObject) getDatiStorici(i,j).get(giornoIniziale);
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
	 * calcola la massima visibilità del vento tra i giorni passati come parametri
	 * @param i
	 * @param j
	 * @param k
	 * @param l
	 * @return the visibilityMax
	 */
	public Double visibilityMax(int i, int j, int giornoIniziale, int giornoFinale) {
		JSONObject jObject= (JSONObject) getDatiStorici(i,j).get(giornoIniziale);
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
	 * calcola la minima velocità del vento tra i giorni passati come parametri
	 * @param i
	 * @param j
	 * @param k
	 * @param l
	 * @return the speedMin
	 */
	public Double speedMin(int i, int j, int giornoIniziale, int giornoFinale) {
		JSONObject jsonObject= (JSONObject) getDatiStorici(i,j).get(giornoIniziale);
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
	 * calcola la massima velocità del vento tra i giorni passati come parametri
	 * @param i
	 * @param j
	 * @param k
	 * @param l
	 * @return the speedMax
	 */
	public Double speedMax(int i, int j, int giornoIniziale, int giornoFinale) {
		JSONObject jsonObject = (JSONObject) getDatiStorici(i,j).get(giornoIniziale);
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
	 * calcola la visibilità media tra i giorni passati come parametri
	 * @param visibilityAverage the visibilityAverage to set
	 * @return visibilityAverage
	 */
	public Double visibilityAverage(int i, int j, int giornoIniziale, int giornoFinale)  {
		Double sum = (double) 0;
		for(int m = giornoIniziale-1; m < giornoFinale; m++) {
			JSONObject jObject= (JSONObject) getDatiStorici(i,j).get(m);
			 sum += (double) jObject.get("visibility");
		}
		Double visibilityAverage = (Double) sum/(giornoFinale-giornoIniziale);
		return visibilityAverage;
	}

	/**
	 * calcola la velocità media del vento tra i giorni passati come parametri
	 * @param speedAverage the speedAverage to set
	 * @return speedAverage
	 */
	public Double speedAverage(int i, int j, int giornoIniziale, int giornoFinale) {
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
	 * calcola la varianza della visibilità tra i giorni passati come parametri
	 * @param visibilitiVariance the visibilitiVariance to set
	 * @return 
	 * @throws Exception 
	 */
	public Double visibilityVariance(int i, int j, int giornoIniziale, int giornoFinale) {
		Double media = visibilityAverage(i,j,giornoIniziale,giornoFinale);
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
	 * calcola la varianza della velocità del vento tra i giorni passati come parametri
	 * @param speedVariance the speedVariance to set
	 * @throws Exception 
	 */
	public Double speedVariance(int i, int j, int giornoIniziale, int giornoFinale)  {
		Double media = speedAverage(i,j,giornoIniziale,giornoFinale);
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
	public JSONObject formatter(int i, int j, int k, int l) throws InvalidParameterException {
		JSONObject jsonObject = new JSONObject();
		if(i != j) 
			throw new InvalidParameterException("Errore di inserimento parametri: "
										+ " \'primaCitta\' deve essere uguale a \'ultimaCitta\' ");
		else {
			Map<String, Double> m1 = new LinkedHashMap<String, Double>(4);
			m1.put("visibilityMax", visibilityMax( i,  j,  k,  l) );
			m1.put("visibilityMin", visibilityMin( i,  j,  k,  l) );
			m1.put("visibilityAverage", visibilityAverage( i,  j,  k,  l));
			m1.put("visibilityVariance", visibilityVariance(i,  j,  k,  l));
			jsonObject.put("visibilityStats", m1);	
			
			Map<String, Double> m2 = new LinkedHashMap<String, Double>(4);
			m2.put("speedMax", speedMax( i,  j,  k,  l) );
			m2.put("speedMin", speedMin( i,  j,  k,  l) );
			m2.put("speedAverage", speedAverage( i,  j,  k,  l));
			m2.put("speedVariance", speedVariance( i,  j,  k,  l));
			jsonObject.put("speedStats", m2);
		}
		return jsonObject;
	}
	
}
