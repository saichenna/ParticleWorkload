package Outputs;

public class RankDiff {
	
	public RankDiff(int source, int destination) {
		this.sourceRank = source;
		this.destinationRank = destination;
	}
	
	public int sourceRank;
	
	public int destinationRank;
	
	  @Override
	  public boolean equals(Object obj) {
		RankDiff diff = (RankDiff) obj;
	    return diff.sourceRank == this.sourceRank && diff.destinationRank == this.destinationRank;
	  }
	  
	  @Override
	  public int hashCode() {
		  return 16384*this.sourceRank + 32768*this.destinationRank;  
	  } 
}
	
