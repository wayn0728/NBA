package nba;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import Jama.Matrix;

public class Op_file {
	public static void trainDataTransfer() throws Exception{ 
		FileReader File1= new FileReader("result2/2012.txt");
		BufferedReader br= new BufferedReader(File1);
		FileWriter File2 = new FileWriter("2009_12.txt",true);
		BufferedWriter bw = new BufferedWriter(File2);
		String str;
		int i;
		while((str = br.readLine()) != null){
			String[] items = str.split(" ");

			bw.write(items[2] + " " + items[3]+ " " + items[5] + " "
					+ items[8] + " " + items[9] + " " + items[11] + " " + items[12] + "\n");

		}
		br.close();
		bw.close();
	}
	
	public static void testDataTransfer() throws Exception{ 
		FileReader File1= new FileReader("result2/2013.txt");
		BufferedReader br= new BufferedReader(File1);
		FileWriter File2 = new FileWriter("2013temp.txt");
		BufferedWriter bw = new BufferedWriter(File2);
		String str;
		int i;
		while((str = br.readLine()) != null){
			String[] items = str.split(" ");

			bw.write(items[0] + " " + items[1]+ " " + items[4] + " "
					+ items[6] + " " + items[7] + " " + items[10] + " " + items[12] + "\n");

		}
		br.close();
		bw.close();
	}

	public static void main(String []args) throws Exception{ 
		testDataTransfer();
	//	trainDataTransfer();
	}
}
