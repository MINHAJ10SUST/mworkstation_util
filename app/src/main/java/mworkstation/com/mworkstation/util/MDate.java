package mworkstation.com.mworkstation.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MDate {

    public static Date getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        //return dateFormat.format(date);
        return date;
    }

    public static long printDifference(Date startDate, Date endDate) {
        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

        System.out.println("startDate : " + startDate);
        System.out.println("endDate : "+ endDate);
        System.out.println("different : " + different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;


        long elapsedSeconds = different / secondsInMilli;

      return elapsedSeconds;
    }
}
