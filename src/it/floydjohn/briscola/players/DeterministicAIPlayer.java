package it.floydjohn.briscola.players;

import it.floydjohn.briscola.model.Card;
import it.floydjohn.briscola.model.Deck;

public class DeterministicAIPlayer extends Player {

    @Override
    public int chooseCard(Deck opponentPoints, Card opponentCard, Card tableCard) {
        float[] prob = new float[getHand().size()];

        //Add points to each card
        for (int i = 0; i < prob.length; i++) {
            Card card = getHand().getCard(i);
            prob[i] -= card.getType().ordinal()*0.5;
            if (card.getSuit().equals(tableCard.getSuit())) prob[i] -= (card.getType().ordinal() + 1);

            if (opponentCard != null) { //Opponent already played a card
                if (card.getSuit().equals(opponentCard.getSuit()) && !card.getSuit().equals(tableCard.getSuit()) && card.getType().ordinal() > opponentCard.getType().ordinal()) {
                    //Can eat (not briscola)
                    prob[i] += card.getPoints() + opponentCard.getPoints();
                }
                if (opponentCard.getSuit().equals(tableCard.getSuit())) {
                    //Player plays briscola, give less points possible
                    prob[i] -= card.getPoints();
                    if (card.getSuit().equals(tableCard.getSuit())) prob[i] -= 8*card.getType().ordinal();
                }
                if (opponentCard.getPoints() >= 10 && card.getSuit().equals(tableCard.getSuit())) {
                    //Player plays load
                    prob[i] += 15 - 2*card.getPoints();
                }
            }

        }

        //Return best card
        int best = -1;
        float bestPoints = Integer.MIN_VALUE;
        for (int i = 0; i < prob.length; i++) {
            if (Double.compare(prob[i], bestPoints) > 0) {
                bestPoints = prob[i];
                best = i;
            }
        }
        return best;
    }
}
