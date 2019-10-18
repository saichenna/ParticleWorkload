package Outputs;

import java.util.Hashtable;
import java.util.List;

import Entities.Particle;
import Utilities.NestedCounterHashtable;
import Utilities.OldParticlePosition;

public class StringParticleMovement {
	private Hashtable<Particle, String> oldParticlePosition;
	
//	private OldParticlePosition oldParticlePosition;
	
	private int delta;
	

	
	private NestedCounterHashtable<RankDiff, Integer> output;
	
	public StringParticleMovement(int particlescaling, int delta) {
		this.oldParticlePosition = new Hashtable<>();
		
//		this.oldParticlePosition = new OldParticlePosition(particlescaling);
		this.output = new NestedCounterHashtable<>();
		this.delta = delta;
	}
	
	public int GetMovedParticleCount(String sourceRank, String destinationRank, String timeStep) {
		return this.output.get(new RankDiff(Integer.parseInt(sourceRank), Integer.parseInt(destinationRank)), Integer.parseInt(timeStep));
	}
	
	public void Insert(Particle p, String currentRank, String timeStep) {
//	public void Insert(Particle p, int currentRank, String timeStep) {		
		if(this.oldParticlePosition.containsKey(p)) {
			String oldRank = this.oldParticlePosition.get(p);
			if(!oldRank.equals(currentRank)) {
				this.output.Insert(new RankDiff(Integer.parseInt(oldRank), Integer.parseInt(currentRank)), Integer.parseInt(timeStep) - 1);
			}
		}
		else {
			this.oldParticlePosition.put(p, currentRank);
		}
		
		//updated to reduce memory consumption of oldParticlePosition hashtable
//		if(this.oldParticlePosition.ContainsKey(p)) {
//			int oldRank = this.oldParticlePosition.Get(p);
//			if(oldRank == -1) {
//				this.oldParticlePosition.Insert(p, currentRank);
//			}
//			else {
//				if(oldRank != currentRank) {
//					this.output.Insert(new RankDiff(oldRank,currentRank), Integer.parseInt(timeStep) - this.delta);
//				}
//			}
//			
//		}
//		else {
//			this.oldParticlePosition.Insert(p, currentRank);
//		}
//		
		
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
