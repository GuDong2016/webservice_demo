package main.smart.llj.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
public class IdUtil {
	//随机字符串
	public static String getRandString(int length) {
		StringBuffer s = new StringBuffer("" + (new Date().getTime()));
		Random r = new Random(10);
		s.append(Math.abs(r.nextInt()));
		if (s.toString().length() > length)
			s.substring(0, length);
		return s.toString();
	}
	//随机20位字符串ID
	public static String getOnlyID() {
		String strRnd;
		double dblTmp;
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		// ---- 4random
		dblTmp = Math.random() * 100000;
		while (dblTmp < 10000) {
			dblTmp = Math.random() * 100000;
		}
		strRnd = String.valueOf(dblTmp).substring(0, 3);
		String s = df.format(new Date()) + strRnd;
		return s;
	}
}
