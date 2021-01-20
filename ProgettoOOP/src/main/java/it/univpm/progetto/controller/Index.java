/**
 * 
 */
package it.univpm.progetto.controller;

/**
 * la classe contiene i parametri passati nel body JSON di una chiamata di tipo HTTP
 * (i valori di default permettono di prendere, ripsettivamente alla chiamata, 
 * tutto il contenuto nel dataset)
 * @author colleluori
 * @author camplese
 */
public class Index {	
	public int primaCitta = 1;
	public int ultimaCitta = 3;
	public int giornoIniziale = 1;
	public int giornoFinale = 5;
	public double soglia_errore = 50;
}

