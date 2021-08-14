package me.xuyuan.server;

import me.xuyuan.data.Coordinate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Server extends Thread{
    private Socket socket = null;
    private ServerSocket server = null;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private Boolean isRunning = false;

    public Server(Socket socket, ServerSocket server, int cInd) throws IOException{
        this.socket = socket;
        this.server = server;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        isRunning = true;
    }

    @Override
    public void run(){
        System.out.println("Receiving data from " + socket.getInetAddress());
        String line = "";
        List<String> data = new ArrayList<>();

        while(true){
            try{
                line = in.readLine();
                if(line.equalsIgnoreCase("overC")) break;
                if(line!=null) {
                    data.add(line);
                    System.out.println("> Received " + line);
                }
            }catch(IOException i){
                System.out.println(i);
            }
            line = "";
        }
        System.out.println("Data received, sorting and replying");

        UUID clientId = ServerProcess.sort(data);
        List<Coordinate> matches = ServerProcess.getMatches(clientId);

        if(matches.size()!=0) for(Coordinate co: matches){
            String coStr = co.getEpoch() + "~" + co.getLatitude() + "~" + co.getLongtitude() + "~" + co.getClientID().toString() + "~" + co.getObjectId().toString();
            out.println(coStr);
            System.out.println("> Sent " + coStr);
        }else System.out.println("> Sent null response (no matches)");

        out.println("overS");
        System.out.println("Client " + socket.getInetAddress() + " finished and disconnected\n");

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
    }

}