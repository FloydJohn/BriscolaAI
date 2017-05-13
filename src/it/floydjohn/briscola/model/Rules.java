package it.floydjohn.briscola.model;

class Rules {
    static boolean doesFirstCardWin(Card firstCard, Card secondCard, Card.Suit briscolaSuit) {
        if (firstCard.getSuit() == secondCard.getSuit()) return firstCard.getType().ordinal() > secondCard.getType().ordinal();
        else return firstCard.getSuit() == briscolaSuit || secondCard.getSuit() != briscolaSuit;
    }
}
