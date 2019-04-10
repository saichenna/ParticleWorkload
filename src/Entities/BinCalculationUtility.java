package Entities;

import Utilities.ArgumentValues;

public class BinCalculationUtility {
	private ArgumentValues args;
	
	private int nDxGp;
	
	private int nDyGp;
	
	private int nDzGp;
	
	private int rDxGp;
	
	private int rDyGp;
	
	private int rDzGp;
	
	public BinCalculationUtility(ArgumentValues args) {
		this.args = args;
		this.nDxGp = ((int) Math.floor((args.xmax-args.xmin)/args.pbSize))+1;
		this.nDyGp = ((int) Math.floor((args.ymax-args.ymin)/args.pbSize))+1;
	    this.nDzGp = ((int) Math.floor((args.zmax-args.zmin)/args.pbSize))+1;
	     
	    this.rDxGp = (args.xmax-args.xmin)/this.nDxGp;	
	    this.rDyGp = (args.ymax-args.ymin)/this.nDyGp;	
	    this.rDzGp = (args.zmax-args.zmin)/this.nDzGp;	
	}
	
	// TODO: Add the scaling code.
	public BinModule GetBin(Location location) {
		int ii = (int) Math.floor((location.x - this.args.xmin)/this.rDxGp);
		int jj = (int) Math.floor((location.y - this.args.ymin)/this.rDyGp);
		int kk = (int) Math.floor((location.z - this.args.zmin)/this.rDzGp);
		if (ii == this.nDxGp){
			ii = this.nDxGp-1;
		}
		if (jj == this.nDyGp){
			jj = this.nDyGp-1;
		}
		if (kk == this.nDzGp){
			kk = this.nDzGp-1;
		}
		
		return new BinModule(ii, jj, kk);
	}
}
