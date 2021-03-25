package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;



import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {

	private Dictionary model;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> boxLingua;

    @FXML
    private TextArea txtInserita;

    @FXML
    private Button btnCheck;

    @FXML
    private TextArea txtWrong;

    @FXML
    private Label lblError;

    @FXML
    private Button btnClear;

    @FXML
    private Label lblTime;

    @FXML
    void doClearText(ActionEvent event) {

    	txtInserita.clear();
    	txtWrong.clear();
    	lblError.setText(null);
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	
		

    	
    	int contatoreerrori=0;
    	
    	String lingua = boxLingua.getValue();
    		model.loadDictionary(lingua);
    		String inserimento=txtInserita.getText();
    		inserimento.replace("\n", "");
    		
    			List<String> listWord = new ArrayList<>();
    			String s2 = inserimento.replaceAll("\n[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
    			String[] sarray = s2.split(" ");
    			for(String i : sarray) {
    				listWord.add(i);
    		}
    		List<RichWord> sct=model.spellCheckText(listWord);
    		 long startTime = System.nanoTime();
    		 // ... the code being measured ...
    		
    		for(RichWord r : sct) {
    			if(r.getStato()==false) {
    				txtWrong.appendText(r.getWord()+" ");
    				
					contatoreerrori++;
    			}
    		}
    		long elapsedNanos = System.nanoTime() - startTime;
    		lblError.setText("The text contains: "+contatoreerrori+" errors ");
    		lblTime.setText("Spell Check completed in: "+elapsedNanos+" nanoseconds");
    	
    		txtInserita.clear();
    }

    @FXML
    void initialize() {
        assert boxLingua != null : "fx:id=\"boxLingua\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtInserita != null : "fx:id=\"txtInserita\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCheck != null : "fx:id=\"btnCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtWrong != null : "fx:id=\"txtWrong\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblError != null : "fx:id=\"lblError\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblTime != null : "fx:id=\"lblTime\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    public void setModel(Dictionary model) {
    	this.model=model;
    	boxLingua.getItems().addAll("English","Italian");
    }
}

