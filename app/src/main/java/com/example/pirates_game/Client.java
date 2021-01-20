package com.example.pirates_game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    Socket socket;
    BufferedReader reader;
    PrintWriter writer;

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
        }catch (IOException ioe){}
    }

    public void sendMessage(String message){
        try{
            writer.println(message);
            writer.flush();
        }catch (Exception ex) {}
    }
}
