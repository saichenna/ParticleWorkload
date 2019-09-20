package Inputs;

import java.util.ArrayList;
import java.util.Arrays;
//import java.util.List;

import Entities.Location;
import Entities.Particle;

public class ParticleElementEntry {
	public Particle particle;
	
	public String timeStep;
	
	public Location location;
	
//	public String elementId;
	
	public ParticleElementEntry(String line) {
		ArrayList<String> myList = new ArrayList<String>(Arrays.asList(line.split(",")));
		this.particle = new Particle(Integer.parseInt(myList.get(0)),Integer.parseInt(myList.get(1)),Integer.parseInt(myList.get(2)));
		this.location = new Location(Double.parseDouble(myList.get(3)),Double.parseDouble(myList.get(4)),Double.parseDouble(myList.get(5)));
//		this.elementId = myList.get(7);
		this.timeStep = myList.get(6);
	}
}
