package AI;

import game.Card;
import game.Deck;

import java.util.Random;

public class RandomAI implements AInterface {


    @Override
    public Card play(Deck plDeck, Deck pcDeck, Deck gaDeck, Card playerCard, Card.Suit briscolaSuit) {
        return pcDeck.getCards().get(new Random().nextInt(pcDeck.size()));
    }

    @Override
    public String name() {
        return "RANDOM";
    }
}
