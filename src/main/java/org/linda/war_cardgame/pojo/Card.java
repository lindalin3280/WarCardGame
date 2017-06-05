package org.linda.war_cardgame.pojo;

public class Card {
	
	private int value;
	private Suit suit;

	public Card() {
		
	}

	public Card(int value, Suit suit) {
		super();
		this.value = value;
		this.suit = suit;
	}

	@Override
	public String toString() {
		return "Card [value=" + value + ", suit=" + suit + "]";
	}

	
}
