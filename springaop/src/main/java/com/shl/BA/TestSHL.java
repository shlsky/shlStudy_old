package com.shl.BA;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jackson on 15/10/13.
 */

public class TestSHL {
    public static DateFormat formatyyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");
    public static DateFormat formatyyyy_MM_ddHHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static DateFormat formatyyyyMMdd = new SimpleDateFormat("yyyyMMdd");
    public static void main(String[] args){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d1 = df.parse("2015-11-04 00:00:00");
            Date d2 = df1.parse("2015-11-03");
            long dd1 = d1.getTime();
            long dd2 = d2.getTime();
            long diff = d1.getTime() - d2.getTime();
            long days = diff / (1000 * 60*60*24);

            String sss = "2015-11-03 03:34:12";
            sss = sss.replaceAll("[\\d]*:[\\d]*:[\\d]*","");
            Date bizDay = formatyyyy_MM_dd.parse(sss);
            String instadate = formatyyyyMMdd.format(bizDay);
            long temp = bizDay.getTime()%(24*60*60*1000);
            long inStartDate = bizDay.getTime()-temp;

            System.out.println(days);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
