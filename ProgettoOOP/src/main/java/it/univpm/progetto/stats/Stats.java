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
	protected int numeroGiorni;
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
	public Stats(String allCityName, boolean flag1 ,boolean flag2, int primaCitta, int ultimaCitta,
																int giornoIniziale, int giornoFinale)  {
		
		super(allCityName, flag1, flag2, primaCitta, ultimaCitta, giornoIniziale, giornoFinale);
		this.setGiornoIniziale(giornoIniziale);
		this.setGiornoFinale(giornoFinale);
		this.numeroGiorni = getGiornoFinale() - getGiornoIniziale();
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

	/**
	 * @return the numeroGiorni
	 */
	public int getNumeroGiorni() {
		return numeroGiorni;
	}

	/**
	 * @param numeroGiorni the numeroGiorni to set
	 */
	public void setNumeroGiorni(int numeroGiorni) {
		this.numeroGiorni = numeroGiorni;
	}

}
