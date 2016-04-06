package it.polito.tdp.spellchecker.model;

import java.util.*;

public class Dictionary {
	
	private List<String> diz = new LinkedList <String>();
	
	public void loadDictionary(){}
	
	public List<RichWord> spellCheckText(List<String> inputTextList)
	{
		List<RichWord> risultato = new LinkedList<RichWord>();
		
		for(String parola:inputTextList)
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
