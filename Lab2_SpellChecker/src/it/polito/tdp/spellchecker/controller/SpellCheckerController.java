package it.polito.tdp.spellchecker.controller;
import java.net.URL;
import java.util.*;

import it.polito.tdp.spellchecker.model.EnglishDictionary;
import it.polito.tdp.spellchecker.model.ItalianDicionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
public class SpellCheckerController {

    @FXML
    private ResourceBundle resources;

    private ItalianDicionary italian;
    private EnglishDictionary inglese;
    
    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cmblingua;

    @FXML
    private TextArea text1;

    @FXML
    private Button bttCorreggi;

    
    @FXML
    private TextFlow text2;
    
    @FXML
    private Button bttClear;

    @FXML
    private Label lblres;

    @FXML
    void doClear(ActionEvent event) {
    	text1.clear();
    	text2.getChildren().clear();
    	lblres.setText("");	 
    	}

    

	@FXML
    void doSpellCheck(ActionEvent event) {
    	text2.getChildren().clear();

		List<String> parole = new ArrayList<String>(); //Lista in cui ci metto le parole inserite dall'utente
		List<RichWord> risultato = new ArrayList<RichWord>(); //Lista in cui ci metto le RichWord gia corrette
		String lingua = cmblingua.getValue();
		long l1=0;

		long l2=0;
		
    	if(text1!= null)
    	{
    		if(lingua!=null)
    		{	
	    		if(lingua.compareTo("Italiano")==0)
	    		{
		    		italian.loadDictionary(); //Carichiamo il dizionario italiano
		    		String inserita = text1.getText();
		    		StringTokenizer st = new StringTokenizer(inserita);
		    		while( st.hasMoreTokens()) 
		    		{
		    			String token = st.nextToken().toLowerCase().trim();
		    			parole.add(token);
		    		}
		    		l1=System.nanoTime();
		    		risultato = italian.spellCheckText(parole); //richiamiamo il metodo che restituisce una lista con le  parole controllate dal dizionario
			    	l2=System.nanoTime();							 //Controlla il tempo finale

		    		aggiornarisultato(risultato,l2-l1); 				//richiamo il metodo che verifica se le parole sono corrette o meno e le stampa nella TextFlow
		    	}
	    	
	    		else if(lingua.compareTo("English")==0)
				{
		    		inglese.loadDictionary();
		    		String inserita = text1.getText();
		    		StringTokenizer st = new StringTokenizer(inserita);
		    		while( st.hasMoreTokens())
		    		{
		    			String token = st.nextToken().toLowerCase().trim();
		    			parole.add(token);
		    		}
		    		l1=System.nanoTime();
		    		risultato = inglese.spellCheckText(parole);
		    		l2=System.nanoTime();

		    		aggiornarisultato(risultato,l2-l1);
		    	}
    		}
	    	else
	    	{
	    		lblres.setText("Selezionare una lingua");
	    		return ;
	    	}

    	}


}
    private void aggiornarisultato(List<RichWord> risultato,double tempo) {
    	for(RichWord r : risultato)
		{
			
			if(r.getStato()== true)
			{
				Text t=new Text();
				t.setText(r.getParola()+" ");
				t.setFill(javafx.scene.paint.Color.RED);
				text2.getChildren().add(t); 
			}
			else{
				Text t=new Text();
				t.setText(r.getParola()+" ");
				t.setFill(javafx.scene.paint.Color.BLACK);
				text2.getChildren().add(t); 
				
			}
		}	
		lblres.setText("Correzione completata in "+tempo/(1e9)+"secondi");

	}

	

	
    
    
    public void setModel( ItalianDicionary ita, EnglishDictionary eng){
    	italian = ita;
    	inglese= eng;
    	}

    @FXML
    void initialize() {
        assert cmblingua != null : "fx:id=\"cmblingua\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert text1 != null : "fx:id=\"text1\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert bttCorreggi != null : "fx:id=\"bttCorreggi\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert bttClear != null : "fx:id=\"bttClear\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert text2 != null : "fx:id=\"text2\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert lblres != null : "fx:id=\"lblres\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        cmblingua.getItems().addAll("English","Italiano");

  
    }
}
