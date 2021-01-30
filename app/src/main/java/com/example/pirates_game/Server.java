package com.example.pirates_game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server{

    ArrayList clientOutputStreams;
    Logger logger;

    public Server() {
        logger = Logger.getLogger(MainActivity.class.getName()); // подключаем LOG
        logger.setLevel(Level.ALL);
    }

    public class ClientHandler implements Runnable {
        Socket socket;
        BufferedReader reader;

        public ClientHandler( Socket clientSocket){
            try{
                socket = clientSocket;
                InputStreamReader isReader = new InputStreamReader(socket.getInputStream());
                reader = new BufferedReader(isReader);
            }
            catch (IOException ioe) {
                logger.severe("method server.ClientHandler: " + ioe.getMessage());
            }
        }

        public void run(){
            String message;
            try{
                while ((message = reader.readLine()) != null) {
                    saveMessage(message);
                }
            }
            catch (IOException ioe){
                logger.severe("method server.run: " + ioe.getMessage());
            }
        }
    }

    public void go(){
        clientOutputStreams = new ArrayList();
        try (ServerSocket serverSocket = new ServerSocket(5000);) {
            while (true){
                Socket clientSocket = serverSocket.accept();
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                clientOutputStreams.add(writer);
                Thread t = new Thread(new ClientHandler(clientSocket));
                t.start();
            }
        }
        catch (IOException ioe) {
            logger.severe("method server.go: " + ioe.getMessage());
        }
    }

    public void saveMessage(String message){
        // написать обработчик сообщений клиента
        MainActivity.test_view.setText(message);
    }


}
