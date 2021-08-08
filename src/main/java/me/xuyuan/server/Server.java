package me.xuyuan.server;

import me.xuyuan.TTA.Main;
import me.xuyuan.TTA.MainData;
import me.xuyuan.data.Coordinate;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Server extends Thread{
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;
    private Boolean isRunning = false;
    private MainData data;
    private int cInd;

    public Server(Socket socket, ServerSocket server, int cInd) throws IOException{
        this.socket = socket;
        this.server = server;
        in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        isRunning = true;
        this.data = Main.data;
        this.cInd = cInd;
        data.setStatus(cInd, true);
    }

    @Override
    public void run(){
        String line = "";
        List<String> data = new ArrayList<>();

        while(line!="overC"){
            try{
                line = in.readUTF();
                data.add(line);
            }catch(IOException i){
                System.out.println(i);
            }
            line = "";
        }
        UUID clientId = ServerProcess.sort(data);
        List<Coordinate> matches = ServerProcess.getMatches(clientId);

        try{
            if(matches.size()!=0) for(Coordinate co: matches)
                out.writeUTF(co.getEpoch() + "~" + co.getLatitude() + "~" + co.getLongtitude() + "~" + co.getClientID().toString() + "~" + co.getObjectId().toString());
            out.writeUTF("overS");
        }catch(IOException e){e.printStackTrace();}

        close();
    }

    //Terminate
    private void close(){
        try{
            socket.close();
            in.close();
        }catch(IOException i){
            System.out.println(i);
        }
        socket = null;
        server = null;
        in = null;
        data.setStatus(cInd, false);
    }

}