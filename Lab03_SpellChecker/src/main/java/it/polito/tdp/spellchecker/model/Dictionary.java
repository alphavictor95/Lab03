package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class Dictionary {

	LinkedHashSet<String> ENDictionary = new LinkedHashSet<>();
	LinkedHashSet<String> ITDictionary = new LinkedHashSet<>();
	
	public void loadDictionary(String Language) {
		if(Language.equals("English")) {
			try {
				FileReader fr = new FileReader("src/main/resources/English.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while ((word = br.readLine()) != null) {
					//RichWord w= new RichWord(word, true);
			    ENDictionary.add(word);
				}
				br.close();
				} catch (IOException e){
				System.out.println("Errore nella lettura del file");
				}
				
		}
		else {
			try {
			FileReader fr = new FileReader("src/main/resources/Italian.txt");
			BufferedReader br = new BufferedReader(fr);
			String word;
			while ((word = br.readLine()) != null) {
				//RichWord w= new RichWord(word, true);
		        ITDictionary.add(word);
			}
			br.close();
			} catch (IOException e){
			System.out.println("Errore nella lettura del file");
			}
		}
	}
	
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		List<RichWord> sct = new ArrayList<RichWord>();
		for(Object w :  inputTextList.toArray()) {
			if(ENDictionary.contains((String)w) || ITDictionary.contains((String)w)) {
				RichWord temp = new RichWord((String)w, true);
				sct.add(temp);
			}
			else {
				RichWord temp = new RichWord((String)w, false);
				sct.add(temp);
			}
		}
		return sct;
	}
	//public List<RichWord> spellCheckTextLinear(List<String> inputTextList){}
//	public List<RichWord> spellCheckTextDichotomic(List<String> inputTextList){}
}
