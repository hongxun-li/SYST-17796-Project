/**
 * SYST 17796 Project Winter 2019 Base code.
 * Students can modify and extend to implement their game.
 * Add your name as a modifier and the date!
 */
package ca.sheridancollege.project;

/**
 * A class to be used as the base Card class for the project. Must be general
 * enough to be instantiated for any Card game. Students wishing to add to the code 
 * should remember to add themselves as a modifier.
 * @author dancye, 2018
 */
public class Card {
    public enum Suit {HEARTS, CLUBS, SPADES, DIAMONDS}

    ;

    public enum Value {ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING}

    ;
    private Suit suit; //object of suit
    private Value value; //object of values

    public Card(Suit s, Value gVal) {
        suit = s;
        value = gVal;
    }

    public Value getValue() {
        return this.value;
    }


    public Suit getSuit() {
        return this.suit;
    }

    public void settValue(Value val) {
        value = val;
    }


    public void settSuit(Suit s) {
        suit = s;
    }


}
