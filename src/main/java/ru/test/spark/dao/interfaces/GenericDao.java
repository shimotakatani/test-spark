package ru.test.spark.dao.interfaces;

import ru.test.spark.filters.AbstractFilter;

import java.util.List;

/**
 * Интерфейс абстрактного DAO
 * create time 11.10.2017
 *
 * @author nponosov
 */
public interface GenericDao<T> {

    /**
     * Получить сущность по её id
     * @param id - id сущности
     * @return Единственную сущность или null если ничего не нашли
     * @author nponosov
     */
    T getById(Long id);

    /**
     * Удаляет сущность по её id
     * @param id - id сущности
     * @author nponosov
     */
    void deleteById(Long id);

    /**
     * Получить все активные сущности
     * @return список сущностей
     * @author nponosov
     */
    List<T> getAllActive();

    /**
     * Получить все сущности(в том числе удалённые)
     * @return список сущностей
     * @author nponosov
     */
    List<T> getAll();

    /**
     * Получить активные сущности по фильтру
     * @param filter - фильтр сущности
     * @return список сущностей
     * @author nponosov
     */
    List<T> getAllActive(AbstractFilter filter);

    /**
     * Обновить сущность
     * @param entity - экземпляр сущности(должен быть с id)
     * @return обновлённую сущность
     */
    T update(T entity);

    /**
     * Вставить сущность
     * @param entity - новый экземпляр сущности
     * @return вставленный экземпляр сущности
     */
    T insert(T entity);

}
