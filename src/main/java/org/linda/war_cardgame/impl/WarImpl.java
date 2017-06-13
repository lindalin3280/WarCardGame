package org.linda.war_cardgame.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.linda.war_cardgame.interfaces.War;
import org.linda.war_cardgame.pojo.Card;
import org.linda.war_cardgame.pojo.Player;

public class WarImpl implements War {
    static Logger logger = Logger.getLogger(War.class);
    private boolean hasAFinalWinner = false;

    @Override
    public void startGame(String[] suits, String[] ranks, int[] valuesOfRanks,
            String[] players) {
        DeckImpl deck = new DeckImpl();
        deck.create(suits, ranks, valuesOfRanks);
        deck.shuffle();
        Player[] playersOfWar = distributeCards(deck, players);
        int count = 1;
        while (!hasAFinalWinner) {
            System.out
                    .println("-------------------------------------------------------------------------");
            System.out.println("After Round " + count++);
            playOneRound(playersOfWar);
        }

    }

    @Override
    public Player[] distributeCards(DeckImpl deck, String[] players) {
        List<Card> cards = deck.getCards();
        logger.info("cards.size() = " + cards.size());
        int numberOfPlayers = players.length;
        Player[] playersOfWar = new Player[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            playersOfWar[i] = new Player(players[i]);
        }
        int initCardsEachPlayer = cards.size() / numberOfPlayers;
        logger.info("initCardsEachPlayer = " + initCardsEachPlayer);
        for (int i = 0; i < initCardsEachPlayer * numberOfPlayers; i++) {
            playersOfWar[i % numberOfPlayers].addCard(deck.deal());
        }
        for (Player p : playersOfWar) {
            System.out.println(p);
        }
        return playersOfWar;
    }

    @Override
    public void playOneRound(Player[] playersOfWar) {
        // cards on table
        List<Card> cardsOnTable = new ArrayList<Card>();
        Player winner = playersOfWar[0];
        int maxFaceValue = Integer.MIN_VALUE;
        boolean someoneWinsThisRound = false;
        int numPlayersWithCard = 0;
        while (!someoneWinsThisRound) {
            for (int i = 0; i < playersOfWar.length; i++) {
                // lose if no cards left
                if (playersOfWar[i].getCardsInHand().size() == 0) {
                    continue;
                }
                numPlayersWithCard++;
                Card dealtCard = playersOfWar[i].dealCard();
                cardsOnTable.add(dealtCard);
                if (dealtCard.getRank().getValue() > maxFaceValue) {
                    maxFaceValue = dealtCard.getRank().getValue();
                    winner = playersOfWar[i];
                    someoneWinsThisRound = true;
                }
            }
        }
        Collections.shuffle(cardsOnTable);
        winner.addCards(cardsOnTable);
        if (numPlayersWithCard == 1) {
            hasAFinalWinner = true;
        }
        for (Player player : playersOfWar) {
            System.out.println(player);
        }
    }
}
