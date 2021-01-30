package com.example.pirates_game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    Socket socket;
    BufferedReader reader;
    PrintWriter writer;
    Logger logger;

    public Client() {
        logger = Logger.getLogger(MainActivity.class.getName()); // подключаем LOG
        logger.setLevel(Level.ALL);
    }

    public void go(){
        setUpNetworking();
    }

    private void setUpNetworking(){
        try {
            socket = new Socket("127.0.0.1", 5000);
            InputStreamReader streamReader = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader(streamReader);
            writer = new PrintWriter(socket.getOutputStream());
            // network established
        }catch (IOException ioe){
            logger.severe("method client.setUpNetworking: " + ioe.getMessage());
        }
    }

    public void sendMessage(String message){
        try{
            writer.println(message);
            writer.flush();
        }catch (Exception ex) {
            logger.severe("method client.sendMessage: " + ex.getMessage());
        }
    }
}
