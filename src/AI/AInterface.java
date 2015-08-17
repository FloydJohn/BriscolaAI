package AI;

import game.Card;
import game.Deck;

public interface AInterface {
    Card play(Deck plDeck, Deck pcDeck, Deck gaDeck, Card playerCard, Card.Suit briscolaSuit);
    String name();
}
