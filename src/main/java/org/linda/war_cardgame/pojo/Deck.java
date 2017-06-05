package org.linda.war_cardgame.pojo;

public interface Deck {
	/* Create the deck of cards */ 
	public void create( int numberOfSuits, int numberOfRanks ); 

	/* Shuffle the deck */ 
	public void shuffle(); 

	/* deal a card from the deck */ 
	public Card deal(); 

}
