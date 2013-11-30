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
        PrintWriter output;

        public void cal() throws IOException {
                File folder = new File(
                                "mid/");
                File[] listOfFiles = folder.listFiles();
                Map<String, String[]> actPace = new HashMap<String, String[]>();
                for (File file : listOfFiles) {

                        String sttr = file.getName();
                        if (sttr.contains("actualPace.csv")) {
                                FileReader file1 = new FileReader(
                                                "mid/"
                                                                + sttr);
                                BufferedReader br = new BufferedReader(file1);
                                output = new PrintWriter(new FileWriter(
                                    "result/" + sttr));
                                
                                // System.out.println(sttr);
                                String s = "";
                                while ((s = br.readLine()) != null) {
                                        // System.out.println(s);
                                				output.println(s);
                                        String[] lines = s.split("\\s");
                                        if (lines.length == 3) {
                                        	String[] actAndDay = new String[2];
                                        	actAndDay[0] = String.valueOf(0);
                                        	actAndDay[1] = lines[2];
                                                actPace.put(lines[1], actAndDay);
                                                // System.out.println(lines[1]);
                                        }
                                }
                                output.flush();
                        }
                                       
                }
                
                
                for (File file : listOfFiles) {

                  String sttr = file.getName();
                  if (sttr.contains("actualDaySinceStart.csv")) {
                          FileReader file1 = new FileReader(
                                          "mid/"
                                                          + sttr);
                          BufferedReader br = new BufferedReader(file1);
                          output = new PrintWriter(new FileWriter(
                              "result/" + sttr));
                          
                          // System.out.println(sttr);
                          String s = "";
                          while ((s = br.readLine()) != null) {
                                  // System.out.println(s);
                          				output.println(s);
                                  String[] lines = s.split("\\s");
                                  if(actPace.get(lines[0]) != null){
                                  String[] keyGameID = actPace.get(lines[0]);
                                  keyGameID[0] = lines[1];
                                          actPace.put(lines[1], keyGameID);
                                          // System.out.println(lines[1]);
                                  }
                          }
                          output.flush();
                  }
                                 
          }
                
                for (File file : listOfFiles) {

                  String sttr = file.getName();
                  if (sttr.contains("restDay1980.csv")) {
                          FileReader file1 = new FileReader(
                                          "mid/"
                                                          + sttr);
                          BufferedReader br = new BufferedReader(file1);
                          output = new PrintWriter(new FileWriter(
                              "result/" + sttr));
                          
                          // System.out.println(sttr);
                          String s = "";
                          while ((s = br.readLine()) != null) {
                                  // System.out.println(s);
                          				output.println(s);
                               
                          }
                          output.flush();
                  }
                                 
          }

                for (File file : listOfFiles) {
                        // Map<String, String> actPace= new HashMap<String, String>();
                        Map<String, String> result = new HashMap<String, String>();
                        if (file.isFile()) {
                                String sttr = file.getName();
                                FileReader file1 = new FileReader(
                                                "mid/"
                                                                + sttr);
                                BufferedReader br = new BufferedReader(file1);
                                // List<String> resultList = new ArrayList<String>();
                                // Map<String, String> result = new HashMap<String, String>();
                               
                                // Map<String, String> actPace= new HashMap<String, String>();

                                if (sttr.matches("[0-9]{4}.*[txt]")) {
                                	 output = new PrintWriter(new FileWriter(
                                       "result/" + sttr));
                                        // System.out.println(sttr);
                                        /*
                                         * FileReader file1 = new FileReader(
                                         * "/Users/jingliu/Documents/Study/RealTimeBigData/ProjectInput/" +
                                         * sttr); BufferedReader br = new BufferedReader(file1);
                                         */
                                        Map<String, Double[]> map = new HashMap<String, Double[]>(); // key: team,
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

                                        List<String[]> list = new ArrayList<String[]>(); // store arrays that
                                                                                                                                                                                                                                                // store vectors
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
                                                String id[] = new String[1];
                                                String[] lines = s.split("\\s");
                                                if (lines.length == 1) {
                                                        id[0] = lines[0];
                                                        list.add(id);

                                                }
                                                if (lines.length > 1) {
                                                        array[0] = lines[0];
                                                        list.add(array);
                                                        keys = lines[0] + " " + lines[2]; // attack team
                                                        attValues = Double.parseDouble(lines[4]); // attack time
                                                        if (map.keySet().contains(keys)) {
                                                        	Double[] data = new Double[2];

                                                        				data[0] = map.get(keys)[1];
                                                                attValues = attValues + map.get(keys)[1];
                                                                data[1] = attValues;
                                                                map.put(keys, data);
                                                                total = mapNumber.get(keys) + 1;
                                                                mapNumber.put(keys, total);
                                                        } else {
                                                        	Double[] data = new Double[2];
                                                        	data[0] = 0.00;
                                                        	data[1] = attValues;
                                     
                                                                map.put(keys, data);
                                                                mapNumber.put(keys, 1);
                                                        }

                                                        keyDef = lines[0] + " " + lines[5]; // defend team
                                                        defValue = Double.parseDouble(lines[7]); // defend time
                                                        if (map.keySet().contains(keyDef)) {
                                                        	Double[] data = new Double[2];
                                                        	data[0] = map.get(keyDef)[1];
                                                        	
                                                                defValue = defValue + map.get(keyDef)[1];
                                                                data[1] = defValue;
                                                                map.put(keyDef, data);
                                                                total = mapNumber.get(keyDef) + 1;
                                                                mapNumber.put(keyDef, total);
                                                        } else {
                                                        	Double[] data = new Double[2];
                                                        	data[0] = 0.00;
                                                        	data[1] = defValue;
                                                                map.put(keyDef, data);
                                                                mapNumber.put(keyDef, 1);
                                                        }
                                                }

                                                double todayHomeAttVal = 0;
                                                double todayHomeDefVal = 0;
                                                double todayAwayAttVal = 0;
                                                double todayAwayDefVal = 0;

                                                if (list.size() != 0 && list.size() % 3 == 0) {
                                                        String todayHomeAtt = "";
                                                        String todayHomeDef = "";
                                                        String todayAwayAtt = "";
                                                        String todayAwayDef = "";

                                                        todayHomeAtt = list.get(list.size() - 2)[0] + " " + "attack"; // home
                                                                                                                                                                                                                                                                                                                // team
                                                                                                                                                                                                                                                                                                                // attack
                                                        todayHomeDef = list.get(list.size() - 2)[0] + " " + "defend"; // home
                                                                                                                                                                                                                                                                                                                // team
                                                                                                                                                                                                                                                                                                                // defend
                                                        todayAwayAtt = list.get(list.size() - 1)[0] + " " + "attack"; // away
                                                                                                                                                                                                                                                                                                                // team
                                                                                                                                                                                                                                                                                                                // attack
                                                        todayAwayDef = list.get(list.size() - 1)[0] + " " + "defend"; // away
                                                                                                                                                                                                                                                                                                                // team
                                                                                                                                                                                                                                                                                                                // defend
                                                        if(mapNumber.get(todayHomeAtt) != 1){
                                                        todayHomeAttVal = map.get(todayHomeAtt)[0]
                                                                        / (mapNumber.get(todayHomeAtt) - 1);
                                                        }
                                                        else if(mapNumber.get(todayHomeAtt) == 1){
                                                        	todayHomeAttVal = 0;
                                                        }
                                                        
                                                        if(mapNumber.get(todayHomeDef) != 1)
                                                        {
                                                        todayHomeDefVal = map.get(todayHomeDef)[0]
                                                                        / (mapNumber.get(todayHomeDef) - 1);
                                                        }
                                                        else if(mapNumber.get(todayHomeDef) == 1){
                                                        	todayHomeDefVal = 0;
                                                        }
                                                        
                                                        if(mapNumber.get(todayAwayAtt) != 1){
                                                        todayAwayAttVal = map.get(todayAwayAtt)[0]
                                                                        / (mapNumber.get(todayAwayAtt) - 1);
                                                        }
                                                        else if(mapNumber.get(todayAwayAtt) == 1){
                                                        	todayAwayAttVal = 0;
                                                        }
                                                        
                                                        if(mapNumber.get(todayAwayDef) !=1 ){
                                                        todayAwayDefVal = map.get(todayAwayDef)[0]
                                                                        / (mapNumber.get(todayAwayDef) - 1);
                                                        }
                                                        else if(mapNumber.get(todayAwayDef) == 1){
                                                        	todayAwayDefVal = 0;
                                                        }
                                                        // if(mapAve.keySet().contains(todayHomeAtt)){

                                                        list.get(list.size() - 2)[1] = String.valueOf(todayHomeAttVal);

                                                        // if(list.get(list.size()-2)[0].equals(todayHomeDefVal)){
                                                        list.get(list.size() - 2)[2] = String.valueOf(todayHomeDefVal);
                                                        // }
                                                        // if(mapAve.keySet().contains(todayAwayAtt)){
                                                        list.get(list.size() - 1)[1] = String.valueOf(todayAwayAttVal);
                                                        // }
                                                        // if(mapAve.keySet().contains(todayAwayDef)){
                                                        list.get(list.size() - 1)[2] = String.valueOf(todayAwayDefVal);
                                                        // }
                                                }
                                        }

                                        Iterator<Entry<String, Double[]>> iter = map.entrySet().iterator(); // calculate
                                                                                                                                                                                                                                                                                                                // average
                                                                                                                                                                                                                                                                                                                // attack
                                                                                                                                                                                                                                                                                                                // and
                                                                                                                                                                                                                                                                                                                // defend
                                                                                                                                                                                                                                                                                                                // time
                                        while (iter.hasNext()) {
                                                @SuppressWarnings("rawtypes")
                                                Map.Entry entry = (Map.Entry) iter.next();
                                                String key = entry.getKey().toString();
                                                double val = map.get(key)[1];
                                                number = mapNumber.get(key);
                                                average = val / number;
                                                mapAve.put(key, average);
                                        }

                                        String searchHomeAtt = "";
                                        String searchHomeDef = "";
                                        String searchAwayAtt = "";
                                        String searchAwayDef = "";
                                        
                                        System.out.println("SIZE: " + list.size());
                                        int count = 0;
                                        for (int i = 0; i < list.size(); i = i + 3) {
                                                count++;
                                                // System.out.println(list.get(i)[0]);
                                                searchHomeAtt = list.get(i + 1)[0] + " " + "attack"; // home team
                                                                                                                                                                                                                                                                        // attack
                                                searchHomeDef = list.get(i + 1)[0] + " " + "defend"; // home team
                                                                                                                                                                                                                                                                        // defend
                                                searchAwayAtt = list.get(i + 2)[0] + " " + "attack"; // away team
                                                                                                                                                                                                                                                                        // attack
                                                searchAwayDef = list.get(i + 2)[0] + " " + "defend"; // away team
                                                                                                                                                                                                                                                                        // defend

                                                list.get(i + 1)[3] = mapAve.get(searchHomeAtt).toString();
                                                list.get(i + 1)[4] = mapAve.get(searchHomeDef).toString();
                                                list.get(i + 2)[3] = mapAve.get(searchAwayAtt).toString();
                                                list.get(i + 2)[4] = mapAve.get(searchAwayDef).toString();
                                               if(Double.parseDouble(list.get(i + 1)[1]) != 0.0 && Double.parseDouble(list.get(i + 1)[2]) != 0.0 && Double.parseDouble(list.get(i + 2)[1]) != 0.0 && Double.parseDouble(list.get(i + 2)[2]) != 0){
                                                result.put(
                                                                list.get(i)[0],
                                                                list.get(i + 1)[0] + " " + list.get(i + 1)[1] + " "
                                                                                + list.get(i + 1)[2] + " " + list.get(i + 1)[3] + " "
                                                                                + list.get(i + 1)[4] + " " + list.get(i + 2)[0] + " "
                                                                                + list.get(i + 2)[1] + " " + list.get(i + 2)[2] + " "
                                                                                + list.get(i + 2)[3] + " " + list.get(i + 2)[4]);
                                        
                                               }
                                                if(list.get(i)[0] == null || "".equals(list.get(i)[0])){
                                                        System.out.println("NULL");
                                                }
                                                //output.println(result.get(list.get(i)[0]) );
                                                //output.println(count);
                                        }
                                        //output.flush();
                                        //System.out.println("Count: " + count);

                                }

                        }
                        combine(result, actPace);
                }
        }

        public void combine(Map<String, String> result, Map<String, String[]> act) {
                int count = 0;
                List<String> removeKey = new ArrayList<String>();
                for(String key:result.keySet()){
                        String val = result.get(key);
                        if (act.keySet().contains(key)) {
                                //count++;
                         output.println(key + " " + val + " " + act.get(key)[0] + " " + act.get(key)[1]);
                        } else {
                                removeKey.add(key);
                        }
                }
                
                output.flush();
                for (int i = 0; i < removeKey.size(); i++) {
                        //System.out.println(removeKey.size());
                        result.remove(removeKey.get(i));
                }
                // System.out.println(result.size());
        }

        private void printResult(Map<String, String> result) {

                Iterator<Entry<String, String>> iterAve = result.entrySet().iterator();
                while (iterAve.hasNext()) {
                        @SuppressWarnings("rawtypes")
                        Map.Entry entry = (Map.Entry) iterAve.next();
                        String key = entry.getKey().toString();
                        String val = result.get(key);
                        System.out.println(key + " " + val);
                }

        }
}