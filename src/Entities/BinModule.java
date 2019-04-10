package Entities;

public class BinModule {
	public BinModule(int i, int j, int k) {
		this.i = i;
		this.j = j;
		this.k = k;
	}
	
	public int i;
	
	public int j;
	
	public int k;

  @Override
  public boolean equals(Object obj) {
  BinModule bin = (BinModule) obj;
  return bin.i == this.i && bin.j == this.j && bin.k == this.k;
  }
  
  @Override
  public int hashCode() {
  return 16384*this.i + 32768*this.j + this.k;  
  } 



}
