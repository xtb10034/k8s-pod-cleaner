package com.xtb10034.kpc.unil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CommonUtil {
    public static double daysBetween(String fromDt, String toDt) throws ParseException {
        Calendar cal = Calendar.getInstance();
        String format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        cal.setTime(formatter.parse(fromDt));
        Long fromTime = cal.getTimeInMillis();
        cal.setTime(formatter.parse(toDt));
        Long toTime  = cal.getTimeInMillis();
        Long betweenDays = (toTime - fromTime) / (1000*3600*24);
        return betweenDays.doubleValue();
    }

    public static  String getDate() {
        Calendar cal = Calendar.getInstance();
        String format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date currentDate = cal.getTime();
        String date = formatter.format(currentDate);
        return date;
    }
}
