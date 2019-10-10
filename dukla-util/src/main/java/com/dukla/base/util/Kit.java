package com.dukla.base.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;

public class Kit {
	/*
	 * 测试
	 */
	public static void main(String args[]) {
        Calendar calendar=Calendar.getInstance();
        calendar.set(2016, Calendar.APRIL, 5, 10, 50, 50);
        System.out.print(calendar.getTime().getTime());
		//System.out.println(Kit.getMD5Str("admin"));
		//System.out.println(Kit.isInt("1200"));
		//System.out.println(Kit.isFloat("1.12"));
	}

	// 判断字符串是否只含有数字或字符
	public static boolean isNumAndStr(String input) {
		if (input!=null && input.matches("[0-9A-Za-z_]*")) {
			return true;
		} else {
			return false;
		}
	}
	
	// 判断字符串是否只含有数字
	public static boolean isNum(String input) {
		if (input != null && input.trim().length() != 0
				&& input.matches("[0-9]*")) {
			return true;
		} else {
			return false;
		}
	}
	
	
	// 判断字符串是否整数
	public static boolean isInt(String input) {
		if (input!=null && input.matches("^[+-]?[\\d]+$")) {
			return true;
		} else {
			return false;
		}
	}
	
	// 判断字符串是否小数
	public static boolean isFloat(String input) {
		if (input!=null && input.matches("^[+-]?[\\d]+\\.[\\d]+$")) {
			return true;
		} else {
			return false;
		}
	}
	

	// 得到对象的字符值
	public static String getObjStr(Object o) {
		return (o != null ? o.toString() : "");
	}

	// 得到对象的整形值，转化失败返回 0
	public static Integer getObjInteger(Object o) {
		Integer oi = new Integer(0);
		if (o instanceof Integer) {
			oi = (Integer) o;
		}else if(o instanceof Long){
			oi=((Long) o).intValue();
		}else if(o instanceof BigDecimal){
			oi=((BigDecimal) o).intValue();
		}
		return oi;
	}

	// 得到对象的长整形值，转化失败返回 0
	public static Long getObjLong(Object o) {
		Long ol = new Long(0);
		if (o instanceof Long) {
			ol = (Long) o;
		}else if(o instanceof Integer){
			ol=((Integer) o).longValue();
		}else if(o instanceof BigDecimal){
			ol=((BigDecimal) o).longValue();
		}
		return ol;
	}

	// 得到对象的浮点形式值，转化失败返回 0.0
	public static Double getObjDouble(Object o) {
		Double od = new Double(0.0);
		if (o instanceof Double) {
			od = (Double) o;
		}else if(o instanceof BigDecimal){
			od=((BigDecimal) o).doubleValue();
		}
		return od;
	}

	// 得到对象的日期值，转化失败返回null
	public static Date getObjDate(Object o) {
		Date date = null;
		if (o instanceof Date) {
			date = (Date) o;
		}
		return date;
	}

	/*
	 * 时间处理工具函数
	 */
	// 得到当前日期的开始时间
	public static Date getCurrentDateTimeStart() {
		Calendar cl = Calendar.getInstance();
		cl.set(cl.get(Calendar.YEAR), cl.get(Calendar.MONTH), cl.get(Calendar.DAY_OF_MONTH), 0,
				0, 0);
		return cl.getTime();
	}

	// 得到当前日期的结束时间

	public static Date getCurrentDateTimeEnd() {
		Calendar cl = Calendar.getInstance();
		cl.set(cl.get(Calendar.YEAR), cl.get(Calendar.MONTH), cl.get(Calendar.DAY_OF_MONTH), 23,
				59, 59);
		return cl.getTime();
	}

	// 得到当前周开始时间

	public static Date getCurrentWeekStartDate() {
		Calendar cl = Calendar.getInstance();
		int day = cl.get(Calendar.DAY_OF_WEEK);
		if (day == 1) {
			day = -6;
		} else {
			day = -1 * (day - 2);
		}
		cl.add(Calendar.DAY_OF_WEEK, day);
		Date firstDay = cl.getTime();
		return getDateTimeStart(firstDay);
	}

	// 得到当前周结束时间

	public static Date getCurrentWeekEndDate() {
		Calendar cl = Calendar.getInstance();
		int day = cl.get(Calendar.DAY_OF_WEEK);
		if (day == 1) {
			day = 0;
		} else {
			day = 8 - day;
		}
		cl.add(Calendar.DAY_OF_WEEK, day);
		Date endDay = cl.getTime();
		return getDateTimeEnd(endDay);
	}

	// 得到当前月开始时间

	public static Date getCurrentMonthStartDate() {
		Calendar cl = Calendar.getInstance();
		cl.set(Calendar.DAY_OF_MONTH, cl
				.getActualMinimum(Calendar.DAY_OF_MONTH));
		return getDateTimeStart(cl.getTime());
	}

	// 得到当前月结束时间

	public static Date getCurrentMonthEndDate() {
		Calendar cl = Calendar.getInstance();
		cl.set(Calendar.DAY_OF_MONTH, cl
				.getActualMaximum(Calendar.DAY_OF_MONTH));
		return getDateTimeEnd(cl.getTime());
	}

	// 得到指定日期的开始时间

