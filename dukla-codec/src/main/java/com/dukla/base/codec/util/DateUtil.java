package com.dukla.base.codec.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 日期工具
 */
public final class DateUtil {

    public static final String FORMAT_YMDHMS = "yyyy-MM-DD HH:mm:ss";

    public static final int[] DAY_OF_MONTH = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static final String[] DATE_FORMATS = new String[]{"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd",
            "yyyy-MM", "yyyy-MM-dd HH:mm:ss.S", "yyyy年MM月dd日", "yyyy年MM月dd日 HH:mm", "yyyyMMdd", "yyyy年MM月dd日 HH:mm:ss",
            "MM.dd"};


    /**
     * 输出当天的时间，格式如：2015-12-07
     */
    public static String today() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date());
    }

    public static String format(Date date, String tpl) {
        SimpleDateFormat format = new SimpleDateFormat(tpl);
        return format.format(date);
    }

    /**
     * 使用参数Format将字符串转为Date
     */
    public static Date parse(String strDate, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 任意一天的开始时间
     */
    public static Date startOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 任意一天的结束时间
     */
    public static Date endOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * 获取时间戳的开始时间
     */
    public static Long startOfDayByTimestamp(Long timestamp) {
        Date date = new Date(timestamp);
        return startOfDay(date).getTime();
    }

    /**
     * 获取时间戳的结束时间
     */
    public static Long endOfDayByTimestamp(Long timestamp) {
        Date date = new Date(timestamp);
        return endOfDay(date).getTime();
    }

    /**
     * 当天的开始时间
     */
    public static Date startOfTodDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 当天的结束时间
     */
    public static Date endOfTodDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * 昨天的开始时间
     */
    public static Date startOfYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DATE, -1);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 昨天的结束时间
     */
    public static Date endOfYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 本周的开始时间 示例：2013-05-13 00:00:00
     */
    public static Date startOfThisWeek() {// 当周开始时间
        Calendar currentDate = Calendar.getInstance();
        currentDate.setFirstDayOfWeek(Calendar.MONDAY);
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        currentDate.set(Calendar.MILLISECOND, 0);
        currentDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return currentDate.getTime();
    }

    /**
     * 获取本周的结束时间 示例：2013-05-19 23:59:59
     */
    public static Date endOfThisWeek() {
        Calendar currentDate = Calendar.getInstance();
        currentDate.setFirstDayOfWeek(Calendar.MONDAY);
        currentDate.set(Calendar.HOUR_OF_DAY, 23);
        currentDate.set(Calendar.MINUTE, 59);
        currentDate.set(Calendar.SECOND, 59);
        currentDate.set(Calendar.MILLISECOND, 999);
        currentDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return currentDate.getTime();
    }

    /**
     * 获取本月的开始时间
     */
    public static Date startOfThisMonth() {
        Calendar currentDate = Calendar.getInstance();
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        currentDate.set(Calendar.MILLISECOND, 0);
        currentDate.set(Calendar.DAY_OF_MONTH, 1);
        return currentDate.getTime();
    }

    public static Date endOfThisMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    /**
     * 获取上月的开始时间
     */
    public static Date startOfLastMonth() {
        Calendar currentDate = Calendar.getInstance();
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        currentDate.set(Calendar.MILLISECOND, 0);
        currentDate.set(Calendar.DAY_OF_MONTH, 1);
        currentDate.add(Calendar.MONTH, -1);
        return currentDate.getTime();
    }

    /**
     * 获取上月的结束时间
     */
    public static Date endOfLastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    /**
     * 某月的起始时间
     */
    public static Date getMonthBeginTime(Date dt) {
        if (dt == null)
            dt = new Date(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 某月的截止时间,eg：param:2011-11-10 12:10:50.999, return：2011-11-30 23:59:59.999
     */
    public static Date getMonthEndTime(Date dt) {
        if (dt == null)
            dt = new Date(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, -1);
        return cal.getTime();
    }

    /**
     * 计算两个日期间相隔的小时
     */
    public static int getHourBetween(Date d1, Date d2) {
        long m = d1.getTime();
        long n = d2.getTime();
        return (int) ((m - n) / 3600000);
    }

    /**
     * 取得两个时间之间的天数，可能是负数(第二个时间的日期小于第一个时间的日期)。如果两个时间在同一天，则返回0
     */
    public static int getDayBetween(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(d1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(d2);
        return (int) ((c2.getTimeInMillis() - c1.getTimeInMillis()) / 86400000);
    }

    /**
     * 计算两个日期间相隔的秒数
     */
    public static long getSecondBetween(Date d1,Date d2) {
        return (d1.getTime() - d2.getTime()) / 1000;
    }

    /**
     * 计算两个日期间相隔的月份数
     */
    public static int getMonthBetween(Date d1,Date d2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        return (c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR)) * 12 + (c1.get(Calendar.MONTH) - c2.get(Calendar.MONTH));
    }

    /**
     * 根据周几的数字标记获得周几的汉字描述
     */
    public static String getCnWeekDesc(int weekNum) {
        String strWeek = "";
        switch (weekNum) {
            case 1:
                strWeek = "周一";
                break;
            case 2:
                strWeek = "周二";
                break;
            case 3:
                strWeek = "周三";
                break;
            case 4:
                strWeek = "周四";
                break;
            case 5:
                strWeek = "周五";
                break;
            case 6:
                strWeek = "周六";
                break;
            case 7:
                strWeek = "周日";
                break;
        }
        return strWeek;
    }

    /**
     * 获得"上下午"标识
     */
    public static String getCnAmPm(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (Calendar.AM == cal.get(Calendar.AM_PM))
            return "AM";
        else
            return "PM";
    }

    /**
     * 获取一个日期的年份
     */
    public static int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    /**
     * 获取一个日期的月份
     */
    public static int getMonthOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }

    /**
     * 获取一个日期的天数
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取一个日期的小时数
     */
    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取一个日期的分钟
     */
    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 获取一个日期的秒数
     */
    public static int getSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 获取一个月的最大天数
     */
    public static int getMaxDayOfMonth(int year, int month) {
        if (month == 1 && isLeapYear(year)) {
            return 29;
        }
        return DAY_OF_MONTH[month];
    }

    /**
     * 判断是否是润年
     */
    public static boolean isLeapYear(int year) {
        Calendar calendar = Calendar.getInstance();
        return ((GregorianCalendar) calendar).isLeapYear(year);
    }

    /**
     * 得到本周的起始时间
     */
    public static Date getBeginDateOfThisWeek(Date currentDate) {
        Calendar current = Calendar.getInstance();
        current.setTime(currentDate);
        int dayOfWeek = current.get(Calendar.DAY_OF_WEEK);

        if (dayOfWeek == 1) { // 如果是星期天，星期一则往前退6天
            current.add(Calendar.DAY_OF_MONTH, -6);
        } else {
            current.add(Calendar.DAY_OF_MONTH, 2 - dayOfWeek);
        }
        current.set(Calendar.HOUR_OF_DAY, 0);
        current.set(Calendar.MINUTE, 0);
        current.set(Calendar.SECOND, 0);
        current.set(Calendar.MILLISECOND, 0);
        return current.getTime();
    }

    /**
     * 年月日、时分秒的模板
     * yyyy-MM-dd HH:mm:ss
     */
    public static final DateTimeFormatter FORMAT_YEAR_2_SECOND = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

    /**
     * 将Date转换成年月日时分秒（yyyy-MM-dd HH:mm:ss）
     *
     * @param date date
     * @return 格式化后的字符串
     */
    public static String format2yyyyMMddHHmmss(Date date) {
        if (date == null) {
            return "";
        }
        LocalDateTime ldt = date.toInstant()
                .atZone(ZoneOffset.ofHours(8))
                .toLocalDateTime();

        return ldt.format(FORMAT_YEAR_2_SECOND);
    }

    /**
     * 计算两个日期相差的自然月
     * @param startDate
     * @param endDate
     * @return
     */
    public static int calDiffMonth(String startDate,String endDate){
        int result=0;
        try {
            SimpleDateFormat sfd=new SimpleDateFormat("yyyy-MM-dd");
            Date start = sfd.parse(startDate);
            Date end = sfd.parse(endDate);
            int startYear=getYear(start);
            int startMonth=getMonth(start);
            int startDay=getDay(start);
            int endYear=getYear(end);
            int endMonth=getMonth(end);
            int endDay=getDay(end);
            if (startDay>endDay){ //1月17 大于 2月28
                if (endDay==getDaysOfMonth(getYear(new Date()),2)){   //也满足一月
                    result=(endYear-startYear)*12+endMonth-startMonth;
                }else{
                    result=(endYear-startYear)*12+endMonth-startMonth-1;
                }
            }else{
                result=(endYear-startYear)*12+endMonth-startMonth;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    /** * 返回日期的月份，1-12,即yyyy-MM-dd中的MM * * @param date * @return */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int getDaysOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 增加日期中某类型的某数值。如增加日期
     *
     * @param date     日期
     * @param dateType 类型
     * @param amount   数值
     * @return 计算后日期
     */
    public static Date addInteger(Date date, int dateType, int amount) {
        Date myDate = null;
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(dateType, amount);
            myDate = calendar.getTime();
        }
        return myDate;
    }

    /**
     * /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.DATE_FORMATS[2]);
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

}