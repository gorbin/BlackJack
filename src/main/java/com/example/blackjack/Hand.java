package com.example.blackjack;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> hand;

    public Hand() {
        hand = new ArrayList<Card>();
    }

    public void clear() {
        hand.clear();
    }

    public void addCard(Card c) {
        hand.add(c);
    }

    public void removeCard(Card c) {
        hand.remove(c);
    }

    public void removeCard(int position) {
        hand.remove(position);
    }

    public int getCardCount() {
        return hand.size();
    }

    public Card getCard(int position) {
        if (position >= 0 && position < hand.size())
            return (Card)hand.get(position);
        else
            return null;
    }
}
