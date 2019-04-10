package Utilities;

import java.util.Hashtable;

public class NestedCounterHashtable<TKey1, TKey2> {
	
	private Hashtable<TKey1, Hashtable<TKey2, Integer>> map;
	
	public NestedCounterHashtable(){
		this.map = new Hashtable<>();
	}
	
	public void Insert(TKey1 key1, TKey2 key2) {		
		if(this.map.containsKey(key1)) {
			Hashtable<TKey2, Integer> newMap = this.map.get(key1);
			newMap.put(key2, 
					   newMap.containsKey(key2)
							   ? newMap.get(key2) + 1 
							   : 1);
			this.map.put(key1, newMap);
		}
		else {
			Hashtable<TKey2, Integer> newMap = new Hashtable<>();
			newMap.put(key2, 1);
			this.map.put(key1, newMap);
		}
	}
	
	public boolean ContainsKey(TKey1 key1) {
		return this.map.containsKey(key1);
	}
	
	public boolean ContainsKey(TKey1 key1, TKey2 key2) {
		return !this.map.containsKey(key1) ? false : this.map.get(key1).containsKey(key2);
	}
}
