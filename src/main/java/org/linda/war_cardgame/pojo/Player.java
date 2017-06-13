package org.linda.war_cardgame.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
/**
 * A player with cards in hand.
 * @author Lindy
 *
 */
public class Player {
	private String name;
	private List<Card> cardsInHand = new ArrayList<Card>();
	
	public Player() {
		
	}
	public Player(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCardsInHand(List<Card> cardsInHand) {
		this.cardsInHand = cardsInHand;
	}
	public void addCard(Card card) {
		cardsInHand.add(card);
	}

	public void addCards(List<Card> cards) {
		cardsInHand.addAll(cards);
	}

	public Card dealCard() {
		return cardsInHand.remove(0);
	}

	/**
	 * 
	 * @return
	 */
	public List<Card> getCardsInHand() {
		return cardsInHand;
	}

	public void setCardsOwned(List<Card> cardsInHand) {
		this.cardsInHand = cardsInHand;
	}

	@Override
	public String toString() {
		return "Player " + name + " has cards: " + cardsInHand;
	}
	

}
