package com.zjw.oa.util;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


/**
 * 描述:
 *
 * @author gqf
 * @date 2015年5月13日
 */
public class DateUtil {

    public static String dateToString(Date date, String format) {
        String result = "";
        if (date != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                result = sdf.format(date);
            } catch (Exception ex) {
                result = "";
            }
        }
        return result;
    }

    //判断选择的日期是否是本周
    public static boolean isThisWeek(long time)
    {
        Calendar calendar = Calendar.getInstance();
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        calendar.setTime(new Date(time));
        int paramWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        if(paramWeek==currentWeek){
            return true;
        }
        return false;
    }

    //判断选择的日期是否是今天
    public static boolean isToday(long time)
    {
        return isThisTime(time,"yyyy-MM-dd");
    }
    //判断选择的日期是否是本月
    public static boolean isThisMonth(long time)
    {
        return isThisTime(time,"yyyy-MM");
    }

    public static boolean isThisMonth(String date){
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        String dateStr=df.format(new Date());
        String MM=dateStr.substring(4, 6);
        String mm = date.substring(5,7);
        if(MM.equals(mm)){
            return true;
        }else{
            return false;
        }
    }
    public static void main(String[] args){
        System.out.println("--------------:"+isThisMonth("2019-06-06"));
    }
    private static boolean isThisTime(long time,String pattern) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String param = sdf.format(date);//参数时间
        String now = sdf.format(new Date());//当前时间
        if(param.equals(now)){
            return true;
        }
        return false;
    }

    public static Date stringToDate(String strDate, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(strDate);
        } catch (Exception er) {
            return null;
        }
    }

    /**
     * 描述: 中文日期转化日期格式
     */
    public static Date chineseStrToDate(String strDate, String format) {
        strDate = strDate.replace("年", "-");
        strDate = strDate.replace("月", "-");
        strDate = strDate.replace("日", "-");
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(strDate);
        } catch (Exception er) {
            return null;
        }
    }

    public static String getCurrentTime() {
        return dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    public static String getCurrentTime2() {
        return dateToString(new Date(), "yyyy-MM-dd_HHmmss");
    }

    public static String getCurrentYear() {
        return dateToString(new Date(), "yyyy");
    }

    /**
     * 获取去年年份
     */
    public static String getLastYear() {
        return String.valueOf(LocalDate.now().getYear() - 1);
    }

    public static String getLastYear(Integer num) {
        return String.valueOf(LocalDate.now().getYear() - num);
    }

    public static String getLastYearMonth() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.YEAR, -1);
        Date y = c.getTime();
        return dateToString(y, "yyyy-MM");
    }

    public static String getCurrentDate() {
        return dateToString(new Date(), "yyyy-MM-dd");
    }

    public static String getCurrentSimpleTime() {
        return dateToString(new Date(), "HH:mm:ss");
    }

    /**
     * 描述: 获取当前完整日期
     */
    public static String getCurrentSimpleDate() {
        return dateToString(new Date(), "yyyyMMdd");
    }

    /**
     * 描述: 把日期转成完整格式。如：2007-1-1 转化后为 2007-01-01
     */
    public static String toComplexDate(String strDate, String regex) {
        try {
            String[] tmpDate = strDate.split(regex);
            String tmpYear = tmpDate[0];
            String tmpMonth = tmpDate[1];
            String tmpDay = tmpDate[2];
            if (Integer.parseInt(tmpMonth) <= 9) {
                tmpMonth = "0" + tmpMonth;
            }
            if (Integer.parseInt(tmpDay) <= 9) {
                tmpDay = "0" + tmpDay;
            }
            return tmpYear + "-" + tmpMonth + "-" + tmpDay;
        } catch (Exception er) {
            return strDate;
        }
    }

    /**
     * 描述: 获取N年后时间
     */
    public static String getNyearlater(Date date, int n) {
        try {
            Calendar now = Calendar.getInstance();
            now.setTime(date);
            now.add(Calendar.DAY_OF_YEAR, 365 * n);
            SimpleDateFormat formatter = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            return formatter.format(now.getTime());
        } catch (Exception er) {
            return getCurrentTime();
        }
    }

    /**
     * 描述: 获取N年后日期
     */
    public static String getNyearlaterDate(Date date, int n) {
        try {
            Calendar now = Calendar.getInstance();
            now.setTime(date);
            now.add(Calendar.DAY_OF_YEAR, 365 * n);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            return formatter.format(now.getTime());
        } catch (Exception er) {
            return getCurrentTime();
        }
    }

    /**
     * 描述: 获取n年后日期对象
     */
    public static Date getnyearlaterDateObject(Date date, int n) {
        Calendar now = Calendar.getInstance();
        try {

            now.setTime(date);
            now.add(Calendar.DAY_OF_YEAR, 365 * n);

        } catch (Exception er) {
            er.printStackTrace();
        }
        return now.getTime();
    }

    /**
     * 描述: 获取n月后时间
     */
    public static String getNmonthlater(Date date, int n) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.add(Calendar.MONTH, n);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(now.getTime());
    }

    /**
     * 描述: 获取n月后日期
     */
    public static Date getNmonthlaterDate(Date date, int n) {
        Calendar now = Calendar.getInstance();
        try {
            now.setTime(date);
            now.add(Calendar.MONTH, n);
        } catch (Exception er) {
            er.printStackTrace();
        }
        return now.getTime();
    }

    /**
     * 描述: 获取n天后日期(字符串形式)
     */
    public static String getNdaylater(Date date, int n) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            return formatter.format(getNdaylaterDate(date, n));
        } catch (Exception er) {
            return getCurrentTime();
        }
    }

    /**
     * 描述: 获取n天后日期
     */
    public static Date getNdaylaterDate(Date date, int n) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.add(Calendar.DAY_OF_YEAR, n);
        return now.getTime();
    }

    /**
     * 描述: 获取n小时后时间
     */
    public static Date getNhourlater(Date date, int n) {
        long myTime = (date.getTime() / 1000) + 60 * 60 * n;
        date.setTime(myTime * 1000);
        return date;
    }

    /**
     * 描述: 获取n分钟后时间
     */
    public static Date getNMinutelater(Date date, int n) {
        long myTime = (date.getTime() / 1000) + 60 * n;
        date.setTime(myTime * 1000);
        return date;
    }

    /**
     * 描述: 计算两日期相差天数
     */
    public static int getDaysBetween(String smdate, String bdate) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long betweenDays = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(betweenDays)) + 1;
    }

    /**
     * 描述: 计算两日期相差天数
     */
    public static int getDaysBetween(Date smdate, Date bdate) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long betweenDays = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(betweenDays)) + 1;
    }

    /**
     * 描述: 获取格式化后日期时间
     */
    public static long getAfterFormatTime(Date date, String format) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String fDate = sdf.format(date);
        return sdf.parse(fDate).getTime();
    }

    /**
     * 描述: 计算两日期相差月数
     */
    public static int getBetweenMonths(String beforeDate, String afterDate) {
        try {
            int iMonth = 0;
            int flag = 0;
            try {
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date d1 = format.parse(beforeDate);
                Date d2 = format.parse(afterDate);

                Calendar objCalendarDate1 = Calendar.getInstance();
                objCalendarDate1.setTime(d1);

                Calendar objCalendarDate2 = Calendar.getInstance();
                objCalendarDate2.setTime(d2);

                if (objCalendarDate2.equals(objCalendarDate1)) {
                    return 0;
                }
                if (objCalendarDate1.after(objCalendarDate2)) {
                    Calendar temp = objCalendarDate1;
                    objCalendarDate1 = objCalendarDate2;
                    objCalendarDate2 = temp;
                }
                if (objCalendarDate2.get(Calendar.DAY_OF_MONTH) < objCalendarDate1
                        .get(Calendar.DAY_OF_MONTH)) {
                    flag = 1;
                }

                if (objCalendarDate2.get(Calendar.YEAR) > objCalendarDate1
                        .get(Calendar.YEAR)) {
                    iMonth = ((objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1
                            .get(Calendar.YEAR))
                            * 12
                            + objCalendarDate2.get(Calendar.MONTH) - flag)
                            - objCalendarDate1.get(Calendar.MONTH);
                } else {
                    iMonth = objCalendarDate2.get(Calendar.MONTH)
                            - objCalendarDate1.get(Calendar.MONTH) - flag;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return iMonth;

        } catch (Exception er) {
            return 0;
        }
    }

    /**
     * 描述: 计算N月之后的时间
     */
    public static Date addMonth(Date d, int i) {
        Date date = null;
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        gc.setTime(d);
        gc.add(Calendar.MONTH, i);
        try {
            date = sf.parse(sf.format(gc.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 描述: 比较两时间前后
     */
    public static int compareDate(String date1, String date2) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 描述: 返回两个日期之间的时间 单位分钟
     */
    public static long getBetweenDaysToMut(Date beforeDate, Date nowDate) {
        long myTime = (nowDate.getTime() / 1000) - (beforeDate.getTime() / 1000);
        return myTime / 60;
    }

    /**
     * 描述: 格式化查询条件：起始日期
     */
    public static Date getStartDate(String startTime) {
        if (startTime != null && startTime.length() > 0) {
            return DateUtil.stringToDate(startTime + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
        }
        return null;
    }

    /**
     * 描述: 格式化查询条件：截止日期
     */
    public static Date getEndDate(String endTime) {
        if (endTime != null && endTime.length() > 0) {
            return DateUtil.stringToDate(endTime + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
        }
        return null;
    }

    /**
     * 描述: 获取日期多在的周几
     */
    public static String getWeekForDate(Date date) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * 获取上周最后一天
     */
    public static String getLastSatDate(String pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_YEAR, -1);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.format(calendar.getTime());
        } catch (Exception e) {
            return null;
        }
    }

    public static String doGetFirstDayInYear() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DATE, 1);
        return dateToString(calendar.getTime(), "yyyy-MM-dd");
    }


    /**
     * 描述 获取当前时间前的 x 个年（包含当前年）
     */
    public static String[] getSomeYears(int x) {
        LocalDate today = LocalDate.now();
        int dateString = today.getYear();

        String[] rqArray = new String[x];
        for (int i = 0; i < x; i++) {
            rqArray[x - 1 - i] = String.valueOf(dateString - i);
        }

        return rqArray;
    }

    /**
     * 描述 获取当前时间前的 x 个年（不包含当前年）
     */
    public static List<String> getSomeYearsNotIncludeCurrent(int x) {
        LocalDate today = LocalDate.now();
        int dateString = today.getYear();

        List<String> rqList = new ArrayList<String>(x);
        for (int i = 0; i < x; i++) {
            rqList.add(String.valueOf(dateString - x + i));
        }

        return rqList;
    }

    /**
     * 描述 获取当前日期 格式：(yyyy年MM月dd日 hh:mm:ss)
     */
    public static String getCurrentFullTimeStr() {
        return java.time.LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss"));
    }

    /**
     * 描述 获取今天星期几
     */
    public static String getDayOfTheWeek() {
        String[][] strArray = {{"1", "一"}, {"2", "二"}, {"3", "三"}, {"4", "四"}, {"5", "五"}, {"6", "六"}, {"7", "日"}};

        LocalDate currentDate = LocalDate.now();
        String k = String.valueOf(currentDate.getDayOfWeek());
        //获取行数
        for (int i = 0; i < strArray.length; i++) {
            if (k.equals(strArray[i][0])) {
                k = strArray[i][1];
                break;
            }
        }

        return "星期" + k;
    }




    /**
     * 描述 获取上一周日期(7天)
     */
    public static List<String> getLastWeekDays() {
        List<String> dateList = new ArrayList<>();
        LocalDate localDate = LocalDate.now().minusWeeks(1);
        for (int i = 0; i < 7; i++) {
            localDate = localDate.plusDays(1);
            dateList.add(localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
        return dateList;
    }

    /**
     * 描述 获取上一月日期(30天)
     */
    public static List<String> getLastMonthDays() {
        List<String> dateList = new ArrayList<>();
        LocalDate localDate = LocalDate.now().minusDays(30);
        for (int i = 0; i < 30; i++) {
            localDate = localDate.plusDays(1);
            dateList.add(localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
        return dateList;
    }

    /*public static void main(String[] args) {
        List<String> kk = DateUtil.getSomeMonthsAgoDaysNotIncludeNow(1, null);
        kk.forEach(e -> System.out.println(e));
    }*/

}
