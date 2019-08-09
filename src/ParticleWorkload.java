import java.io.IOException;

import Entities.BinCalculationUtility;
import Entities.BinModule;
import Entities.Particle;
import Inputs.BinToRankMap;
import Inputs.ParticleElementEntry;
import Inputs.ParticleElementMap;
import Inputs.RankToElementMap;
import Inputs.ScalingData;
import Outputs.GhostParticleOutput;
import Outputs.ParticleCount;
import Outputs.ParticleMovement;
import Utilities.ArgumentValues;
import Utilities.IOFileNames;

public class ParticleWorkload {

    // TODO1: 
	// 1. Reading input files
	// 2. Writing the scaling logic in the bin utility
	public static void main(String[] args) throws IOException {
	     String argsFileName = args[2];
	     String ioFileName = args[1];
	     
	     ArgumentValues inputAargs = new ArgumentValues(argsFileName);
	     IOFileNames ioFileNames = new IOFileNames(ioFileName);
	     
	     Run(inputAargs, ioFileNames);
	}

	
	public static void Run(ArgumentValues args, IOFileNames ioFileNames) throws IOException {
		BinCalculationUtility utility = new BinCalculationUtility(args);
		
		ParticleElementMap firstInput = new ParticleElementMap(ioFileNames.firstInput);
		RankToElementMap secondInput = new RankToElementMap();
		BinToRankMap thirdInput = new BinToRankMap();
		ScalingData scalingInput = new ScalingData(args.elementScaling);
		
		ParticleCount output1 = new ParticleCount();
		ParticleMovement output2 = new ParticleMovement();
		GhostParticleOutput output3 = new GhostParticleOutput(thirdInput);
		
		while(firstInput.hasNext()) {
			ParticleElementEntry entry = firstInput.getNext();
			
			for (int i = 0; i < args.particleScaling; i++) {
				int newElement = scalingInput.GetNewRandomElement(entry.elementId);
				int rank = secondInput.GetRank(newElement);
				Particle syntheticParticle = new Particle(entry.particle, i);
				
				BinModule bin = utility.GetBin(entry.location);			
				
				output1.Insert(rank, entry.timeStep);		
				output2.Insert(syntheticParticle, rank, entry.timeStep);
				output3.Insert(bin, rank, entry.timeStep);
			}			
		}
	}
	
}
