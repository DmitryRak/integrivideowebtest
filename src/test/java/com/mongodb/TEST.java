package com.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class TEST    {

    //TODO open remote connections on the server
    public static void main(String[] args) {
        MongoClient client = new MongoClient("dev.integrivideo.com", 27017);
        MongoDatabase mongoDb = client.getDatabase("integri-video-dev");
        MongoCollection<Document> collection = mongoDb.getCollection("projects");
        System.out.println(collection.count());
    }
}
