package Inputs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import Entities.BinModule;

public class StringBinToRankMap {
    private Hashtable<BinModule, ArrayList<String>> bin2processor;
	
	public StringBinToRankMap(String binMapFileName) throws NumberFormatException, IOException {
		int threadCount = 100;
		this.bin2processor = new Hashtable<>();
		String line;
		BufferedReader binMapFile = new BufferedReader(new FileReader(binMapFileName));
		
		var fileMem = new ArrayList<String>();
		while((line = binMapFile.readLine()) != null) {
			fileMem.add(line);
		}
		binMapFile.close();
		
		var threadPool = new ArrayList<StringToIntConverter2>();
		var fileSize = fileMem.size();
		int stride = fileSize / threadCount;
		int offset = fileSize % threadCount;
		int start = 0;
		for (int i = 0; i < threadCount; i++) {
			var from = start;
			var to = start + (stride - 1) + (i < offset ? 1 : 0);
			var newThread = new StringToIntConverter2(from, to, fileMem, this.bin2processor);
			threadPool.add(newThread);
			start = to + 1;
		}
		
		for (StringToIntConverter2 stringToIntConverter : threadPool) {
			stringToIntConverter.start();
		}
		
		while(this.getCount() != fileMem.size()) {
			
		}	
	}
		
	public ArrayList<String> GetRanks(BinModule bin){
		return this.bin2processor.get(bin);
	}
	
	public boolean Contains(BinModule bin) {
		return this.bin2processor.containsKey(bin);
	}

	public int getCount() {
		return this.bin2processor.keySet().size();
	}
}