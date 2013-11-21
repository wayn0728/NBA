import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import libsvm.*;
import Jama.Matrix;

 /*
  * Big Data Final Project.
  * Qi Cai,
  * Jiawei Dong,
  * Jing Liu,
  * Shaoxin Xu
  * 
  */
//Linear Regression
public class regr_training{

	///svm///
	static int num_of_data =0;
	//set parameters for training data.
	
	/*
	 * trainData: double[numbers of entry][numbers of feature]. 
	 * teams:   assuming each team has a base value.
	 * file:    input file string. Please refer to "train.txt" for the file format.
	 * 
	 */
	/**
	 * @param train:  train array is returned from the method ReadData as parameter trainData.
	 * 
	 * This function will compute the MSE after training the dataset.
	 * 
	 */
	public static void svrTrain(double[][] train){
		 svm_problem prob = new svm_problem();
		 double[] predict_result = new double[train.length];
		 

		 int num_of_features= train[0].length-1;///total length - 1(y);
		 svm_parameter param = new svm_parameter();
		 		 
		 param.C = 1;
		 param.degree =3;
		 param.cache_size = 400;
		 param.svm_type = 4;
		 param.kernel_type=2;
		 param.gamma = 109;//need to set the gamma.
		 param.nr_weight = 0;
		 param.nu = 0.1;
		 //param.p = 0.1;
		 param.weight_label = new int[0];
		 param.weight = new double[0];
		 
		 prob.y = new double[num_of_data];
		 prob.l = num_of_data;
		 prob.x = new svm_node[num_of_data][]; 
		 
		 for (int i = 0; i < num_of_data; i++){ 
			 prob.y[i] = train[i][0];
			 prob.x[i] = new svm_node[num_of_features];
			 for(int j =1; j< num_of_features+1;j++){
				 svm_node node = new svm_node();
		         node.index = j;
		         node.value = train[i][j];
				 prob.x[i][j-1]=node;
			 }
		 }
		 svm.svm_cross_validation(prob, param, 2, predict_result);
	
		 System.out.print("\nResult: "+Mean_Square_Error(prob.y,predict_result)+ ".\n");
		 
	
		// return svm.svm_train(prob, param);		 
		// return svm.svm_train(prob, param);
	}
	
	
	
	/**
	 * @param expResult
	 * @param predResult
	 * @return MSE
	 */
	public static double Mean_Square_Error(double[] expResult,double[] predResult){
		double sum = 0;
		 for(int i = 0; i < expResult.length; i++){
			 System.out.print("Predict result: "+expResult[i]*100+ " "+predResult[i]*100+".\n");
			 sum += (predResult[i]*100 - expResult[i]*100)*(predResult[i]*100 - expResult[i]*100);
		 }
		 return sum/expResult.length;
	}
	
	

	/**
	 * @param trainData  trainData, double[numbers of entry][numbers of feature]. 
	 * @param teams      match team to one of the feature values.  Assuming each team has a base value.
	 * @param file       input file string. Please refer to "train.txt" for the file format.
	 */
	public static void ReadData(double[][] trainData,Map<String, Integer> teams,String file){
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String line;
			String[] input_Parameters;
			
			while ((line = br.readLine()) != null) {
				
				input_Parameters = line.split(" ");
				trainData[num_of_data][0] = Double.parseDouble(input_Parameters[input_Parameters.length-1])/100;
				for(int i = 1; i<input_Parameters.length-1; i ++)	{
					if(i==1 || i ==6){
						trainData[num_of_data][i] = teams.get(input_Parameters[i]);				
					}
					else{
						trainData[num_of_data][i] = Double.parseDouble(input_Parameters[i]);
					}
					
				}
				System.out.print("Entry "+num_of_data+" success.\n");
				num_of_data++;
		} 
			System.out.print("Finish.\n");
			br.close();
			}catch (Exception e) {
				
			e.fillInStackTrace();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	///svm///
	
	/*/
	 * 
	 * Program entry point: read train.txt file from local. And call function svrTrain();
	 *  
	 */
	
	public static void main(String[] args){
		Map<String, Integer> teams = new HashMap<String, Integer>();
		teams.put("ATL", 1);	teams.put("GSW", 10);	teams.put("NOH", 19);	teams.put("TOR", 28);
		teams.put("BRK", 2);	teams.put("HOU", 11);	teams.put("NYK", 20);	teams.put("UTA", 28);
		teams.put("BOS", 3);	teams.put("IND", 12);	teams.put("OKC", 21);	teams.put("WAS", 28);
		teams.put("CHA", 4);	teams.put("LAC", 13);	teams.put("ORL", 22);
		teams.put("CHI", 5);	teams.put("LAL", 14);	teams.put("PHI", 23);
		teams.put("CLE", 6);	teams.put("MEM", 15);	teams.put("PHO", 24);
		teams.put("DAL", 7);	teams.put("MIA", 16);	teams.put("POR", 25);
		teams.put("DEN", 8);	teams.put("MIL", 17);	teams.put("SAC", 26);
		teams.put("DET", 9);	teams.put("MIN", 18);	teams.put("SAS", 27);
		
		double[][] trainData = new double[100000][11];
		String file = "/home/shao/bigData/Final_Project/src/train";
		
		
		ReadData(trainData,teams,file);
		svrTrain(trainData);
		 //svm.svm_predict(arg0, arg1);
		
	}
	
	
	
}
