package org.linda.war_cardgame.interfaces;

import org.linda.war_cardgame.pojo.Card;

/**
 * 
 * @author Linda
 *
 */
public interface Deck {

    /**
     * This method is used to create the deck of cards.
     * 
     * @param suits
     * @param ranks
     * @param valuesOfRanks
     */
    public void create(String[] suits, String[] ranks, int[] valuesOfRanks);

    /**
     * This method is used to shuffle the deck.
     */
    public void shuffle();

    /**
     * This method is used to deal a card from the deck.
     * 
     * @return the dealt Card
     */
    public Card deal();
}
