package org.example;

import org.example.config.AppConfig;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        System.out.println("Press 1 for server and 2 for client");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if (choice == 1) {
            server s = new server();
            s.run_server();
        }else{
            client c = new client();
            c.run_client();
        }
    }
}