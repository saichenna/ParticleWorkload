package Outputs;

import java.util.Hashtable;

import Entities.Particle;
import Utilities.NestedCounterHashtable;

public class ParticleMovement {
	
	private Hashtable<Particle, Integer> oldParticlePosition;
	
	private NestedCounterHashtable<RankDiff, Integer> output;
	
	public ParticleMovement() {
		this.oldParticlePosition = new Hashtable<>();
		this.output = new NestedCounterHashtable<>();
	}
	
	public int GetMovedParticleCount(int sourceRank, int destinationRank, int timeStep) {
		return 1;
	}
	
	public void Insert(Particle p, int currentRank, int timeStep) {
		
		if(this.oldParticlePosition.containsKey(p)) {
			int oldRank = this.oldParticlePosition.get(p);
			if(oldRank != currentRank) {
				this.output.Insert(new RankDiff(oldRank, currentRank), timeStep - 1);
			}
		}
		else {
			this.oldParticlePosition.put(p, currentRank);
		}
	}
}
