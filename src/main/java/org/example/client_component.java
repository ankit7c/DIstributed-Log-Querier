package org.example;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class client_component extends Thread {

    String ipAddress;
    int port;
    String command;

    public client_component(String ipAddress,int port, String command) {
            this.ipAddress = ipAddress;
            this.port = port;
            this.command = command;
    }
//    get_result_from_server : String ip, int port, String command
    public void run() {
        System.out.println( "establishing a connection to machine " + ipAddress + " " + port);

        // establish a connection
        try {
            Socket socket = null;
//            ServerSocket serverSocket = new ServerSocket(5001);
            try {
                socket = new Socket(ipAddress, port);
                System.out.println("Connected");
            }
            catch (Exception e) {
                System.out.println("Unable to connect the machine : " + e);
            }

            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF(command);

            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);

            String response = "";
            while(!response.equals("Query Completed")) {
                response = dataInputStream.readUTF();
                System.out.println(response);
            }

            //close the connection
            socket.close();
        }
        catch (IOException i) {
            System.out.println(i);
        }

    }
}
