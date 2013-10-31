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

public class VectorCalcu {
	public void cal() throws IOException {
		File folder = new File(
				"/Users/jingliu/Documents/Study/RealTimeBigData/ProjectInput");
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
			if (file.isFile()) {
				String sttr = file.getName();

				if (sttr.matches("[0-9]{4}.*[txt]")) {
					// System.out.println(sttr);
					FileReader file1 = new FileReader(
							"/Users/jingliu/Documents/Study/RealTimeBigData/ProjectInput/"
									+ sttr);
					BufferedReader br = new BufferedReader(file1);
					Map<String, Double> map = new HashMap<String, Double>(); // key: team,
																																		// value:
																																		// sum of
																																		// time till
																																		// now
					Map<String, Integer> mapNumber = new HashMap<String, Integer>(); // key:
																																						// team,
																																						// value:
																																						// game
																																						// number
																																						// of
																																						// the
																																						// team
					Map<String, Double> mapAveToday = new HashMap<String, Double>();
					Map<String, Double> mapAve = new HashMap<String, Double>(); // key:
																																			// team,
																																			// value:
																																			// average
																																			// attack
																																			// or
																																			// defend
																																			// time
					
					List<String[]> list = new ArrayList<String[]>();         //store arrays that store vectors
					String s = "";
					String keys = "";
					String keyDef = "";
					double attValues = 0;
					double defValue = 0;
					int number = 0;
					int total = 0;
					double averageAtt = 0;
					double averageDef = 0;
					double average = 0;
					while ((s = br.readLine()) != null) {
						String[] array = new String[5];
						String[] lines = s.split("\\s");
						if (lines.length > 1) {
							array[0] = lines[0];
							list.add(array);
							keys = lines[0] + " " + lines[2]; // attack team
							attValues = Double.parseDouble(lines[4]); // attack time
							if (map.keySet().contains(keys)) {
								attValues = attValues + map.get(keys);
								map.put(keys, attValues);
								total = mapNumber.get(keys) + 1;
								mapNumber.put(keys, total);
							} else {
								map.put(keys, attValues);
								mapNumber.put(keys, 1);
							}

							keyDef = lines[0] + " " + lines[5]; // defend team
							defValue = Double.parseDouble(lines[7]); // defend time
							if (map.keySet().contains(keyDef)) {
								defValue = defValue + map.get(keyDef);
								map.put(keyDef, defValue);
								total = mapNumber.get(keyDef) + 1;
								mapNumber.put(keyDef, total);
							} else {
								map.put(keyDef, defValue);
								mapNumber.put(keyDef, 1);
							}
						}
						
						double todayHomeAttVal = 0;
						double todayHomeDefVal = 0;
						double todayAwayAttVal = 0;
						double todayAwayDefVal = 0;

						if(list.size() != 0 && list.size() % 2 == 0){
							String todayHomeAtt = "";
							String todayHomeDef = "";
							String todayAwayAtt = "";
							String todayAwayDef = "";
						
							todayHomeAtt = list.get(list.size()-2)[0] + " " + "attack";            //home team attack
							todayHomeDef = list.get(list.size()-2)[0] + " " + "defend";            //home team defend
							todayAwayAtt = list.get(list.size()-1)[0] + " " + "attack";					//away team attack
							todayAwayDef = list.get(list.size()-1)[0] + " " + "defend";					//away team defend
							
							todayHomeAttVal = map.get(todayHomeAtt) / mapNumber.get(todayHomeAtt);
							todayHomeDefVal = map.get(todayHomeDef) / mapNumber.get(todayHomeDef);
							todayAwayAttVal = map.get(todayAwayAtt) / mapNumber.get(todayAwayAtt);
							todayAwayDefVal = map.get(todayAwayDef) / mapNumber.get(todayAwayDef);
							
							if(mapAve.keySet().contains(todayHomeAtt)){
								list.get(list.size()-2)[1] = String.valueOf(todayHomeAttVal);
							}
							if(mapAve.keySet().contains(todayHomeDef)){
								list.get(list.size()-2)[2] = String.valueOf(todayHomeDefVal);
							}
							if(mapAve.keySet().contains(todayAwayAtt)){
								list.get(list.size()-1)[1] = String.valueOf(todayAwayAttVal);
							}
							if(mapAve.keySet().contains(todayAwayDef)){
								list.get(list.size()-1)[2] = String.valueOf(todayAwayDefVal);
							}
						}
					}

					Iterator<Entry<String, Double>> iter = map.entrySet().iterator(); // calculate
																																						// average
																																						// attack
																																						// and
																																						// defend
																																						// time
					while (iter.hasNext()) {
						@SuppressWarnings("rawtypes")
						Map.Entry entry = (Map.Entry) iter.next();
						String key = entry.getKey().toString();
						double val = map.get(key);
						number = mapNumber.get(key);
						average = val / number;
						mapAve.put(key, average);
					}
					System.out.println(sttr);
					printAve(mapAve);
					
					String searchHomeAtt = "";
					String searchHomeDef = "";
					String searchAwayAtt = "";
					String searchAwayDef = "";
					
					PrintWriter output = new PrintWriter(new FileWriter("/Users/jingliu/Documents/Study/RealTimeBigData/result/" + sttr));
					
					for(int i = 0; i < list.size(); i = i + 2){
						//System.out.println(list.get(i)[0]);
						searchHomeAtt = list.get(i)[0] + " " + "attack";            //home team attack
						searchHomeDef = list.get(i)[0] + " " + "defend";            //home team defend
						searchAwayAtt = list.get(i+1)[0] + " " + "attack";					//away team attack
						searchAwayDef = list.get(i+1)[0] + " " + "defend";					//away team defend
						
						if(mapAve.keySet().contains(searchHomeAtt)){
							list.get(i)[3] = mapAve.get(searchHomeAtt).toString();
						}
						if(mapAve.keySet().contains(searchHomeDef)){
							list.get(i)[4] = mapAve.get(searchHomeDef).toString();
						}
						if(mapAve.keySet().contains(searchAwayAtt)){
							list.get(i+1)[3] = mapAve.get(searchAwayAtt).toString();
						}
						if(mapAve.keySet().contains(searchAwayDef)){
							list.get(i+1)[4] = mapAve.get(searchAwayDef).toString();
						}
						//home name, home attack time, home defend time, away name, away attack time, away defend time
						output.println(list.get(i)[0] + " " + list.get(i)[1] + " " + list.get(i)[2] + " " + list.get(i)[3] + list.get(i)[4] + " " + list.get(i+1)[0] + " " + list.get(i+1)[1] + " " + list.get(i+1)[2] + " " + list.get(i+1)[3] + " " + list.get(i+1)[4]);

					}
				}
				
			}
		}
	}

	private void printAve(Map<String, Double> mapAve) {
		Iterator<Entry<String, Double>> iterAve = mapAve.entrySet().iterator();
		while (iterAve.hasNext()) {
			@SuppressWarnings("rawtypes")
			Map.Entry entry = (Map.Entry) iterAve.next();
			String key = entry.getKey().toString();
			double val = mapAve.get(key);
			System.out.println(key + " " + val);
		}

	}
}
