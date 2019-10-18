package Utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ArgumentValues {
	
public ArgumentValues(String inputFilePath) throws NumberFormatException, IOException {
	
	// TO DO:
	// 1. Read from the input file and extract 
	BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
	this.xmin = Double.parseDouble(reader.readLine());
	this.xmax = Double.parseDouble(reader.readLine());
	this.ymin = Double.parseDouble(reader.readLine());
	this.ymax = Double.parseDouble(reader.readLine());
	this.zmin = Double.parseDouble(reader.readLine());
	this.zmax = Double.parseDouble(reader.readLine());
	this.pbSize = Double.parseDouble(reader.readLine());
	this.particleScaling = Integer.parseInt(reader.readLine());
	this.elementScaling = Integer.parseInt(reader.readLine());
	this.totalranks = Integer.parseInt(reader.readLine());
	this.firsttimestep = Integer.parseInt(reader.readLine());
	this.secondtimestep = Integer.parseInt(reader.readLine());
	this.delta = Integer.parseInt(reader.readLine());
	this.elementSizeX = Double.parseDouble(reader.readLine());
	this.elementSizeY = Double.parseDouble(reader.readLine());
	this.elementSizeZ = Double.parseDouble(reader.readLine());
	
	reader.close();
		
	}
	
	public double xmin;
	
	public double xmax;
	
	public double ymin;
	
	public double ymax;
	
	public double zmin;
	
	public double zmax;
	
	public double pbSize;	
	
	public int particleScaling;
	
	public int elementScaling;
	
	public int totalranks;
	
	public int firsttimestep;
	
	public int secondtimestep;
	
	public int delta;
	
	public double elementSizeX;
	
	public double elementSizeY;
	
	public double elementSizeZ;
}
