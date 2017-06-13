package org.linda.war_cardgame.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.linda.war_cardgame.interfaces.Deck;
import org.linda.war_cardgame.pojo.Card;
import org.linda.war_cardgame.pojo.Rank;
import org.linda.war_cardgame.pojo.Suit;

public class DeckImpl implements Deck {
	
	private List<Card> cards = new ArrayList<Card>();

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	
	@Override
	public void create(String[] suits, String[] ranks, int[] valuesOfRanks) {
		for (int i = 0; i < suits.length; i++) {
			for (int j = 0; j < ranks.length; j++) {
				cards.add(new Card(new Rank(valuesOfRanks[j], ranks[j]), new Suit(suits[i])));
			}
		}

	}
	
	@Override
	public void shuffle() {
		if (cards == null) {
			throw new RuntimeException("Deck has not be created yet.");
		}
		Collections.shuffle(this.cards);
	}

	@Override
	public Card deal() {
		if (this.cards == null || cards.size() == 0) {
			throw new RuntimeException("Deck has not be created yet.");
		}
		return cards.remove(0);
	}
}
