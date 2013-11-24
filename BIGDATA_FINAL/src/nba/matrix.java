package nba;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;


import Jama.Matrix;

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
		C.print(0, 4);
		*/
		int i,j;
		double var , sum = 0;
		Matrix A = new Matrix(2,2);
		Matrix B = new Matrix(2,2);
		FileReader File1 = new FileReader("2009_12.txt");
		BufferedReader br = new BufferedReader(File1);
		
		Matrix C = new Matrix(1,1);
		Matrix E = new Matrix(1,1);
		Matrix v = new Matrix(1,1);
		C = C.read(br);
		int row = C.getRowDimension();
		int column = C.getColumnDimension();
		A = C.getMatrix(0 , row-1 , 0 , column-2);
		B = C.getMatrix(0 , row-1 , column-1 , column-1);
		double max,min;
		// A left matrix  B real pace  
		//-----------------------------back to one
//		max = Double.MIN_VALUE;
//		min = Double.MAX_VALUE;
//		double temp;
//		for(i = 0 ; i < row ; i++)
//			for(j = 0 ; j < column-1 ; j++){
//				if ( A.get(i , j) > max) 
//					max = A.get(i , j) ;
//				if ( A.get(i , j) < min) 
//					min = A.get(i , j) ;
//			}
//		
//		for(i = 0 ; i < row ; i++)
//			for(j = 0 ; j < column-1 ; j++){
//				temp= ( A.get(i, j) - min ) / (max - min);
//				A.set(i, j, temp);
//			}
			//A.print(1, 3);
		
		//-----------------------------back to one
		v = A.solve(B);
		v.print( 1 , 15 );
		//v: ={{-2.3523},{5.2955},{-2.2462},{5.1127}};
		
		FileReader File2= new FileReader("2013temp.txt");
		BufferedReader br2= new BufferedReader(File2);
		C=C.read(br2);
		
		row=C.getRowDimension();
		column=C.getColumnDimension();
		A=C.getMatrix(0 , row-1 , 0 , column-2);
		B=C.getMatrix(0 , row-1 , column-1 , column-1);
		// 2013:  A left matrix     B right vector : real pace
		//-----------------------------back to one
//		max = Double.MIN_VALUE;
//	    min = Double.MAX_VALUE;
//		for(i = 0 ; i < row ; i++)
//			for(j = 0 ; j < column-1 ; j++){
//				if ( A.get(i , j) > max) 
//					max = A.get(i , j) ;
//				if ( A.get(i , j) < min) 
//					min = A.get(i , j) ;
//			}
//		
//		for(i = 0 ; i < row ; i++)
//			for(j = 0 ; j < column-1 ; j++){
//				temp= ( A.get(i, j) - min ) / (max - min);
//				A.set(i, j, temp);
//			}
//			//A.print(1,3);
		
		//-----------------------------back to one
		C = A.times(v); // C    A * v (result from 2009-2012 least squares)
		E = C.minus(B); // 
		//E.print(1, 15);
		int E_row=E.getRowDimension();
		for(i=0 ; i < E_row ; i++)
			{	
				sum += (E.get(i, 0)) * (E.get(i, 0));
			}
		// sum the squares 
		var = sum / E_row;
		System.out.println(var);
		
	}
}
