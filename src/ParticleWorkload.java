import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;

import Entities.BinCalculationUtility;
import Entities.BinModule;
import Entities.Location;
import Entities.Particle;
import Inputs.ElementData;
import Inputs.ParticleElementEntry;
import Inputs.ParticleElementMap;
import Inputs.StringBinToRankMap;
import Inputs.StringRankToElementMap;
import Inputs.StringScalingData;
import Outputs.StringGhostParticleOutput;
import Outputs.StringParticleCount;
import Outputs.StringParticleMovement;
import Utilities.ArgumentValues;
import Utilities.IOFileNames;

public class ParticleWorkload {

    // TODO: 
	// 1. Reading input files
	// 2. Writing the scaling logic in the bin utility
	public static void main(String[] args) throws IOException {
//	     String argsFileName = args[0];
//	     String ioFileName = args[1];
	     
	     String argsFileName = "Input_args.txt";
	     String ioFileName = "Input_filenames.txt";
	     
	     ArgumentValues inputAargs = new ArgumentValues(argsFileName);
	     IOFileNames ioFileNames = new IOFileNames(ioFileName);
	     
	     System.out.println("Running!");
	     Run(inputAargs, ioFileNames);
	}

	
	public static void Run(ArgumentValues args, IOFileNames ioFileNames) throws IOException {
		BinCalculationUtility utility = new BinCalculationUtility(args);
		
		ParticleElementMap firstInput = new ParticleElementMap(ioFileNames.firstInput);
//		StringRankToElementMap secondInput = new StringRankToElementMap(ioFileNames.secondInput);
//		StringBinToRankMap thirdInput = new StringBinToRankMap(ioFileNames.thirdInput);
		ElementData elemData = new ElementData(ioFileNames.secondInput,utility);
//		StringScalingData scalingInput = new StringScalingData(args.elementScaling,ioFileNames.fourthInput);
		
		StringParticleCount output1 = new StringParticleCount();
		StringParticleMovement output2 = new StringParticleMovement();
//		StringGhostParticleOutput output3 = new StringGhostParticleOutput(thirdInput,utility.nDxGp,utility.nDyGp,utility.nDzGp); //Change made over here

		
		//version 3.0
		StringGhostParticleOutput output3 = new StringGhostParticleOutput(elemData,utility.nDxGp,utility.nDyGp,utility.nDzGp); 		
		long startTime = System.nanoTime();
		while(firstInput.hasNext()) {
//			ParticleElementEntry entry = firstInput.getNext();
			ParticleElementEntry entry = new ParticleElementEntry(firstInput.getNext());
			
			for (int i = 0; i < args.particleScaling; i++) {
//				String newElement = scalingInput.GetNewRandomElement(entry.elementId);
//				String rank = secondInput.GetRank(newElement);
//				Particle syntheticParticle = new Particle(entry.particle, i);				
				
//				BinModule bin = utility.GetBin(entry.location);			
				
//				output1.Insert(rank, entry.timeStep);		
//				output2.Insert(syntheticParticle, rank, entry.timeStep);
//				output3.Insert(bin, rank, entry.timeStep);
				
				
				// version 3.0 - Added to incorporate new changes
				
				Particle syntheticParticle = new Particle(entry.particle, i);
				
				List<Double> newloc = entry.location.Random(args.elementSizeX,args.elementSizeY,args.elementSizeZ, i);
				Location newlocation = new Location(newloc.get(0),newloc.get(1),newloc.get(2));
				
				BinModule bin = utility.GetBin(newlocation);
				Integer actualElement = 0;
				List<Integer> Elemlist = new ArrayList<Integer>();
				Elemlist = elemData.GetElementFromBin(bin);
				/*
				 * System.out.println(utility.nDxGp+","+utility.nDyGp+","+utility.nDzGp);
				 * System.out.println(utility.rDxGp+","+utility.rDyGp+","+utility.rDzGp);
				 * System.out.println(Double.toString(newloc.get(0))+","+Double.toString(newloc.
				 * get(1))+","+Double.toString(newloc.get(2)));
				 * System.out.println(bin.i+","+bin.j+","+bin.k); System.out.println(Elemlist);
				 */
				if (Elemlist.size() > 1) {
					for (int elem : Elemlist) {
						List<Double> corners = elemData.GetCorners(elem);
						if (newlocation.x <= corners.get(1) && newlocation.x >= corners.get(0)) {
							if (newlocation.y <= corners.get(3) && newlocation.y >= corners.get(2)) {
								if (newlocation.z <= corners.get(5) && newlocation.z >= corners.get(4)) {
									actualElement = elem;
									break;
									
								}
							}
						}
					}
					
				}
				else {
					actualElement = Elemlist.get(0);
				}
				
				if(actualElement == 0) {
					System.out.println("Error!!: Unable to detect the element in which the particle resides!");
					System.exit(0);
				}
				
				Integer actualRank = elemData.GetRankFromElem(actualElement);
				output1.Insert(Integer.toString(actualRank), entry.timeStep);		
				output2.Insert(syntheticParticle, Integer.toString(actualRank), entry.timeStep);
				output3.Insert(bin, Integer.toString(actualRank), entry.timeStep);				
				
				
				
				
				
			}			
		}
		long endTime = System.nanoTime();
		var timeTaken = (endTime - startTime)/1000000; //divide by 1000000 to get milliseconds.
		
		 var abc = new PrintWriter(ioFileNames.firstOutput, "UTF-8");
         var def = new PrintWriter(ioFileNames.secondOutput, "UTF-8");
         var ghi = new PrintWriter(ioFileNames.thirdOutput,"UTF-8");
         var jkl = new PrintWriter(ioFileNames.fourthOutput,"UTF-8");
         
         String[] arr = new String[2];
         arr[0] = ((Integer)args.firsttimestep).toString();
         arr[1] = ((Integer)args.secondtimestep).toString();
         abc.println(output1.toString(args.totalranks, arr));
         def.println(output2.toString());
         ghi.println(output3.toStringCount(args.totalranks,arr));
         jkl.println(output3.toStringMovement());
         System.out.println("Exited successfully!!!" + "\n");
         
         abc.close();
         def.close();
         ghi.close();
         jkl.close();

		
		
		
		
	}
	
}
