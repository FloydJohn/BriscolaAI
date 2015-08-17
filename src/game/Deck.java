package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {

    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        //if (cards.size() == 3) throw new IllegalArgumentException("Deck has already 3 cards!");
        cards.add(card);
    }

    public Card remove(Card card) {
        for (Card c : cards) if (c.equals(card)) {
            cards.remove(c);
            return c;
        }
        return null;
    }

    public Card remove(int index) {
        return cards.remove(index);
    }

    public void generate(long randomSeed) {
        for (Card.Suit suit : Card.Suit.values())
            for (Card.Type type : Card.Type.values())
                cards.add(new Card(suit, type));
        Collections.shuffle(cards, new Random(randomSeed));
    }

    public Card random() {
        return cards.get(new Random().nextInt(cards.size()));
    }

    public Card extract() {
        return remove(random());
    }

    public Card draw() {
        if (cards.size() == 0) return null;
        return remove(cards.get(cards.size() - 1));
    }

    public String toString(int indentation) {
        String ind = "";
        for (int i = 0; i < indentation; i++) ind += "\t";
        String out = "";
        int index = 0;
        for (Card card : cards) out += ind + String.valueOf(++index) + ". " + card.toString() + "\n";
        return out;
    }

    public int size() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getPoints() {
        int points = 0;
        for (Card card : cards) points += card.getPoints();
        return points;
    }

    public boolean hasIndex(int choice) {
        return choice >= 0 && cards.size() > choice;
    }

    @Override
    public String toString() {
        String out = "";
        int index = 0;
        for (Card card : cards) out += "\t" + String.valueOf(++index) + ". " + card.toString() + "\n";
        return out;
    }


    @Override
    public Deck clone() {
        try {
            super.clone();
        } catch (CloneNotSupportedException ignored) {}
        Deck cloned = new Deck();
        for (Card card : cards) cloned.cards.add(card.clone());
        return cloned;
    }
}
