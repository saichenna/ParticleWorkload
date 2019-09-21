package Outputs;

import java.util.Hashtable;

import Entities.Particle;
import Utilities.NestedCounterHashtable;

public class StringParticleMovement {
	private Hashtable<Particle, String> oldParticlePosition;
	
	private NestedCounterHashtable<RankDiff, Integer> output;
	
	public StringParticleMovement() {
		this.oldParticlePosition = new Hashtable<>();
		this.output = new NestedCounterHashtable<>();
	}
	
	public int GetMovedParticleCount(String sourceRank, String destinationRank, String timeStep) {
		return this.output.get(new RankDiff(Integer.parseInt(sourceRank), Integer.parseInt(destinationRank)), Integer.parseInt(timeStep));
	}
	
	public void Insert(Particle p, String currentRank, String timeStep) {
		
		if(this.oldParticlePosition.containsKey(p)) {
			String oldRank = this.oldParticlePosition.get(p);
			if(!oldRank.equals(currentRank)) {
				this.output.Insert(new RankDiff(Integer.parseInt(oldRank), Integer.parseInt(currentRank)), Integer.parseInt(timeStep) - 1);
			}
		}
		else {
			this.oldParticlePosition.put(p, currentRank);
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (RankDiff key: this.output.keySet()) {
			Hashtable<Integer, Integer> timeBasedCount = this.output.get(key);
			for(int ts:timeBasedCount.keySet()) {
				sb.append(key.sourceRank+",");
				sb.append(key.destinationRank+",");
				sb.append(ts+",");
				sb.append(timeBasedCount.get(ts)+"\n");	
			}
			
		}
		
		return sb.toString();
	}
}
