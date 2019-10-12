package Utilities;

import java.util.Hashtable;
//import java.util.List;

import Entities.Particle;

public class OldParticlePosition {
	
	public Hashtable<Particle, int[]> oldparpos;
	private int scalingratio;
	
	
	public OldParticlePosition(int scalingratio) {
		this.oldparpos = new Hashtable<Particle, int[]>();
		this.scalingratio = scalingratio;
	}
	
	public void Insert(Particle p, int rank) {
		Particle temp = new Particle(p.v,p.w,p.x);
		if(this.oldparpos.containsKey(temp)) {
			int[] tmplist = this.oldparpos.get(temp);
			tmplist[p.y] = rank;
			this.oldparpos.put(temp,tmplist);
			
		}
		else {
			
			this.Init(p);
			int[] tmplist = this.oldparpos.get(temp);
			tmplist[p.y] = rank;
			this.oldparpos.put(temp,tmplist);
			
		}
	}
		
	public void Init(Particle p2) {
		int[] list = new int[this.scalingratio];
		
		for(int i=0; i < this.scalingratio; i++) {
			list[i] = -1;
		}
		Particle temp2 = new Particle(p2.v,p2.w,p2.x);
		this.oldparpos.put(temp2, list);
		
		
	}
	
	public int Get(Particle p3) {
		Particle temp3 = new Particle(p3.v,p3.w,p3.x);
		return this.oldparpos.get(temp3)[p3.y];
	}
	
	public boolean ContainsKey(Particle p4) {
		Particle temp4 = new Particle(p4.v,p4.w,p4.x);
		return this.oldparpos.containsKey(temp4);
	
	}

}
