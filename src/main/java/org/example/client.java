package org.example;

import org.example.config.AppConfig;

import java.util.Properties;
import java.util.Scanner;
public class client {

    public void run_client(){

        AppConfig appConfig = new AppConfig();
        Properties properties = appConfig.readConfig();

        System.out.println("Please enter the command : ");
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        System.out.println("Command to be executed is " + command);

        for (int nodes=0; nodes<1; nodes++){
            client_component client = new client_component(properties.getProperty("peer.machine.ip" + nodes),
                    Integer.parseInt(properties.getProperty("peer.machine.port" + nodes)), command);
            client.start();
        }

    }


}
