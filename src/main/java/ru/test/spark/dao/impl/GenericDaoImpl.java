package ru.test.spark.dao.impl;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import ru.test.spark.consts.CollectionsConst;
import ru.test.spark.dao.interfaces.GenericDao;
import ru.test.spark.filters.AbstractFilter;
import static com.mongodb.client.model.Filters.*;

import java.util.List;

/**
 * Абстрактное DAO
 * create time 11.10.2017
 *
 * @author nponosov
 */
public abstract class GenericDaoImpl<T> implements GenericDao<T>{

    //Коллекция с которой работает DAO
    private MongoCollection<Document> collection;

    protected GenericDaoImpl(){

    }

    @Override
    public T getById(Long id) {

        return collection.find(eq(CollectionsConst.Collections.Abstract.ID, id)).first();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<T> getAllActive() {
        return null;
    }

    @Override
    public List<T> getAll() {
        return null;
    }

    @Override
    public List<T> getAllActive(AbstractFilter filter) {
        return null;
    }

    @Override
    public T update(T entity) {
        return null;
    }

    @Override
    public T insert(T entity) {
        return null;
    }
}
