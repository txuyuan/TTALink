package me.xuyuan.server;

import me.xuyuan.data.Coordinate;

import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Process {

    public static void sort(ArrayList<String> dataArray){
        List<Coordinate> data = new ArrayList<>();
        for(String i : dataArray){
            String[] split = i.split("~", 3);
            if(split.length > 3)
                System.out.println("Client sent data with too many arguments");
            Coordinate co = null;
            Boolean valid = true;
            try{
                co = new Coordinate(Long.parseLong(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]), UUID.fromString(split[3]));
            }catch(Exception e){
                System.out.println(e);
                valid = false;}
            if(valid)
                data.add(co);
        }

        //TODO:Redirect to Database
    }

}
