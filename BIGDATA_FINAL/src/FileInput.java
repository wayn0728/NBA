
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
public class FileInput {
	public static void main(String[] args) throws Exception
	{
	FileReader file1=new FileReader("/Users/Tony/Desktop/NBA/200010310ATL.html");
	   BufferedReader br=new BufferedReader(file1);
	   String s,st1,st2;
	   String n="";
	   int page=0;
	   int time=0;
	   boolean t;
	   while((s=br.readLine())!=null) {
		    page++;
	        if(s.compareTo(n)==0) 	continue;
		    if(s.matches("<table class=\"no_highlight stats_table\">")) {break;}
	   }
	   while((s=br.readLine())!=null) {
		    page++;
	        if(s.compareTo(n)==0) 	continue;
	        //  if(s.matches("<td class=\"align_right\">.*")) {
		 	//  System.out.print(page1+"\n");}
            if(s.matches(".*[\\d]{1,2}:[0-9][0-9].[0-9].*")) {
            st1=s.substring(s.indexOf('>')+1, s.indexOf(':',5));
            st2=s.substring(s.indexOf(':')+1, s.indexOf('.',5));
            time=60*Integer.valueOf(st1)+Integer.valueOf(st2);
	 	    System.out.println("second: "+time+"  "+page);}
         }
	   br.close();
	   }
	 }