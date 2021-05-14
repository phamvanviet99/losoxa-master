package com.phamvanviet.losoxa.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
//    public static Date getDate(String time)  {
//        StringBuilder stringBuilder = new StringBuilder(time);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        Date date = null;
//        try {
//            date = simpleDateFormat.parse(stringBuilder.toString());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return date;
//    }
    public static Date getDatetime(String time)  {
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = simpleDateFormat.parse(stringBuilder.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Integer getDate(Date date)  {
        SimpleDateFormat formatter = new SimpleDateFormat("dd");
        return Integer.parseInt(formatter.format(date));
    }
    public static Integer getMonth(Date date)  {
        SimpleDateFormat formatter = new SimpleDateFormat("MM");
        return Integer.parseInt(formatter.format(date));
    }
    public static Integer getYear(Date date)  {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        return Integer.parseInt(formatter.format(date));
    }
}
