package Data;

import java.util.Calendar;
import java.util.TimeZone;

public class Coordinate {

    private long time;
    private double lat;
    private double longt;
    private Calendar cal;

    //Builders
    /**
     * Object to store time and location data (Epoch builder)
     * @param epoch Unix Epoch Value > Number of seconds since 1Jan, 1970 (Long)
     * @param latitude Latitude (-90 to 90)
     * @param longtitude Longtitude (-180 to 180)
     * @return Coordinate object of all neccesary values (time, location)
     */
    public Coordinate(long epoch, double latitude, double longtitude){
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        cal.setTimeInMillis(epoch);

        this.time = epoch;
        this.cal = cal;
        this.lat = latitude;
        this.longt = longtitude;
    }

    /**
     * Object to store time and location data
     * @param year Year in Gregorian Calendar (eg. 2021)
     * @param month Month of Year (eg. 7 for July)
     * @param day Day of Month (eg. 4)
     * @param hour Hour of Day (24h clock)
     * @param minute Minute of Hour (0-59)
     * @param latitude Latitude of location (-90 to 90)
     * @param longtitude Longtitude of location (-180 to 180)
     * @return Coordinate object of all neccesary values (time, location)
     */
    public Coordinate(int year, int month, int day, int hour, int minute, double latitude, double longtitude){
        if(latitude > 90 || latitude < -90)
            throw new IllegalArgumentException("Latitude must be between -90 and 90, input was " + latitude);
        if(longtitude > 180 || longtitude < -180)
            throw new IllegalArgumentException("Longtitude must be between -180 and 180, input was " + longtitude);
        if(year < 1970 || month > 12 || day > 31 || hour > 24 || minute > 60)
            throw new IllegalArgumentException("Invalid date/time, values too high" + year + " " + month + " " + day + " " + hour + " " + minute);
        if(month <=0 || day <= 0 || hour < 0 || minute < 0)
            throw new IllegalArgumentException("Time cannot be negative" + month + " " + day + " " + hour + " " + minute);
        if(minute%5 !=0)
            throw new IllegalArgumentException(minute + " mins is invalid. Must be multiple of 5");

        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        cal.set(year, month, day, hour, minute);
        long epoch = cal.getTimeInMillis();
        //Round&truncate to seconds
        long rem = epoch%1000;
        if(rem >= 500)
            epoch = (epoch+1000-rem)/1000;
        else
            epoch = (epoch-rem)/1000;

        this.cal = cal;
        this.time = epoch;
        this.lat = latitude;
        this.longt = longtitude;


    }



    //Callers
    /** @return latitude (Double)*/
    public double getLatitude(){
        return lat;
    }
    /** @return longtitude (Double)*/
    public double getLongtitude(){
        return longt;
    }
    /** @return Unix Epoch Value > Number of seconds since 1Jan, 1970 (Long)*/
    public long getEpochTime(){
        return time;
    }
    /** @return Calendar Object > Java.Util.Calendar (Calendar)*/
    public Calendar getCalendar(){
        return cal;
    }
    //Time details
    /** @return Year (int)*/
    public int getYear(){
        return cal.get(Calendar.YEAR);
    }
    /** @return Month of Year (int)*/
    public int getMonth(){
        return cal.get(Calendar.MONTH);
    }
    /** @return Day of Month (int)*/
    public int getDay(){
        return cal.get(Calendar.DAY_OF_MONTH);
    }
    /** @return Hour of Day (int)*/
    public int getHour(){
        return cal.get(Calendar.HOUR_OF_DAY);
    }
    /** @return Minute of Hour (int)*/
    public int getMinute(){
        return cal.get(Calendar.MINUTE);
    }



}
