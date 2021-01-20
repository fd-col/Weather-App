/**
 * 
 */
package it.univpm.progetto.stats;

import it.univpm.progetto.model.Dataset;

/**
 * @author colleluori
 * @author camplese
 *
 */
public class Stats extends Dataset {
	
	protected int giornoIniziale, giornoFinale;
	protected int numeroGiorni;
	/**
	 * costruttore che chiama il costruttore della superclasse e inoltre inizializza degli attributi
	 * @param allCityName nomi delle città contenute nel dataset
	 * @param flag1 true per i dati attuali, false per le previsioni future oppure i dati storici
	 * @param flag2 true per le previsioni future, false per i dati storici
	 * @param primaCitta variabile intera corrispondente all'indice della prima citta'
	 * @param ultimaCitta variabile intera corrispondente all'indice dell'ultima citta'
	 * @param giornoIniziale primo giorno di cui si vogliono i dati meteo
	 * @param giornoFinale ultimo giorno di cui si vogliono i dati meteo
	 */
	public Stats(String allCityName, boolean flag1 ,boolean flag2, int primaCitta, int ultimaCitta,
																int giornoIniziale, int giornoFinale)  {
		
		super(allCityName, flag1, flag2, primaCitta, ultimaCitta, giornoIniziale, giornoFinale);
		this.setGiornoIniziale(giornoIniziale);
		this.setGiornoFinale(giornoFinale);
		this.numeroGiorni = getGiornoFinale() - getGiornoIniziale() + 1;
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
