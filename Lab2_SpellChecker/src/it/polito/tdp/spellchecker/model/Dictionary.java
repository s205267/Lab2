package it.polito.tdp.spellchecker.model;

import java.util.*;

import it.polito.tdp.spellchecker.db.ParolaDAO;

public class Dictionary {
	
	protected List<String> diz = new ArrayList <String>();
	
	public void loadDictionary(){}
	
	public List<RichWord> spellCheckText(List<String> parole)
	{
		List<RichWord> risultato = new ArrayList<RichWord>();
		
		for(String parola:parole)
		{
			RichWord temp = new RichWord(parola);
			/* con ricerca dicotomica
			if(containsDicotomico(parola,0,diz.size()-1)){
				risultato.add(temp);
				temp.setStato(false);
			}else{
				risultato.add(temp);
				temp.setStato(true);
			}
			*/
			//DIRETTAMENTE SUL DATABASE 
			ParolaDAO dao = new ParolaDAO();
			if(dao.contains(parola))
			{
				risultato.add(temp);
				temp.setStato(false);
			}
			else
			{
				risultato.add(temp);
				temp.setStato(true);
			}
			
		}
		
		return risultato;
	}
	
	public void addParolaADizionario(String parola){
		diz.add(parola);
	}
	
	public boolean containsDicotomico(String parola,int a,int b){
		if(a<=b){
			if(parola.equals(diz.get((a+b)/2)))
				return true;
			if(parola.compareTo(diz.get((a+b)/2))>0)
				return containsDicotomico(parola,1+(a+b)/2,b);
			else
				return containsDicotomico(parola,a,(a+b)/2-1);
		}
		return false;
	}

}
