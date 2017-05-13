package it.floydjohn.briscola.model;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public class RulesTest {
    @Test
    public void testFirstCardWins() throws Exception {
        boolean response = Rules.doesFirstCardWin(new Card(Card.Suit.Coppe, Card.Type.Due), new Card((Card.Suit.Bastoni), Card.Type.Due), Card.Suit.Denari);
        Assert.assertTrue(response);
    }

    @Test
    public void testBriscolaWins() throws Exception {
        boolean response = Rules.doesFirstCardWin(new Card(Card.Suit.Coppe, Card.Type.Due), new Card((Card.Suit.Bastoni), Card.Type.Asso), Card.Suit.Bastoni);
        Assert.assertFalse(response);
    }

    @Test
    public void testHigherCardWins() throws Exception {
        boolean response = Rules.doesFirstCardWin(new Card(Card.Suit.Bastoni, Card.Type.Quattro), new Card((Card.Suit.Bastoni), Card.Type.Due), Card.Suit.Denari);
        Assert.assertTrue(response);
    }

    @Test
    public void testPriorityOrder() throws Exception {
        boolean response = Rules.doesFirstCardWin(new Card(Card.Suit.Bastoni, Card.Type.Sette), new Card((Card.Suit.Bastoni), Card.Type.Sei), Card.Suit.Denari);
        Assert.assertTrue(response);
        response = Rules.doesFirstCardWin(new Card(Card.Suit.Bastoni, Card.Type.Sette), new Card((Card.Suit.Bastoni), Card.Type.Fante), Card.Suit.Denari);
        Assert.assertFalse(response);
        response = Rules.doesFirstCardWin(new Card(Card.Suit.Bastoni, Card.Type.Tre), new Card((Card.Suit.Bastoni), Card.Type.Cavallo), Card.Suit.Denari);
        Assert.assertTrue(response);
        response = Rules.doesFirstCardWin(new Card(Card.Suit.Bastoni, Card.Type.Asso), new Card((Card.Suit.Bastoni), Card.Type.Tre), Card.Suit.Denari);
        Assert.assertTrue(response);
    }
}
