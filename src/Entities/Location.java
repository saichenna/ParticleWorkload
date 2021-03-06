package Entities;

public class Location {
	
	public Location(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double x;
	
	public double y;
	
	public double z;
	
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
