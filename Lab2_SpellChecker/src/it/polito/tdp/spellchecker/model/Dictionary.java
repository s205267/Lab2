package it.polito.tdp.spellchecker.model;

import java.util.*;

public class Dictionary {
	
	private Set<String> diz = new HashSet <String>();
	
	public void loadDictionary(){}
	
	public List<RichWord> spellCheckText(List<String> parole)
	{
		List<RichWord> risultato = new ArrayList<RichWord>();
		
		for(String parola:parole)
		{
			RichWord temp = new RichWord(parola);
			
			if(diz.contains(parola))
			{
				//Parola corretta
				temp.setStato(false);	
			}
			else
			{
				//Parola errata
				temp.setStato(true);
			}
			risultato.add(temp);
			
		}
		
		return risultato;
	}
	
	public void addParolaADizionario(String parola){
		diz.add(parola);
	}

}
