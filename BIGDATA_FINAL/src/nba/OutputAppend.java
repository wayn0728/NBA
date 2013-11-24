package nba;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class OutputAppend {
	public static void main(String []args) throws Exception{ 
	FileReader File1= new FileReader("result1/2013.txt");
	BufferedReader br= new BufferedReader(File1);
	FileWriter File2 = new FileWriter("result2/2013.txt");
	BufferedWriter bw = new BufferedWriter(File2);
	String str;
	int GameID=0;
	int HomeName=1;
	int AwayName=8;
	while((str=br.readLine())!=null){
		String[] items = str.split("\\s+");
		String NonameStr = "";
		for( int i=0 ; i<items.length ; i++){
			if ( i == GameID || i== HomeName || i== AwayName )
				continue;
			NonameStr += items[i] + " ";
		}
		bw.write(NonameStr+"\n");	
	
		
	}
	br.close();
	bw.close();
}
}