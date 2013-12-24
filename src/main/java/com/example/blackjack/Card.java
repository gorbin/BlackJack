package com.example.blackjack;


public class Card {
    public final static int SPADES = 0, HEARTS = 1, DIAMONDS = 2, CLUBS = 3;
	public final static String SPADES_STRING = "Пики", HEARTS_STRING = "Червы", DIAMONDS_STRING = "Бубны", CLUBS_STRING = "Трефы", ACE = "Туз", JACK = "Валет", QUEEN = "Королева", KING = "Король";
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
                return SPADES_STRING;
            case HEARTS:
                return HEARTS_STRING;
            case DIAMONDS:
                return DIAMONDS_STRING;
            case CLUBS:
                return CLUBS_STRING;
            default:
                return null;
        }
    }

    public String getValueAsString() {
        switch ( value ) {
            case 1:
                return ACE;
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
                return JACK;
            case 12:
                return QUEEN;
            case 13:
                return KING;
            default:
                return null;
        }
    }

    public String toString() {
        return getValueAsString() + " " + getSuitAsString();
    }
}
