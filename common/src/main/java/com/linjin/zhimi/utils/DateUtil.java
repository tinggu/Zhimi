package com.linjin.zhimi.utils;

import android.text.format.Time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2015/12/30
 **/

public class DateUtil {

    /*获取系统时间 格式为："yyyy/MM/dd "*/
    public static String getCurrentDate() {
        Date d = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
        return sf.format(d);
    }

    /*时间戳转换成字符窜*/
    public static String getDateToString(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sf.format(d);
    }

    /*时间戳转换成字符窜*/
    public static String getDateToString1(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("MM月dd日");
        return sf.format(d);
    }

    /*将字符串转为时间戳*/
    public static long getStringToDate(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static String getTodayZeroHourStr() {
        Time time = new Time();
        time.setToNow();
        //min 0~59 hour:0~23 month:0~11 monthDay:1~31
        String str = time.year + "-" + (time.month + 1) + "-" + time.monthDay + " " + "00:00";
        return str;
    }

    //hourStr格式为"xx:xx"
    public static String getTodayHourStr(String hourStr) {
        Time time = new Time();
        time.setToNow();
        //min 0~59 hour:0~23 month:0~11 monthDay:1~31
        String str = time.year + "-" + (time.month + 1) + "-" + time.monthDay + " " + hourStr;
        return str;
    }

    public static String getYesterdayHourStr(String hourStr) {
        Time time = new Time();
        time.setToNow();
        String str = time.year + "-" + (time.month + 1) + "-" + (time.monthDay - 1) + " " + hourStr;
        return str;
    }


//    Time time = latest Time();
    // 设置当前时间
    // time.setToNow();
    // 获取当前时区
//              System.out.println(Time.getCurrentTimezone());
//   // 输出当前日期
//            System.out.println(time.year + "年" + time.month + "月" + time.monthDay
//               + "日" + time.hour + "时" + time.minute + "分" + time.second + "秒"
//                   + ":现在是一年中的第" + time.yearDay + "天");

    public static boolean compareTodayZeroTime(long compareTime) {

        String zeroTimeStr = getTodayZeroHourStr();
        long zeroTime = getStringToDate(zeroTimeStr);
        if (compareTime > zeroTime) {
            return true;
        } else {
            if (System.currentTimeMillis() >= zeroTime) {
                return false;
            } else if (getStringToDate(getYesterdayHourStr("00:00")) > compareTime) {
                return false;
            }
            return true;
        }

    }

    public static boolean compareToday19HourTime(long compareTime) {
        String timeStr = getTodayHourStr("19:00");
        long hourTime = getStringToDate(timeStr);
        if (compareTime > hourTime) {
            return true;
        } else {
            if (System.currentTimeMillis() >= hourTime) {
                return false;
            } else if (getStringToDate(getYesterdayHourStr("19:00")) > compareTime) {
                return false;
            }
            return true;
        }
    }

    /**
     * 日期时间（当天，一周内，其他时间）
     *
     * @param time
     * @return
     */
    public static String formatDateTime12(long time) {
        StringBuffer text = new StringBuffer();
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat("hh:mm");
        String timeStr = format.format(date);
        //1.当天的显示为12小时制 格式 上午/下午 时间
        if (isToday(time)) {
            text.append(getAmOrPm(time) + " " + timeStr);
            return text.toString();
        }
        //2.一周内的时间且不是今天的显示为 格式 星期X 上午/下午 时间
        if (isThisWeek(time)) {
            int index = getDayOfWeek(time);
            switch (index) {
                case Calendar.SUNDAY:
                    text.append("星期日");
                    break;
                case Calendar.MONDAY:
                    text.append("星期一");
                    break;
                case Calendar.TUESDAY:
                    text.append("星期二");
                    break;
                case Calendar.WEDNESDAY:
                    text.append("星期三");
                    break;
                case Calendar.THURSDAY:
                    text.append("星期四");
                    break;
                case Calendar.FRIDAY:
                    text.append("星期五");
                    break;
                case Calendar.SATURDAY:
                    text.append("星期六");
                    break;
            }
            text.append(" " + getAmOrPm(time) + " " + timeStr);
            return text.toString();
        }
        //3.其他时间显示为 格式 X年X月X日 上午/下午 时间
        format.applyPattern("yyyy-MM-dd");
        text.append(format.format(date) + " " + getAmOrPm(time) + " " + timeStr);
        return text.toString();
    }

    /**
     * 判断当前时间是上午 下午
     *
     * @param time
     * @return
     */
    public static String getAmOrPm(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time));
        if (calendar.get(Calendar.HOUR_OF_DAY) >= 12) {
            return "下午";
        } else {
            return "上午";
        }
    }

    /**
     * 判断是否为今天
     *
     * @param time
     * @return
     */
    public static boolean isToday(long time) {
        Date date = new Date(time);
        Date today = new Date(System.currentTimeMillis());
        if (date.getDay() != today.getDay()) {
            return false;
        }
        if (date.getMonth() != today.getMonth()) {
            return false;
        }
        if (date.getYear() != today.getYear()) {
            return false;
        }
        return true;
    }

    /**
     * 判断是否为本周
     *
     * @param time
     * @return
     */
    public static boolean isThisWeek(long time) {
        Calendar today = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time));
        int dayOfYearT = today.get(Calendar.DAY_OF_YEAR);
        int dayOfYearC = calendar.get(Calendar.DAY_OF_YEAR);
        //同一年
        if (today.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)) {
            int dayOfWeekT = today.get(Calendar.DAY_OF_WEEK);
            int dayOfWeekC = calendar.get(Calendar.DAY_OF_WEEK);
            int sub = Math.abs((dayOfYearC - dayOfYearT));
            if (sub > 6) {
                //不在一周
                return false;
            }

            if (dayOfWeekT == dayOfWeekC) {
                return false;
            }

            int tep = dayOfYearC > dayOfYearT ? dayOfWeekT : dayOfWeekC;
            if (tep == Calendar.SUNDAY) {
                return false;
            }
            for (int i = 1; i < sub; i++) {
                tep = tep + i;
                if (tep == Calendar.SUNDAY) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获得当前时间是本周第几天
     *
     * @param time
     * @return
     */
    public static int getDayOfWeek(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time));
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

}
