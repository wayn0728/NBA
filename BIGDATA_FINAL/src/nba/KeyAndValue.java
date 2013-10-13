package nba;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KeyAndValue {
	static File folder = new File("/Users/jingliu/Documents/Study/RealTimeBigData/raw1");
  static List<File> fileList = Arrays.asList(folder.listFiles());
	public static void main(String[] args) throws IOException{
		readFile(fileList);
		//System.out.println(fileList);
	}
	
	public static void readFile(List<File> fileList) throws IOException{
		
			for(int i = 0; i < fileList.size(); i++){
				//System.out.print("\n");
				FileReader file1=new FileReader(fileList.get(i));
				BufferedReader br=new BufferedReader(file1);
				//PrintWriter output = new PrintWriter(new FileWriter("output" + i + ".txt"));

				for (String line;(line = br.readLine()) != null;) {
					
			    if (line.trim().isEmpty()) {
			    	continue;
			    } else {
			    	Pattern pattern = Pattern.compile("<td align=\"left\" ><a href=\"/teams/([A-Z][A-Z][A-Z])");
			    	Matcher matcher = pattern.matcher(line);
			    	if(matcher.find()){
			    		System.out.print(matcher.group(1) + "\t");
			    	}
			    }
			}
				System.out.print("\n");
				
			}
			
	}
	
}
