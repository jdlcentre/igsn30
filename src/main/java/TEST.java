import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TEST {
	public static void main(String [] args) throws ParseException{
		String date = "2012-02-22T00:00:00";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		java.util.Date d = df.parse(date);
		System.out.println(d);		
		
		java.util.Date date2= new java.util.Date();
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(date2);
		System.out.println(df2.format(date2));
				
	}

}
