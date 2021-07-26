package me.xuyuan.client;

import me.xuyuan.data.Coordinate;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClientProcess {

    public static List<Coordinate> parse(List<String> data) throws IOException{
        List<Coordinate> coList = new ArrayList<>();

        for(String line: data){
            String[] elements = line.split("~");
            if(elements.length > 5){
                IOException i = new IOException("Too many elements in server-returned value " + line);
                throw i;
            }
            try{
                Long epoch = Long.parseLong(elements[0]);
                Double lat = Double.parseDouble(elements[1]);
                Double longt = Double.parseDouble(elements[2]);
                UUID clientId = UUID.fromString(elements[3]);
                ObjectId id = new ObjectId(elements[4]);
                Coordinate co = new Coordinate(epoch, lat, longt, clientId, id);
                coList.add(co);
            }catch(Exception e){
                e.printStackTrace();
                return null;
            }
        }

        return coList;
    }

}
