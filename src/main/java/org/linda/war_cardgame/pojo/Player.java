package org.linda.war_cardgame.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Player {
	private String name;
	private List<Card> cardsOwned = new ArrayList<Card>();

	public void addCard(Card card) {
		cardsOwned.add(card);
	}

	public void addCards(Set<Card> cards) {
		cardsOwned.addAll(cards);
	}

	public Card showCard() {
		return cardsOwned.remove(0);
	}

	public List<Card> getCardsOwned() {
		return cardsOwned;
	}

	public void setCardsOwned(List<Card> cardsOwned) {
		this.cardsOwned = cardsOwned;
	}

}
