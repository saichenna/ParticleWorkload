package Utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class IOFileNames {
	
	public IOFileNames(String inputFilePath) throws IOException {
//		String line = null;
		BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
		this.firstInput = reader.readLine();
		this.secondInput = reader.readLine();
// v3.0	this.thirdInput = reader.readLine();
// v3.0	this.fourthInput = reader.readLine();
		this.firstOutput = reader.readLine();
		this.secondOutput = reader.readLine();
		this.thirdOutput = reader.readLine();
		this.fourthOutput = reader.readLine();
		reader.close();
		
		
	}
	
	public String firstInput;
	
	public String secondInput;
	
//	public String thirdInput;
	
//	public String fourthInput;
	
    public String firstOutput;
	
	public String secondOutput;
	
	public String thirdOutput;
	
	public String fourthOutput;
}
