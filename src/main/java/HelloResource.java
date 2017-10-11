/**
 * create time 11.10.2017
 *
 * @author nponosov
 */

import static spark.Spark.*;

public class HelloResource {
    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello World");
    }
}

