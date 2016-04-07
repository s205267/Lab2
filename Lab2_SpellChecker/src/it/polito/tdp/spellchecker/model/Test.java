package it.polito.tdp.spellchecker.model;

import java.util.*;

public class Test {

	public static void main(String[] args) {
		List<String> parole = new ArrayList<String>();
		List<RichWord> risultato = new ArrayList<RichWord>();
		Dictionary d = new ItalianDicionary();
		d.loadDictionary();
		parole.add("ciao");
		parole.add("domani");
		parole.add("cacca");
		parole.add("sjidjsidj");
		risultato= d.spellCheckText(parole);
		System.out.println("Le parole errate sono: \n");

		for(RichWord parola : risultato)
		{
			if(parola.getStato()== true)
			{
				System.out.println(parola.getParola());
			}
		}
		

	}

}
