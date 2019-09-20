package Outputs;

import java.util.Hashtable;

import Utilities.NestedCounterHashtable;

public class StringParticleCount {
    private NestedCounterHashtable<String, String> counts;
	
	public StringParticleCount() {
		this.counts = new NestedCounterHashtable<>();
	}
	
	public int GetParticleCount(String rank, String timeStep) {
		return this.counts.get(rank, timeStep);
	}
	
	public void Insert(String rank, String timeStep) {
		this.counts.Insert(rank, timeStep);
	}
	
	public String toString(int totalranks, String[] timestepList) {
		StringBuilder sb = new StringBuilder();
		
		for (int rid=0; rid < totalranks; rid++) {
			var stringRid = ((Integer)rid).toString();
			if(this.counts.ContainsKey(stringRid)) {
				Hashtable<String, Integer> timeBasedCount = this.counts.get(stringRid);
				for (String ts:timestepList) {
					sb.append(rid+",");
					if(timeBasedCount.containsKey(ts)) {
						sb.append(ts+",");
						sb.append(timeBasedCount.get(ts)+"\n");
					}
					else {
						sb.append(ts+",");
						sb.append(0 +"\n");
					}
				}
			}
			else {
				for (String ts:timestepList) {
					sb.append(rid+",");
					sb.append(ts+",");
					sb.append(0+"\n");
				}
			}
		}
		return sb.toString();
	}
}
