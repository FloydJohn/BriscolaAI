package it.floydjohn.briscola.players;

import it.floydjohn.briscola.utils.IO;
import it.floydjohn.briscola.model.Card;
import it.floydjohn.briscola.model.Deck;

import java.util.List;

public class RealPlayer extends Player {
    @Override
    public int chooseCard(Deck opponentPoints, Card opponentCard, Card tableCard) {
        System.out.println("~~~~~NEW HAND~~~~~");
        System.out.println("Your cards:");
        List<Card> cards = getHand().getCards();
        for (int i = 0; i < cards.size(); i++)
            System.out.printf("\t%d. %s\n", i + 1, cards.get(i).toString());
        System.out.printf("\nCard on table: %s\n\n", tableCard.toString());
        if (opponentCard != null)
            System.out.printf("Opponent played: %s\n\n", opponentCard.toString());
        int choice = -1;
        while (!getHand().hasIndex(choice-1))
        choice = IO.askNumber("Choose a card to play > ");
        return choice-1;
    }
}
