package org.example;

import org.example.config.CLIPrinter;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class client_component extends Thread {

    String ipAddress;
    int port;
    String command;

    public client_component(String ipAddress,int port, String command) {
            this.ipAddress = ipAddress;
            this.port = port;
            this.command = command;
    }

    public void run() {
        String threadName = Thread.currentThread().getName() + ": ";
        System.out.println( threadName + "establishing a connection to machine " + ipAddress + " " + port);

        // establish a connection
        try {
            Socket socket = null;
            ObjectInputStream ois = null;

            try {
                socket = new Socket(ipAddress, port);
                socket.setSoTimeout(10000);
                System.out.println(threadName + " " + "Connected");
            }
            catch (Exception e) {
//                System.out.println(threadName + " " + "Unable to connect the machine with IP Address " + ipAddress + " and Port " + port);
                throw new RuntimeException("Unable to connect the machine with IP Address " + ipAddress + " and Port " + port);
            }

            try {
                OutputStream outputStream = socket.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                dataOutputStream.writeUTF(command);
            } catch (Exception e) {
                throw new RuntimeException("Not able to send command " + command);
            }

            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);

            String response = "";
            while(!response.equals("Query Completed")) {
                response = dataInputStream.readUTF();
                System.out.println(threadName + " " + response);
                if(response.equals("Query Failed")) {
                    throw new RuntimeException(response);
                }
            }

            try {
                ois = new ObjectInputStream(socket.getInputStream());
                List<String> obj = (List<String>) ois.readObject();
                System.out.println(obj.size());
                CLIPrinter cliPrinter = new CLIPrinter();
                cliPrinter.printResult(obj);
            }catch (Exception e) {
//                System.out.println(threadName + " Error occured while processing the command");
//                e.printStackTrace();
                throw new RuntimeException("Error occured while processing the result");
            }

            //close the connection
            socket.close();
        }
        catch (Exception i) {
            System.out.println(threadName + " " + i.getMessage());
        }

    }
}
