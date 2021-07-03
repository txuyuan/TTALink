package server;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;

    public Server(){
        try{
            server = new ServerSocket(443);
            System.out.println("Server Started, waiting for client");
            socket = server.accept();
            System.out.println("Client Accepted");
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            receive();
        }catch(IOException i){
            System.out.println(i);
        }
    }

    private void receive(){
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
        Data.sort(data);
        receive();
    }

    public void close(){
        try{
            socket.close();
            in.close();
        }catch(IOException i){
            System.out.println(i);
        }
    }

}