package com.example.pirates_game;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> deck = new ArrayList<Card>(); //колода карт
    private ArrayList<Card> discard_pile = new ArrayList<Card>(); //отложенные карты с черепом
    private ArrayList<Card> buffer = new ArrayList<Card>(); //промежуточный буфер для отложенных карт с черепом

    enum Cards_types {
        card1("Не хочу к акулам", "*"),
        card2("Поддать жару", ""),
        card3("Подтолкнуть левого", ""),
        card4("Подтолкнуть правого", ""),
        card5("Подтолкнуть любого", "*"),
        card6("Подпилить доску", "*"),
        card7("Приколотить доску", "*"),
        card8("Подтащить к морю", ""),
        card9("Подтащить к кораблю", ""),
        card10("Решить по ходу", "");
        String name;
        String scull;

        Cards_types(String name, String scull) {
            this.name = name;
            this.scull = scull;
        }
    }
    // конструктор класса Deck с картами выбранного цвета
    public Deck(Context context, String card_color){
        int i = 1;
        for(Cards_types card : Cards_types.values()){
            deck.add(new Card(card.name,
                              card.scull,
                              context.getResources().getIdentifier(card_color + "_card" + i++, "drawable", context.getPackageName())));
        }
    }

    public Card getCardFromDeck(int n){
        return deck.get(n);
    }

    public int getDeckSize(){return deck.size();}

    public Card getCardFromPile(int n){
        return discard_pile.get(n);
    }

    public int getPileSize(){
        return discard_pile.size();
    }

    public Card getCardFromBuffer(int n){return buffer.get(n);}

    public ArrayList<Card> getBuffer(){return buffer;}

    public int getBufferSize(){
        return buffer.size();
    }

    public void shuffle(){
        Collections.shuffle(deck);
    }

    public void deckToPile(){
        for(int i = 2; i >= 0; --i)
            if (deck.get(i).getScull().equals("*")) {
                buffer.add(deck.get(i)); // кладем в буфер
                deck.remove(i);          //убираем карты из колоды
            }
    }

    public void pileToDeck(){
        if (!discard_pile.isEmpty()) { //из pile кладем в колоду
            for (Card card : discard_pile)
                deck.add(card);

            discard_pile.clear();
        }

        if (!buffer.isEmpty()) { // из буфера двигаем карты в pile
            for (Card card : buffer)
                discard_pile.add(card);

            buffer.clear();
        }
    }
}
