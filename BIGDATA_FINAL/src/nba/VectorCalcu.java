package nba;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class VectorCalcu {
	public void cal() throws IOException {
		File folder = new File(
				"/Users/jingliu/Documents/Study/RealTimeBigData/ProjectInput");
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
			if (file.isFile()) {
				String sttr = file.getName();
				
				if (sttr.matches("[0-9]{4}.*[txt]")) {
					System.out.println(sttr);
					FileReader file1=new FileReader("/Users/jingliu/Documents/Study/RealTimeBigData/ProjectInput/"+sttr);
					BufferedReader br=new BufferedReader(file1);
					Map map = new HashMap();
					
					String s = "";
					while((s=br.readLine())!=null) {
						String[] lines = s.split("\\s");
						//map.put(key, value);
					}
					
				}
			}
		}
	}
}
