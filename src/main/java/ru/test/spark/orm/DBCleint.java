package ru.test.spark.orm;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import ru.test.spark.consts.AppConst;
import ru.test.spark.consts.CollectionsConst;

import static ru.test.spark.utils.CommonUtils.isNotNull;

/**
 * Клиент для базы данных(кривенький)
 * create time 11.10.2017
 *
 * @author nponosov
 */
public class DBCleint {

    private DBCleint(){}

    private static MongoClient mongoClient = new MongoClient(AppConst.Default.HOST_URL, AppConst.Default.HOST_PORT );
    private static MongoDatabase database = mongoClient.getDatabase(AppConst.Default.DB_NAME);
    private static MongoCollection<Document> userCollection = database.getCollection(CollectionsConst.Collections.Users.COLLECTION_NAME);
    private static MongoCollection<Document> departmentCollection = database.getCollection(CollectionsConst.Collections.Department.COLLECTION_NAME);

    public static MongoCollection<Document> getCollectionByName(String collectionName){
        MongoCollection<Document> defaultCollection = userCollection; //пока коллекция по умолчанию - пользователи
        if (isNotNull(collectionName)){
            switch (collectionName){
                case CollectionsConst.Collections.Users.COLLECTION_NAME : return userCollection;
                case CollectionsConst.Collections.Department.COLLECTION_NAME : return departmentCollection;
                default: return defaultCollection;
            }
        }
        return defaultCollection;
    }

    public static MongoClient getMongoClient() {
        return mongoClient;
    }

    public static MongoDatabase getDatabase() {
        return database;
    }

    public static MongoCollection<Document> getUserCollection() {
        return userCollection;
    }

    public static MongoCollection<Document> getDepartmentCollection() {
        return departmentCollection;
    }
}
