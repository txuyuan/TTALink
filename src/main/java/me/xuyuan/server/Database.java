package me.xuyuan.server;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import me.xuyuan.data.Coordinate;
import org.bson.Document;
import org.bson.types.ObjectId;

public class Database {

    MongoClient client = null;
    MongoDatabase database = null;

    public Database(){ connect("mongodb://localhost:27017/mongoTest"); }
    public Database(String uri){ connect(uri); }


    private void connect(String uri){
        try{
            //MongoClient client = new MongoClient(uri);
            database = client.getDatabase("mongoTest");
            MongoCollection<Document> collection = database.getCollection("testCollection");


            Document doc2 = collection.find().first();
            System.out.println(doc2.toString());

            Document doc1 = new Document("index1", "DocumentValue1")
                    .append("index2", "DocumentValue2");
            //collection.insertOne(doc1);

            Document homeCoords = new Document("_id", new ObjectId())
                    .append("epoch", 1625840365)
                    .append("lat", 1.349024207451044)
                    .append("longt", 103.71602810739925);


        }catch(Exception e){ System.out.println(e); }
    }

    //TEMP
    private void listCoordinates(Coordinate coordinate){
        System.out.println("Time " + coordinate.getEpoch() + " Lat " + coordinate.getLatitude() + " Longt " + coordinate.getLongtitude() +
                " ClientID " + coordinate.getClientID() + " ObjectID " + coordinate.getObjectId());
    }

    public void close(){
        client.close();
    }

}