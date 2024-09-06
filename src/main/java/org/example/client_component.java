package org.example;

import java.io.*;
import java.net.Socket;

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
        System.out.println("My Name " + Thread.currentThread().getName());
        System.out.println(port);
        // establish a connection
        try {
            Socket socket = null;
//            ServerSocket serverSocket = new ServerSocket(5001);

            socket = new Socket(ipAddress, port);
            System.out.println("Connected");

            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF(command);

            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);

            String response = dataInputStream.readUTF();
            System.out.println(response);

            //close the connection
            socket.close();
        }
        catch (IOException i) {
            System.out.println(i);
        }

    }
}
