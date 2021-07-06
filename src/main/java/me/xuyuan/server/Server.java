package me.xuyuan.server;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread{
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;

    public Server(Socket socket, ServerSocket server) throws IOException{
        this.socket = socket;
        this.server = server;
        in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
    }

    @Override
    public void run(){
        String line = "";
        ArrayList<String> data = new ArrayList<String>();

        while(line!="over"){
            try{
                line = in.readUTF();
                data.add(line);
            }catch(IOException i){
                System.out.println(i);
            }
            line = "";
        }
        DataProc.sort(data);
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
    }

}