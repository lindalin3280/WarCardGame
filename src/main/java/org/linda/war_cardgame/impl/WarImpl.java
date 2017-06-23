package org.linda.war_cardgame.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.linda.war_cardgame.interfaces.War;
import org.linda.war_cardgame.pojo.Card;
import org.linda.war_cardgame.pojo.Player;

public class WarImpl implements War {
    static Logger logger = Logger.getLogger(WarImpl.class);
    /**
     * whether the final winner of the game has been determined
     */
    private boolean hasAFinalWinner = false;
    /**
     * the number of face-down cards that every player should deal in case of draw/war
     */
    public static int numFaceDownCardsIfDraw;

    @Override
    public void startGame(String[] suits, String[] ranks, int[] valuesOfRanks,
            String[] playerNames) {
        DeckImpl deck = new DeckImpl();
        deck.create(suits, ranks, valuesOfRanks);
        deck.shuffle();
        Player[] players = distributeCards(deck, playerNames);
        int count = 1;
        do {
            System.out
                    .println("-------------------------------------------------------------------------");
            System.out.println("Time " + count++);
            playOneTime(players);
            stopIfDone(players);
            for (Player player : players) {
                System.out.println(player);
            }
        } while (!hasAFinalWinner);
    }

    @Override
    public Player[] distributeCards(DeckImpl deck, String[] playerNames) {
        List<Card> cards = deck.getCards();
        logger.info("cards.size() = " + cards.size());
        int numberOfPlayers = playerNames.length;
        Player[] playersOfWar = new Player[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            playersOfWar[i] = new Player(playerNames[i]);
        }
        int numCardsInEachPlayer = cards.size() / numberOfPlayers;
        logger.info("numCardsInEachPlayer = " + numCardsInEachPlayer);
        for (int i = 0; i < numCardsInEachPlayer * numberOfPlayers; i++) {
            playersOfWar[i % numberOfPlayers].addCard(deck.deal());
        }
        for (Player p : playersOfWar) {
            System.out.println(p);
        }
        return playersOfWar;
    }

    /**
     * play one or more rounds until someone gets all the cards on the table
     */
    @Override
    public void playOneTime(Player[] players) {
        List<Card> cardsOnTable = new ArrayList<Card>();
        boolean allCardsHaveTheSameVal = false;
        int roundNum = 0;
        int winnerId = 0;
        while (true) {
            ++roundNum;
            // key : card dealt in this round, 
            // value: index of player who deal this card
            List<CardByPlayerId> sortedCardsDealtThisRound = getSortedCardsDealtThisRound(players);
            if (sortedCardsDealtThisRound.size() == 0) {
                hasAFinalWinner = true;
                players[0].addCards(cardsOnTable);
                return;
            }
            for (CardByPlayerId p : sortedCardsDealtThisRound) {
                cardsOnTable.add(p.card);
            }
            allCardsHaveTheSameVal = allCardsHaveTheSameVal(sortedCardsDealtThisRound);
            logger.debug("sortedCardsDealtThisRound = "
                    + sortedCardsDealtThisRound + ", cardsOnTable = "
                    + cardsOnTable + ", allCardsHaveTheSameVal = "
                    + allCardsHaveTheSameVal);
            if (sortedCardsDealtThisRound.size() < 2
                    || shouldCheckWinnerInThisTime(allCardsHaveTheSameVal, roundNum)) {
                winnerId = sortedCardsDealtThisRound.get(0).playerId;
                break;
            }
        }
        players[winnerId].addCards(cardsOnTable);

    }

    /**
     * Find whether we should check if someone can win all the cards on the table
     * 
     * For example, if numFaceDownCardsIfDraw = 2,
     * start from round 2, we check whether someone who can win all the cards on the table
     * exists every third round until we find such a player
     * 
     * round  1, check if winner in this round exists. If not, continue to next round
     * round  2, skip checking
     * round  3, skip checking
     * round  4, check if winner in this round exists. If not, continue to next round
     * round  5, skip checking
     * round  6, skip checking
     * round  7, check if winner in this round exists. If not, continue to next round
     * ...
     * @param allCardsHaveTheSameVal
     * @param roundNum
     * @return
     */
    private boolean shouldCheckWinnerInThisTime(boolean allCardsHaveTheSameVal,
            int roundNum) {
        return !allCardsHaveTheSameVal
                && (roundNum - 1) % (numFaceDownCardsIfDraw + 1) == 0;
    }

    /**
     * If only 0 or 1 player has cards in hand, the game is over.
     * @param players
     */
    private void stopIfDone(Player[] players) {
        // number of players with at least one card in hand
        int numPlayesWithCard = 0;
        for (Player player : players) {
            if (player.getCardsInHand().size() > 0) {
                ++numPlayesWithCard;
            }
        }
        if (numPlayesWithCard < 2) {
            hasAFinalWinner = true;
            System.out.println("hasAFinalWinner = true");
        }
    }

    /**
     * Let each player deal one cards,
     * get the sorted cards dealt in this round.
     * @param players
     * @return
     */
    private List<CardByPlayerId> getSortedCardsDealtThisRound(Player[] players) {
        List<CardByPlayerId> cardsDealtThisRound = new ArrayList<CardByPlayerId>();
        for (int i = 0; i < players.length; ++i) {
            Card dealtCard = players[i].dealCard();
            if (dealtCard != null) {
                cardsDealtThisRound.add(new CardByPlayerId(i, dealtCard));
            }
        }
        Collections.sort(cardsDealtThisRound, Collections.reverseOrder());
        return cardsDealtThisRound;
    }

    /**
     * A wrapper class that maps playerId to a Card.
     * This wrapper class can be sorted based on the value on this card.
     * @author Linda
     *
     */
    class CardByPlayerId implements Comparable<CardByPlayerId> {
        int playerId;
        Card card;

        public CardByPlayerId(int playerId, Card card) {
            super();
            this.playerId = playerId;
            this.card = card;
        }

        @Override
        public int compareTo(CardByPlayerId o) {
            return card.compareTo(o.card);
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(playerId + " ");
            builder.append(card);
            return builder.toString();
        }
    }

    /**
     * check if all the cards have the same face value
     * 
     * @param cardsDealtThisRound
     * @return
     */
    private boolean allCardsHaveTheSameVal(
            List<CardByPlayerId> sortedCardsDealtThisRound) {
        return sortedCardsDealtThisRound.size() > 0
                && sortedCardsDealtThisRound.get(0).card.getRank().compareTo(
                        sortedCardsDealtThisRound.get(sortedCardsDealtThisRound
                                .size() - 1).card.getRank()) == 0;
    }
}
