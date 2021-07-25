package me.xuyuan.client;

import me.xuyuan.data.Coordinate;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.UUID;
import org.bson.types.ObjectId;

public class Client {

    private Socket socket = null;
    private DataOutputStream out = null;

    /**
     * Client object to send coordinate (time, location) data to server
     * Use Client.send with appropriate parameters for each set of time-location coordinates
     * Remember to close connection with Client.close, or the connection will keep running and produce errors
     * If no address is supplied default of 34.126.108.92 is used
     * @return Client object to send data to server
     */
    public Client (){
        connect("34.126.108.92");
    }
    /**
     * Client object to send coordinate (time, location) data to server
     * Use Client.send with appropriate parameters for each set of time-location coordinates
     * Remember to close connection with Client.close, or the connection will keep running and produce errors
     * If no address is supplied default of 34.126.108.92 is used
     * @param address Target server address (IP Address)
     * @return Client object to send data to server
     */
    public Client(String address){
        connect(address);
    }

    private void connect(String address){
        try{
            socket = new Socket(address, 443);
            System.out.println("Connected");
            out = new DataOutputStream(socket.getOutputStream());
        }catch (UnknownHostException u){
            System.out.println(u);
        }catch (IOException i){
            System.out.println(i);
        }
    }

    //Functions

    /**
     * Send coordinate data along established connection
     * Note: Close connection after all data is sent
     * @param year Year in Gregorian Calendar (eg. 2021)
     * @param month Month of Year (eg. 7 for July)
     * @param day Day of Month (eg. 4)
     * @param hour Hour of Day (24h clock)
     * @param minute Minute of Hour (0-59)
     * @param latitude Latitude of location (-90 to 90)
     * @param longtitude Longtitude of location (-180 to 180)
     * @param uuid Unique Identifier for client. Ensure this is based on device
     */
    public void sendT (int year, int month, int day, int hour, int minute, double latitude, double longtitude, UUID uuid, ObjectId objectId){
        try {
            long epoch = (new Coordinate(year, month, day, hour, minute, latitude, longtitude, objectId)).getEpoch();
        }catch (IllegalArgumentException i){
            throw i;
        }
        long epoch = (new Coordinate(year, month, day, hour, minute, latitude, longtitude, objectId)).getEpoch();
        String data = epoch + "~" + latitude + "~" + longtitude + "~" + uuid.toString() + "~" + objectId.toString();
        try{
            out.writeUTF(data);
        }catch(IOException i){
            System.out.println(i);
        }
    }

    public void sendE(long epoch, double latitude, double longtitude, UUID clientId, ObjectId objectId){
        String data = epoch + "~" + latitude + "~" + longtitude + "~" + clientId.toString() + "~" + objectId.toString();
        try{
            out.writeUTF(data);
        }catch(IOException i){ System.out.println(i); }
    }



    /** Terminate connection to server. Client object is terminated.*/
    public void close() {
        try {
            out.close();
            socket.close();
        } catch (IOException i) {
            System.out.println(i);
        }
        socket = null;
        out = null;
    }

}
