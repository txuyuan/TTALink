package me.xuyuan.TTA;

import me.xuyuan.server.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main (String[] args) throws IOException{
        Scanner in = new Scanner(System.in);
        Thread readT = new ReadIn(in);
        readT.start();
        
        switch(args[0]){
            case "server":{
                while(true){
                    Socket socket = null;
                    ServerSocket server = null;
                    System.out.println("Listening on port 443");
                    server = new ServerSocket(443);
                    socket = server.accept();
                    System.out.println("Client Accepted");
                    Thread t = new Server(socket, server);
                    t.start();
                }
            }


        }
    }
}
