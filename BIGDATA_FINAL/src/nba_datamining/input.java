package nba_datamining;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class input {
	public static void main(String[] args) throws Exception
	{
		
		ArrayList<TableCell> cells = new ArrayList<TableCell>();
		
	    FileReader file1=new FileReader("/Users/Tony/Desktop/NBA/200010310DAL.html");
		BufferedReader br=new BufferedReader(file1);
		String s,st1,st2,tmpS,homeName="",awayName="";
		String n="";
		int p=0,i=0,iend=0,k=0,tmp=0;
		int time1,time2=720;
		int[] attack=new int[1000];
		int[] defend=new int[1000];
		int[] attack1=new int[1000];
		int[] defend1=new int[1000];
		boolean t;
		double average_attack,average_defend;
		int center,keyword,sum=0;
//		TableCell cell = new TableCell();
		int flag=0;
		while((s=br.readLine())!=null) {
			if(s.compareTo(n)==0) 	continue;
			Pattern pattern = Pattern.compile("<td align=\"left\" ><a href=\"/teams/([A-Z][A-Z][A-Z])");
	    	Matcher matcher = pattern.matcher(s);
	    	if(matcher.find()){
	    		//System.out.print(matcher.group(1) + "\t");
	    		if(flag==0) {awayName=matcher.group(1);flag=1;}
	    		if(flag==1) homeName=matcher.group(1);
	    	}
			if(s.matches("<table class=\"no_highlight stats_table\">")) {break;}
		}
		while((s=br.readLine())!=null) {
			if(s.compareTo(n)==0) 	continue;
			if(s.matches(".*[\\d]{1,2}:[0-9][0-9].[0-9].*")) {
				TableCell cell=new TableCell();
				st1=s.substring(s.indexOf('>')+1, s.indexOf(':',5));
				st2=s.substring(s.indexOf(':')+1, s.indexOf('.',5));
				cell.time=st1+":"+st2+".0";
				if(cell.time.compareTo("12:00.0")==0||cell.time.compareTo("0:00.0")==0) continue;
				   //jump ball
				s=br.readLine();
				if(s.indexOf("Jump ball")>0)  continue;
				tmp=s.indexOf("</td>");
				tmpS=s.substring(s.indexOf("<td")+4,tmp);
				if(tmpS.compareTo("&nbsp;")==0) tmpS="";
				else{   
					if(tmpS.indexOf("misses 2-pt")>0) tmpS="misses 2-pt by away";
					if(tmpS.indexOf("misses 3-pt")>0) tmpS="misses 3-pt by away";
					if(tmpS.indexOf("makes 2-pt")>0) tmpS="makes 2-pt by away";
					if(tmpS.indexOf("Official timeout")>0) tmpS="timeout";
					if(tmpS.indexOf("makes 3-pt")>0) tmpS="makes 3-pt by away";
					if(tmpS.indexOf("Defensive rebound")>0) tmpS="Defensive rebound by away";
					if(tmpS.indexOf("Turnover")>0) tmpS="TurnOver by home";
					if(tmpS.indexOf("makes free throw")>0) tmpS="makes free throw by away";
					if(tmpS.indexOf("misses free throw")>0) tmpS="misses free throw by away";
					if(tmpS.indexOf("enters the game for")>0) tmpS="somebody enters the game";
					if(tmpS.indexOf("Offensive")>0)  tmpS="Offensive rebound by away";
					if(tmpS.indexOf("foul")>0)   tmpS="foul by home";
					if(tmpS.indexOf("Violation")>0) tmpS="Violation by home";
					if(tmpS.indexOf("foul")>0)   tmpS="foul by home";
					if(tmpS.indexOf("full timeout")>0)   tmpS="full timeout";
				};
				cell.awayEvent=tmpS;
				tmpS=s.substring(s.indexOf("<td",tmp+1)+4,s.indexOf("/td>",tmp+4)-1);
				tmp=s.indexOf("/td>",tmp+4)+3;
				if(tmpS.indexOf("&nbsp;")>-1) tmpS="";
				else{
					if(tmpS.indexOf("+2")>-1)   tmpS="+2";
					if(tmpS.indexOf("+3")>-1)   tmpS="+3";
					if(tmpS.indexOf("+1")>-1)   tmpS="+1";
				}
				cell.awayRecord=tmpS;
				
				tmpS=s.substring(s.indexOf("<td",tmp+1)+4,s.indexOf("/td>",tmp+4)-1);
				tmpS=tmpS.replaceAll("[^0-9]+[>]","");
				cell.score=tmpS;
				
				tmp=s.indexOf("/td>",tmp+4)+3;
			    tmpS=s.substring(s.indexOf("<td",tmp+1)+4,s.indexOf("/td>",tmp+4)-1);
				tmp=s.indexOf("/td>",tmp+4)+3;
				if(tmpS.indexOf("&nbsp;")>-1) tmpS="";
				else{
					if(tmpS.indexOf("+2")>-1)   tmpS="+2";
					if(tmpS.indexOf("+3")>-1)   tmpS="+3";
					if(tmpS.indexOf("+1")>-1)   tmpS="+1";
				}
				cell.homeRecord=tmpS;
				
				tmpS=s.substring(s.indexOf("<td",tmp+1)+4,s.indexOf("/td>",tmp+4)-1);
				if(tmpS.compareTo("&nbsp;")==0) tmpS="";
				else{   
					if(tmpS.indexOf("misses 2-pt")>0) tmpS="misses 2-pt by away";
					if(tmpS.indexOf("misses 3-pt")>0) tmpS="misses 3-pt by away";
					if(tmpS.indexOf("makes 2-pt")>0) tmpS="makes 2-pt by away";
					if(tmpS.indexOf("Official timeout")>0) tmpS="timeout";
					if(tmpS.indexOf("makes 3-pt")>0) tmpS="makes 3-pt by away";
					if(tmpS.indexOf("Defensive rebound")>0) tmpS="Defensive rebound by away";
					if(tmpS.indexOf("Turnover")>0) tmpS="TurnOver by home";
					if(tmpS.indexOf("makes free throw")>0) tmpS="makes free throw by away";
					if(tmpS.indexOf("misses free throw")>0) tmpS="misses free throw by away";
					if(tmpS.indexOf("enters the game for")>0) tmpS="somebody enters the game";
					if(tmpS.indexOf("Offensive")>0)  tmpS="Offensive rebound by away";
					if(tmpS.indexOf("foul")>0)   tmpS="foul by home";
					if(tmpS.indexOf("Violation")>0) tmpS="Violation by home";
					if(tmpS.indexOf("foul")>0)   tmpS="foul by home";
					if(tmpS.indexOf("full timeout")>0)   tmpS="full timeout";
				};
				
				cell.homeEvent=tmpS;
				
			    time1=60*Integer.valueOf(st1)+Integer.valueOf(st2);
				p=time2-time1;
				time2=time1;
				if(p>0)
				{
					center=s.indexOf("/td",s.indexOf("/td",s.indexOf("/td")+1)+1);
					keyword=s.indexOf("misses 2-pt")+s.indexOf("makes 2-pt")
							+s.indexOf("Turnover")+s.indexOf("Offensive")
							+s.indexOf("Violation by")+s.indexOf("Personal foul")
							+s.indexOf("Shooting foul by")+s.indexOf("full timeout")+7;
					if(keyword>center) {attack[i]=p;defend[i++]=0;}
					else {attack[i]=0;defend[i++]=p;}

				}
				iend=i;
				cells.add(cell);
			}
		}
		br.close();

		//for(TableCell x:cells) 
		//	System.out.println(x.time+" "+x.awayEvent+" "+x.awayRecord+" "+x.score+" "+x.homeRecord+" "+x.homeEvent);

		for(i=0;i<iend;i++)
		{
			if(attack[i]==0)continue;
			attack1[k]=attack[i];
			while(attack[i+1]>0)
			{attack1[k]+=attack[i+1];
			i++;
			}
			k++;
		}
		int kb=k;
		for(i=0,k=0;i<iend;i++)
		{
			if(defend[i]==0)continue;
			defend1[k]=defend[i];
			while(defend[i+1]>0)
			{defend1[k]+=defend[i+1];
			i++;
			}
			k++;
		}
		int kc=k;
		for(i=0;i<kb;i++){
			//System.out.print(attack1[i]+" ");
			sum+=attack1[i];
			//if(i%40==39) System.out.println("");
		}
		average_attack=Math.round(100*sum/kb)/100.0;
		System.out.println("\n"+homeName+" home attack average time is "+average_attack);
		sum=0;
		for(i=0;i<kc;i++){
			//System.out.print(defend1[i]+" ");
			sum+=defend1[i];
			//if(i%40==39) System.out.println("");
		}
		average_defend=Math.round(100*sum/kc)/100.0;
		System.out.print("\n"+awayName+" away attack average time is "+average_defend);  
		FileWriter File_2 = new FileWriter("/Users/Tony/Desktop/NBA/output.txt");
		BufferedWriter bw = new BufferedWriter(File_2);
		bw.write("\n"+homeName+" home attack average time is "+average_attack);
		bw.write("\n"+awayName+" away attack average time is "+average_defend);
		bw.close();
		}
}
