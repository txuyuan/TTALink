package me.xuyuan.server;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import me.xuyuan.data.Coordinate;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.print.Doc;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Database {

    MongoClient client = null;
    MongoDatabase database = null;

    /** Database constructor for local server*/
    public Database(){
        try{
            MongoClient client = new MongoClient();
            database = client.getDatabase("TtaDb");
        }catch(Exception e){ System.out.println(e);}
    }
    /** @param uri IP Address of target server. Include ports*/
    public Database(String uri){
        try{
            MongoClient client = new MongoClient(uri);
            database = client.getDatabase("TtaDb");
        }catch(Exception e){ System.out.println(e);}
    }



    //Functions
    public void save(List<Coordinate> coList){
        MongoCollection<Document> collection = database.getCollection("coordinates");
        try{
            for(Coordinate co : coList)
                collection.insertOne(co.getDocument());
        }catch(Exception e){
            System.out.println(e);
        }
    }

    /**
     * Gets Coordinate object from target ObjectId
     * Returns null if object nonexistent or other error printed
     * May throw FileSystemException
     * @param id ObjectId of intended object
     */
    public Coordinate getCoordinate(ObjectId id){
        MongoCollection<Document> collection = database.getCollection("coordinates");
        Coordinate co;
        try{
            FindIterable<Document> it = collection.find(Filters.eq("_id", id));
            List<Document> docs = new ArrayList<>();
            for(Document doc : it)
                docs.add(doc);
            if(docs.size()!=1) {
                System.out.println("Document retrieval gave " + docs.size() + " documents");
                throw new FileSystemException("File search for " + id + " did not give 1 document only");
            }
            co = Coordinate.getCoordinate(docs.get(0));
            return co;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
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