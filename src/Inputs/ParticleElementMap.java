package Inputs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import Entities.Location;
import Entities.Particle;

public class ParticleElementMap {
	
	private String nextLine;
	
	private BufferedReader particleMapFile;
	
	public ParticleElementMap(String inputFilePath) throws FileNotFoundException {
		this.nextLine = null;
		this.particleMapFile = new BufferedReader(new FileReader(inputFilePath));
	}
	
	public int GetElement(Particle particle, int timeStep) {
		return 1;
	}	
	
	public Location GetParticleLocation(Particle particle, int timeStep) {
		return null;
	}
	
	public boolean hasNext() throws IOException {
		this.nextLine = this.particleMapFile.readLine();
		return this.nextLine != null;
	}
	
	public ParticleElementEntry getNext() {
		
		return null;
	}
}
