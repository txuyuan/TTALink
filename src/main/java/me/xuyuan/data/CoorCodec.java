package me.xuyuan.data;

import com.mongodb.MongoClientSettings;
import org.bson.*;
import org.bson.codecs.*;
import org.bson.types.ObjectId;

import java.util.UUID;

public class CoorCodec implements CollectibleCodec<Coordinate> {

    private Codec<Document> documentCodec = new DocumentCodec();


    @Override
    public void encode(BsonWriter writer, Coordinate coordinate, EncoderContext context){
        try{
            writer.writeStartDocument();
            writer.writeObjectId("_id", new ObjectId());
            writer.writeInt64("epoch", coordinate.getEpoch());
            writer.writeDouble("lat", coordinate.getLatitude());
            writer.writeDouble("long", coordinate.getLongtitude());
            writer.writeString("clientId", coordinate.getClientID().toString());
            writer.writeEndDocument();
        }catch(BsonSerializationException b){ System.out.println(b); }
    }

    @Override
    public Coordinate decode(BsonReader reader, DecoderContext decoderContext) {
        long epoch = reader.readInt64("epoch");
        double lat = reader.readDouble("lat");
        double longt = reader.readDouble("long");
        UUID clientId = UUID.fromString(reader.readString("clientId"));

        return new Coordinate(epoch, lat, longt, clientId);
    }

    @Override public Class<Coordinate> getEncoderClass() {
        return Coordinate.class;
    }

    @Override
    public Coordinate generateIdIfAbsentFromDocument(Coordinate document) {
        return null;
    }

    @Override
    public boolean documentHasId(Coordinate document) {
        return false;
    }

    @Override
    public BsonValue getDocumentId(Coordinate document) {
        return null;
    }
}
