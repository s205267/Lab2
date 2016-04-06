package it.polito.tdp.spellchecker.controller;
import java.awt.Color;
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
		List<String> parole = new LinkedList<String>();
		List<RichWord> risultato = new LinkedList<RichWord>();
		String visualizza = "";
		String lingua = cmblingua.getValue();
		long l1=System.nanoTime();
		long l2=0;
		
    	if(text1!= null)
    	{
    		if(lingua!=null)
    		{	
	    		if(lingua.compareTo("Italiano")==0)
	    		{
		    		italian.loadDictionary();
		    		String inserita = text1.getText();
		    		StringTokenizer st = new StringTokenizer(inserita);
		    		while( st.hasMoreTokens())
		    		{
		    			String token = st.nextToken().toLowerCase().trim();
		    			parole.add(token);
		    		}
		    		
		    		risultato = italian.spellCheckText(parole);
		    		aggiungiParola(risultato);
		    	l2=System.nanoTime();
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
		    		
		    		risultato = inglese.spellCheckText(parole);
		    		aggiungiParola(risultato);
		    		l2=System.nanoTime();
		    	}
    		}
	    	else
	    	{
	    		lblres.setText("Selezionare una lingua");
	    		return ;
	    	}
    		lblres.setText("Correzione completata in "+(l2-l1)/(1e9)+"secondi");

    	}


}
    private void aggiungiParola(List<RichWord> risultato) {
    	for(RichWord r : risultato)
		{
			
			if(r.getStato()== true)
			{
				Text casella = new Text(r.getParola()+" ");
				casella.setFill(javafx.scene.paint.Color.RED);
				text2.getChildren().add(casella);
				System.out.println(r.toString());
			}
			else{
				Text casella = new Text(r.getParola()+" ");
				casella.setFill(javafx.scene.paint.Color.BLACK);

		    	text2.getChildren().add(casella);
				
			}
		}		
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
  
    }
}
