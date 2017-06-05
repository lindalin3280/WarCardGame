package org.linda.war_cardgame.pojo;

import java.util.List;



public class War {
	public void play(int numberOfSuits, int numberOfRanks, int numberOfPlayers) {
		 DeckImpl deck = new DeckImpl();
	        deck.create(numberOfSuits, numberOfRanks);
	        deck.shuffle();
	        divideCards(deck, numberOfPlayers);
	}
	
	private Player[] divideCards(DeckImpl deck, int numberOfPlayers) {
		List<Card> cards = deck.getCards();
		System.out.println("cards.size() = " + cards.size());
		//int total_cards = cards.size();
        Player[] players = new Player[numberOfPlayers];
        for (int i=0; i< numberOfPlayers; i++) {
            players[i] = new Player();
            while(cards.size() > 0) {
            	if(cards.size() % numberOfPlayers == i) {
            		System.out.println("cards.size() = " + cards.size());
            		players[i].addCard(cards.get(0));
            		System.out.println("cards.size() = " + cards.size());
                	cards.remove(cards.get(0));
            	}
            
            }
        } 
        System.out.println(players[0].getCardsOwned().size());
        System.out.println(players[1].getCardsOwned().size());
        System.out.println(players[2].getCardsOwned().size());
        return players;
    }
 public static void main(String[] args) {
	 new War().play(4, 13, 3);
 }
}
