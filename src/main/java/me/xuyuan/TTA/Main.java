package me.xuyuan.TTA;

import me.xuyuan.server.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main (String[] args) throws IOException{
        Scanner in = new Scanner(System.in);
        read(in);

        if(args[0] == "server"){
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

    //Read cmd input
    private static void read(Scanner in){
        Boolean readEn = true;
        while(readEn){
            String line = in.nextLine();
            switch (line){
                case "exit": {
                    System.out.println("Exiting...");
                    readEn = false;
                    System.exit(0);
                }
                //TODO: Graceful termination, allow existing transfers to complete
            }
        }
    }

}
