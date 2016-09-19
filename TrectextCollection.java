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
public class TrectextCollection implements DocumentCollection {
	//you can add essential private methods or variables
//	String docNo;
//	Object context; 
	BufferedReader reader;
	
	
	// YOU SHOULD IMPLEMENT THIS METHOD
	public TrectextCollection() throws IOException {
		// This constructor should open the file in Path.DataTextDir
		// and also should make preparation for function nextDocument()
		// you cannot load the whole corpus into memory here!!
		
		/*
		 * 文件是trectext，<DOC></DOC> 文件no在<DOCNO></NOCNO>里，内容在<TEXT></TEXT>里，要先将no和内容取出来，再一行行读取
		 */
        FileInputStream fis = null;
               
        fis = new FileInputStream(Path.DataTextDir);
        reader = new BufferedReader(new InputStreamReader(fis));
     
      
//        String line = reader.readLine();  // read line by line
//         while(line != null){
//            System.out.println(line);
//            line = reader.readLine();
//          }
//        
//          reader.close();
//          fis.close();
		
	}
	
	// YOU SHOULD IMPLEMENT THIS METHOD
	public Map<String, Object> nextDocument() throws IOException {
		// this method should load one document from the corpus, and return this document's number and content.
		// the returned document should never be returned again.
		// when no document left, return null
		// NTT: remember to close the file that you opened, when you do not use it any more
		
		HashMap<String, Object> res = new HashMap<String, Object>();
		String line = reader.readLine(); // read line by line
		StringBuilder text = new StringBuilder();
		boolean isText = false;
		String key = null;
		while((line= reader.readLine()) != null){
			if(line.startsWith("</DOC>")){
				break; //the end of doc
			}
			if(line.startsWith("<DOCNO>") && line.contains("</DOCNO>")){
				key = line.substring(8, line.length()- 9);
				continue;  // go to the next line
			}
			if(line.startsWith("<TEXT>")){
				isText = true;
				continue;   // go to the next line
			}
			if(line.contentEquals("</TEXT>")){
				isText = false;
				continue;
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
		res.put(key, text.toString().toCharArray());
		return res;
		
		
		
	}
	
}
