package org.linda.war_cardgame.pojo;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * A player with cards in hand.
 * 
 * @author Linda
 *
 */
public class Player {
    private String name;
    private Queue<Card> cardsInHand = new LinkedList<Card>();

    /**
     * default constructor.
     */
    public Player() {

    }

    /**
     * Class constructor specifying name of the player.
     * 
     * @param name
     */
    public Player(String name) {
        super();
        this.name = name;
    }

    public Player(Queue<Card> cardsInHand) {
        super();
        this.cardsInHand = cardsInHand;
    }

    public String getName() {
        return name;
    }

    public Queue<Card> getCardsInHand() {
        return cardsInHand;
    }

    /**
     * Add one Card to the player's cards in hand.
     * 
     * @param card
     */
    public void addCard(Card card) {
        cardsInHand.add(card);
    }

    /**
     * Add a number of Cards to the player's cards in hand.
     * 
     * @param cards
     */
    public void addCards(List<Card> cards) {
        cardsInHand.addAll(cards);
    }

    /**
     * player deal one Card, at the same time, player's cards in hand -1
     * 
     * @return
     */
    public Card dealCard() {
        return cardsInHand.poll();
    }

    @Override
    public String toString() {
        return name + " has " + cardsInHand.size() + " cards: " + cardsInHand;
    }

}
