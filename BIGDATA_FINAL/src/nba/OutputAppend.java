package nba;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class OutputAppend {
	public static void main(String []args) throws Exception{ 
	FileReader File1= new FileReader("result/2013.txt");
	BufferedReader br= new BufferedReader(File1);
	FileWriter File2 = new FileWriter("result1/2013.txt");
	BufferedWriter bw = new BufferedWriter(File2);
	String x;
	int i;
	while((x=br.readLine())!=null){
		x=x.substring(16);
		for(i=0;i<x.length();i++)
			{if(x.charAt(i)>='A') 
				break;}
		bw.write(x.substring(0,i)+x.substring(i+3)+"\n");
		
	}
	br.close();
	bw.close();
}
}