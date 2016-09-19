package PreProcessData;

import java.util.ArrayList;
import java.util.List;

/**
 * This is for INFSCI 2140 in 2015
 * 
 * TextTokenizer can split a sequence of text into individual word tokens.
 */
public class WordTokenizer {
	private List<String> words = new ArrayList<String>();
	int index = 0;
	//you can add essential private methods or variables
	
	// YOU MUST IMPLEMENT THIS METHOD
	public WordTokenizer( char[] texts ) {
		// this constructor will tokenize the input texts (usually it is a char array for a whole document)
		String str = new String(texts);
		String[] word = str.split("[^\\pL\\pN]");
		for(int i = 0 ; i < word.length; i++){
			if(!word[i].trim().equals("")){
				words.add(word[i]);
			}
		}
		
	}
	
	// YOU MUST IMPLEMENT THIS METHOD
	public char[] nextWord() {
		// read and return the next word of the document
		// or return null if it is the end of the document
		if(words.size()==0){
			return null;
		}else if(index >= words.size()){
			return null;
		}
		else{
			return words.get(index++).toCharArray();
		}
	}
	
}
