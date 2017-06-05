package org.linda.war_cardgame.pojo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckImpl implements Deck {
	private ArrayList<Card> cards = new ArrayList<Card>();

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	public void create(int numberOfSuits, int numberOfRanks) {
		for (int i = 0; i < numberOfSuits; i++) {
			for (int j = 0; j < numberOfRanks; j++) {
				cards.add(new Card(i, new Suit(j)));
			}
		}

	}

	public void shuffle() {
		 if (cards == null) {
		      throw new RuntimeException("Deck has not be created yet.");
		    }
		Collections.shuffle(this.cards);
	}

	public Card deal() {
		if (this.cards == null || cards.size() == 0) {
		      throw new RuntimeException("Deck has not be created yet.");
		    }
		    return cards.remove(0);
	}

	public static void main(String[] args) {
		DeckImpl deck = new DeckImpl();
		List<Card> cards = deck.getCards();
		deck.create(4, 13);
		System.out.println("cards size = " + cards.size());
		for (Card card : cards) {
			System.out.println(card);
		}
		cards = null;
		deck.shuffle();
		// System.out.println("cards size = " + cards.size());
		//
		// for(Card card: cards){
		// System.out.println(card);
		// }

	}
}
