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
import org.bson.types.MinKey;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.function.Consumer;

public class Main {

    public static void main (String[] args){

        MainData counter = new MainData();

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
                        Thread t = new Server(socket, server, counter, ind);
                        t.start();
                    }catch(IOException e){e.printStackTrace();}
                }
            }


        }
    }
}
