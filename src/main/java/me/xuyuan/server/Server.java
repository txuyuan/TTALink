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
    private BufferedReader in = null;
    private PrintWriter out = null;
    private Boolean isRunning = false;
    private MainData data;
    private int cInd;

    public Server(Socket socket, ServerSocket server, int cInd) throws IOException{
        this.socket = socket;
        this.server = server;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream());
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
                line = in.readLine();
                data.add(line);
            }catch(IOException i){
                System.out.println(i);
            }
            line = "";
        }
        UUID clientId = ServerProcess.sort(data);
        List<Coordinate> matches = ServerProcess.getMatches(clientId);

        if(matches.size()!=0) for(Coordinate co: matches)
            out.println(co.getEpoch() + "~" + co.getLatitude() + "~" + co.getLongtitude() + "~" + co.getClientID().toString() + "~" + co.getObjectId().toString());
        out.println("overS");
        out.flush();

        close();
    }

    //Terminate
    private void close(){
        try{
            socket.close();
            in.close();
            out.close();
        }catch(Exception e){e.printStackTrace();}

        socket = null;
        server = null;
        in = null;
        data.setStatus(cInd, false);
    }

}