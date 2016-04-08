package it.polito.tdp.spellchecker.model;

import java.io.*;

import it.polito.tdp.spellchecker.db.ParolaDAO;

public class ItalianDicionary extends Dictionary {

	@Override
	public void loadDictionary() {
		/*con file di testo
		try {
			FileReader fr = new FileReader("rsc/Italian.txt");
			BufferedReader br = new BufferedReader(fr);
			String word;
			while ((word = br.readLine()) != null) {
			this.addParolaADizionario(word.toLowerCase());
			}
			br.close();
			} catch (IOException e){
			System.out.println("Errore nella lettura del file");
			}
		*/
		//CON DATABASE
		ParolaDAO dao = new ParolaDAO();
		diz.addAll(dao.elencoParole());
		
	}

}
