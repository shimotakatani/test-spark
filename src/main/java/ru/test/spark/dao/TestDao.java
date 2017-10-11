package ru.test.spark.dao;

/**
 * create time 11.10.2017
 *
 * @author nponosov
 */
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.bson.Document;
import java.util.Arrays;
import com.mongodb.Block;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;

public class TestDao {

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;
    public TestDao(){
        mongoClient = new MongoClient( "localhost" , 27017 );
        database = mongoClient.getDatabase("test");
        collection = database.getCollection("users");
    }

    public void addUser( String userName){
        Document newUser = new Document("name", userName);
        collection.insertOne(newUser);
    }

    public Long getUsersCount(){
        return collection.count();
    }

    public Document getUserById(Long id){
        return collection.find().first();
    }

}
