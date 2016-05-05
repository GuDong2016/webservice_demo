package main.smart.llj.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期工具类
 * 
 * @author zhanghong
 */
public class DateUtilZh {
	/**
	 * Default date format.
	 */
	public static final String FORMAT_DEFAULTPATTERN = "yyyy-MM-dd HH:mm:ss"; //$NON-NLS-1$
	public static final String FORMAT_DEFAULTPATTERN_DATE = "yyyy-MM-dd"; //$NON-NLS-1$
	public static final String FORMAT_DEFAULTPATTERN_TIME = "HH:mm:ss"; //$NON-NLS-1$
	public static final String FORMAT_DEFAULTPATTERN_DATETIME = "yyyyMMddHHmmss"; //$NON-NLS-1$
	public static final String FORMAT_DEFAULTPATTERN_MILL = "yyyy-MM-dd HH:mm:ss.SSS"; //$NON-NLS-1$
	public static final String FORMAT_DEFAULTPATTERN_MONTH = "yyyy-MM";
	public static final String FORMAT_YYYYMM = "yyyyMM"; //$NON-NLS-1$

	public static final long day = 60 * 60 * 24 * 1000;
	public static final long hour = 60 * 60 * 1000;
	public static final long minute = 60 * 1000;

	public static final String HOUR_0 = " 00:00:00";
	public static final String HOUR_23 = " 23:59:59";
	/**
	 * 日期间隔
	 */
	public static final String INTERVAR_DAY = "day";// 本日
	public static final String INTERVAR_3DAY = "3day";// 3日内
	public static final String INTERVAR_HOUR = "hour";// 本小时内

	/**
	 * 根据日期间隔标志，转换得到日期字符串
	 * 
	 * @param interverStr
	 * @return
	 */
	public static String transInterverDateToString(String interverStr) {
		String ret = null;
		if (interverStr != null) {
			Date temp = transInterverDate(interverStr);
			if (temp != null) {
				ret = getFormattedDate(temp);
			}
		}
		return ret;
	}

