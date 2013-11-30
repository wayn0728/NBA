package nba;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class AddTwoVectors {
	PrintWriter output;
	Map<String, String[]> mapInputResult;
	String[] list; // store arrays that store vectors
	Map<String, String[]> mapNumber;
	Map<String, List<String>> mapInputAct;
	Map<String, String[]> restDay;


	public void cal() throws IOException {
		File folder = new File("result/");
		File[] listOfFiles = folder.listFiles();
		String array[];
		List<String[]> act = new ArrayList<String[]>();
		for (File file : listOfFiles) {
			if (file.isFile()) {
				String sttr = file.getName();
				if (sttr.contains("actualPace.csv")) {
					FileReader file1 = new FileReader("result/" + sttr);
					BufferedReader br = new BufferedReader(file1);
					String ss = "";
					while ((ss = br.readLine()) != null) {
						String[] idAndPace = ss.split("\\s");
						if (idAndPace.length == 3) {
							String[] a = { idAndPace[1], idAndPace[2] };
							act.add(a);
						}

					}
				}
			}
		}
		
		for (File file : listOfFiles) {
			if (file.isFile()) {
				String sttr = file.getName();
				if (sttr.contains("restDay1980.csv")) {
					restDay = new HashMap<String, String[]>();
					FileReader file1 = new FileReader("result/" + sttr);
					BufferedReader br = new BufferedReader(file1);
					String ss = "";
					while ((ss = br.readLine()) != null) {
						if(!ss.contains("Game")){
							String[] rest = ss.split("\\s");
							String[] formal = new String[2];
							formal[0] = rest[4];
							formal[1] = rest[5];
						  restDay.put(rest[0], formal);
						}
					}
				}
			}
		}
		
		for (File file2 : listOfFiles) {
			if (file2.isFile()) {
				String sttr = file2.getName();

				if (sttr.matches("[0-9]{4}.*[txt]")) {
					Map<String, List<Double>> storeAvePace = new HashMap<String, List<Double>>();
					String s = "";
					mapInputResult = new HashMap<String, String[]>();
					mapNumber = new HashMap<String, String[]>();
					mapInputAct = new HashMap<String, List<String>>();
					output = new PrintWriter(new FileWriter("result1/" + sttr));
					FileReader file1 = new FileReader("result/" + sttr);
					BufferedReader br = new BufferedReader(file1);
					while ((s = br.readLine()) != null) {
						
						list = new String[36];
						array = s.split("\\s");
						// for(int i = 1; i < array.length; i++){
						// list.add(array[i]);
						// }

						list[0] = array[1];
						list[1] = array[2];
						list[2] = array[3];
						list[3] = array[4];
						list[4] = array[5];
						list[5] = "";
						list[6] = "";
						list[7] = "";
						list[8] = "";
						list[9] = array[6];
						list[10] = array[7];
						list[11] = array[8];
						list[12] = array[9];
						list[13] = array[10];
						list[14] = "";
						list[15] = "";
						list[16] = "";
						list[17] = "";
						for(int i = 18; i < 34; i++){
							list[i] = "";
						}
						list[34] = array[11];
						list[35] = array[12];
						/**
						 * for(int i = 0; i < list.length; i++){ System.out.print(list[i] +
						 * "\t"); } System.out.println("\n");
						 **/
						mapInputResult.put(array[0], list);
					}
					System.out.println(act.size());
					for (int j = 0; j < act.size(); j++) {
						if (mapInputResult.keySet().contains(act.get(j)[0])) {
						
							String ele1 = mapInputResult.get(act.get(j)[0])[0];
							String ele2 = mapInputResult.get(act.get(j)[0])[9];
							// System.out.println(ele1);
							if (!mapNumber.keySet().contains(ele1) && !mapNumber.keySet().contains(ele2)) {
								String[] pac = { String.valueOf(1), act.get(j)[1] };
								List<Double> avePace1 = new ArrayList<Double>();
								List<Double> avePace2 = new ArrayList<Double>();
								mapNumber.put(ele1, pac);
								mapNumber.put(ele2, pac);
								avePace1.add(Double.parseDouble(act.get(j)[1]));
								avePace2.add(Double.parseDouble(act.get(j)[1]));
								storeAvePace.put(ele1, avePace1);
								storeAvePace.put(ele2, avePace2);
								String[] result = mapInputResult.get(act.get(j)[0]);
								result[5] = String.valueOf(0);
								result[7] = String.valueOf(0);
								result[14] = String.valueOf(0);
								result[16] = String.valueOf(0);
								mapInputResult.put(act.get(j)[0], result);
							} else if (mapNumber.keySet().contains(ele1) && !mapNumber.keySet().contains(ele2)) {
								int num = Integer.valueOf(mapNumber.get(ele1)[0]);
								double value = Double.parseDouble(act.get(j)[1])
										+ Double.parseDouble(mapNumber.get(ele1)[1]);
								String[] pac = { String.valueOf(num + 1), String.valueOf(value) };
								mapNumber.put(ele1, pac);
								List<Double> vaePac = storeAvePace.get(ele1);
								
								vaePac.add(Double.parseDouble(act.get(j)[1]));
								String[] result = mapInputResult.get(act.get(j)[0]);
								result[5] = String.valueOf ((Double.parseDouble(pac[1]) - Double.parseDouble(act.get(j)[1]))
										/ num);
								
								double squSum = 0;
								for(int i = 0; i < (vaePac.size() - 1); i++){
									squSum = squSum + (vaePac.get(i) - Double.parseDouble(result[5])) * (vaePac.get(i) - Double.parseDouble(result[5]));
								}
							
								if((vaePac.size() - 1) != 0)
								{
									result[7] = String.valueOf(squSum / (vaePac.size() - 1));
								}
								else{
									result[7] = String.valueOf(0);
								}
								storeAvePace.put(ele1, vaePac);
								
								List<Double> avePace2 = new ArrayList<Double>();
								avePace2.add(Double.parseDouble(act.get(j)[1]));
								result[14] = String.valueOf(0);
								result[16] = String.valueOf(0);
								storeAvePace.put(ele2, avePace2);
								String[] pac1 = { String.valueOf(1), act.get(j)[1] };
								mapNumber.put(ele2, pac1);
								mapInputResult.put(act.get(j)[0], result);
							} else if (mapNumber.keySet().contains(ele2) && !mapNumber.keySet().contains(ele1)) {
								int num = Integer.valueOf(mapNumber.get(ele2)[0]);
								double value = Double.parseDouble(act.get(j)[1])
										+ Double.parseDouble(mapNumber.get(ele2)[1]);
								String[] pac = { String.valueOf(num + 1), String.valueOf(value) };
								mapNumber.put(ele2, pac);
								List<Double> avePace = storeAvePace.get(ele2);
							
								String[] result = mapInputResult.get(act.get(j)[0]);
							
								result[14] = String.valueOf((Double.parseDouble(pac[1]) - Double.parseDouble(act.get(j)[1]))
										/ num);
								
								double squSum = 0;
								for(int i = 0; i < avePace.size(); i++){
									squSum = squSum + (avePace.get(i) - Double.parseDouble(result[14])) * (avePace.get(i) - Double.parseDouble(result[14]));
								}
									result[16] = String.valueOf(squSum / avePace.size());
								
								avePace.add(Double.parseDouble(act.get(j)[1]));
								storeAvePace.put(ele2, avePace);
								
								List<Double> avePace1 = new ArrayList<Double>();
								avePace1.add(Double.parseDouble(act.get(j)[1]));
								result[5] = String.valueOf(0);
								result[7] = String.valueOf(0);
								storeAvePace.put(ele1, avePace1);
								String[] pac1 = { String.valueOf(1), act.get(j)[1] };
								mapNumber.put(ele1, pac1);

								mapInputResult.put(act.get(j)[0], result);
							} else if (mapNumber.keySet().contains(ele2)
									&& mapNumber.keySet().contains(ele1)) {
								int num = Integer.parseInt(mapNumber.get(ele2)[0]);
								double value = Double.parseDouble(act.get(j)[1])
										+ Double.parseDouble(mapNumber.get(ele2)[1]);
								String[] pac = { String.valueOf(num + 1), String.valueOf(value) };
								mapNumber.put(ele2, pac);
								String[] result = mapInputResult.get(act.get(j)[0]);
								
								result[14] = String.valueOf((Double.parseDouble(pac[1]) - Double.parseDouble(act.get(j)[1]))
										/ num);
								
								List<Double> avePace2 = storeAvePace.get(ele2);
								double squSum = 0;
								for(int i = 0; i< avePace2.size(); i++){
									squSum = squSum + (avePace2.get(i) - Double.parseDouble(result[14])) * (avePace2.get(i) - Double.parseDouble(result[14]));
								}
								result[16] = String.valueOf(squSum / avePace2.size());
								
								avePace2.add(Double.parseDouble(act.get(j)[1]));
								storeAvePace.put(ele2, avePace2);
								
								int num1 = Integer.valueOf(mapNumber.get(ele1)[0]);
								double value1 = Double.parseDouble(act.get(j)[1])
										+ Double.parseDouble(mapNumber.get(ele1)[1]);
								String[] pac1 = { String.valueOf(num1 + 1),
										String.valueOf(value1) };
								mapNumber.put(ele1, pac1);
								result[5] = String.valueOf((Double.valueOf(pac1[1]) - Double.parseDouble(act.get(j)[1]))
										/ num1);
								List<Double> avePace1 = storeAvePace.get(ele1);
								double squSum1 = 0;
								for(int i = 0; i < avePace1.size(); i++){
									squSum1 = squSum1 + (avePace1.get(i) - Double.parseDouble(result[5])) * (avePace1.get(i) - Double.parseDouble(result[5]));
								}
								result[7] = String.valueOf(squSum1 / avePace1.size());
								avePace1.add(Double.parseDouble(act.get(j)[1]));
								storeAvePace.put(ele1, avePace1);
					
								mapInputResult.put(act.get(j)[0], result);
							}
						}
					}
					Iterator<Entry<String, String[]>> iter = mapInputResult.entrySet()
							.iterator();
					while (iter.hasNext()) {
						@SuppressWarnings("rawtypes")
						Map.Entry entry = (Map.Entry) iter.next();
						String key = entry.getKey().toString();
						String[] finalResult = mapInputResult.get(key);
						String[] teamRestDay = restDay.get(key);
						if(teamRestDay[0].equals("1")){
							for(int i = 18; i < 26; i++){
								if(i == 18){
									finalResult[i] = String.valueOf(1);
								}
								else{
									finalResult[i] = String.valueOf(0);
								}
							}
						}
						else if(teamRestDay[0].equals("2")){
							for(int i = 18; i < 26; i++){
								if(i == 19){
									finalResult[i] = String.valueOf(1);
								}
								else{
									finalResult[i] = String.valueOf(0);
								}
							}
						}
						else if(teamRestDay[0].equals("5plus")){
							for(int i = 18; i < 26; i++){
								if(i == 20){
									finalResult[i] = String.valueOf(1);
								}
								else{
									finalResult[i] = String.valueOf(0);
								}
							}
						}
						else if(teamRestDay[0].equals("3plus")){
							for(int i = 18; i < 26; i++){
								if(i == 21){
									finalResult[i] = String.valueOf(1);
								}
								else{
									finalResult[i] = String.valueOf(0);
								}
							}
						}
						else if(teamRestDay[0].equals("3IN4")){
							for(int i = 18; i < 26; i++){
								if(i == 22){
									finalResult[i] = String.valueOf(1);
								}
								else{
									finalResult[i] = String.valueOf(0);
								}
							}
						}
						else if(teamRestDay[0].equals("B2B")){
							for(int i = 18; i < 26; i++){
								if(i == 23){
									finalResult[i] = String.valueOf(1);
								}
								else{
									finalResult[i] = String.valueOf(0);
								}
							}
						}
						else if(teamRestDay[0].equals("3IN4B2B")){
							for(int i = 18; i < 26; i++){
								if(i == 24){
									finalResult[i] = String.valueOf(1);
								}
								else{
									finalResult[i] = String.valueOf(0);
								}
							}
						}
						else if(teamRestDay[0].equals("4IN5")){
							for(int i = 18; i < 26; i++){
								if(i == 25){
									finalResult[i] = String.valueOf(1);
								}
								else{
									finalResult[i] = String.valueOf(0);
								}
							}
						}
						
						if(teamRestDay[1].equals("1")){
							for(int i = 26; i < 34; i++){
								if(i == 26){
									finalResult[i] = String.valueOf(1);
								}
								else{
									finalResult[i] = String.valueOf(0);
								}
							}
						}
						else if(teamRestDay[1].equals("2")){
							for(int i = 26; i < 34; i++){
								if(i == 27){
									finalResult[i] = String.valueOf(1);
								}
								else{
									finalResult[i] = String.valueOf(0);
								}
							}
						}
						else if(teamRestDay[1].equals("5plus")){
							for(int i = 26; i < 34; i++){
								if(i == 28){
									finalResult[i] = String.valueOf(1);
								}
								else{
									finalResult[i] = String.valueOf(0);
								}
							}
						}
						else if(teamRestDay[1].equals("3plus")){
							for(int i = 26; i < 34; i++){
								if(i == 29){
									finalResult[i] = String.valueOf(1);
								}
								else{
									finalResult[i] = String.valueOf(0);
								}
							}
						}
						else if(teamRestDay[1].equals("3IN4")){
							for(int i = 26; i < 34; i++){
								if(i == 30){
									finalResult[i] = String.valueOf(1);
								}
								else{
									finalResult[i] = String.valueOf(0);
								}
							}
						}
						else if(teamRestDay[1].equals("B2B")){
							for(int i = 26; i < 34; i++){
								if(i == 31){
									finalResult[i] = String.valueOf(1);
								}
								else{
									finalResult[i] = String.valueOf(0);
								}
							}
						}
						else if(teamRestDay[1].equals("3IN4B2B")){
							for(int i = 26; i < 34; i++){
								if(i == 32){
									finalResult[i] = String.valueOf(1);
								}
								else{
									finalResult[i] = String.valueOf(0);
								}
							}
						}
						else if(teamRestDay[1].equals("4IN5")){
							for(int i = 26; i < 34; i++){
								if(i == 33){
									finalResult[i] = String.valueOf(1);
								}
								else{
									finalResult[i] = String.valueOf(0);
								}
							}
						}
						String keyPace = finalResult[0];
						List<Double> storeActPace = storeAvePace.get(keyPace);
						

						// System.out.println(keyPace);
						int count = Integer.parseInt(mapNumber.get(keyPace)[0]);
						double totalPace = Double.parseDouble(mapNumber.get(keyPace)[1]);
						double ave = totalPace / count;
						finalResult[6] = String.valueOf(ave);
						
						double totalPac = 0;
						for(int i = 0; i < storeActPace.size(); i++){
							totalPac = totalPac + (storeActPace.get(i) - Double.parseDouble(finalResult[6])) * (storeActPace.get(i) - Double.parseDouble(finalResult[6]));
						}
						
						finalResult[8] = String.valueOf((totalPac / storeActPace.size()));
						String keyPace1 = finalResult[9];
						int count1 = Integer.parseInt(mapNumber.get(keyPace1)[0]);
						double totalPace1 = Double.parseDouble(mapNumber.get(keyPace1)[1]);
						double ave1 = totalPace1 / count1;
						finalResult[15] = String.valueOf(ave1);
						List<Double> storeActPace1 = storeAvePace.get(keyPace1);
						double totalPac1 = 0;
						for(int i = 0; i < storeActPace1.size(); i++){
							totalPac1 = totalPac1 + (storeActPace1.get(i) - Double.valueOf(finalResult[15])) * (storeActPace1.get(i) - Double.valueOf(finalResult[15]));
						}
						finalResult[17] = String.valueOf(totalPac1 / storeActPace1.size());
						mapInputResult.put(key, finalResult);

						
						if (Double.parseDouble(finalResult[5]) != 0 && Double.parseDouble(finalResult[14]) != 0) {
							output.print(key + " ");						
							}
						
						for (int i = 0; i < finalResult.length; i++) {
							if (Double.parseDouble(finalResult[5]) != 0 && Double.parseDouble(finalResult[14]) != 0) {
								output.print(finalResult[i] + " ");
							}
						}
						if (Double.parseDouble(finalResult[5]) != 0 && Double.parseDouble(finalResult[14]) != 0) {
							output.println();					
							}
						
						output.flush();
					}

				}

			}
		}

	}

}
