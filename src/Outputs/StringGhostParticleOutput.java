package Outputs;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import Entities.BinModule;
import Inputs.ElementData;
import Inputs.StringBinToRankMap;
import Utilities.CustomTuple;
import Utilities.IntegertoString;
import Utilities.NestedCounterHashtable;

public class StringGhostParticleOutput {

	private IntegertoString itos;
	private NestedCounterHashtable<String, String> ghostParticleCount;
	
	private NestedCounterHashtable<RankDiff, String> ghostParticleMovement;
	
	// version 2.0
//	private StringBinToRankMap input3;	
	
	
	//version 3.0
	private ElementData elemData;
	
	private int[] el_face_num = {-1,0,0, 1,0,0, 0,-1,0, 0,1,0, 0,0,-1, 0,0,1};
	
	private int[] el_edge_num = {-1,-1,0,   1,-1,0,   1,1,0,    -1,1,0, 
			                     0,-1,-1,   1,0,-1,   0,1,-1,   -1,0,-1,
			                     0,-1,1,    1,0,1,    0,1,1,    -1,0,1  };
	private int[] el_corner_num = {-1,-1,-1,   1,-1,-1,   1,1,-1,   -1,1,-1, 
								   -1,-1,1,    1,-1,1,    1,1,1,    -1,1,1};
	
	private int nfacegp;
	
	private int nedgegp;
	
	private int ncornergp;
	
	private int ndxgp;
	
	private int ndygp;
	
	private int ndzgp;
	
	private int[] bc_part = {0,0,1,-1,0,0}; // Assuming these are the correct values for the following box bdy condns: P,P,W,O,P,P
	
	
	//version 2.0
//	public StringGhostParticleOutput(StringBinToRankMap input3, int ndxgp, int ndygp, int ndzgp) {
//		this.ghostParticleCount = new NestedCounterHashtable<>();
//		this.ghostParticleMovement = new NestedCounterHashtable<>();
//		this.input3 = input3;
//		this.nfacegp = 6; //set to 4 in case of 2D problem
//		this.nedgegp = 12; //set to 4 in case of 2D problem
//		this.ncornergp = 8; //set to 0 in case of 2d problem
//		this.ndxgp = ndxgp;
//		this.ndygp = ndygp;
//		this.ndzgp = ndzgp;

//	}
	
	//version 3.0
	public StringGhostParticleOutput(ElementData elemData, int ndxgp, int ndygp, int ndzgp, IntegertoString itos) {
		this.ghostParticleCount = new NestedCounterHashtable<>();
		this.ghostParticleMovement = new NestedCounterHashtable<>();
		this.elemData = elemData;
		this.nfacegp = 6; //set to 4 in case of 2D problem
		this.nedgegp = 12; //set to 4 in case of 2D problem
		this.ncornergp = 8; //set to 0 in case of 2d problem
		this.ndxgp = ndxgp;
		this.ndygp = ndygp;
		this.ndzgp = ndzgp;
		this.itos = itos;


	}
	
	
	
	public void Insert(BinModule bin, String currentRank, String timeStep) {
//		for (int i = bin.i - 1; i < bin.i + 2; i++) {
//			for (int j = bin.j - 1; j < bin.j + 2; j++) {
//				for (int k = bin.k - 1; k < bin.k + 2; k++) {
//					
//					BinModule adjacentBin = new BinModule(i, j, k);
//					if(this.input3.Contains(adjacentBin)) {
//						
//						ArrayList<String> otherRanks = this.input3.GetRanks(adjacentBin);
//						for (String otherRank : otherRanks) {
//							
//							if(!otherRank.equals(currentRank)) {
//								this.ghostParticleCount.Insert(otherRank, timeStep);
//								this.ghostParticleMovement.Insert(new RankDiff(Integer.parseInt(currentRank), Integer.parseInt(otherRank)), timeStep);
//							}
//						}
//					}
//				}
//			}
//		}

		
		// version 2.0
//		if(this.input3.Contains(bin)) {
//			ArrayList<String> otherRanks = this.input3.GetRanks(bin);
//			for (String otherRank : otherRanks) {
				
//				if(!otherRank.equals(currentRank)) {
//					this.ghostParticleCount.Insert(otherRank, timeStep);
//					this.ghostParticleMovement.Insert(new RankDiff(Integer.parseInt(currentRank), Integer.parseInt(otherRank)), timeStep);
//				}
//			}
			
//		}

		
		
		//version 3.0 
		
		if(this.elemData.Contains(bin)) {
			List<Integer> otherRanks = this.elemData.GetRankFromBin(bin);
			for (Integer otherRank : otherRanks) {
				
				if(!this.itos.GetString(otherRank).equals(currentRank)) {
					this.ghostParticleCount.Insert(this.itos.GetString(otherRank), timeStep);
					this.ghostParticleMovement.Insert(new RankDiff(Integer.parseInt(currentRank), otherRank), timeStep);
				}
			}
			
			
		}
		
		
		this.GpCheckerLoop(this.nfacegp, this.el_face_num, 1, bin, currentRank, timeStep);
		this.GpCheckerLoop(this.nedgegp, this.el_edge_num, 2, bin, currentRank, timeStep);
		this.GpCheckerLoop(this.ncornergp, this.el_corner_num, 3, bin, currentRank, timeStep);					
	}
	
