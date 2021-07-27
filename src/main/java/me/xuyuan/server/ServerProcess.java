package me.xuyuan.server;

import me.xuyuan.data.Coordinate;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ServerProcess {

    public static void sort(List<String> dataArray){
        List<Coordinate> data = new ArrayList<>();
        for(String i : dataArray){
            String[] split = i.split("~");
            if(split.length > 5) {
                System.out.println("Client sent data with too many arguments");
                return;
            }
            Coordinate co = parse(split);
            if(co!=null) data.add(co);
        }

        Database db = new Database("34.126.108.92:27017");
        db.save(data);
    }



    private static Coordinate parse(String[] data){
        try{
            Long epoch = Long.parseLong(data[0]);
            Double lat = Double.parseDouble(data[1]);
            Double longt = Double.parseDouble(data[2]);
            UUID clientId = UUID.fromString(data[3]);
            ObjectId id = new ObjectId(data[4]);
            Coordinate co = new Coordinate(epoch, lat, longt, clientId, id);
            return co;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

}
