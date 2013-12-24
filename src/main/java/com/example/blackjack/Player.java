package com.example.blackjack;

import android.app.Activity;
import android.widget.TextView;

public class Player extends Bot {

    public Player(Activity main)
    {
        name = "Игрок";
        scoreTextView = (TextView) main.findViewById(R.id.score);
        handTextView = (TextView) main.findViewById(R.id.player);
        sum=100;
    }

    public void hit(Deck deck, int playerValue){
        hand.addCard(deck.dealCard());
    }
}
