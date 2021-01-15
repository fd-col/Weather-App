/**
 * 
 */
package it.univpm.progetto.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
/**
 * @author falco
 *
 */
public class Stats extends Dataset{

	private Double visibilityAverage;
	private Double speedAverage;
	private Double visibilityVariance;
	private Double speedVariance;
	
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
	 * @param visibilityAverage the visibilityAverage to set
	 * @return 
	 */
	public Double visibilityAverage(int i, int j, int k, int l) throws Exception {
		if(i != j) 
			throw new Exception("Errore di inserimento parametri");
		else
		{
		JSONArray jsonArray = getDatiStorici(i,j);
		Double sum = (double) 0;
		for(int m = k; m <= l; m++) {
			JSONObject jObject= (JSONObject) jsonArray.get(m);
			 sum += (Long) jObject.get("visibility");
		}
		visibilityAverage = (Double) sum/(l-k);
		return visibilityAverage;
		}
	}

	/**
	 * @param speedAverage the speedAverage to set
	 */
	public Double speedAverage(int i, int j, int k, int l) throws Exception {
		if(i != j) 
			throw new Exception("Errore di inserimento parametri");
		else
		{
		Double sum = (double) 0;
		for(int m = k; m <= l; m++) {
			JSONObject jObject= (JSONObject) getDatiStorici(i,j).get(m);
			JSONObject wind = (JSONObject) jObject.get("wind");
			Double rawSpeed = (Double) Double.parseDouble(wind.get("speed").toString());
			sum += (Double) rawSpeed;
		}
		speedAverage = sum/(l-k);
		return speedAverage;
		}
	}

	/**
	 * @param visibilitiVariance the visibilitiVariance to set
	 * @return 
	 * @throws Exception 
	 */
	public Double visibilityVariance(int i, int j, int k, int l) throws Exception {
		Double media = visibilityAverage(i,j,k,l);
		Double sommaScartiQuad = (double) 0;
		for(int m = k; m <= l; m++) {
			JSONObject jObject= (JSONObject) getDatiStorici(i,j).get(m);
			sommaScartiQuad += ((Long) jObject.get("visibility") - media)
								*((Long) jObject.get("visibility") - media);
		}
		visibilityVariance = sommaScartiQuad/(l-k);
		return visibilityVariance;
	}

	/**
	 * @param speedVariance the speedVariance to set
	 * @throws Exception 
	 */
	public Double speedVariance(int i, int j, int k, int l) throws Exception {
		Double media = speedAverage(i,j,k,l);
		Double sommaScartiQuad = (double) 0;
		for(int m = k; m <= l; m++) {
			JSONObject jObject= (JSONObject) getDatiStorici(i,j).get(m);
			JSONObject wind = (JSONObject) jObject.get("wind");
			Double rawSpeed = (Double) Double.parseDouble(wind.get("speed").toString());
			sommaScartiQuad += (rawSpeed - media)*(rawSpeed - media);
		}
		speedVariance = sommaScartiQuad/(l-k);
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
	public JSONObject formatter(int i, int j, int k, int l) {
		JSONObject jsonObj = new JSONObject();
		//jsonObj.put("", );
		//jsonObj.put("", );
		try {
			jsonObj.put("visibilityAverage", visibilityAverage( i,  j,  k,  l));
			jsonObj.put("speedAverage", speedAverage( i,  j,  k,  l));
			jsonObj.put("visibilityVariance", visibilityVariance(i,  j,  k,  l));
			jsonObj.put("speedVariance", speedVariance( i,  j,  k,  l));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return jsonObj;
	}
	
}
