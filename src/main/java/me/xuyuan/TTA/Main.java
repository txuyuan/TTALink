package me.xuyuan.TTA;

import me.xuyuan.server.Server;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main (String[] args){

        if(args.length!=0 && args[0].equals("client"))
            TestClass.test();
        if(args.length!=0 && args[0].equals("server"))
            TestClass.serverGenerate();
        else serverStart();

    }

    private static void serverStart(){

        System.out.println("TTALink Server Starting...");
        Scanner in = new Scanner(System.in);
        ReadIn readT = new ReadIn(in);
        readT.start();

        System.out.println("Listening on port 8082\n");
        int ind = 1;
        while(true) try {
            Socket socket = null;
            ServerSocket server = null;
            server = new ServerSocket(8082);
            socket = server.accept();
            System.out.println("Client accepted from " + socket.getInetAddress());
            ind++;
            Thread t = new Server(socket, server, ind);
            t.start();
        }catch(IOException e){ if(!(e instanceof BindException)) e.printStackTrace(); }


    }
}
