package ru.test.spark.service.interfaces;

import ru.test.spark.dto.UserDto;
import ru.test.spark.filters.UserFilter;

import java.util.List;
import java.util.UUID;

/**
 * Сервис пользователей
 * create time 13.10.2017
 *
 * @author nponosov
 */
public interface UserService {

    /**
     * Получить DTO пользователя по id
     * @param id - идентификатор пользователя
     * @return DTO пользователя
     * @author nponosov
     */
    UserDto getUserById(UUID id);

    /**
     * Удаление пользователя по его id
     * @param id - идентификатор пользователя
     * @author nponosov
     */
    void deleteUserById(UUID id);

    /**
     * Получить список пользователей (с фильтрацией)
     * @param filter - фильтр с параметрами для запроса
     * @return список пользователей
     * @author nponosov
     */
    List<UserDto> getUserDtoList(UserFilter filter);

    /**
     * Получить список пользователей(просто всех)
     * @return список пользователей
     * @author nponosov
     */
    List<UserDto> getUserDtoList();

    /**
     * Получить размер выборки пользователей по фильтру
     * @param filter - фильтр с параметрами для запроса
     * @return количество записей в выборке
     * @author nponosov
     */
    Long getUserDtoListCount(UserFilter filter);

    /**
     * Получить размер выборки пользователей (просто всех)
     * @return количество записей в выборке
     * @author nponosov
     */
    Long getUserDtoListCount();

    /**
     * Обновить пользователя
     * @param updateUserDto - DTO пользователя с новыми данными
     * @return обновлённый пользователь
     */
    UserDto updateUser(UserDto updateUserDto);

    /**
     * Вставить пользователя
     * @param newUserDto - DTO вставляемого пользователя с данными
     * @return вставленный пользователь
     */
    UserDto insertUser(UserDto newUserDto);
}
