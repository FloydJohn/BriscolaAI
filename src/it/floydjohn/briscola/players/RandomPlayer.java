package it.floydjohn.briscola.players;

import it.floydjohn.briscola.model.Card;
import it.floydjohn.briscola.model.Deck;

import java.util.Random;

/**
 * Author: alessandro
 * Date:   5/13/17.
 */
public class RandomPlayer extends Player {
    @Override
    public int chooseCard(Deck opponentPoints, Card opponentCard, Card tableCard) {
        return new Random().nextInt(getHand().size());
    }
}
