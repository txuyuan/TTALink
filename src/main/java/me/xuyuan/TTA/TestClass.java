package me.xuyuan.TTA;

import me.xuyuan.client.Client;
import me.xuyuan.data.Coordinate;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestClass {

    static void test(){
        System.out.println("\n\nStart");

        System.out.println("Connecting...");
        Client cl = new Client();
        System.out.println("Connected!");

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
    }

}
