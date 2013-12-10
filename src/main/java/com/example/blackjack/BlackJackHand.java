package com.example.blackjack;

/**
 * Created by Пользователь on 24.11.13.
 */
public class BlackJackHand extends Hand{
    public int getValue() {
        int value, count;
        boolean ace;
        value = 0;
        ace = false;
        count = getCardCount();
        for(int i = 0;i < count;i++) {
            Card card;
            int cardValue;
            card = getCard(i);
            cardValue = card.getValue();
            if (cardValue > 10) {
                cardValue = 10;
            }
            if (cardValue == 1) {
                ace = true;
            }
            value = value + cardValue;
        }
        if (ace == true && value+10 <= 21)
            value = value + 10;
        return value;
    }
}
