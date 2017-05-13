package it.floydjohn.briscola.players;

import it.floydjohn.briscola.model.Card;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Author: alessandro
 * Date:   5/13/17.
 */
public class DeterministicAIPlayerTest {
    private DeterministicAIPlayer player;
    private Card tableCard;
    private Card nonBriscolaLoad, assoBriscola, smallBriscola, smallNonBriscola;



    @Before
    public void setUp() throws Exception {
        player = new DeterministicAIPlayer();
        tableCard = new Card(Card.Suit.Bastoni, Card.Type.Due);
        nonBriscolaLoad = new Card(Card.Suit.Spade, Card.Type.Tre);
        assoBriscola = new Card(Card.Suit.Bastoni, Card.Type.Asso);
        smallBriscola = new Card(Card.Suit.Bastoni, Card.Type.Quattro);
        smallNonBriscola = new Card(Card.Suit.Denari, Card.Type.Quattro);

        player.draw(new Card(Card.Suit.Bastoni, Card.Type.Cinque)); //Small briscola
        player.draw(new Card(Card.Suit.Coppe, Card.Type.Asso)); //Load non briscola
        player.draw(new Card(Card.Suit.Denari, Card.Type.Due)); //Small non briscola
    }

    @Test
    public void testLeaveSmallBriscola() throws Exception {
        int choice = player.chooseCard(null, smallBriscola, tableCard);
        Assert.assertEquals(2, choice);
    }

    @Test
    public void testTakeLoadWithSmallBriscola() throws Exception {
        int choice = player.chooseCard(null, nonBriscolaLoad, tableCard);
        Assert.assertEquals(0, choice);
    }

    @Test
    public void testNoPointsWhenOtherTakes() throws Exception {
        int choice = player.chooseCard(null, assoBriscola, tableCard);
        Assert.assertEquals(2, choice);
    }

    @Test
    public void testMakeOtherTake() throws Exception {
        int choice = player.chooseCard(null, smallNonBriscola, tableCard);
        Assert.assertEquals(2, choice);
    }
}