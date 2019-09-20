package Outputs;

import java.util.ArrayList;
import java.util.Hashtable;

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
	
	
	public String toStringCount(int totalranks, int[] timestepList) {
		StringBuilder sb = new StringBuilder();
		
		for (int rid=0; rid < totalranks; rid++) {
			if(this.ghostParticleCount.ContainsKey(rid)) {
				Hashtable<Integer, Integer> timeBasedCount = this.ghostParticleCount.get(rid);
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
	
	public String toStringMovement() {
		StringBuilder sb = new StringBuilder();
		
		for (RankDiff key: this.ghostParticleMovement.keySet()) {
			Hashtable<Integer, Integer> timeBasedCount = this.ghostParticleMovement.get(key);
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
