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

        System.out.println(command);

        String[] ip_address = {"127.0.0.1"};

//        client.run(ip_address[0],0, command);
//        client.run(ip_address[0],1, command);
//        client.run(ip_address[0],2, command);
//        client.run(ip_address[0],3, command);
        for (int nodes=0; nodes<1; nodes++){
            client_component client = new client_component(properties.getProperty("machine.ip"),
                    Integer.parseInt(properties.getProperty("port.number")), command);
            client.start();
        }

    }


}
