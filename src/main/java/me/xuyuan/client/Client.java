package me.xuyuan.client;

import me.xuyuan.data.Coordinate;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Client {

    private Socket socket = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;

    /**
     * Client object to send coordinate (time, location) data to server
     * Use Client.send with appropriate parameters for each set of time-location coordinates
     * If no address is supplied default of 34.126.108.92 is used
     * @return Client object to send data to server
     */
    public Client (){
        connect("34.126.108.92");
    }
    /**
     * Client object to send coordinate (time, location) data to server
     * Use Client.send with appropriate parameters for each set of time-location coordinates
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
            in = new DataInputStream(socket.getInputStream());
        }catch (UnknownHostException u){
            System.out.println(u);
        }catch (IOException i){
            System.out.println(i);
        }
    }


    //Functions

    /**
     * Send list of coordinates for the day
     * @param coList List of coordinates to be sent. Ensure all coordinates to be sent are included here. After data is sent, receiving potential contact cases will immediately begin
     * @return Returned list of coordinates is the coordinates of all potential contact cases. Use this to display to client
     */
    public List<Coordinate> send(List<Coordinate> coList) throws IOException{
        for(Coordinate co: coList){
            String data = co.getEpoch() + "~" + co.getLatitude() + "~" + co.getLongtitude() + "~" + co.getClientID().toString() + "~" + co.getObjectId().toString();
            out.writeUTF(data);
        }
        out.writeUTF("over");

        List<Coordinate> rList = new ArrayList<>();
        List<String> rData  = new ArrayList<>();
        String inStr = "";
        try{
            while(inStr!="over"){
                inStr = in.readUTF();
                rData.add(inStr);
            }
        }catch(IOException i){i.printStackTrace();}

        if(rData.size() > 0)
            rList = ClientProcess.parse(rData);
        close();
        return rList;
    }


    private void close() {
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
