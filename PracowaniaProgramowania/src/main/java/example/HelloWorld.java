package example;

import org.apache.log4j.Logger;

import java.io.IOException;

public class HelloWorld {

    final static Logger logger = Logger.getLogger(HelloWorld.class);

    public static void main(String [ ] args) throws InterruptedException, IOException {
        logger.info("PRZYROST I");
    }
}