package Entities;

public class SyntheticParticle {
	
	public SyntheticParticle(int v, int w, int x, int y, int z) {
		this.v = v;
		this.w = w;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public SyntheticParticle(int v, int w, int x, int y) {
		this.v = v;
		this.w = w;
		this.x = x;
		this.y = y;
		this.z = 0;
	}
	
	public SyntheticParticle(Particle p, int z) {
		this.v = p.v;
		this.w = p.w;
		this.x = p.x;
		this.y = p.y;
		this.z = z;
	}

	public int v;

	public int w;
	
	public int x;
	
	public int y;
	
	public int z;
	
	@Override
	public boolean equals(Object obj) {
		Particle particle = (Particle) obj;
		return particle.v == this.v && particle.w == this.w && particle.x == this.x && particle.y == this.y && particle.z == this.z ;
	}
	
	@Override
	public int hashCode() {
		return 16384*v + 32768*w + 65536*x + 131071*y + z;  
	}
}
