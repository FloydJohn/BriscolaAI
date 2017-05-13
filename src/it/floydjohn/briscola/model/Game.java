package it.floydjohn.briscola.model;

import it.floydjohn.briscola.Players.Player;
import it.floydjohn.briscola.model.Card;
import it.floydjohn.briscola.model.Deck;
import it.floydjohn.briscola.model.Rules;

import java.util.ArrayList;
import java.util.Random;

/**
 * Models a game between two players.
 */
public class Game {

    public static final boolean DEBUG = true;
    private Player playerA, playerB;
    private Deck deck;
    private Card tableCard;

    private boolean ended = false;

    /**
     * True  -> Player A starts
     * False -> Player B starts
     */
    private boolean turn;

    /**
     * Initializes the game drawing 3 cards for each player, the table card and choosing randomly the first player.
     * @param playerA First player.
     * @param playerB Second player.
     */
    public Game(Player playerA, Player playerB) {
        this.playerA = playerA;
        this.playerB = playerB;
        deck = new Deck(false);
        for (int i = 0; i < 3; i++) {
            this.playerA.draw(deck.draw());
            this.playerB.draw(deck.draw());
        }
        tableCard = deck.draw();
        turn = new Random().nextBoolean();
        log("Initialized game. PlayerA is "+playerA.getType()+", PlayerB is "+playerB.getType());
    }

    /**
     * Simulates an entire game, and stops at the end.
     * @return Winner of the game. Null if game draws.
     */
    public Player run() {
        while (!ended) step();
        if (playerA.getPoints() > playerB.getPoints()) return playerA;
        if (playerB.getPoints() > playerA.getPoints()) return playerB;
        return null;
    }

    /**
     * Simulates an entire hand.
     * 1 - Starting player plays first, then second player
     * 2 - The winner takes both cards and adds them to its points
     * 3 - The winner draws, then the loser draws
     * 4 - The winner will be the first player of the next hand
     */
    private void step() {
        if (ended) return;
        log("New step. Player "+(turn ? "A" : "B")+" is first.");
        Player p1 = turn ? playerA : playerB;
        Player p2 = turn ? playerB : playerA;

        Card c1 = p1.play(p2.getPointsDeck(), null, tableCard);
        log("Player "+(p1 == playerA ? "A" : "B")+" played "+c1);
        Card c2 = p2.play(p1.getPointsDeck(), c1, tableCard);
        log("Player "+(p1 == playerB ? "A" : "B")+" played "+c2);

        ArrayList<Card> hand = new ArrayList<>();
        hand.add(c1);
        hand.add(c2);

        boolean firstWins = Rules.doesFirstCardWin(c1, c2, tableCard.getSuit());
        Player winner = firstWins ? p1 : p2;
        Player loser  = firstWins ? p2 : p1;
        log("Player "+(winner == playerA ? "A" : "B")+" won the hand.");

        winner.take(hand);
        if (!deck.isEmpty()) winner.draw(deck.draw());
        if (!deck.isEmpty()) loser.draw(deck.draw());

        turn = winner == playerA;

        if (!playerA.hasCardsInHand() || !playerA.hasCardsInHand()) ended = true;
    }

    private void log(String in) {
        System.out.println("[Game] "+in);
    }
}
