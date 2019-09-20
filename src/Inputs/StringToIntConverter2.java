package Inputs;

import java.util.ArrayList;
import java.util.Hashtable;

import Entities.BinModule;

public class StringToIntConverter2 extends Thread{
	private int from;
	
	private int to;
	
	private ArrayList<String> input;
	
	private Hashtable<BinModule, ArrayList<String>> bin2processor;
	
	public StringToIntConverter2(int from, int to, ArrayList<String> input, Hashtable<BinModule, ArrayList<String>> bin2processor) {
		this.from = from;
		this.to = to;
		this.input = input;
		this.bin2processor = bin2processor;
	}
	
	public void run() {
		for (int i = from; i <= to; i++) {
			var line = this.input.get(i);
			var binMap = line.split(",");
			
			var bin = new BinModule(Integer.parseInt(binMap[0]),Integer.parseInt(binMap[1]),Integer.parseInt(binMap[2]));
			ArrayList<String> binRanks = new ArrayList<>();
			for(int j = 3; j < binMap.length; j++) {
				binRanks.add(binMap[j]);
			}
			if(this.bin2processor.containsKey(bin)) {
				System.out.println("Duplicate key found!");
			}
            this.bin2processor.put(bin, binRanks);
		}
	}
}
