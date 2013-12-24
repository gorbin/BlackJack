package com.example.blackjack;

import android.app.Activity;
import android.widget.TextView;

public class Bot {
    int sum;
    boolean play = true;
    BlackJackHand hand;
    TextView scoreTextView, handTextView;
    String name;
    boolean win, finish;


    public Bot(){
        hand = new BlackJackHand();
        finish = false;
        win = false;
    }

    public boolean canPlay(){
        if(sum>=MainActivity.BET)
        {
            sum -= MainActivity.BET;
        }
        else{
            play=false;
            hand.clear();
        }
        return play;
    }
    public void start(BlackJackHand hand, Deck deck){
        if(play){
            hand.addCard(deck.dealCard());
            hand.addCard(deck.dealCard());
        }
    }
    public void hit(Deck deck, int playerValue){

    }



}