	private void GpCheckerLoop(int n, int[] shapeArray, int shapeType, BinModule bin, String currentRank, String timeStep) {
		for(int ifc = 1; ifc <= n; ifc++) {
			var tuple = this.GpChecker(bin, ifc, shapeArray);
			if(tuple.First == -1 || tuple.First != shapeType) continue;
			
			int iflgsum = tuple.First;
			BinModule faceBin = tuple.Second;			
			this.UpdateMaps(faceBin, currentRank, iflgsum, timeStep, 1);
		}
	}
	
	private CustomTuple<Integer, BinModule> GpChecker(BinModule bin, int ifc, int[] shapeArray) {
		int ist = (ifc-1)*3;
		int ii1 = bin.i + shapeArray[ist];
		int jj1 = bin.j + shapeArray[ist+1];
		int kk1 = bin.k + shapeArray[ist+2];
		
		int iig = ii1;
		int jjg = jj1;
		int kkg = kk1;
		int iflgx = 0;
		int iflgy = 0;
		int iflgz = 0;
		
		if (iig < 0 || iig > this.ndxgp-1 ) {
			iflgx = 1;
			iig = iig%this.ndxgp;
			if ((Math.abs(this.bc_part[0])+Math.abs(this.bc_part[1])) != 0) return new CustomTuple<Integer, BinModule>(-1, null);
			
		}
		
		if (jjg < 0 || jjg > this.ndygp-1 ) {
			iflgy = 1;
			jjg = jjg%this.ndygp;
			if ((Math.abs(this.bc_part[2])+Math.abs(this.bc_part[3])) != 0) return new CustomTuple<Integer, BinModule>(-1, null);
			
		}
		
		if (kkg < 0 || kkg > this.ndzgp-1 ) {
			iflgz = 1;
			kkg = kkg%this.ndzgp;
			if ((Math.abs(this.bc_part[4])+Math.abs(this.bc_part[5])) != 0) return new CustomTuple<Integer, BinModule>(-1, null);
			
		}
		
		int iflgsum = iflgx + iflgy + iflgz;		
		BinModule faceBin = new BinModule(iig,jjg,kkg);	
		
		return new CustomTuple<Integer, BinModule>(iflgsum, faceBin);
	}
	 
	private void UpdateMaps(BinModule bin, String currentRank, int iFlgSum, String timeStep, int shapeType) {
		if(this.elemData.Contains(bin)) {
			List<Integer> otherRanks = this.elemData.GetRankFromBin(bin);
			for (Integer otherRank : otherRanks) {
				
				if(!this.itos.GetString(otherRank).equals(currentRank) || (this.itos.GetString(otherRank).equals(currentRank) && iFlgSum == shapeType)) {
					this.ghostParticleCount.Insert(this.itos.GetString(otherRank), timeStep);
					this.ghostParticleMovement.Insert(new RankDiff(Integer.parseInt(currentRank), otherRank), timeStep);
				}
			}
		
		}
	}
	
	public String toStringCount(int totalranks, String[] timestepList) {
		StringBuilder sb = new StringBuilder();
		
		for (int rid=0; rid < totalranks; rid++) {
			var stringRid = ((Integer)rid).toString();			
			if(this.ghostParticleCount.ContainsKey(stringRid)) {
				Hashtable<String, Integer> timeBasedCount = this.ghostParticleCount.get(stringRid);
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
	
	public String toStringMovement() {
		StringBuilder sb = new StringBuilder();
		
		for (RankDiff key: this.ghostParticleMovement.keySet()) {
			Hashtable<String, Integer> timeBasedCount = this.ghostParticleMovement.get(key);
			for(String ts:timeBasedCount.keySet()) {
				sb.append(key.sourceRank+",");
				sb.append(key.destinationRank+",");
				sb.append(ts+",");
				sb.append(timeBasedCount.get(ts)+"\n");	
			}
			
		}
		
		return sb.toString();
	}
}
