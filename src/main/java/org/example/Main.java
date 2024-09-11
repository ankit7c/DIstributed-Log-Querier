package org.example;

import org.example.config.AppConfig;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

//        System.out.println("Press 1 for server and 2 for client");
//        Scanner scanner = new Scanner(System.in);
//        int choice = scanner.nextInt();
        String choice = args[0];
        System.out.println(args.length);
        if (choice.equals("server")) {
            server s = new server();
            s.run_server();
        }else if(choice.equals("client")){
            client c = new client();
            c.run_client(args[1]);
        }else {
            System.out.println("Invalid choice");
        }
    }
}