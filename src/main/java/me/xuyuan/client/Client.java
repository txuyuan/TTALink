package me.xuyuan.client;

import me.xuyuan.data.Coordinate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Client {

    private Socket socket = null;
    private PrintStream out = null;
    private BufferedReader in = null;

    public Client (){
        connect("34.126.108.92");
    }
    public Client(String address){ connect(address); }

    private void connect(String address){
        try{
            socket = new Socket(address, 8082);
            System.out.println("Connected!");
            out = new PrintStream(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }catch (UnknownHostException u){
            u.printStackTrace();
            System.exit(1);
        }catch (IOException i){
            i.printStackTrace();
            System.exit(1);
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
            out.println(data);
            System.out.println("> Sent " + data);
        }
        out.println("overC");
        System.out.println("Data sent, awaiting reply");

        List<Coordinate> rList = new ArrayList<>();
        List<String> rData  = new ArrayList<>();
        String inStr = "";
        while(inStr.equalsIgnoreCase("overS") && inStr!=null){
            inStr = in.readLine();
            rData.add(inStr);
            System.out.println("> Received " + inStr);
        }
        System.out.println("Data received, processing...");

        if(rData.contains("overS")) rData.remove("overS");
        if(rData.size() != 0)
            rList = ClientProcess.parse(rData);
        if(rList.size()==0) System.out.println("> Parsed a null response (no matches)");
        else System.out.println("> Parsed to " + rList.toString());
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
