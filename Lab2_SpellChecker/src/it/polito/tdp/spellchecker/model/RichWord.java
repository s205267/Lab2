package it.polito.tdp.spellchecker.model;

/**
 * La classe RichWord rappresenta un singolo elemento del
 * Dizionario
 * Semplice Java BEAN (POJO)
 */

public class RichWord {
	public RichWord(String parola) {
		super();
		this.parola = parola;
	}
	private String parola;
	private boolean stato;
	
	
	public String getParola() {
		return parola;
	}
	public void setParola(String parola) {
		this.parola = parola;
	}
	
	public boolean getStato() {
		return stato;
	}
	public void setStato(boolean stato) {
		this.stato = stato;
	}
	@Override
	public String toString() {
		return parola ;
	}
	
}
