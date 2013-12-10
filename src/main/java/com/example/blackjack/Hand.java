package com.example.blackjack;

import java.util.Vector;

public class Hand {
    private Vector hand;

    public Hand() {
        hand = new Vector();
    }

    public void clear() {
        hand.removeAllElements();
    }

    public void addCard(Card c) {
        hand.addElement(c);
    }

    public void removeCard(Card c) {
        hand.removeElement(c);
    }

    public void removeCard(int position) {
        hand.removeElementAt(position);
    }

    public int getCardCount() {
        return hand.size();
    }

    public Card getCard(int position) {
        if (position >= 0 && position < hand.size())
            return (Card)hand.elementAt(position);
        else
            return null;
    }
}
