package org.example;

import org.example.config.AppConfig;
import org.unix4j.Unix4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigestSpi;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class server {

    public void run_server(){
        System.out.println("Server is running");
        Socket socket = null;
        ServerSocket server = null;

                // starts server and waits for a connection
        AppConfig appConfig = new AppConfig();
        Properties properties = appConfig.readConfig();
        try
        {
            server = new ServerSocket(Integer.parseInt(properties.getProperty("port.number")));
            while(true) {
                ObjectOutputStream oos = null;
                String request = null;
                String response = "";
                System.out.println("Server started");
                System.out.println("Waiting for a client to connect...");
                socket = server.accept();
                System.out.println("Client __ is connected to server");

                InputStream inputStream = socket.getInputStream();
                DataInputStream dataInputStream = new DataInputStream(inputStream);

                request = dataInputStream.readUTF();

                OutputStream outputStream = socket.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                dataOutputStream.writeUTF("command received");
                //Enter the querying code below
                //------------
                GrepExecutor grepExecutor = new GrepExecutor();
                List<String> responseList = grepExecutor.executeGrep(request);
                //TODO for now printing it later send it back to client
                System.out.println(responseList.size());
                try {
                    while (!response.equals("Query Completed")) {

                        if (responseList != null && responseList.size() > 0) {
                            response = "Query Completed";
                            System.out.println(response);
                            dataOutputStream.writeUTF(response);
                            dataOutputStream.flush();
                        }
                        else {
                            response = "Query Failed";
                            dataOutputStream.writeUTF(response);
                            dataOutputStream.flush();
                        }

                        oos = new ObjectOutputStream(socket.getOutputStream());
                        oos.writeObject(responseList);
                    }
                }
                catch (Exception e) {
                    dataOutputStream.writeUTF("Query Failed");
                    throw new RuntimeException("Query Failed");
                }
//                oos.close();
                dataOutputStream.close();
                dataInputStream.close();

                socket.close();
            }
//            server.close();
        }
        catch(IOException i)
        {
            System.out.println(i.getMessage());
        }
    }

}
