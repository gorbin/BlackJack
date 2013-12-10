package com.example.blackjack;

/**
 * Created by Пользователь on 24.11.13.
 */
public class Card {
    public final static int SPADES = 0, HEARTS = 1, DIAMONDS = 2, CLUBS = 3;
    private final int suit;

    private final int value;

    public Card(int value, int suit) {
        this.value = value;
        this.suit = suit;
    }

    public int getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    public String getSuitAsString() {
        switch ( suit ) {
            case SPADES:
                return "Пики";
            case HEARTS:
                return "Червы";
            case DIAMONDS:
                return "Бубны";
            case CLUBS:
                return "Трефы";
            default:
                return "Something went wrong with suit!";
        }
    }

    public String getValueAsString() {
        switch ( value ) {
            case 1:
                return "Туз";
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                return Integer.toString(value);
            case 11:
                return "Валет";
            case 12:
                return "Дама";
            case 13:
                return "Король";
            default:
                return "Something went wrong with value!";
        }
    }

    public String toString() {
        return getValueAsString() + " " + getSuitAsString();
    }
}
