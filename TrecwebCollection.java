package PreProcessData;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import Classes.Path;

/**
 * This is for INFSCI 2140 in 2015
 *
 */
public class TrecwebCollection implements DocumentCollection {
	//you can add essential private methods or variables
	BufferedReader reader;
	
	
	// YOU SHOULD IMPLEMENT THIS METHOD
	public TrecwebCollection() throws IOException {
		// This constructor should open the file in Path.DataWebDir
		// and also should make preparation for function nextDocument()
		// you cannot load the whole corpus into memory here!!
		FileInputStream fis = null;
        
        fis = new FileInputStream(Path.DataWebDir);
        reader = new BufferedReader(new InputStreamReader(fis));
	}
	
	// YOU SHOULD IMPLEMENT THIS METHOD
	public Map<String, Object> nextDocument() throws IOException {
		// this method should load one document from the corpus, and return this document's number and content.
		// the returned document should never be returned again.
		// when no document left, return null
		// NT: the returned content of the document should be cleaned, all html tags should be removed.
		// NTT: remember to close the file that you opened, when you do not use it any more
		
		HashMap<String, Object> res = new HashMap<String, Object>();
		String line = reader.readLine(); // read line by line
		StringBuilder text = new StringBuilder();
		boolean isText = false;
		String key = null;
		while((line = reader.readLine())!= null){
			if(line.startsWith("</DOC>")){
				isText = false;
				break; //the end of doc
			}
			if(line.startsWith("<DOCNO>") && line.contains("</DOCNO>")){
				key = line.substring(7, line.length()- 8);
				continue;  // go to the next line
			}
			if(line.startsWith("</DOCHDR>")){
				isText = true;
				continue;   // go to the next line
			}
			if(!isText){
				//line = reader.readLine();
				continue;
			}
			if(isText){
				text.append(line);//add text content into stringbuilder
				//line = reader.readLine();
			}
			
		}
		if(line == null) return null;
		res.put(key, text.toString().replaceAll("<[^>]+>", "").toCharArray());
		return res;
		
		
		
	}
	
}
