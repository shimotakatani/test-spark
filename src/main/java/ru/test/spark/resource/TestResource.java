package ru.test.spark.resource; /**
 * create time 11.10.2017
 *
 * @author nponosov
 */

import org.bson.types.ObjectId;
import ru.test.spark.dao.TestDao;
import ru.test.spark.dao.impl.UserDaoImpl;
import ru.test.spark.dao.interfaces.UserDao;
import ru.test.spark.entity.UserEntity;
import ru.test.spark.service.TestService;

import static spark.Spark.*;

public class TestResource {

    public static TestDao dao = new TestDao();
    public static UserDao userDao = new UserDaoImpl();
    public static TestService testService = new TestService();

    public static void main(String[] args) {

        get("/hello", (req, res) -> createHelloMassage());
        get("/insert", (req, res) -> generateData());

    }

    private static String createHelloMassage(){

        ObjectId id =  new ObjectId("59ddff6139f23979a0c33ebb");
        return "Hello World! We have " + userDao.getActiveCount() + " users!!!\n First user is " + dao.getUserById(1L).toJson();
    }

    private static String generateData(){
        UserEntity newUser = testService.newRandomUser();
        userDao.insert(newUser);

        return newUser.toString();
    }


}

