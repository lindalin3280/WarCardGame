package org.linda.war_cardgame.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Before;
import org.junit.Test;
import org.linda.war_cardgame.pojo.Card;
import org.linda.war_cardgame.pojo.Player;
import org.linda.war_cardgame.pojo.Rank;

public class WarImplTest {
    List<Card> cards;
    String[] players ;
    DeckImpl deckImpl;
    WarImpl warImpl;
    @Before
    public void setUp() throws Exception {
        cards = new ArrayList<Card>(Arrays.asList(new Card(new Rank("5")), new Card(
                new Rank("6")), new Card(new Rank("7")), new Card(new Rank("8"))));
        players = new String[] {"Jack", "Tom"};
        deckImpl = new DeckImpl();
        deckImpl.setCards(cards);
        warImpl = new WarImpl();
    }
    @Test
    public void testDistributeCards() {
        Player[] playersOfWar = warImpl.distributeCards(deckImpl, players);
        assertEquals(playersOfWar[0].getCardsInHand().size(), 2);
    }

    @Test
    public void testPlayOneRound() {
        Player[] playersOfWar = new Player[2];
        Queue<Card> cards1 = new LinkedList<>(Arrays.asList(new Card(new Rank("5"))));
        Queue<Card> cards2 = new LinkedList<>(Arrays.asList(new Card(new Rank("8"))));
        
        playersOfWar[0] = new Player(cards1);
        playersOfWar[1] = new Player(cards2);
        warImpl.playOneTime(playersOfWar);
        assertEquals(playersOfWar[0].getCardsInHand().size(), 2);
        assertEquals(playersOfWar[1].getCardsInHand().size(), 0);
    }

}
