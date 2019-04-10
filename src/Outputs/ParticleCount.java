package Outputs;

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
}