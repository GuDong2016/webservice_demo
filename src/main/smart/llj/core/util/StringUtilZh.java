package main.smart.llj.core.util;

import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;

public class StringUtilZh {
	public static String getNumberString(double str){
		String ret = null;
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		ret = nf.format(str);
		return ret;
	}
	public static String transString(String strName,String charsetName){
		String ret = null;
		try{
			ret = new String(strName.getBytes(charsetName),"iso8859-1");
		}catch(UnsupportedEncodingException e){
			System.out.println("String -> iso8859-1 error. s:=" + strName + e.getMessage());
		}
		return ret;
	}
}
