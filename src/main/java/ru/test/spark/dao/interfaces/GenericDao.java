package ru.test.spark.dao.interfaces;

import ru.test.spark.filters.AbstractFilter;

import java.util.List;

/**
 * create time 11.10.2017
 *
 * @author nponosov
 */
public interface GenericDao<T> {

    T getById(Long id);

    void deleteById(Long id);

    List<T> getAllActive();

    List<T> getAll();

    List<T> getAllActive(AbstractFilter filter);

}
