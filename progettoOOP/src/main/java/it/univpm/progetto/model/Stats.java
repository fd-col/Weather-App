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

	private Long visibilityAverage;
	private Double speedAverage;
	private Double visibilityVariance;
	private Double speedVariance;
	
	
	public Stats(String cityName1, String cityName2, String cityName3, boolean flag) {
		super(cityName1, cityName2, cityName3, flag);
		}


	/**
	 * @param visibilityAverage the visibilityAverage to set
	 */
	public void setVisibilityAverage(int i, int j, int k, int l) throws Exception {
		if(i != j) 
			throw new Exception("Errore di inserimento parametri");
		else
		{
		JSONArray jsonArray = getDatiStorici(i,j);
		Long sum = null;
		for(int m = k; m <= l; m++) {
			JSONObject jObject= (JSONObject) jsonArray.get(m);
			 sum += (Long) jObject.get("visibility");
		}
		
		visibilityAverage = sum/(l-k);
		
		}
	}


	/**
	 * @param speedAverage the speedAverage to set
	 */
	public void setSpeedAverage(Double speedAverage) {
		this.speedAverage = speedAverage;
	}


	/**
	 * @param visibilitiVariance the visibilitiVariance to set
	 */
	public void setVisibilytiVariance(Double visibilitiVariance) {
		this.visibilityVariance = visibilitiVariance;
	}



	/**
	 * @param speedVariance the speedVariance to set
	 */
	public void setSpeedVariance(Double speedVariance) {
		this.speedVariance = speedVariance;
	}
	
	
}
