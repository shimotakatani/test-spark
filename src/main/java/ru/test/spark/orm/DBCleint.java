package ru.test.spark.orm;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import ru.test.spark.consts.AppConst;
import ru.test.spark.consts.CollectionsConst;
import ru.test.spark.entity.DepartmentEntity;
import ru.test.spark.entity.UserEntity;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static ru.test.spark.utils.CommonUtils.isNotNull;

/**
 * Клиент для базы данных(кривенький)
 * create time 11.10.2017
 *
 * @author nponosov
 */
public class DBCleint {

    private DBCleint(){}

    private static CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build()));
    private static MongoClient mongoClient = new MongoClient(AppConst.Default.HOST_URL, MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build() );
    private static MongoDatabase database = mongoClient.getDatabase(AppConst.Default.DB_NAME);
    private static MongoCollection<UserEntity> userCollection = database.getCollection(CollectionsConst.Collections.Users.COLLECTION_NAME, UserEntity.class);
    private static MongoCollection<DepartmentEntity> departmentCollection = database.getCollection(CollectionsConst.Collections.Department.COLLECTION_NAME, DepartmentEntity.class);

//    public static MongoCollection<Document> getCollectionByName(String collectionName){
//        MongoCollection<Document> defaultCollection = userCollection; //пока коллекция по умолчанию - пользователи
//        if (isNotNull(collectionName)){
//            switch (collectionName){
//                case CollectionsConst.Collections.Users.COLLECTION_NAME : return userCollection;
//                case CollectionsConst.Collections.Department.COLLECTION_NAME : return departmentCollection;
//                default: return defaultCollection;
//            }
//        }
//        return defaultCollection;
//    }

    public static MongoClient getMongoClient() {
        return mongoClient;
    }

    public static MongoDatabase getDatabase() {
        return database;
    }

    public static MongoCollection<UserEntity> getUserCollection() {
        return userCollection;
    }

    public static MongoCollection<DepartmentEntity> getDepartmentCollection() {
        return departmentCollection;
    }
}
