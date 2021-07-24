package me.xuyuan.TTA;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import me.xuyuan.data.Coordinate;
import me.xuyuan.server.Database;
import me.xuyuan.server.Process;
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

        Database db = new Database("34.126.108.92:27017");
        List<String> testData = Arrays.asList("1625840365~1.349024207451044~103.71602810739925~9a9b92ca-9b1c-40e9-a297-345c4b3e6036~60fbb487cef88854141d39e2");
        Process.sort(testData);

        ObjectId testId = new ObjectId("60fbb487cef88854141d39e2");
        Coordinate co = db.getCoordinate(testId);
        System.out.print(co.toString());

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
