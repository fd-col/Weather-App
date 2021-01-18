/**
 * 
 */
package it.univpm.progetto.stats;

import it.univpm.progetto.model.Dataset;

/**
 * @author fedju
 *
 */
public class Stats extends Dataset {

	protected int giornoIniziale, giornoFinale;
	/**
	 * costruttore
	 */
	public Stats(String allCityName, boolean flag,boolean flag2, 
					String cityName, int giornoIniziale, int giornoFinale)  {
		
		super(allCityName, flag, flag2, cityName, giornoIniziale, giornoFinale);
		this.setGiornoIniziale(giornoIniziale);
		this.setGiornoFinale(giornoFinale);
	}
	
	
	/**
	 * @return the giornoIniziale
	 */
	public int getGiornoIniziale() {
		return giornoIniziale;
	}
	/**
	 * @param giornoIniziale the giornoIniziale to set
	 */
	public void setGiornoIniziale(int giornoIniziale) {
		this.giornoIniziale = giornoIniziale;
	}
	/**
	 * @return the giornoFinale
	 */
	public int getGiornoFinale() {
		return giornoFinale;
	}
	/**
	 * @param giornoFinale the giornoFinale to set
	 */
	public void setGiornoFinale(int giornoFinale) {
		this.giornoFinale = giornoFinale;
	}
	
	

}
