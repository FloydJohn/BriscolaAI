package game;

public class Card {

    private final Suit suit;
    private final Type type;

    public Card(Suit suit, Type type) {
        this.suit = suit;
        this.type = type;
    }

    public Suit getSuit() {
        return suit;
    }

    public Type getType() {
        return type;
    }

    public int getPoints() {
        switch (type) {
            case Due:
            case Quattro:
            case Cinque:
            case Sei:
            case Sette:
                return 0;
            case Fante:
                return 2;
            case Cavallo:
                return 3;
            case Re:
                return 4;
            case Tre:
                return 10;
            case Asso:
                return 11;
        }
        return -1000;
    }

    public boolean isLoad() {
        return type.ordinal() >= Type.Tre.ordinal();
    }

    @Override
    public String toString() {
        return String.format("%s di %s", type.name(), suit.name());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Card)) return false;
        Card in = (Card) obj;
        return in.getSuit().equals(suit) && in.getType().equals(type);
    }

    @Override
    protected Card clone() throws CloneNotSupportedException {
        super.clone();
        return new Card(this.suit, this.type);
    }

    public enum Suit {Spade, Bastoni, Denari, Coppe}

    public enum Type {Due, Quattro, Cinque, Sei, Sette, Fante, Cavallo, Re, Tre, Asso}
}