	public static Date getDateTimeStart(Date date) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		cl.set(cl.get(Calendar.YEAR), cl.get(Calendar.MONTH), cl.get(Calendar.DAY_OF_MONTH), 0,
				0, 0);
		return cl.getTime();
	}

	// 得到指定日期的结束时间

	public static Date getDateTimeEnd(Date date) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		cl.set(cl.get(Calendar.YEAR), cl.get(Calendar.MONTH), cl.get(Calendar.DAY_OF_MONTH), 23,
				59, 59);
		return cl.getTime();
	}

	// 得到两个日期的间隔月
	public static int getBetweenMonth(Date s, Date e) {
		if (s.after(e)) {
			Date t = s;
			s = e;
			e = t;
		}
		Calendar start = Calendar.getInstance();
		start.setTime(s);
		Calendar end = Calendar.getInstance();
		end.setTime(e);
		Calendar temp = Calendar.getInstance();
		temp.setTime(e);
		temp.add(Calendar.DATE, 1);

		int y = end.get(Calendar.YEAR) - start.get(Calendar.YEAR);
		int m = end.get(Calendar.MONTH) - start.get(Calendar.MONTH);

		if ((start.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) == 1)) {// 前后都不破月
			return y * 12 + m + 1;
		} else if ((start.get(Calendar.DATE) != 1)
				&& (temp.get(Calendar.DATE) == 1)) {// 前破月后不破月
			return y * 12 + m;
		} else if ((start.get(Calendar.DATE) == 1)
				&& (temp.get(Calendar.DATE) != 1)) {// 前不破月后破月
			return y * 12 + m;
		} else {// 前破月后破月
			return (y * 12 + m - 1) < 0 ? 0 : (y * 12 + m - 1);
		}
	}

	// 得到两个日期的间隔天
	public static int getBetweenDay(Date s, Date e) {
		return (new Long((e.getTime() - s.getTime()) / (1000 * 3600 * 24)))
				.intValue();
	}

	// 解析日期时间,解析失败返回null
	// @format 常用格式1.yyyy-MM-dd HH:mm:ss 2.yyyy-MM-dd
	public static Date parseDateTimeStr(String dateTimeStr, String format) {
		dateTimeStr=dateTimeStr.replace('.', '-');
		dateTimeStr=dateTimeStr.replace('/', '-');
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern(format);
		Date date = null;
		try {
			date = sdf.parse(dateTimeStr);
		} catch (Exception ex) {

		}
		return date;
	}

	// 格式化日期时间字符串
	// @format 常用格式1.yyyy-MM-dd HH:mm:ss 2.yyyy-MM-dd
	public static String formatDateTime(Date date, String format) {
		if(date!=null){
			SimpleDateFormat sdf = new SimpleDateFormat();
			sdf.applyPattern(format);
			return sdf.format(date);
		}else{
			return "";
		}
	}

	// 得到随机数串
	// @length随机数的长度
	public static String getRadomNumber(int length) {
		Double r = Math.random() * Math.pow(10, length);
		String ra = String.valueOf(r.longValue());
		String radom = ra;
		if (ra.length() < length) {
			for (int i = 0; i < length - ra.length(); i++) {
				radom = "0" + radom;
			}
		}
		return radom;
	}

	// 得到32位UUID
	public static String get32UUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "").substring(0, 31);
	}

	// 得到36位UUID
	public static String get36UUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	// 得到MD5摘要串
	public static String getMD5Str(String s) {
		// 用作十六进制的数组.
		byte hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("MD5"
					.toUpperCase());// 使用MD5加密
			byte[] strTemp = s.getBytes();// 把传入的字符串转换成字节数组
			mdTemp.update(strTemp);//
			byte[] md = mdTemp.digest();
			int j = md.length;
			byte str[] = new byte[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);// 返回加密后的字符串.
		} catch (Exception ex) {
			return null;
		}
	}
	//判断是否是UTF-8编码
	public static boolean isUTF8(String s) {
		try{
			if(s!=null && s.length()!=0){
				String tmp=new String(s.getBytes("UTF-8"),"UTF-8");
				return s.equals(tmp);
			}
		}catch(Exception ex){
			return false;
		}
		return true;
	}
	//判断是否是ISO-8859-1编码
	public static boolean isISO8859(String s) {
		try{
			if(s!=null && s.length()!=0){
				String tmp=new String(s.getBytes("ISO-8859-1"),"ISO-8859-1");
				return s.equals(tmp);
			}
		}catch(Exception ex){
			return false;
		}
		return true;
	}

   public static String getRandomFileName(){
	   return formatDateTime(new Date(), "yyyyMMddHHmmss")+"_"+getRadomNumber(4);
   }
   

    public static List<String[]> readPropFile(String filePath,String splitChar){
        List<String[]> propList=new ArrayList<String[]>();
        if(filePath!=null && filePath.length()!=0){
            File file=new File(filePath);
            BufferedReader reader=null;
            if(file.exists()){
                try{
                    reader=new BufferedReader(new FileReader(file));
                    String line;
                    while((line=reader.readLine().trim()) != null){
                        if(!line.startsWith("#")){
                            String[] prop=line.split(splitChar);
                            propList.add(prop);
                        }
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }finally {
                    if(reader!=null){
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return propList;
    }

}
