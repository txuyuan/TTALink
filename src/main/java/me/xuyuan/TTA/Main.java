package me.xuyuan.TTA;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import me.xuyuan.server.Database;
import me.xuyuan.server.Server;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.function.Consumer;

public class Main {

    public static void main (String[] args){

        Database db = new Database();

        /*Scanner in = new Scanner(System.in);
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


        }*/
    }
}
