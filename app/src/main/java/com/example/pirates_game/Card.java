package com.example.pirates_game;


public class Card {
    private String name;
    private String scull;
    private int image;
    private int sound;

    public Card(String name, String scull, int image){
        this.name = name;
        this.scull = scull;
        this.image = image;
    }
    public String getName(){return this.name;}
    public String getScull(){return this.scull;}
    public int getImage(){return this.image;}
}





