package com.baris;

public class Card {
    // Codes for the 4 suits.
    public final static int SPADES =0;
    public final static int HEARTS =1;
    public final static int DIAMONDS =2;
    public final static int CLUBS =3;

    /* Codes for the non-numeric cards.
    *   Cards 2 through 10 have their
    *numerical values for their codes.
    * */
    public final static int ACE =1;
    public final static int JACK =11;
    public final static int QUEEN =12;
    public final static int KING =13;

    /* The suit of this card, one of the constants
        SPADES, HEARTS, DIAMONDS, CLUBS.*/
    private final int suit;
    // The value of this card, from 1 to 11.
    private final int value;

    // Construct a card with the specified value and suit.
    // Value must be between 1 and 13.  Suit must be between
    // 0 and 3.  If the parameters are outside these ranges,
    // the constructed card object will be invalid.
    public Card(int theValue, int theSuit) {
        //this.suit = suit;
        //this.value = value;
        value = theValue;
        suit = theSuit;
    }

    public int getSuit() {
        // Return the int that codes for this card's suit.
        return suit;
    }

    public int getValue() {
        // Return the int that codes for this card's value.
        return value;
    }

    public String getSuitAsString() {
        // Method Return a String representing the card's suit.
        switch (suit) {
            case SPADES:    return "Spades";
            case HEARTS:    return "Hearts";
            case DIAMONDS:  return "Diamonds";
            case CLUBS:     return "Clubs";
            default:        return "??";
        }

    }

    public String getValueAsString() {
        // Method Return a String representing the card's value.
        switch (value) {
            case 1:   return "Ace";
            case 2:   return "2";
            case 3:   return "3";
            case 4:   return "4";
            case 5:   return "5";
            case 6:   return "6";
            case 7:   return "7";
            case 8:   return "8";
            case 9:   return "9";
            case 10:  return "10";
            case 11:  return "Jack";
            case 12:  return "Queen";
            case 13:  return "King";
            default:  return "??";
        }
    }

    public String toString() {
         if (getSuitAsString().equals(DIAMONDS)){
            System.out.println("shine bright like a diamond");
        }
        return getValueAsString() + " of " + getSuitAsString();
    }

}
