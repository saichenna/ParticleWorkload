package Inputs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class RankToElementMap {
	
	private Hashtable<Integer, Integer> elem2rank;
	
	public RankToElementMap(String rank2elemMapFileName) throws NumberFormatException, IOException {
		
		BufferedReader rank2elemFile = new BufferedReader(new FileReader(rank2elemMapFileName));
		this.elem2rank = new Hashtable<>();
		String line;
		while ((line = rank2elemFile.readLine()) != null) {
			 String[] rank2elem = line.split(",");

             int Rank = Integer.parseInt(rank2elem[0]);
             for(int i = 1; i < rank2elem.length; i++) {
            	 	 elem2rank.put(Integer.parseInt(rank2elem[i]),Rank);
             }

		}
		rank2elemFile.close();	
		
	}
	
	public int GetRank(int elementId) {
		return this.elem2rank.get(elementId);
	}
}
