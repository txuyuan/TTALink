package me.xuyuan.server;

import com.mongodb.*;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import me.xuyuan.data.CoorCodec;
import me.xuyuan.data.Coordinate;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class Database {

    MongoClient client = null;
    MongoDatabase database = null;

    public Database(){ connect("mongodb://localhost:27017/mongoTest"); }
    public Database(String uri){ connect(uri); }


    private void connecttest(String uri){
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

    public void connect(String uri){
        ConnectionString connectionString = new ConnectionString("mongodb://127.0.0.1:27017/mongoTest");
        CodecRegistry codecReg = CodecRegistries.fromCodecs(new CoorCodec());
        client = new MongoClient("", MongoClientOptions.builder().codecRegistry(codecReg).build());
        MongoDatabase db = client.getDatabase("mongoTest");
        db = db.withCodecRegistry(codecReg);

        MongoCollection<Coordinate> coordinates = db.getCollection("testCollection", Coordinate.class).withCodecRegistry(codecReg);
        Coordinate coord1 = new Coordinate(Long.parseLong("1626176232"), 1.349056579971551, 103.7160372787831, UUID.fromString("9f755961-017d-4bfa-9646-ac6944936d9f"));
        coordinates.insertOne(coord1);

        if(coordinates!=null)
            listCoordinates(coordinates);
    }



    private void listCoordinates(MongoCollection<Coordinate> coCollection){
        List<Coordinate> coordinates = getCoordinates(coCollection);
        for(Coordinate co: coordinates){
            System.out.println("Epoch: " + co.getEpoch() + co.getYear() + ", Month: " + co.getMonth() + ", Day: " + co.getDay() + ", Hour: " + co.getHour() + ", Minute: " + co.getMinute() +
                    ", Latitude: " + co.getLatitude() + ", Longtitude: " + co.getLongtitude());
        }
    }

    private List<Coordinate> getCoordinates(MongoCollection<Coordinate> coCollection){
        List<Coordinate> coordinates = null;
        Coordinate co = coCollection.find().first();
        coordinates.add(co);
        return coordinates;
    }

    public void close(){
        client.close();
    }

}