	/**
	 * 根据日期间隔标志，转换得到日期字符串
	 * 
	 * @param interverStr
	 * @return
	 */
	public static Date transInterverDate(String interverStr) {
		Date ret = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		if (INTERVAR_DAY.equals(interverStr)) {
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
		} else if (INTERVAR_3DAY.equals(interverStr)) {
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - 3);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
		} else if (INTERVAR_HOUR.equals(interverStr)) {
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
		}
		ret = cal.getTime();
		return ret;
	}

	/**
	 * 转换时间长整型
	 * 
	 * @param interverStr
	 * @return
	 */
	public static long transInterverDateLong(String interverStr) {
		Date ret = transInterverDate(interverStr);
		return ret.getTime();
	}

	/**
	 * 根据参数在当前时间下，增加相应的时间。
	 * 
	 * @param dayhourminute
	 *            [?:?:?]
	 * @return
	 */
	public static Date addDayHourMinute(String dayhourminute) {
		if (dayhourminute != null && dayhourminute.trim().length() > 0) {
			String t[] = dayhourminute.split(":");
			if (t != null && t.length == 3) {
				long _d = new Integer(t[0]).intValue();
				long _h = new Integer(t[1]).intValue();
				long _m = new Integer(t[2]).intValue();
				long dt = new Date().getTime();
				dt = dt + _d * day + _h * hour + _m * minute;
				return new Date(dt);
			}
		}
		return null;
	}

	public static String getFormatDateTimeMill(Date date) {
		return getFormattedDate(date, FORMAT_DEFAULTPATTERN_MILL);
	}

	public static String getFormatDateTime(Date date) {
		return getFormattedDate(date, FORMAT_DEFAULTPATTERN_DATETIME);
	}

	public static String getFormatMonth_(Date date) {
		return getFormattedDate(date, FORMAT_DEFAULTPATTERN_MONTH);
	}

	public static String getFormattedDate(Date date) {
		return getFormattedDate(date, FORMAT_DEFAULTPATTERN);
	}

	public static String getFormatDate(Date date) {
		return getFormattedDate(date, FORMAT_DEFAULTPATTERN_DATE);
	}

	public static String getFormatTime(Date date) {
		return getFormattedDate(date, FORMAT_DEFAULTPATTERN_TIME);
	}

	public static String getFormatTimeYYYYYMM(Date date) {
		return getFormattedDate(date, FORMAT_YYYYMM);
	}

	public static String getFormattedDate(Date date, String formatPattern) {
		if (formatPattern == null) {
			formatPattern = FORMAT_DEFAULTPATTERN;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(formatPattern);
		String fd = sdf.format(date);
		return fd;
	}

	public static String getCurrentDateMinus(String endStr) {
		String ret = null;
		Date endDate = getDateForFormat(endStr);
		if (null != endDate) {
			ret = getDateMinus(new Date(), endDate);
		}
		return ret;
	}

	public static Date getDateForFormat(String dateStr) {
		Date t = null;
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DEFAULTPATTERN);
		try {
			t = sdf.parse(dateStr);
		} catch (ParseException e) {
			System.out.println("getDateForFormat()" + e.getMessage());
			t = null;
		}
		return t;
	}

	/**
	 * 
	 * @param dateStr
	 * @return "HHmmssdd..."
	 */
	public static String getDateMinus(Date beginDate, Date endDate) {
		String ret = null;
		if (beginDate != null && endDate != null) {
			long b = beginDate.getTime();
			long e = endDate.getTime();
			long temp = e - b;
			if (temp > 0) {
				long currentDay = temp / day;
				temp = temp % day;
				long currentHour = temp / hour;
				temp = temp % hour;
				long currentMinute = temp / minute;
				temp = temp % minute;
				long currentSecond = temp / 1000;
				StringBuffer sb = new StringBuffer();
				if (currentHour < 10) {
					sb.append(' ');
				}
				sb.append(currentHour);
				if (currentMinute < 10) {
					sb.append(' ');
				}
				sb.append(currentMinute);
				if (currentSecond < 10) {
					sb.append(' ');
				}
				sb.append(currentSecond);
				sb.append(currentDay);
				ret = sb.toString();
			}
		}

		return ret;
	}

	/**
	 * 
	 * @param dateStr
	 * @return seconds
	 */
	public static long getIntByDateMinus(Date beginDate, Date endDate) {
		long ret = 0;
		if (beginDate != null && endDate != null) {
			long b = beginDate.getTime();
			long e = endDate.getTime();
			ret = (e - b) / 1000;
		}
		return ret;
	}

	/**
	 * 
	 * @param endStr
	 * @return seconds
	 */
	public static long getIntMinusByCurrentDate(String endStr) {
		long ret = 0;
		Date endDate = getDateForFormat(endStr);
		if (null != endDate) {
			ret = getIntByDateMinus(new Date(), endDate);
		}
		return ret;
	}

	public static long getMinutsByDate(String beginStr, String endStr) {
		long ret = 0;
		Date beginDate = getDateForFormat(beginStr);
		Date endDate = getDateForFormat(endStr);
		if (null != endDate && null != beginDate) {
			ret = getIntByDateMinus(beginDate, endDate);
		}
		return ret;
	}

	/**
	 * Returns a date object which is the midnight of the input date.
	 * 
	 * @param date
	 *            The input date
	 * @return date The midnight date.
	 */
	public static Date getDateMidnight(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 指定今天的零时
	 * 
	 * @return
	 */
	public static String getTodayZeroHour() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return getFormattedDate(cal.getTime());
	}

	/**
	 * 指定指定日期的零时
	 * 
	 * @return
	 */
	public static String getZeroHour(Date d) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return getFormattedDate(cal.getTime());
	}

	/**
	 * 根据参数（开始年月yyyymm)，返回从此参数到当前时间的年月字符串集合
	 * 
	 * @param yyyymm
	 * @return
	 */
	public static Collection getFromParamToCurrYYYYMM(String yyyymm) {
		Collection ret = null;
		String tmp = yyyymm;
		if (tmp != null && tmp.length() == 6) {
			String syear = tmp.substring(0, 4);
			String smonth = tmp.substring(4);
			int iyear = Integer.parseInt(syear);
			int imonth = Integer.parseInt(smonth);

			Calendar cal = GregorianCalendar.getInstance();
			int currYear = cal.get(Calendar.YEAR);
			int currMonth = cal.get(Calendar.MONTH);

			ret = new ArrayList();
			int y = iyear;
			int m = imonth - 1;
			cal.set(Calendar.YEAR, y);
			cal.set(Calendar.MONTH, m);
			while (!(y == currYear && m == currMonth)) {
				StringBuffer sb = new StringBuffer();
				sb.append(y);
				if (m < 9) {
					sb.append("0");
				}
				int a = m + 1;
				sb.append(a);
				ret.add(sb.toString());
				cal.set(Calendar.MONTH, m + 1);

				y = cal.get(Calendar.YEAR);
				m = cal.get(Calendar.MONTH);
			}

			StringBuffer sss = new StringBuffer();
			sss.append(currYear);
			if (currMonth < 9) {
				sss.append("0");
			}
			int a = currMonth + 1;
			sss.append(a);
			ret.add(sss.toString());
		}
		return ret;
	}

	/**
	 * 根据参数（开始年月yyyymm)，返回从此参数到当前时间的年月字符串集合,反序
	 * 
	 * @param yyyymm
	 * @return
	 */
	public static Collection getFromParamToCurrDescYYYYMM(String yyyymm) {
		Collection ret = null;
		String tmp = yyyymm;
		if (tmp != null && tmp.length() == 6) {
			String syear = tmp.substring(0, 4);
			String smonth = tmp.substring(4);
			int iyear = Integer.parseInt(syear);
			int imonth = Integer.parseInt(smonth);

			Calendar cal = GregorianCalendar.getInstance();
			int currYear = cal.get(Calendar.YEAR);
			int currMonth = cal.get(Calendar.MONTH);

			ret = new ArrayList();
			int y = currYear;
			int m = currMonth;
			while (!(y == iyear && m == imonth - 1)) {
				StringBuffer sb = new StringBuffer();
				sb.append(y);
				if (m < 9) {
					sb.append("0");
				}
				int a = m + 1;
				sb.append(a);
				ret.add(sb.toString());
				cal.set(Calendar.MONTH, m - 1);

				y = cal.get(Calendar.YEAR);
				m = cal.get(Calendar.MONTH);
			}

			ret.add(tmp);
		}
		return ret;
	}
	/**
	 * 当前日期减一个月
	 * @return
	 */
	public static String getFrontMonth(Date date){
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
        cal.add(cal.MONTH,-1); 
		return getFormatTimeYYYYYMM(cal.getTime());
	}
}
