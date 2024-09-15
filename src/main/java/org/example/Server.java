package org.example;

import org.example.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Properties;

/**
 * This class contains Server related logic
 */
public class Server {

    private static final Logger logger = LoggerFactory.getLogger(Server.class);

    /**
     * This functions connects to Server and passes the command and then prints and save the result.
     */
    public void runClient(){
        Socket socket = null;
        ServerSocket server = null;

        AppConfig appConfig = new AppConfig();
        Properties properties = appConfig.readConfig();
        try
        {
            server = new ServerSocket(Integer.parseInt(properties.getProperty("port.number")));
            while(true) {
                ObjectOutputStream oos = null;
                String request = null;
                String response = "";
                logger.info("Server started");
                logger.info("Waiting for a Client to connect...");
                socket = server.accept();
                logger.info("Client __ is connected to Server");

                InputStream inputStream = socket.getInputStream();
                DataInputStream dataInputStream = new DataInputStream(inputStream);

                request = dataInputStream.readUTF();

                OutputStream outputStream = socket.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                dataOutputStream.writeUTF("command received");

                // Code for executing the Grep Command
                GrepExecutor grepExecutor = new GrepExecutor(properties.getProperty("file.path"));
                List<String> responseList = grepExecutor.executeGrep(request);
                //TODO for now printing it later send it back to Client
                logger.info("No of Lines returned : " + responseList.size());
                try {
//                    while (!response.equals("Query Completed")) {
//
//                        if (responseList != null && !responseList.isEmpty()) {
//                            response = "Query Completed";
//                            logger.info(response);
//                            dataOutputStream.writeUTF(response);
//                            dataOutputStream.flush();
//                        }
//                        else {
//                            response = "Query Failed";
//                            dataOutputStream.writeUTF(response);
//                            dataOutputStream.flush();
//                        }
//
//                        oos = new ObjectOutputStream(socket.getOutputStream());
//                        oos.writeObject(responseList);
//                    }
                    response = "Query Completed";
                    logger.info(response);
                    dataOutputStream.writeUTF(response);
                    dataOutputStream.flush();
                    oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(responseList);
                }
                catch (Exception e) {
                    dataOutputStream.writeUTF("Query Failed" + e.getMessage());
                    dataOutputStream.flush();
                    throw new RuntimeException("Query Failed" + e.getMessage());
                }

                dataOutputStream.close();
                dataInputStream.close();

                socket.close();
            }
//            Server.close();
        }
        catch(IOException i)
        {
            System.out.println(i.getMessage());
        }
    }

}
