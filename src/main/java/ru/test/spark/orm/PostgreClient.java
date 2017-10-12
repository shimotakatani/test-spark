package ru.test.spark.orm;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Клиент для Postgresql
 * create time 12.10.2017
 *
 * @author nponosov
 */
public final class PostgreClient {

    public static Connection getPostgresConnection() {
        try {
            DriverManager.registerDriver((Driver)
                    Class.forName("org.postgresql.Driver").newInstance());


            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test","test", "test");
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
