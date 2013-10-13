
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
	   int p=0,i=0;
	   int time1,time2=720;
	   int[] a=new int[1000];
	   int[] d=new int[1000];
	   boolean t;
	   int center,keyword;
	   while((s=br.readLine())!=null) {
		    if(s.compareTo(n)==0) 	continue;
		    if(s.matches("<table class=\"no_highlight stats_table\">")) {break;}
	   }
	   while((s=br.readLine())!=null) {
		    if(s.compareTo(n)==0) 	continue;
	        if(s.matches(".*[\\d]{1,2}:[0-9][0-9].[0-9].*")) {
            st1=s.substring(s.indexOf('>')+1, s.indexOf(':',5));
            st2=s.substring(s.indexOf(':')+1, s.indexOf('.',5));
            time1=60*Integer.valueOf(st1)+Integer.valueOf(st2);
            p=time2-time1;
           // System.out.println("s: "+time1+" "+p);
            time2=time1;
            if(p!=0)
            {
            	center=s.indexOf("/td",s.indexOf("/td",s.indexOf("/td")+1)+1);
            	keyword=s.indexOf("misses 2-pt")+s.indexOf("makes 2-pt")
            			+s.indexOf("Turnover")+s.indexOf("Offensive")
            			+s.indexOf("Violation by")+s.indexOf("Personal foul")
            			+s.indexOf("Shooting foul by")+s.indexOf("full timeout")+7;
            	System.out.println(keyword);
            if(keyword>center) {a[i]=p;d[i++]=0;}
            else {a[i]=0;d[i++]=p;}
            			
            }
            }
	        }
	   
	   br.close();
	   for(i=0;i<a.length;i++)
		   System.out.println(a[i]+" "+d[i]);
	   }
	 }