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

	public void cal() throws IOException {
		File folder = new File("result/");
		File[] listOfFiles = folder.listFiles();
		String array[];
		List<String[]> act = new ArrayList<String[]>();
		for (File file : listOfFiles) {
			if (file.isFile()) {
				String sttr = file.getName();
				if (sttr.contains(".csv")) {
					System.out.println("aaaa");
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
		for (File file2 : listOfFiles) {
			if (file2.isFile()) {
				String sttr = file2.getName();

				if (sttr.matches("[0-9]{4}.*[txt]")) {
					String s = "";
					mapInputResult = new HashMap<String, String[]>();
					mapNumber = new HashMap<String, String[]>();
					mapInputAct = new HashMap<String, List<String>>();
					output = new PrintWriter(new FileWriter("result1/" + sttr));
					FileReader file1 = new FileReader("result/" + sttr);
					BufferedReader br = new BufferedReader(file1);
					while ((s = br.readLine()) != null) {
						list = new String[15];
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
						list[7] = array[6];
						list[8] = array[7];
						list[9] = array[8];
						list[10] = array[9];
						list[11] = array[10];
						list[12] = "";
						list[13] = "";
						list[14] = array[11];
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
							String ele2 = mapInputResult.get(act.get(j)[0])[7];
							// System.out.println(ele1);
							if (!mapNumber.keySet().contains(ele1)
									&& !mapNumber.keySet().contains(ele2)) {
								String[] pac = { String.valueOf(1), act.get(j)[1] };
								mapNumber.put(ele1, pac);
								mapNumber.put(ele2, pac);
								String[] result = mapInputResult.get(act.get(j)[0]);
								result[5] = act.get(j)[1];
								result[12] = act.get(j)[1];
								// System.out.println(result[13]);

								mapInputResult.put(act.get(j)[0], result);
							} else if (mapNumber.keySet().contains(ele1)
									&& !mapNumber.keySet().contains(ele2)) {
								int num = Integer.valueOf(mapNumber.get(ele1)[0]);
								double value = Double.parseDouble(act.get(j)[1])
										+ Double.parseDouble(mapNumber.get(ele1)[1]);
								String[] pac = { String.valueOf(num + 1), String.valueOf(value) };
								mapNumber.put(ele1, pac);
								String[] result = mapInputResult.get(act.get(j)[0]);
								result[5] = String.valueOf(Double.parseDouble(pac[1])
										/ Double.parseDouble(pac[0]));

								result[12] = act.get(j)[1];
								String[] pac1 = { String.valueOf(1), act.get(j)[1] };
								mapNumber.put(ele2, pac1);

								mapInputResult.put(act.get(j)[0], result);
							} else if (mapNumber.keySet().contains(ele2)
									&& !mapNumber.keySet().contains(ele1)) {
								int num = Integer.valueOf(mapNumber.get(ele2)[0]);
								double value = Double.parseDouble(act.get(j)[1])
										+ Double.parseDouble(mapNumber.get(ele2)[1]);
								String[] pac = { String.valueOf(num + 1), String.valueOf(value) };
								mapNumber.put(ele2, pac);
								String[] result = mapInputResult.get(act.get(j)[0]);
								result[12] = String.valueOf(Double.parseDouble(pac[1])
										/ Double.parseDouble(pac[0]));

								result[5] = act.get(j)[1];
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
								result[12] = String.valueOf(Double.parseDouble(pac[1])
										/ Double.parseDouble(pac[0]));

								int num1 = Integer.valueOf(mapNumber.get(ele1)[0]);
								double value1 = Double.parseDouble(act.get(j)[1])
										+ Double.parseDouble(mapNumber.get(ele1)[1]);
								String[] pac1 = { String.valueOf(num1 + 1),
										String.valueOf(value1) };
								mapNumber.put(ele1, pac1);
								result[5] = String.valueOf(Double.valueOf(pac1[1])
										/ Double.parseDouble(pac1[0]));
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
						String keyPace = finalResult[0];
						// System.out.println(keyPace);
						int count = Integer.parseInt(mapNumber.get(keyPace)[0]);
						double totalPace = Double.parseDouble(mapNumber.get(keyPace)[1]);
						double ave = totalPace / count;
						finalResult[6] = String.valueOf(ave);

						String keyPace1 = finalResult[7];
						int count1 = Integer.parseInt(mapNumber.get(keyPace1)[0]);
						double totalPace1 = Double.parseDouble(mapNumber.get(keyPace1)[1]);
						double ave1 = totalPace1 / count1;
						finalResult[13] = String.valueOf(ave1);

						mapInputResult.put(key, finalResult);
						output.print(key + " ");
						for (int i = 0; i < finalResult.length; i++) {
							output.print(finalResult[i] + " ");
						}
						output.println();
						output.flush();
					}

				}

			}
		}

	}

}
