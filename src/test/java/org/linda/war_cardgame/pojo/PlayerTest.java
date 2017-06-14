package org.linda.war_cardgame.pojo;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Before;
import org.junit.Test;
import org.linda.war_cardgame.impl.DeckImpl;

public class PlayerTest {
    Player p; 
    
    @Before
    public void setUp() throws Exception {
        Queue<Card> cardsInHand = new LinkedList<>(Arrays.asList(new Card(
                new Rank("5")), new Card(new Rank("6")),
                new Card(new Rank("7")), new Card(new Rank("8"))));
        p = new Player(cardsInHand);
    }

    @Test
    public void testGetCardsInHand() {     
        assertEquals(p.getCardsInHand().size(), 4);
    }

    @Test
    public void testDealCard() {
        Card dealtCard = p.dealCard();
        assertEquals(dealtCard.toString(), new Card(new Rank("5")).toString());
    }

}
