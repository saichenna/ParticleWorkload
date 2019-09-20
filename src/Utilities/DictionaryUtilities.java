package Utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import Entities.BinModule;

public class DictionaryUtilities<TKey, TValue> {
	
	private Hashtable<BinModule,List<Integer>> dict;
//	private BinModule key ;
//	private List<Integer> valuelist;
	
	
	
	public DictionaryUtilities() {
		this.dict = new Hashtable<BinModule,List<Integer>>();
		
	}
	
	public void AddTo(BinModule key, int value)
	{
		List<Integer> lst = new ArrayList<Integer>();
		if (this.dict.containsKey(key)) {
			lst = this.dict.get(key);
			lst.add(value);

		}
		else {
			lst = new ArrayList<Integer>(value);
		}
		/*
		 * if(key.i == 169 && key.j == 0 && key.k == 2) {
		 * System.out.println("Found key!"); }
		 */
		this.dict.put(key,lst);		
	}
	
	public List<Integer> Get(BinModule key){
		List<Integer> lst = new ArrayList<Integer>();
		lst = this.dict.get(key);
		/*
		 * System.out.println(lst); System.out.println(this.ContainsKey(key));
		 */
		return this.dict.get(key);
	}
	
	public boolean ContainsKey(BinModule key) {
		return this.dict.containsKey(key);
	}
}
