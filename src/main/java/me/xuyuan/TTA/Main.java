package me.xuyuan.TTA;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import me.xuyuan.client.Client;
import me.xuyuan.data.Coordinate;
import me.xuyuan.server.Database;
import me.xuyuan.server.Server;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.print.Doc;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Main {

    public static MainData data;

    public static void main (String[] args){


        System.out.println("\n\nStart");

        Client cl = new Client();

        Coordinate coT = new Coordinate(Long.parseLong("16072394762"), 1523.3391317312310583, 403.79496685803359, UUID.randomUUID(), new ObjectId());

        List<Coordinate> coList = new ArrayList<>();
        coList.add(coT);
        List<Coordinate> rList = new ArrayList<>();
        try{
            rList = cl.send(coList);
        }catch(IOException e){e.printStackTrace();}

        for(Coordinate co: rList){
            co.print();
        }


        /*
        System.out.println("TTALink Server Starting...");
        MainData counter = new MainData();
        data = counter;

        Scanner in = new Scanner(System.in);
        Thread readT = new ReadIn(in);
        readT.start();

        System.out.println("Listening on port 443");
        int ind = 1;
        while(counter.allowNew){
            try {
                Socket socket = null;
                ServerSocket server = null;
                server = new ServerSocket(443);
                socket = server.accept();
                System.out.println("Client accepted from " + socket.getInetAddress());
                ind++;
                Thread t = new Server(socket, server, ind);
                t.start();
            }catch(IOException e){ if(!(e instanceof BindException)) e.printStackTrace(); }
        }


         */
    }
}
