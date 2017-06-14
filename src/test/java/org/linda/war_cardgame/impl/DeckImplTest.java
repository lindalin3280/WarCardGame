package org.linda.war_cardgame.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.linda.war_cardgame.pojo.Card;
import org.linda.war_cardgame.pojo.Rank;

public class DeckImplTest {
    private List<Card> cards = new ArrayList<Card>();
    private DeckImpl deckImpl;

    @Before
    public void setUp() throws Exception {
        deckImpl = new DeckImpl();
    }

    @Test
    public void testCreate() {
        String[] suits = new String[] { "hearts", "clubs" };
        String[] ranks = new String[] { "K", "Q", "8" };
        int[] valuesOfRanks = new int[] { 13, 12, 8 };
        deckImpl.setCards(cards);
        deckImpl.create(suits, ranks, valuesOfRanks);
        assertEquals(cards.size(), 6);
    }

    @Test
    public void testShuffle() {
        cards = new ArrayList<>(Arrays.asList(new Card(new Rank("5")), new Card(new Rank("6")),
                new Card(new Rank("7"))));
        List<Card> cardsCopy = Arrays.asList(new Card(new Rank("5")), new Card(
                new Rank("6")), new Card(new Rank("7")));
        System.out.println("cards = " + cards);
        System.out.println("cardsCopy = " + cardsCopy);
        assertEquals(cards.toString(), cardsCopy.toString());
        deckImpl.setCards(cards);
        deckImpl.shuffle();
        deckImpl.shuffle();
        System.out.println("after shuffle, cards = " + cards);
        assertNotEquals(cards.toString(), cardsCopy.toString());

    }

    @Test
    public void testDeal() {
        cards = new ArrayList<>(Arrays.asList(new Card(new Rank("5")), new Card(new Rank("6")),
                new Card(new Rank("7"))));
        deckImpl.setCards(cards);
        Card dealtCard = deckImpl.deal();
        Card card = new Card(new Rank("5"));
        assertEquals(dealtCard.toString(),card.toString());
    }

}
