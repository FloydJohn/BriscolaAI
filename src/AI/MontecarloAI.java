package AI;

import game.Card;
import game.Deck;

public class MontecarloAI implements AInterface {

    private final int maxDeepness;

    public MontecarloAI(int maxDeepness) {
        this.maxDeepness = maxDeepness;
    }

    @Override
    public Card play(Deck plDeck, Deck pcDeck, Deck gaDeck, Card playerCard, Card.Suit briscolaSuit) {
        Node root = new Node(0, maxDeepness, playerCard, null, plDeck, pcDeck, gaDeck, playerCard != null);
        //noinspection StatementWithEmptyBody
        while (root.update() == 1);
        return root.getBest().getPcCard();
    }

    @Override
    public String name() {
        return "MONTE"+maxDeepness;
    }
}
