package org.linda.war_cardgame.pojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

public class War {
	static Logger logger = Logger.getLogger(War.class);
	private boolean hasAFinalWinner = false;

	public void play(int numberOfSuits, int numberOfRanks, int numberOfPlayers, String[] namesOfPlayers) {
		DeckImpl deck = new DeckImpl();
		deck.create(numberOfSuits, numberOfRanks);
		deck.shuffle();
		Player[] players = distributeCards(deck, numberOfPlayers, namesOfPlayers);
		while (!hasAFinalWinner) {
			playOneRound(players);
		}

	}

	private Player[] distributeCards(DeckImpl deck, int numberOfPlayers, String[] namesOfPlayers) {
		List<Card> cards = deck.getCards();
		System.out.println("cards.size() = " + cards.size());
		Player[] players = new Player[numberOfPlayers];
		for (int i = 0; i < players.length; i++) {
			players[i] = new Player(namesOfPlayers[i]);
		}
		int initCardsEachPlayer = cards.size() / numberOfPlayers;
		System.out.println("initCardsEachPlayer = " + initCardsEachPlayer);
		for (int i = 0; i < initCardsEachPlayer * numberOfPlayers; i++) {
			players[i % numberOfPlayers].addCard(deck.deal());
		}

		logger.info(players[0].getName() + " has " + players[0].getCardsInHand().size() + " cards");
		logger.info("They are: " + players[0].getCardsInHand());
		logger.info(players[1].getName() + " has " + players[1].getCardsInHand().size() + " cards");
		logger.info("They are: " + players[1].getCardsInHand());
		return players;
	}

	private void playOneRound(Player[] players) {
		// cards on table
		List<Card> cardsOnTable = new ArrayList<Card>();
		Player winner = players[0];
		int maxFaceValue = Integer.MIN_VALUE;
		boolean someoneWinsThisRound = false;
		int numPlayersWithCard = 0;
		while (!someoneWinsThisRound) {
			for (int i = 0; i < players.length; i++) {
				// lose if no cards left
				if (players[i].getCardsInHand().size() == 0) {
					continue;
				}
				++numPlayersWithCard;
				Card dealtCard = players[i].dealCard();
				cardsOnTable.add(dealtCard);
				if (dealtCard.getRank().getValue() > maxFaceValue) {
					maxFaceValue = dealtCard.getRank().getValue();
					winner = players[i];
					someoneWinsThisRound = true;
				}
			}
		}
		Collections.shuffle(cardsOnTable);
		winner.addCards(cardsOnTable);
		if(numPlayersWithCard == 1) {
			hasAFinalWinner = true;
		}
		for(Player player : players) {
			System.out.println(player);
		}
		System.out.println("-------------------------------------------------------------------------");
	}
}
