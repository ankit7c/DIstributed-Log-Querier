package org.example;

import org.unix4j.Unix4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigestSpi;
import java.util.ArrayList;
import java.util.List;

public class server {

    public void run_server(){
        System.out.println("Server is running");
        Socket socket = null;
        ServerSocket server = null;
        ObjectOutputStream oos = null;
        // starts server and waits for a connection
        try
        {
            server = new ServerSocket(5001);
            System.out.println("Server started");
            System.out.println("Waiting for a client to connect...");
            while(true) {
                socket = server.accept();

                System.out.println("Client __ is connected to server");

                InputStream inputStream = socket.getInputStream();
                DataInputStream dataInputStream = new DataInputStream(inputStream);

                String request = dataInputStream.readUTF();

                OutputStream outputStream = socket.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                dataOutputStream.writeUTF("command received");


                String response = "";
                //Enter the querying code below
                //------------
                List<String> responseList = GrepExecutor.executeGrep(request);
                //TODO for now printing it later send it back to client
                System.out.println(responseList);

                while(!response.equals("Query Completed")){

                    if(true){
                        response = "Query Completed";
                    }
                    dataOutputStream.writeUTF("Query Completed");
                    dataOutputStream.flush();

                    oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(responseList);
                }

                socket.close();
                dataOutputStream.close();
                dataInputStream.close();
                oos.close();
            }
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

}
