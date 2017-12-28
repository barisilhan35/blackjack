package com.baris;

/*
    An object of type Deck represents an ordinary deck of 52 playing cards.
    The deck can be shuffled, and cards can be dealt from the deck.
*/

public class Deck {
    //An array of 52 cards
    private Card[] deck;
    //how many cards is dealt from deck
    private int cardsUsed;

    public Deck() {
        // create a deck
        deck = new Card[52];
        int cardCt = 0; // how many cards created so far

        for (int suit = 0;suit <= 3.; suit++) {
            for (int value=1; value<=13; value++) {
                deck[cardCt] = new Card(value,suit);
                cardCt++;
            }
        }
        cardsUsed=0;
    }

    public void shuffle() {
        // Put all the used cards back into the deck, and shuffle
        for (int i =51;i>0;i--) {
            int rand =(int)(Math.random()*(i+1));
            Card temp =deck[i];
            deck[i]=deck[rand];
            deck[rand]=temp;
        }
        cardsUsed=0;
    }
    public int cardsLeft() {
        // As cards are dealt from the deck, the number of cards left
        // decreases.  This function returns the number of cards that
        // are still left in the deck.
        return 52 - cardsUsed;
    }
    public Card dealCard() {
        // Deals one card from the deck and returns it.
        if (cardsUsed == 52)
            shuffle();
        cardsUsed++;
        return deck[cardsUsed - 1];
    }
}
