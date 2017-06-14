package org.linda.war_cardgame.interfaces;

import org.linda.war_cardgame.impl.DeckImpl;
import org.linda.war_cardgame.pojo.Player;

public interface War {

    /**
     * This method is used to start the game.
     * 
     * @param suits
     * @param ranks
     * @param valuesOfRanks
     * @param players
     */
    public void startGame(String[] suits, String[] ranks, int[] valuesOfRanks,
            String[] players);

    /**
     * This method is used to distribute cards. 
     * It will be called only once for each game.
     * 
     * @param deck
     * @param players
     * @return
     */
    public Player[] distributeCards(DeckImpl deck, String[] players);

    /**
     * 
     * @param playersOfWar
     */
    public void playOneTime(Player[] playersOfWar);
}
