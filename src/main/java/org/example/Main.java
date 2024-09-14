package org.example;

import org.example.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        String choice = args[0];
        if (choice.equals("server")) {
            server s = new server();
            s.run_server();
        }else if(choice.equals("client")){
            client c = new client();
            c.run_client(args[1]);
        }else {
            logger.error("Invalid choice");
        }
    }
}