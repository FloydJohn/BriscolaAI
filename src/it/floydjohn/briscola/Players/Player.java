package it.floydjohn.briscola.Players;

import it.floydjohn.briscola.model.Card;
import it.floydjohn.briscola.model.Deck;

import java.util.List;

/**
 * Author: alessandro
 * Date:   5/12/17.
 */
public abstract class Player {

    private Deck hand = new Deck(true);
    private Deck pointsDeck = new Deck(true);

    Deck getHand() {
        return hand;
    }

    public Deck getPointsDeck() {
        return pointsDeck;
    }

    public int getPoints() {
        return pointsDeck.getPoints();
    }

    /**
     * Adds a card to the hand deck.
     * @param card Card to add.
     */
    public void draw(Card card) {
        hand.add(card);
    }

    /**
     * Takes a list of cards and puts it in the points deck.
     * @param cards Cards to take.
     */
    public void take(List<Card> cards) {
        for (Card c : cards)
            pointsDeck.add(c);
    }

    /**
     * Chooses a card from the hand deck to play.
     * @param opponentPoints Deck of points taken by the opponent.
     * @param opponentCard Card played by the opponent. Null if this player is first.
     * @param tableCard Card that defines the "Briscola".
     * @return Card chosen.
     */
    public final Card play(Deck opponentPoints, Card opponentCard, Card tableCard) {
        int choice = chooseCard(opponentPoints, opponentCard, tableCard);
        if (!hand.hasIndex(choice)) return null;
        return hand.remove(choice);
    }

    public abstract int chooseCard(Deck opponentPoints, Card opponentCard, Card tableCard);

    public boolean hasCardsInHand() {
        return !hand.isEmpty();
    }

    public String getType() {
        return this.getClass().getSimpleName();
    }
}
