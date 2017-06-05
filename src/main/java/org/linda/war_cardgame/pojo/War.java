package org.linda.war_cardgame.pojo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class War {
	public void play(int numberOfSuits, int numberOfRanks, int numberOfPlayers) {
		DeckImpl deck = new DeckImpl();
		deck.create(numberOfSuits, numberOfRanks);
		deck.shuffle();
		Player[] players = distributeCards(deck, numberOfPlayers);
		System.out.println("before one round : " + players[0].getCardsOwned().size());
		System.out.println("before one round : " + players[1].getCardsOwned().size());
		System.out.println("before one round : " + players[2].getCardsOwned().size());
		while (playOneRound(players)) {
			playOneRound(players);
			System.out.println("after one round : " + players[0].getCardsOwned().size());
			System.out.println("after one round : " + players[1].getCardsOwned().size());
			System.out.println("after one round : " + players[2].getCardsOwned().size());
		}

	}

	private Player[] distributeCards(DeckImpl deck, int numberOfPlayers) {
		List<Card> cards = deck.getCards();
		System.out.println("cards.size() = " + cards.size());
		Player[] players = new Player[numberOfPlayers];
		// Arrays.fill(players, new Player());
		for (int i = 0; i < players.length; i++) {
			players[i] = new Player();
		}
		int initCardsEachPlayer = cards.size() / numberOfPlayers;
		System.out.println("initCardsEachPlayer = " + initCardsEachPlayer);
		for (int i = 0; i < initCardsEachPlayer * numberOfPlayers; i++) {
			players[i % numberOfPlayers].addCard(deck.deal());
		}

		System.out.println(players[0].getCardsOwned().size());
		System.out.println(players[1].getCardsOwned().size());
		return players;
	}

	private boolean playOneRound(Player[] players) {
		int losers = 0;
		Set<Card> set = new HashSet<Card>();
		Player winner = new Player();
		Card maxCard = new Card(new Rank(-1), new Suit(-1));
		for (int i = 0; i < players.length; i++) {
			if (players[i].getCardsOwned().size() == 0) {
				losers++;
				continue;
			}
			Card showedCard = players[i].showCard();
			set.add(showedCard);
			winner = showedCard.getRank().getValue() > maxCard.getRank().getValue() ? players[i] : winner;
			if (losers == players.length - 1) {
				System.out.println("winner = " + winner);
				return false;
			}
		}
		winner.addCards(set);
		return true;
	}
}
