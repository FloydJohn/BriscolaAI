package AI;

import game.Card;
import game.Deck;

public class SameCardAI implements AInterface {

    private int cardNumber;

    public SameCardAI(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public Card play(Deck plDeck, Deck pcDeck, Deck gaDeck, Card playerCard, Card.Suit briscolaSuit) {
        return pcDeck.getCards().get(Math.min(cardNumber, pcDeck.size() - 1));
    }

    @Override
    public String name() {
        return null;
    }
}
