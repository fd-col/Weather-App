/**
 * 
 */
package it.univpm.progetto.model;

import java.security.InvalidParameterException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
/**
 * @author falco
 *
 */
public class Stats extends Dataset {
	
	/**
	 * costruttore che chiama il costruttore della superclasse con i parametri inseriti
	 * @param cityName1
	 * @param cityName2
	 * @param cityName3
	 * @param flag
	 */
	public Stats(String cityName1, String cityName2, String cityName3, boolean flag) {
		super(cityName1, cityName2, cityName3, flag);
	}

	/**
	 * calcola la minima visibilità tra i giorni passati come parametri
	 * @param i
	 * @param j
	 * @param k
	 * @param l
	 * @return the visibilityMin
	 */
	public Long visibilityMin(int i, int j, int k, int l) {
		JSONArray jsonArray = getDatiStorici(i,j);
		JSONObject jObject= (JSONObject) jsonArray.get(k);
		Long visibilityMin = (Long) jObject.get("visibility");
		for(int m = k-1; m < l; m++) {
			JSONObject jObj= (JSONObject) jsonArray.get(m);
			if(visibilityMin > (Long) jObj.get("visibility")) {
				visibilityMin = (Long) jObj.get("visibility");
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
	public Long visibilityMax(int i, int j, int k, int l) {
		JSONArray jsonArray = getDatiStorici(i,j);
		JSONObject jObject= (JSONObject) jsonArray.get(k);
		Long visibilityMax = (Long) jObject.get("visibility");
		for(int m = k-1; m < l; m++) {
			JSONObject jObj= (JSONObject) jsonArray.get(m);
			if(visibilityMax < (Long) jObj.get("visibility")) {
				visibilityMax = (Long) jObj.get("visibility");
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
	public Double speedMin(int i, int j, int k, int l) {
		JSONArray jsonArray = getDatiStorici(i,j);
		JSONObject jObject= (JSONObject) jsonArray.get(k);
		JSONObject wind1 = (JSONObject) jObject.get("wind");
		Double speedMin = (Double) Double.parseDouble(wind1.get("speed").toString());
		for(int m = k-1; m < l; m++) {
			JSONObject jObj= (JSONObject) jsonArray.get(m);
			JSONObject wind2 = (JSONObject) jObj.get("wind");
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
	public Double speedMax(int i, int j, int k, int l) {
		JSONArray jsonArray = getDatiStorici(i,j);
		JSONObject jObject= (JSONObject) jsonArray.get(k);
		JSONObject wind1 = (JSONObject) jObject.get("wind");
		Double speedMax = (Double) Double.parseDouble(wind1.get("speed").toString());
		for(int m = k-1; m < l; m++) {
			JSONObject jObj= (JSONObject) jsonArray.get(m);
			JSONObject wind2 = (JSONObject) jObj.get("wind");
			if(speedMax < (Double) Double.parseDouble(wind2.get("speed").toString())) {
				speedMax = (Double) Double.parseDouble(wind2.get("speed").toString());
			}
		}
		return speedMax;
	}
	
	/**
	 * calcola la visibilità media tra i giorni passati come parametri
	 * @param visibilityAverage the visibilityAverage to set
	 * @return visibilityAverage
	 */
	public Double visibilityAverage(int i, int j, int k, int l)  {
		
		Double sum = (double) 0;
		for(int m = k-1; m < l; m++) {
			JSONObject jObject= (JSONObject) getDatiStorici(i,j).get(m);
			 sum += (Long) jObject.get("visibility");
		}
		Double visibilityAverage = (Double) sum/(l-k);
		return visibilityAverage;
	}

	/**
	 * calcola la velocità media del vento tra i giorni passati come parametri
	 * @param speedAverage the speedAverage to set
	 * @return speedAverage
	 */
	public Double speedAverage(int i, int j, int k, int l) {
		
		
		Double sum = (double) 0;
		for(int m = k-1; m < l; m++) {
			JSONObject jObject= (JSONObject) getDatiStorici(i,j).get(m);
			JSONObject wind = (JSONObject) jObject.get("wind");
			Double rawSpeed = (Double) Double.parseDouble(wind.get("speed").toString());
			sum += (Double) rawSpeed;
		}
		Double speedAverage = sum/(l-k);
		return speedAverage;
		
	}

	/**
	 * calcola la varianza della visibilità tra i giorni passati come parametri
	 * @param visibilitiVariance the visibilitiVariance to set
	 * @return 
	 * @throws Exception 
	 */
	public Double visibilityVariance(int i, int j, int k, int l) {
		Double media = visibilityAverage(i,j,k,l);
		Double sommaScartiQuad = (double) 0;
		for(int m = k-1; m < l; m++) {
			JSONObject jObject= (JSONObject) getDatiStorici(i,j).get(m);
			sommaScartiQuad += ((Long) jObject.get("visibility") - media)
								*((Long) jObject.get("visibility") - media);
		}
		Double visibilityVariance = sommaScartiQuad/(l-k);
		return visibilityVariance;
	}

	/**
	 * calcola la varianza della velocità del vento tra i giorni passati come parametri
	 * @param speedVariance the speedVariance to set
	 * @throws Exception 
	 */
	public Double speedVariance(int i, int j, int k, int l)  {
		Double media = speedAverage(i,j,k,l);
		Double sommaScartiQuad = (double) 0;
		for(int m = k-1; m < l; m++) {
			JSONObject jObject= (JSONObject) getDatiStorici(i,j).get(m);
			JSONObject wind = (JSONObject) jObject.get("wind");
			Double rawSpeed = (Double) Double.parseDouble(wind.get("speed").toString());
			sommaScartiQuad += (rawSpeed - media)*(rawSpeed - media);
		}
		Double speedVariance = sommaScartiQuad/(l-k);
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
		JSONObject jsonObj = new JSONObject();
		if(i != j) 
			throw new InvalidParameterException("Errore di inserimento parametri: "
										+ " \'primaCitta\' deve essere uguale a \'ultimaCitta\' ");
		else {
			try {
				jsonObj.put("visibilityMax", visibilityMax( i,  j,  k,  l) );
				jsonObj.put("visibilityMin", visibilityMin( i,  j,  k,  l) );
				jsonObj.put("visibilityAverage", visibilityAverage( i,  j,  k,  l));
				jsonObj.put("visibilityVariance", visibilityVariance(i,  j,  k,  l));
				
				jsonObj.put("speedMax", speedMax( i,  j,  k,  l) );
				jsonObj.put("speedMin", speedMin( i,  j,  k,  l) );
				jsonObj.put("speedAverage", speedAverage( i,  j,  k,  l));
				jsonObj.put("speedVariance", speedVariance( i,  j,  k,  l));
			}catch(InvalidParameterException e) {
				e.printStackTrace();
			}
		}
		return jsonObj;
	}
	
}
