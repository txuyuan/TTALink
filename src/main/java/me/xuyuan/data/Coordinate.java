package me.xuyuan.data;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.UUID;

public class Coordinate implements Serializable {

    private long time;
    private double lat;
    private double longt;
    private UUID clientID;
    private ObjectId objectId;

    //Builders
    /**
     * Object to store time and location data (Epoch builder)
     * @param epoch Unix Epoch Value > Number of seconds since 1Jan, 1970 (Long)
     * @param latitude Latitude (-90 to 90)
     * @param longtitude Longtitude (-180 to 180)
     * @return Coordinate object of all neccesary values (time, location)
     */

    public Coordinate(long epoch, double latitude, double longtitude, UUID clientID, ObjectId id){
        this.time = epoch;
        this.lat = latitude;
        this.longt = longtitude;
        this.clientID = clientID;
        this.objectId = id;
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
    public Coordinate(int year, int month, int day, int hour, int minute, double latitude, double longtitude, ObjectId id){
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

        long epoch = ConvertCal.getEpoch(year, month, day, hour, minute);
        //Round&truncate to seconds
        long rem = epoch%1000;
        epoch = (rem >= 500) ? (epoch+1000-rem)/1000 : (epoch-rem)/1000;

        this.time = epoch;
        this.lat = latitude;
        this.longt = longtitude;
        this.objectId = id;
    }



    //Codec
    public Document getDocument(){
        Document doc = new Document();
        doc.append("time", time);
        doc.append("lat", lat);
        doc.append("longt", longt);
        doc.append("clientId", clientID);
        doc.append("_id", objectId);
        return doc;
    }

    public static Coordinate getCoordinate(Document doc){
        Long time = (Long)doc.get("time");
        Double lat = (Double)doc.get("lat");
        Double longt = (Double)doc.get("longt");
        UUID clientId = (UUID)doc.get("clientId");
        ObjectId objectId = (ObjectId)doc.get("_id");

        Coordinate coordinate = new Coordinate(time, lat, longt, clientId, objectId);
        return coordinate;
    }



    //Callers
    /** @return latitude (Double)*/
    public double getLatitude(){ return lat; }
    /** @return longtitude (Double)*/
    public double getLongtitude(){ return longt; }
    /** @return Unix Epoch Value > Number of seconds since 1Jan, 1970 (Long)*/
    public long getEpoch(){ return time; }
    /** @return Client UUID*/
    public UUID getClientID(){ return clientID; }
    /** @return Coordinate ID (ObjectID)*/
    public ObjectId getObjectId(){ return objectId;}

    //Time details
    /** @return Year (int)*/
    public int getYear(){ return ConvertCal.getYear(time); }
    /** @return Month of Year (int)*/
    public int getMonth(){ return ConvertCal.getMonth(time); }
    /** @return Day of Month (int)*/
    public int getDay(){ return ConvertCal.getDay(time); }
    /** @return Hour of Day (int)*/
    public int getHour(){ return ConvertCal.getHour(time); }
    /** @return Minute of Hour (int)*/
    public int getMinute(){ return ConvertCal.getMinute(time); }

}
