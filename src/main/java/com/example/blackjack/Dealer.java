package com.example.blackjack;

import android.app.Activity;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by Пользователь on 25.11.13.
 */
public class Dealer extends Bot {
    private final static int MAX_VALUE = 16;
    private final static int MIN_VALUE = 10;
    public Dealer(Activity main)
    {
        name = "Дилер";
        handTextView = (TextView) main.findViewById(R.id.dealer);
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

        if (hand.getValue()>MAX_VALUE && hand.getValue()<=playerValue){
            hand.addCard(deck.dealCard());
        }
        if(hand.getValue()>21 || hand.getValue()>playerValue)
            finish=true;
    }
}
