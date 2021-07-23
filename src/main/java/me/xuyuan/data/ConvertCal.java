package me.xuyuan.data;

import java.util.Calendar;
import java.util.TimeZone;

public class ConvertCal {

    private static Calendar getCal(long epoch){
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        cal.setTimeInMillis(epoch);
        return cal;
    }

    public static int getYear(long epoch){ return getCal(epoch).get(Calendar.YEAR); }
    public static int getMonth(long epoch){ return getCal(epoch).get(Calendar.MONTH); }
    public static int getDay(long epoch){ return getCal(epoch).get(Calendar.DAY_OF_MONTH); }
    public static int getHour(long epoch){ return getCal(epoch).get(Calendar.HOUR_OF_DAY); }
    public static int getMinute(long epoch){ return getCal(epoch).get(Calendar.MINUTE); }

    public static long getEpoch(int year, int month, int day, int hour, int minute){
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        cal.set(year, month, day, hour, minute);
        long epoch = cal.getTimeInMillis();
        long rem = epoch%1000;
        epoch = (rem >= 500) ? (epoch+1000-rem)/1000 : (epoch-rem)/1000;
        return epoch;
    }
}
