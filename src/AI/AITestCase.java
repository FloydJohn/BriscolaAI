package AI;

import game.Card;
import game.Deck;
import game.Rules;

import java.util.ArrayList;
import java.util.List;

public class AITestCase {

    private static final int NUM_SIMULAZIONI = 1;
    private static final float MAX_MUL = 10;
    private static final float STEP_MUL = 0.1f;
    private static final float MAX_ADD = 10;
    private static final float STEP_ADD = 1f;
    static Deck gameDeck, p1Deck, p2Deck;

    public static void main(String[] args) {
        multiTester();
    }


    public static void multiTester() {

        List<AInterface> methods = new ArrayList<>();
        methods.add(new RandomAI());
        methods.add(new CustomAI());
        methods.add(new MontecarloAI(1));
        methods.add(new MontecarloAI(2));
        methods.add(new MontecarloAI(3));
        methods.add(new MontecarloAI(4));
        methods.add(new MontecarloAI(5));

        for (AInterface current : methods) {
            List<AInterface> vs = new ArrayList<>();
            vs.addAll(methods);
            vs.remove(current);
            for (AInterface versus : vs) {
                System.out.print(current.name() + " vs "+versus.name()+": ");
                System.out.println(test(current, versus, 20));
            }
        }
    }

    private static Stats test(AInterface p1, AInterface p2, int testNumber){
        Stats stats = new Stats(testNumber);
        ElapsedTimer timer = new ElapsedTimer();
        for (int i = 0; i < testNumber; i++) {
            Deck currentDeck = play(9, p1, p2);
            stats.addPoints(currentDeck.getPoints());
        }
        stats.setElapsedMs(timer);
        return stats;
    }

    /**
     * @return player1 deck
     */
    private static Deck play(int deckNumber, AInterface player1, AInterface player2) {
        gameDeck = new Deck();
        gameDeck.generate(deckNumber);
        p1Deck = new Deck();
        p2Deck = new Deck();
        Deck p1Points = new Deck(), p2Points = new Deck();
        for (int i = 0; i < 3; i++) {
            p1Deck.add(gameDeck.draw());
            p2Deck.add(gameDeck.draw());
        }
        Card.Suit briscola = gameDeck.getCards().get(0).getSuit();
        boolean player1First = true;

        while (p1Deck.size() > 0) { //Until there are cards to play
            Card p1Card = null, p2Card = null;
            if (player1First) {
                p1Card = player1.play(p2Deck, p1Deck, gameDeck, p2Card, briscola);
                p2Card = player2.play(p1Deck, p2Deck, gameDeck, p1Card, briscola);
            } else {
                p2Card = player2.play(p1Deck, p2Deck, gameDeck, p1Card, briscola);
                p1Card = player1.play(p2Deck, p1Deck, gameDeck, p2Card, briscola);
            }
            p1Deck.remove(p1Card);
            p2Deck.remove(p2Card);

            if (Rules.doesPlayerWin(p1Card, p2Card, briscola, player1First)) {
                //Player 1 won
                player1First = true;
                p1Points.add(p1Card);
                p1Points.add(p2Card);
            } else {
                //Player 2 won
                player1First = false;
                p2Points.add(p1Card);
                p2Points.add(p2Card);
            }
            if (gameDeck.size() > 0) { //Draw
                if (player1First) {
                    p1Deck.add(gameDeck.draw());
                    p2Deck.add(gameDeck.draw());
                } else {
                    p2Deck.add(gameDeck.draw());
                    p1Deck.add(gameDeck.draw());
                }
            }
        }

        return p1Points;
        }

}
