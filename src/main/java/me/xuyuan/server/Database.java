package me.xuyuan.server;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import me.xuyuan.data.Coordinate;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.UUID;

public class Database {

    MongoClient client = null;
    MongoDatabase database = null;

    public Database(){
        try{
            MongoClient client = new MongoClient();
            database = client.getDatabase("TTADB");
        }catch(Exception e){ System.out.println(e);}
    }
    public Database(String uri){
        try{
            MongoClient client = new MongoClient(uri);
            database = client.getDatabase("TTADB");
        }catch(Exception e){ System.out.println(e);}
    }



    //Functions
    public void save(List<Coordinate> coList){
        try{
            MongoCollection<Document> collection = database.getCollection("coordinates");
            for(Coordinate co : coList)
                collection.insertOne(co.getDocument());
        }catch(Exception e){
            System.out.println(e);
        }
    }



    //TEMP
    private Coordinate getTestCoordinate(){
        Long epoch = Long.parseLong("1625840365");
        Double lat = Double.parseDouble("1.349024207451044");
        Double longt = Double.parseDouble("103.71602810739925");
        UUID clientId = UUID.fromString("9a9b92ca-9b1c-40e9-a297-345c4b3e6036");

        Coordinate co = new Coordinate(epoch, lat, longt, clientId, new ObjectId());
        return co;
    }



    public void close(){
        client.close();
    }

}