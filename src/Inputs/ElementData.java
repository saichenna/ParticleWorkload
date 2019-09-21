package Inputs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import Entities.BinCalculationUtility;
import Entities.BinModule;
import Entities.Location;
import Utilities.DictionaryUtilities;

import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;

public class ElementData {
	
	private Hashtable<Integer, Integer> elem2rank;
	
	private Hashtable<Integer, List<Double> > elem2corner;
	
	private BinCalculationUtility utility ;
	
//	private Hashtable<BinModule, List<Integer>> Bin2Elem;
	
//	private Hashtable<BinModule, List<Integer>> Bin2Rank;
	
	private DictionaryUtilities<BinModule, List<Integer>> Bin2Elem;
	
	private DictionaryUtilities<BinModule, List<Integer>> Bin2Rank;

	

	public ElementData(String ElemMapFileName, BinCalculationUtility utility) throws NumberFormatException, IOException {
	
		BufferedReader elemFile = new BufferedReader(new FileReader(ElemMapFileName));
		this.elem2rank = new Hashtable<>();
		this.elem2corner = new Hashtable<>();
		this.Bin2Elem = new DictionaryUtilities();
		this.Bin2Rank = new DictionaryUtilities();
		this.utility = utility;
		String line;
		while ((line = elemFile.readLine()) != null) {
			String[] rank2elem = line.split(",");

			this.elem2rank.put(Integer.parseInt(rank2elem[0]),Integer.parseInt(rank2elem[1]));
			this.elem2corner.put(Integer.parseInt(rank2elem[0]),Arrays.asList(Double.parseDouble(rank2elem[3]),Double.parseDouble(rank2elem[2]),Double.parseDouble(rank2elem[5]),Double.parseDouble(rank2elem[4]),Double.parseDouble(rank2elem[7]),Double.parseDouble(rank2elem[6])));
		}
		elemFile.close();	
		
		List<Integer> elem2rankkeys = Collections.list(this.elem2rank.keys());
		
		Collections.sort(elem2rankkeys);
		
		for (int element: elem2rankkeys) {
			int rank = this.elem2rank.get(element);
			double imin = 1E4;
			double jmin = 1E4;
			double kmin = 1E4;
			double imax = -1E4;
			double jmax = -1E4;
			double kmax = -1E4;
			List<Double> domain = this.elem2corner.get(element);
			Location first = new Location(domain.get(0),domain.get(2),domain.get(4));
			Location second = new Location(domain.get(0),domain.get(2),domain.get(5));
			Location third = new Location(domain.get(0),domain.get(3),domain.get(4));
			Location fourth = new Location(domain.get(0),domain.get(3),domain.get(5));
			Location fifth = new Location(domain.get(1),domain.get(2),domain.get(4));
			Location sixth = new Location(domain.get(1),domain.get(2),domain.get(5));
			Location seventh = new Location(domain.get(1),domain.get(3),domain.get(4));
			Location eigth = new Location(domain.get(1),domain.get(3),domain.get(5));
			
			/*
			 * if(element == 1088) {
			 * System.out.println("1088"+","+first.x+","+first.y+","+first.z);
			 * System.out.println("1088"+","+second.x+","+second.y+","+second.z);
			 * System.out.println("1088"+","+third.x+","+third.y+","+third.z);
			 * System.out.println("1088"+","+fourth.x+","+fourth.y+","+fourth.z);
			 * System.out.println("1088"+","+fifth.x+","+fifth.y+","+fifth.z);
			 * System.out.println("1088"+","+sixth.x+","+sixth.y+","+sixth.z);
			 * System.out.println("1088"+","+seventh.x+","+seventh.y+","+seventh.z);
			 * System.out.println("1088"+","+eigth.x+","+eigth.y+","+eigth.z); }
			 */
			
			List<Location> corners = Arrays.asList(first,second,third,fourth,fifth,sixth,seventh,eigth);
			
			for (Location corner: corners){
				/*
				 * if(element == 1088) {
				 * System.out.println("1088"+","+corner.x+","+corner.y+","+corner.z); }
				 */
				BinModule bin = this.utility.GetBin(corner);
				imin = Math.min(imin, bin.i);
				jmin = Math.min(jmin, bin.j);
				kmin = Math.min(kmin, bin.k);
				imax = Math.max(imax, bin.i);
				jmax = Math.max(jmax, bin.j);
				kmax = Math.max(kmax, bin.k);
				
//				BinModule test = new BinModule(169,0,2); // debug purposes only

				for (int i = (int) imin; i < (int)imax+1; i++ ) {
					for (int j = (int)jmin; j < (int)jmax+1; j++) {
						for (int k = (int) kmin; k < (int)kmax+1; k++) {
							
							BinModule currentBin = new BinModule(i,j,k);
							
							/*
							 * if (test.i == i && test.j == j && test.k == k) {
							 * System.out.println("First: FOund it!!!"); }
							 */
							
							/*
							 * if (test == currentBin) { System.out.println("Found it!!"); }
							 */
							this.Bin2Elem.AddTo(currentBin,element);
							this.Bin2Rank.AddTo(currentBin,rank);
							
								
						}
					}
				}
//				System.out.println(corner.x+","+corner.y+","+corner.z);
//				System.out.println(imin+","+imax+","+jmin+","+jmax+","+kmin+","+kmax);				
				
			}
			
			/*
			 * if (element == 1088) {
			 * 
			 * System.out.println(1088+","+imin+","+imax+","+jmin+","+jmax+","+kmin+","+kmax
			 * ); }
			 */
		}
		
	
	}

	public Integer GetRankFromElem(Integer elementId) {
	return this.elem2rank.get(elementId);
}
	
	public List<Integer> GetRankFromBin(BinModule bin) {
	return this.Bin2Rank.Get(bin);
}

	public List<Integer> GetElementFromBin(BinModule bin) {
//		System.out.println(this.Bin2Elem.Get(bin));
		return this.Bin2Elem.Get(bin);
}	
	
	
	public boolean Contains(BinModule bin) {
		return this.Bin2Rank.ContainsKey(bin);
	}
	
	
	public List<Double> GetCorners(Integer elementId) {
		return this.elem2corner.get(elementId);
	}
	
	public List<Integer> GetKeys(String name) {
		
		List<Integer> value= Arrays.asList(0);
		if(name == "elem2rank") {
			value = Collections.list(this.elem2rank.keys());
			Collections.sort(value);
		}
		if (name == "elem2corner") {
			value = Collections.list(this.elem2corner.keys());
			Collections.sort(value);
		}
		return value;
	}
}
