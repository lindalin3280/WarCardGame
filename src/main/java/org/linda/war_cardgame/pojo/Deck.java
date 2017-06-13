package org.linda.war_cardgame.pojo;

public interface Deck {
	/* Create the deck of cards */ 
	public void create(String[] suits, String[] ranks, int[] valuesOfRanks); 

	/* Shuffle the deck */ 
	public void shuffle(); 

	/* deal a card from the deck */ 
	public Card deal(); 

}
