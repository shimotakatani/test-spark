package ru.test.spark.resource; /**
 * create time 11.10.2017
 *
 * @author nponosov
 */

import ru.test.spark.dao.TestDao;

import static spark.Spark.*;

public class TestResource {
    public static void main(String[] args) {
        TestDao dao = new TestDao();
        String message = "Hello World! We have " + dao.getUsersCount() + " users!!!\n First user is " + dao.getUserById(1L).toJson();
        get("/hello", (req, res) -> message);


    }
}

