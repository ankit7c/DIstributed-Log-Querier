package org.example;

import org.example.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
public class client {

    private static final Logger logger = LoggerFactory.getLogger(client.class);

    public void runClient(String command){

        AppConfig appConfig = new AppConfig();
        Properties properties = appConfig.readConfig();
        logger.info("Command to be executed is " + command);

        for (int nodes=0; nodes < Integer.parseInt(properties.getProperty("no.of.machines")); nodes++){
            client_component client = new client_component(properties.getProperty("peer.machine.ip" + nodes),
                    Integer.parseInt(properties.getProperty("peer.machine.port" + nodes)),
                    properties.getProperty("peer.machine.name" + nodes),
                    command);
            client.start();
        }

    }


}

