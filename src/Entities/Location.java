package Entities;

import java.util.Arrays;
import java.util.List;

public class Location {
	
	public Location(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double x;
	
	public double y;
	
	public double z;
	
	public List<Double> Random(double Xstride,double Ystride,double Zstride, Integer i) {
		
		if(i > 0) {
//		double x = Math.random(this.x-stride,this.x+stride);
//		double y = Math.random(this.y-stride,this.y+stride);
//		double z = Math.random(this.z-stride,this.z+stride);
		
		double x = this.x-Xstride + (2*Xstride)*Math.random();
		double y = this.y-Ystride + (2*Ystride)*Math.random();
		double z = this.z-Zstride + (2*Zstride)*Math.random();
		
		
		}
		else {
			double x = this.x;
			double y = this.y;
			double z = this.z;
		}
		
		List<Double> newloc;
		
		newloc = Arrays.asList(x,y,z);
		return newloc;
		
	}
	
	@Override
	  public boolean equals(Object obj) {
		Location location = (Location) obj;
	    return location.x == this.x && location.y == this.y && location.z == this.z;
	  }
	  
	  @Override
	  public int hashCode() {
	     return (int) (16384*this.x + 32768*this.y + this.z);  
	  } 
}
