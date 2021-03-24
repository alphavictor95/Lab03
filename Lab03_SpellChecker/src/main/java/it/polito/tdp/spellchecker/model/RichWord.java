package it.polito.tdp.spellchecker.model;

public class RichWord {

	String word;
	Boolean stato;
	public RichWord(String word, boolean stato) {
		this.stato=stato;
		this.word=word;
		
	}
	public String getWord() {
		return word;
	}
	public Boolean getStato() {
		return stato;
	}

	
	
}
