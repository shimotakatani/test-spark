package ru.test.spark.logger;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.AbstractNCSARequestLog;

import java.io.IOException;

/**
 * create time 12.10.2017
 *
 * @author nponosov
 */
public class RequestLogFactory {
    private Logger logger;

    public RequestLogFactory(Logger logger) {
        this.logger = logger;
    }

    AbstractNCSARequestLog create() {
        return new AbstractNCSARequestLog() {
            @Override
            protected boolean isEnabled() {
                return true;
            }

            @Override
            public void write(String s) throws IOException {
                logger.info(s);
            }
        };
    }
}
