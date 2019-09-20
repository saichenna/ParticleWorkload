package Inputs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

public class ScalingData {
	
	private int elementScaling;
	
	private Hashtable<Integer, ArrayList<Integer>> orig2syn;
	
	public ScalingData(int elementScale,String orig2SynElemFileName) throws IOException {
		this.elementScaling = elementScale;
		
		this.orig2syn = new Hashtable<>();
		if(this.elementScaling > 1) {
			var orig2synFile = new BufferedReader(new FileReader(orig2SynElemFileName));
			String line;
			while ((line = orig2synFile.readLine()) != null) {
				 String[] old2synMap = line.split(",");

                 int oldElem = Integer.parseInt(old2synMap[0]);
                 ArrayList<Integer> synelements = new ArrayList<>();
                 for(int i = 1; i < old2synMap.length; i++) {
                         synelements.add(Integer.parseInt(old2synMap[i]));
                 }
                 orig2syn.put(oldElem, synelements);

			}
		    orig2synFile.close();
		}		
	}
	
	public int GetNewRandomElement(int elementId) {
		if(this.elementScaling == 1) {
			return elementId;
		}
		
		else {			
            ArrayList<Integer> value = this.orig2syn.get(elementId);
            int size = value.size();
            int loc = (int)(Math.random()*(size-1));
            return value.get(loc);			
		}		
	}	
}
