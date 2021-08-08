package me.xuyuan.server;

import me.xuyuan.data.Coordinate;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ServerProcess {

    public static UUID sort(List<String> dataArray){
        List<Coordinate> data = new ArrayList<>();
        for(String i : dataArray){
            String[] split = i.split("~");
            if(!(split.length > 5)) {
                Coordinate co = parse(split);
                if(co!=null) data.add(co);
            }
            else System.out.println("Client sent data with too many arguments : " + split.toString());
        }

        List<UUID> ids = new ArrayList<>();
        for(Coordinate co: data){
            UUID uuid = co.getClientID();
            if(!ids.contains(uuid))
                ids.add(uuid);
        }
        if(ids.size()!=1)
            new Exception("Ingress datastream includes more than one clientId, " + ids.toString()).printStackTrace();
        UUID clientId = ids.get(1);

        Database db = new Database("34.126.108.92:27017");
        db.save(data);

        return clientId;
    }

    public static List<Coordinate> getMatches(UUID clientId){
        Database db = new Database("34.126.108.92:27017");
        return db.getMatches(clientId);
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
