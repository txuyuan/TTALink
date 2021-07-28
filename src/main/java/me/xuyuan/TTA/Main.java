package me.xuyuan.TTA;

import me.xuyuan.server.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Main {

    public static MainData data;

    public static void main (String[] args){

        MainData counter = new MainData();
        data = counter;

        Scanner in = new Scanner(System.in);
        Thread readT = new ReadIn(in, counter);
        readT.start();

        switch(args[0]){
            default: {
                for(int ind=1; counter.allowNew; ind++){
                    counter.updateInd(ind);
                    try {
                        Socket socket = null;
                        ServerSocket server = null;
                        System.out.println("Listening on port 443");
                        server = new ServerSocket(443);
                        socket = server.accept();
                        System.out.println("Client Accepted");
                        Thread t = new Server(socket, server, ind);
                        t.start();
                    }catch(IOException e){e.printStackTrace();}
                }
            }


        }
    }
}
