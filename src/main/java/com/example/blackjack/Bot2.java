package com.example.blackjack;

import android.app.Activity;
import android.widget.TextView;

public class Bot2 extends Bot {
    private final static int MAX_VALUE = 18;
    public Bot2(Activity main)
    {
        name = "Bot2";
        scoreTextView = (TextView) main.findViewById(R.id.bot2Score);
        handTextView = (TextView) main.findViewById(R.id.bot2);
        sum=100;
    }
    public void hit(Deck deck, int playerValue)
    {
        if(hand.getValue()< MAX_VALUE){
            hand.addCard(deck.dealCard());
        }
        if (hand.getValue()>= MAX_VALUE){
            finish=true;
        }
    }
}
