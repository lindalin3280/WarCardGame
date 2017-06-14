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
    /*
     * String[] players ; DeckImpl deckImpl; WarImpl warImpl;
     * 
     * @Before public void setUp() throws Exception { List<Card> cards = new
     * ArrayList<Card>(Arrays.asList(new Card(new Rank("5")), new Card( new
     * Rank("6")), new Card(new Rank("7")), new Card(new Rank("8")))); players =
     * new String[] {"Jack", "Tom"}; deckImpl = new DeckImpl();
     * deckImpl.setCards(cards); warImpl = new WarImpl(); }
     */
    WarImpl warImpl;
    
    @Before
    public void setUp() throws Exception {
        warImpl = new WarImpl();
    }

    @Test
    public void testDistributeCards() {
        List<Card> cards = new ArrayList<Card>(
                Arrays.asList(new Card(new Rank("5")), new Card(new Rank("6")),
                        new Card(new Rank("7")), new Card(new Rank("8"))));
        DeckImpl deckImpl = new DeckImpl();
        deckImpl.setCards(cards);
        String[] players = new String[] { "Jack", "Tom" };
        Player[] playersOfWar = warImpl.distributeCards(deckImpl, players);
        assertEquals(playersOfWar[0].getCardsInHand().size(), 2);
    }

    @Test
    public void testPlayOneTime() {
        Player[] playersOfWar = {
                new Player(new LinkedList<>(
                        Arrays.asList(new Card(new Rank(5, "5"))))),
                new Player(new LinkedList<>(
                        Arrays.asList(new Card(new Rank(8, "8"))))) };
        warImpl.playOneTime(playersOfWar);
        assertEquals(playersOfWar[0].getCardsInHand().size(), 0);
        assertEquals(playersOfWar[1].getCardsInHand().size(), 2);
    }

    @Test
    public void testPlayOneTime2() {
        WarImpl.numFaceDownCardsIfDraw = 2;
        Player[] playersOfWar = {
                new Player(new LinkedList<>(Arrays.asList(
                        new Card(new Rank(5, "5")), 
                        new Card(new Rank(6, "6")),
                        new Card(new Rank(6, "6")),
                        new Card(new Rank(11, "J"))))),
                new Player(new LinkedList<>(Arrays.asList(
                        new Card(new Rank(5, "5")), 
                        new Card(new Rank(7, "7")),
                        new Card(new Rank(7, "7")),
                        new Card(new Rank(12, "Q"))))) };
        warImpl.playOneTime(playersOfWar);
        assertEquals(playersOfWar[0].getCardsInHand().size(), 0);
        assertEquals(playersOfWar[1].getCardsInHand().size(), 8);
    }

    @Test
    public void testPlayOneTime3() {
        WarImpl.numFaceDownCardsIfDraw = 3;
        Player[] playersOfWar = {
                new Player(new LinkedList<>(Arrays.asList(
                        new Card(new Rank(5, "5")), 
                        new Card(new Rank(6, "6")),
                        new Card(new Rank(6, "6")),
                        new Card(new Rank(11, "J"))))),
                new Player(new LinkedList<>(Arrays.asList(
                        new Card(new Rank(5, "5")), 
                        new Card(new Rank(7, "7")),
                        new Card(new Rank(7, "7")),
                        new Card(new Rank(12, "Q"))))) };
        warImpl.playOneTime(playersOfWar);
        assertEquals(playersOfWar[0].getCardsInHand().size(), 8);
        assertEquals(playersOfWar[1].getCardsInHand().size(), 0);
    }
    
    @Test
    public void testPlayOneTime4() {
        WarImpl.numFaceDownCardsIfDraw = 2;
        Player[] playersOfWar = {
                new Player(new LinkedList<>(Arrays.asList(
                        new Card(new Rank(5, "5")), 
                        new Card(new Rank(6, "6")),
                        new Card(new Rank(6, "6")),
                        new Card(new Rank(11, "J"))))),
                new Player(new LinkedList<>(Arrays.asList(
                        new Card(new Rank(5, "5")), 
                        new Card(new Rank(6, "6")),
                        new Card(new Rank(6, "6")),
                        new Card(new Rank(11, "J")))))};
        warImpl.playOneTime(playersOfWar);
        assertEquals(playersOfWar[0].getCardsInHand().size(), 8);
        assertEquals(playersOfWar[1].getCardsInHand().size(), 0);
    }
    
    @Test
    public void testPlayOneTime5() {
        WarImpl.numFaceDownCardsIfDraw = 2;
        Player[] playersOfWar = {
                new Player(new LinkedList<>(Arrays.asList(
                        new Card(new Rank(5, "5")), 
                        new Card(new Rank(8, "8")),
                        new Card(new Rank(6, "6")),
                        new Card(new Rank(11, "J"))))),
                new Player(new LinkedList<>(Arrays.asList(
                        new Card(new Rank(5, "5")), 
                        new Card(new Rank(6, "6")),
                        new Card(new Rank(11, "J")),
                        new Card(new Rank(11, "J"))))),
                new Player(new LinkedList<>(Arrays.asList(
                        new Card(new Rank(5, "5")), 
                        new Card(new Rank(6, "6")),
                        new Card(new Rank(13, "K")),
                        new Card(new Rank(11, "J")))))};
        warImpl.playOneTime(playersOfWar);
        assertEquals(playersOfWar[0].getCardsInHand().size(), 12);
        assertEquals(playersOfWar[1].getCardsInHand().size(), 0);
        assertEquals(playersOfWar[2].getCardsInHand().size(), 0);
    }
    
    @Test
    public void testPlayOneTime6() {
        WarImpl.numFaceDownCardsIfDraw = 1;
        Player[] playersOfWar = {
                new Player(new LinkedList<>(Arrays.asList(
                        new Card(new Rank(5, "5")), 
                        new Card(new Rank(6, "6")),
                        new Card(new Rank(7, "7")),
                        new Card(new Rank(10, "10"))))),
                new Player(new LinkedList<>(Arrays.asList(
                        new Card(new Rank(100, "A")), 
                        new Card(new Rank(6, "6")),
                        new Card(new Rank(8, "8")),
                        new Card(new Rank(13, "K"))))),
                new Player(new LinkedList<>(Arrays.asList(
                        new Card(new Rank(5, "5")), 
                        new Card(new Rank(6, "6")),
                        new Card(new Rank(9, "9")),
                        new Card(new Rank(11, "J")))))};
        warImpl.playOneTime(playersOfWar);
        assertEquals(playersOfWar[0].getCardsInHand().size(), 0);
        assertEquals(playersOfWar[1].getCardsInHand().size(), 12);
        assertEquals(playersOfWar[2].getCardsInHand().size(), 0);
    }
}
