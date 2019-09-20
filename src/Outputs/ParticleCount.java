package Outputs;

import java.util.Hashtable;

import Utilities.NestedCounterHashtable;

public class ParticleCount {
	
	private NestedCounterHashtable<Integer, Integer> counts;
	
	public ParticleCount() {
		this.counts = new NestedCounterHashtable<>();
	}
	
	public int GetParticleCount(int rank, int timeStep) {
		return 1;
	}
	
	public void Insert(int rank, int timeStep) {
		this.counts.Insert(rank, timeStep);
	}
	
	public String toString(int totalranks, int[] timestepList) {
		StringBuilder sb = new StringBuilder();
		
		for (int rid=0; rid < totalranks; rid++) {
			if(this.counts.ContainsKey(rid)) {
				Hashtable<Integer, Integer> timeBasedCount = this.counts.get(rid);
				for (int ts:timestepList) {
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
				for (int ts:timestepList) {
					sb.append(rid+",");
					sb.append(ts+",");
					sb.append(0+"\n");
				}
			}
		}
		return sb.toString();
	}
}