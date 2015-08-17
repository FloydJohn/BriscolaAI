package AI;

import game.Card;
import game.Deck;

public class CustomAI implements AInterface {

    @Override
    public Card play(Deck plDeck, Deck pcDeck, Deck gaDeck, Card playerCard, Card.Suit briscolaSuit) {
        float[] prob = new float[pcDeck.size()];

        //Add points to each card
        for (int i = 0; i < pcDeck.getCards().size(); i++) {
            Card card = pcDeck.getCards().get(i);
            prob[i] -= card.getPoints()*0.5;
            //System.out.format("%s weight: %.1f\n", card, prob[i]);
            if (card.getSuit().equals(briscolaSuit)) {
                prob[i] -= (card.getType().ordinal() + 0.5);
                //System.out.format("%s is briscola: -%.2f\n", card, card.getType().ordinal() + 0.5);
            }
            if (playerCard != null) { //Last move
                if (card.getSuit().equals(playerCard.getSuit()) && !card.getSuit().equals(briscolaSuit) && card.getType().ordinal() > playerCard.getType().ordinal()) {
                    //Can eat (not briscola)
                    prob[i] += card.getPoints() + playerCard.getPoints();
                    //System.out.format("%s can eat: +%d\n", card, card.getPoints() + playerCard.getPoints());
                }
                if (playerCard.getSuit().equals(briscolaSuit)) {
                    //Player plays briscola, give less points possible
                    //System.out.format("Player plays briscola, %s: -%d\n", card, card.getPoints());
                    prob[i] -= card.getPoints();
                }
                if (playerCard.getPoints() >= 10 && card.getSuit().equals(briscolaSuit)) {
                    //Player plays load
                    prob[i] += 15 - card.getPoints();
                    //System.out.format("Player plays load, %s: +%d\n", card, 15 - card.getPoints());
                }
            }

        }

        //Return best card
        Card best = null;
        float bestPoints = Integer.MIN_VALUE;
        for (int i = 0; i < prob.length; i++) {
            if (Double.compare(prob[i], bestPoints) > 0) {
                bestPoints = prob[i];
                best = pcDeck.getCards().get(i);
            }
        }
        //System.out.println("AI -> "+ Arrays.toString(prob));
        return best;
    }

    @Override
    public String name() {
        return "CUSTOM";
    }
}
