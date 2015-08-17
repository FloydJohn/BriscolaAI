package game;

public class Rules {
    public static boolean doesPlayerWin(Card playerCard, Card pcCard, Card.Suit briscolaSuit, boolean isPlayerFirst) {
        if (playerCard.getSuit() == pcCard.getSuit()) return playerCard.getType().ordinal() > pcCard.getType().ordinal();
        else return playerCard.getSuit() == briscolaSuit || pcCard.getSuit() != briscolaSuit && isPlayerFirst;
    }
}
