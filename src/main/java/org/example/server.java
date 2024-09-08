package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class server {

    public void run_server(){
        System.out.println("Server is running");
        Socket socket = null;
        ServerSocket server = null;
        DataInputStream in =  null;
        // starts server and waits for a connection
        try
        {
            server = new ServerSocket(5000);
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
                while(!response.equals("Query Completed")){

                    if(true){
                        response = "Query Completed";
                    }
                    dataOutputStream.writeUTF("Query Completed");
                }

                socket.close();
            }
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

}
