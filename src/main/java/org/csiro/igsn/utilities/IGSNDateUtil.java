package org.csiro.igsn.utilities;

import java.text.SimpleDateFormat;

public class IGSNDateUtil {
	
	public static SimpleDateFormat getISODateFormatter(){
		return new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ssXXX");
	}
	
	public static SimpleDateFormat getISODateFormatterHtml(){
		return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS");
		
	}

}
