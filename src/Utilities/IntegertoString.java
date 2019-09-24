package Utilities;

import java.util.Hashtable;

public class IntegertoString {
	
	private Hashtable<Integer,String> strs;
	
	public IntegertoString(){
		this.strs = new Hashtable<Integer, String>();
	}
	
	public String GetString(Integer a) {
		if (!this.strs.containsKey(a)) {
			this.strs.put(a,Integer.toString(a));
		}
		return this.strs.get(a);
		
	}
		
		

}
