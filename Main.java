package com.baris;
/*
       This program lets the user play Blackjack.  The computer
       acts as the dealer.  The user has a stake of $100, and
       makes a bet on each game.  The user can leave at any time,
       or will be kicked out when he loses all the money.
       House rules:  The dealer hits on a total of 16 or less
       and stands on a total of 17 or more.  Dealer wins ties.
       A new deck of cards is used for each game.
    */
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        int money;          // Amount of money the user has.
        int bet;            // Amount user bets on a game.
        boolean userWins;   // Did the user win the game?

        TextIO.putln("Welcome to the game of blackjack.");
        TextIO.putln();

        money = 100;  // User starts with $100.

        while (true) {
            TextIO.putln("You have " + money + " dollars.");
            do {
                TextIO.putln("How many dollars do you want to bet?  (Enter 0 to end.)");
                TextIO.put("? ");
                bet = TextIO.getlnInt();
                if (bet < 0 || bet > money)
                    TextIO.putln("Your answer must be between 0 and " + money + '.');
            } while (bet < 0 || bet > money);
            if (bet == 0)
                break;
            userWins = playBlackjack();
            if (userWins)
                money = money + bet;
            else
                money = money - bet;
            TextIO.putln();
            if (money == 0) {
                TextIO.putln("Looks like you are out of money!");
                break;
            }
        }

        TextIO.putln();
        TextIO.putln("You leave with $" + money + '.');

    }


    static boolean playBlackjack() {
        // Let the user play one game of Blackjack.
        // Return true if the user wins, false if the user loses.

        Deck deck;                  // A deck of cards.  A new deck for each game.
        BlackJackHand dealerHand;   // The dealer's hand.
        BlackJackHand userHand;     // The user's hand.

        deck = new Deck();
        dealerHand = new BlackJackHand();
        userHand = new BlackJackHand();

          /*  Shuffle the deck, then deal two cards to each player. */

        deck.shuffle();
        dealerHand.addCard( deck.dealCard() );
        dealerHand.addCard( deck.dealCard() );
        userHand.addCard( deck.dealCard() );
        userHand.addCard( deck.dealCard() );

        TextIO.putln();
        TextIO.putln();

          /* Check if one of the players has Blackjack (two cards totaling to 21).
             The player with Blackjack wins the game.
          */

        if (dealerHand.getBlackJackValue() == 21) {
            TextIO.putln("Dealer has the " + dealerHand.getCard(0)
                    + " and the " + dealerHand.getCard(1) + ".");
            TextIO.putln("User has the " + userHand.getCard(0)
                    + " and the " + userHand.getCard(1) + ".");
            TextIO.putln();
            TextIO.putln("Dealer has Blackjack.  Dealer wins.");
            return false;
        }

        if (userHand.getBlackJackValue() == 21) {
            TextIO.putln("Dealer has the " + dealerHand.getCard(0)
                    + " and the " + dealerHand.getCard(1) + ".");
            TextIO.putln("User has the " + userHand.getCard(0)
                    + " and the " + userHand.getCard(1) + ".");
            TextIO.putln();
            TextIO.putln("You have Blackjack.  You win.");
            return true;
        }

          /*  If neither player has Blackjack, play the game.  First the user
              gets a chance to draw cards (i.e., to "Hit").  The while loop ends
              when the user chooses to "Stand".  If the user goes over 21,
              the user loses immediately.
          */

        while (true) {

               /* Display user's cards, and let user decide to Hit or Stand. */

            TextIO.putln();
            TextIO.putln();
            TextIO.putln("Your cards are:");
            for ( int i = 0; i < userHand.getCardCount(); i++ )
                TextIO.putln("    " + userHand.getCard(i));
            TextIO.putln("Your total is " + userHand.getBlackJackValue());
            TextIO.putln();
            TextIO.putln("Dealer is showing the " + dealerHand.getCard(0));
            TextIO.putln();
            TextIO.put("Hit (H) or Stand (S)? ");
            char userAction;  // User's response, 'H' or 'S'.
            do {
                userAction = Character.toUpperCase( TextIO.getlnChar() );
                if (userAction != 'H' && userAction != 'S')
                    TextIO.put("Please respond H or S:  ");
            } while (userAction != 'H' && userAction != 'S');

               /* If the user Hits, the user gets a card.  If the user Stands,
                  the loop ends (and it's the dealer's turn to draw cards).
               */

            if ( userAction == 'S' ) {
                // Loop ends; user is done taking cards.
                break;
            }
            else {  // userAction is 'H'.  Give the user a card.
                // If the user goes over 21, the user loses.
                Card newCard = deck.dealCard();
                userHand.addCard(newCard);
                TextIO.putln();
                TextIO.putln("User hits.");
                TextIO.putln("Your card is the " + newCard);
                TextIO.putln("Your total is now " + userHand.getBlackJackValue());
                if (userHand.getBlackJackValue() > 21) {
                    TextIO.putln();
                    TextIO.putln("You busted by going over 21.  You lose.");
                    TextIO.putln("Dealer's other card was the "
                            + dealerHand.getCard(1));
                    return false;
                }
            }

        } // end while loop

          /* If we get to this point, the user has Stood with 21 or less.  Now, it's
             the dealer's chance to draw.  Dealer draws cards until the dealer's
             total is > 16.  If dealer goes over 21, the dealer loses.
          */

        TextIO.putln();
        TextIO.putln("User stands.");
        TextIO.putln("Dealer's cards are");
        TextIO.putln("    " + dealerHand.getCard(0));
        TextIO.putln("    " + dealerHand.getCard(1));
        while (dealerHand.getBlackJackValue() <= 16) {
            Card newCard = deck.dealCard();
            TextIO.putln("Dealer hits and gets the " + newCard);
            dealerHand.addCard(newCard);
            if (dealerHand.getBlackJackValue() > 21) {
                TextIO.putln();
                TextIO.putln("Dealer busted by going over 21.  You win.");
                return true;
            }
        }
        TextIO.putln("Dealer's total is " + dealerHand.getBlackJackValue());

          /* If we get to this point, both players have 21 or less.  We
             can determine the winner by comparing the values of their hands. */

        TextIO.putln();
        if (dealerHand.getBlackJackValue() == userHand.getBlackJackValue()) {
            TextIO.putln("Dealer wins on a tie.  You lose.");
            return false;
        }
        else if (dealerHand.getBlackJackValue() > userHand.getBlackJackValue()) {
            TextIO.putln("Dealer wins, " + dealerHand.getBlackJackValue()
                    + " points to " + userHand.getBlackJackValue() + ".");
            return false;
        }
        else {
            TextIO.putln("You win, " + userHand.getBlackJackValue()
                    + " points to " + dealerHand.getBlackJackValue() + ".");
            return true;
        }

    }  // end playBlackjack()
}
