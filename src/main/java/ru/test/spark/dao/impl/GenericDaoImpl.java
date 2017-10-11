package ru.test.spark.dao.impl;

import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;
import ru.test.spark.consts.CollectionsConst;
import ru.test.spark.dao.interfaces.GenericDao;
import ru.test.spark.enums.EntityStatusEnum;
import ru.test.spark.filters.AbstractFilter;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Абстрактное DAO
 * create time 11.10.2017
 *
 * @author nponosov
 */
public abstract class GenericDaoImpl<T> implements GenericDao<T>{

    //Коллекция с которой работает DAO
    protected MongoCollection<T> collection;

    protected GenericDaoImpl(){

    }

    @Override
    public T getById(ObjectId id) {
        return collection.find(eq(CollectionsConst.Collections.Abstract.ID, id)).first();
    }

    @Override
    public void deleteById(ObjectId id) {
        collection.deleteOne(eq(CollectionsConst.Collections.Abstract.ID, id));
    }

    @Override
    public List<T> getAllActive() {
        List<T> entities = new ArrayList<>();
        Block<T> findedList = new Block<T>() {
            @Override
            public void apply(T t) {
                entities.add(t);
            }
        };
        collection.find(eq(CollectionsConst.Collections.Abstract.STATUS, EntityStatusEnum.ACTIVE.toString())).forEach(findedList);
        return entities;
    }

    @Override
    public List<T> getAll() {
        List<T> entities = new ArrayList<>();
        Block<T> findedList = new Block<T>() {
            @Override
            public void apply(T t) {
                entities.add(t);
            }
        };
        collection.find().forEach(findedList);
        return entities;
    }

    @Override
    public List<T> getAllActive(AbstractFilter filter) {
        return null; //TODO реализуется только после реализации построителя запросов
    }

    //TODO Переделать метод
    @Override
    public T update(T entity, ObjectId id) {
        collection.replaceOne(eq(CollectionsConst.Collections.Abstract.ID, id.toString()), entity);
        return entity;
    }

    @Override
    public void insert(T entity) {
        collection.insertOne(entity);
        //return entity; //TODO тут потом посмотреть что вернётся, возможно придётся ещё раз искать или интерфейс менять
    }

    //Ненужный метод, потом удалить
    @Deprecated
    @Override
    public T getFirst() {
        return collection.find().first();
    }

    @Override
    public Long getActiveCount() {
        return collection.count();
    }
}
