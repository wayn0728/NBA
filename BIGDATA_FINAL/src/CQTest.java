
public class CQTest {

	public static void main(String[] args) throws Exception
	{
		String f="<td class=\"background_white\"><a href=\"/players/c/campbel01.html\">E. Campbell</a> misses 2-pt shot from 5 ft</td><td class=\"align_right background_white\">&nbsp;</td><td class=\"align_center background_white\">2-4</td><td>&nbsp;</td><td>&nbsp;</td>";
		String tmpS=f;
		tmpS=tmpS.replaceAll("&nbsp;", "");
		//tmpS=tmpS.replaceAll("[class=\"background_][\\w]+[\">]", "");
		//tmpS=tmpS.replace("class=>", "");
		//tmpS=tmpS.replaceAll("[<][a][ ][\\w]{4}[=][\"][\\d\\w\\/]+[\">]", "");
		System.out.println(tmpS);
	}
}
