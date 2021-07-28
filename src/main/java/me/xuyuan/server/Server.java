package me.xuyuan.server;

import me.xuyuan.TTA.Main;
import me.xuyuan.TTA.MainData;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread{
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;
    private Boolean isRunning = false;
    private MainData data;
    private int cInd;

    public Server(Socket socket, ServerSocket server, int cInd) throws IOException{
        this.socket = socket;
        this.server = server;
        in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        isRunning = true;
        this.data = Main.data;
        this.cInd = cInd;
        data.setStatus(cInd, true);
    }

    @Override
    public void run(){
        String line = "";
        List<String> data = new ArrayList<>();

        while(line!="over"){
            try{
                line = in.readUTF();
                data.add(line);
            }catch(IOException i){
                System.out.println(i);
            }
            line = "";
        }
        ServerProcess.sort(data);
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