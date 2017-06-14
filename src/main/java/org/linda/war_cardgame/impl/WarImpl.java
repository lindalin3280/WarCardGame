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
    private boolean hasAFinalWinner = false;
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
            System.out.println("At game " + count++);
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
                    || shouldCheckWinner(allCardsHaveTheSameVal, roundNum)) {
                winnerId = sortedCardsDealtThisRound.get(0).playerId;
                break;
            }
        }
        players[winnerId].addCards(cardsOnTable);

    }

    private boolean shouldCheckWinner(boolean allCardsHaveTheSameVal,
            int roundNum) {
        return !allCardsHaveTheSameVal
                && (roundNum - 1) % (numFaceDownCardsIfDraw + 1) == 0;
    }

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
            System.out.println("hasAFinalWinner = true. players = "
                    + Arrays.toString(players));
        }
    }

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

    public void playOneTime2(Player[] players) {
        // cards on table
        List<Card> cardsOnTable = new ArrayList<Card>();
        Player winner = players[0];
        // whether there exists someone wins this round
        boolean someoneWinsThisRound = false;
        int roundNum = 0;
        int numFaceDownCardsOnTable = 0;
        while (!someoneWinsThisRound) {
            ++roundNum;
            // number of players with at least one card in hand
            int numPlayersWithCard = 0;
            // the maximum face value of face-up cards on the table
            int maxFaceVal = Integer.MIN_VALUE;
            int preFaceVal = Integer.MIN_VALUE;
            Player prePlayer = null;
            for (Player player : players) {
                // lose if no cards left
                if (player.getCardsInHand().size() == 0) {
                    continue;
                }
                Card dealtCard = player.dealCard();
                if (player.getCardsInHand().size() > 0) {
                    numPlayersWithCard++;
                }
                cardsOnTable.add(dealtCard);
                if (roundNum > 1
                        && numFaceDownCardsOnTable < numFaceDownCardsIfDraw) {
                    ++numFaceDownCardsOnTable;
                    continue;
                }
                int curFaceVal = dealtCard.getRank().getValue();
                if (preFaceVal != curFaceVal) {
                    if (preFaceVal != Integer.MIN_VALUE) {
                        someoneWinsThisRound = true;
                        if (curFaceVal > maxFaceVal) {
                            winner = player;
                            maxFaceVal = curFaceVal;
                        }
                    }
                    preFaceVal = curFaceVal;
                }
                prePlayer = player;
            }
            if (numPlayersWithCard == 1) {
                hasAFinalWinner = true;
                break;
            }
        }
        Collections.shuffle(cardsOnTable);
        winner.addCards(cardsOnTable);

        for (Player player : players) {
            System.out.println(player);
        }
    }
}
