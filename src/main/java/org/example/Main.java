package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is main entry point
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        String choice = args[0];
        if (choice.equals("server")) {
            server s = new server();
            s.run_server();
        }else if(choice.equals("client")){
            client c = new client();
            c.runClient(args[1]);
        }else {
            logger.error("Invalid choice");
        }
    }
}