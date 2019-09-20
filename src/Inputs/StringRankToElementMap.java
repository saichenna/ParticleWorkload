package Inputs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class StringRankToElementMap {
	
	private Hashtable<String, String> elem2rank;
	
	public StringRankToElementMap(String rank2elemMapFileName) throws NumberFormatException, IOException {
		
		BufferedReader rank2elemFile = new BufferedReader(new FileReader(rank2elemMapFileName));
		this.elem2rank = new Hashtable<>();
		String line;
		while ((line = rank2elemFile.readLine()) != null) {
			 String[] rank2elem = line.split(",");

			 String Rank = rank2elem[0];
             for(int i = 1; i < rank2elem.length; i++) {
            	 	 elem2rank.put(rank2elem[i], Rank);
             }

		}
		rank2elemFile.close();	
		
	}
	
	public String GetRank(String elementId) {
		return this.elem2rank.get(elementId);
	}
}
