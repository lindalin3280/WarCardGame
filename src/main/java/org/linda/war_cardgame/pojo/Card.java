package org.linda.war_cardgame.pojo;

public class Card {

	private Rank rank;
	private Suit suit;

	public Card() {

	}

	public Card(Rank rank, Suit suit) {
		super();
		this.rank = rank;
		this.suit = suit;
	}

	public Rank getRank() {
		return rank;
	}

	public Suit getSuit() {
		return suit;
	}


	@Override
	public String toString() {
		return rank + "-" + suit;
	}

}
