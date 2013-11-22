import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import Jama.*;


public class matrix {
	public static void main(String []args) throws Exception{ 
		/*double[][] vals = {{1,2},{3,5},{2,2},{10,100}};
		double[][] target = {{-1},{-1.8},{0.2},{-88}};
		Matrix A = new Matrix(vals);
		Matrix B = new Matrix(target);
	    A.print(0, 0);
	    B.print(0, 0);
	    Matrix C = new Matrix(2,1);
	    C=A.solve(B);
		C.print(0, 4);*/
		Matrix A = new Matrix(2,2);
		Matrix B = new Matrix(2,2);
		Matrix D = new Matrix(2,2);
		FileReader File1= new FileReader("2009temp.txt");
		BufferedReader br= new BufferedReader(File1);
		Matrix C = new Matrix(1,1);
		C=C.read(br);
		int row=C.getRowDimension();
		int column=C.getColumnDimension();
		A=C.getMatrix(0,row-1,0,column-2);
		B=C.getMatrix(0,row-1,column-1,column-1);
		B.print(1, 4);
		A.print(1, 4);
		D=A.solve(B);
		D.print(1, 4);
		
		/*
		FileWriter File2 = new FileWriter("2013temp.txt");
		BufferedWriter bw = new BufferedWriter(File2);
		String x;
		int i;
		while((x=br.readLine())!=null){
			x=x.substring(17);
			for(i=0;i<x.length();i++)
				{if(x.charAt(i)>='A') 
					break;}
			bw.write(x.substring(0,i)+x.substring(i+3)+"\n");
			
		}
		br.close();
		bw.close();*/
	}
	
}
