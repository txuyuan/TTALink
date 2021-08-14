package me.xuyuan.TTA;

import me.xuyuan.client.Client;
import me.xuyuan.data.Coordinate;
import me.xuyuan.server.Database;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class TestClass {

    static void test(){
        System.out.println("\n\nTTALink Client Test Started");

        System.out.println("Connecting...\n");
        Client cl = new Client("127.0.0.1");

        System.out.println("Generating Coordinate\n");
        ObjectId id = new ObjectId();
        Coordinate coT = new Coordinate(Long.parseLong("16072394762"), 1523.3391317312310583, 403.79496685803359, UUID.randomUUID(), id);

        System.out.println("Sending...");
        List<Coordinate> coList = new ArrayList<>();
        coList.add(coT);
        List<Coordinate> rList = new ArrayList<>();
        try{
            rList = cl.send(coList);
        }catch(IOException e){e.printStackTrace();}

        if(!rList.contains(null) && rList.size()!=0) for (Coordinate co: rList){
            System.out.print("Coordinate received >> "); co.print();
        }else System.out.println("No matches found\n");
    }

    static void serverGenerate(){
        try{
            System.out.println("Generating");
            Long epoch = System.currentTimeMillis()*1000;
            epoch = epoch-epoch%1;
            Coordinate co5 = new Coordinate(epoch+198, 1523.339171233120000, 123.339173120583, UUID.randomUUID(), new ObjectId());
            List<Coordinate> coList = Arrays.asList(co5);
            System.out.println("List generated");
            Database db = new Database();
            System.out.println("Database connected");
            db.save(coList);
            System.out.println("Completed");
            System.exit(0);
        }catch(Exception e){e.printStackTrace();}
    }

}
