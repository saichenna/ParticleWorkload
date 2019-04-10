package Outputs;

import java.util.ArrayList;

import Entities.BinModule;
import Inputs.BinToRankMap;
import Utilities.NestedCounterHashtable;

public class GhostParticleOutput {
	
	private NestedCounterHashtable<Integer, Integer> ghostParticleCount;
	
	private NestedCounterHashtable<RankDiff, Integer> ghostParticleMovement;
	
	private BinToRankMap input3;
	
	public GhostParticleOutput(BinToRankMap input3) {
		this.ghostParticleCount = new NestedCounterHashtable<>();
		this.ghostParticleMovement = new NestedCounterHashtable<>();
		this.input3 = input3;
	}
	
	public void Insert(BinModule bin, int currentRank, int timeStep) {
		for (int i = bin.i - 1; i < bin.i + 2; i++) {
			for (int j = bin.j - 1; j < bin.j + 2; j++) {
				for (int k = bin.k - 1; k < bin.k + 2; k++) {
					
					BinModule adjacentBin = new BinModule(i, j, k);
					if(this.input3.Contains(adjacentBin)) {
						
						ArrayList<Integer> otherRanks = this.input3.GetRanks(adjacentBin);
						for (int otherRank : otherRanks) {
							
							if(otherRank != currentRank) {
								this.ghostParticleCount.Insert(otherRank, timeStep);
								this.ghostParticleMovement.Insert(new RankDiff(currentRank, otherRank), timeStep);
							}
						}
					}
				}
			}
		}
	}
}
