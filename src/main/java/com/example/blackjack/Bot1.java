package com.example.blackjack;

import android.app.Activity;
import android.widget.TextView;

import java.util.Random;


public class Bot1 extends Bot{
    private final static int MAX_VALUE = 16;
    private final static int MIN_VALUE = 10;
    public Bot1(Activity main)
    {
        name = "Bot1";
        scoreTextView = (TextView) main.findViewById(R.id.bot1Score);
        handTextView = (TextView) main.findViewById(R.id.bot1);
        sum=100;
    }
    public void hit(Deck deck, int playerValue)
    {
        if(hand.getValue()<MIN_VALUE){
            hand.addCard(deck.dealCard());
        }
        if (hand.getValue()<=MAX_VALUE && hand.getValue()>=MIN_VALUE){
            Random rnd = new Random();
            if (rnd.nextBoolean()) {
                hand.addCard(deck.dealCard());
            } else {
                finish=true;
                return;
            }
        }
        if (hand.getValue()>MAX_VALUE){
            finish=true;
        }
    }
}
