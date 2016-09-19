package PreProcessData;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import Classes.Path;

public class StopWordRemover {
	//you can add essential private methods or variables
	
	FileInputStream fis=null;
	BufferedReader reader=null;
	private static HashMap<String, Object> stopWords = new HashMap<>();
	
	public StopWordRemover( ) {
		// load and store the stop words from the fileinputstream with appropriate data structure
		// that you believe is suitable for matching stop words.
		// address of stopword.txt should be Path.StopwordDir
		try(BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(Path.StopwordDir)))){
			String line = reader.readLine();
			while(line!=null){
				stopWords.put(line, "");
				line=reader.readLine();
			}
			
			reader.close();

		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		
	}
	
	// YOU MUST IMPLEMENT THIS METHOD
	public boolean isStopword( char[] word ) {
		// return true if the input word is a stopword, or false if not
		return stopWords.containsKey(new String(word));
	}
}
