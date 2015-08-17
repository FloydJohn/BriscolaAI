package AI;

import game.Rules;
import game.Card;
import game.Deck;

public class Node {
    private int maxDeepness;
    private static Card.Suit brSuit;
    private boolean completelyExtended = false;

    public static void setBrSuit(Card.Suit suit) {
        brSuit = suit;
    }

    private Node[] childs;
    private final int deepness;
    private final Card plCard, pcCard;
    private final Deck plDeck, pcDeck, gaDeck;
    private final boolean isPcFirst;

    public Node(int deepness, int maxDeepness, Card plCard, Card pcCard, Deck plDeck, Deck pcDeck, Deck gaDeck, boolean isPcFirst) {
        this.deepness = deepness;
        this.maxDeepness = maxDeepness;
        this.plCard = plCard;
        this.pcCard = pcCard;
        this.plDeck = plDeck;
        this.pcDeck = pcDeck;
        this.gaDeck = gaDeck;
        this.isPcFirst = isPcFirst;
    }

    public static Card.Suit getBrSuit() {
        return brSuit;
    }

    /**
     * @return 0 already extended
     *         1 extended
     */
    public int update() {
        if (deepness >= maxDeepness) completelyExtended = true;
        if (completelyExtended) return 0;
        if (!hasChilds()) {
            extend();
            return 1;
        }
        for (Node child : childs)
            if (child.update() == 1) return 1;
        completelyExtended = true;
        return 0;
    }

    private void extend() {
        childs = new Node[pcDeck.size() * plDeck.size()];
        for (int pc = 0; pc < pcDeck.size(); pc++) {
            for (int pl = 0; pl < plDeck.size(); pl++) {
                Deck pcDeckNew = pcDeck.clone();
                Deck plDeckNew = plDeck.clone();
                Deck gaDeckNew = gaDeck.clone();

                Card pcCardNew = pcDeckNew.remove(pc);
                Card plCardNew = plDeckNew.remove(pl);

                boolean pcFirstNew = !Rules.doesPlayerWin(plCardNew, pcCardNew, brSuit, !isPcFirst);

                if (gaDeckNew.size() > 1) {
                    if (pcFirstNew) {
                        pcDeckNew.add(gaDeckNew.draw());
                        plDeckNew.add(gaDeckNew.draw());
                    } else {
                        plDeckNew.add(gaDeckNew.draw());
                        pcDeckNew.add(gaDeckNew.draw());
                    }
                }
                //TODO Update algorithm, childs above max deepness should NOT be created
                childs [plDeck.size()*pc + pl] = new Node(deepness+1, maxDeepness, plCardNew, pcCardNew, plDeckNew, pcDeckNew, gaDeckNew, pcFirstNew);
            }
        }
    }

    public boolean hasChilds() {
        return childs != null;
    }

    public int getPoints() {
        if (plCard == null) throw new IllegalArgumentException("Can't call this method if plcard == null");
        if (pcCard == null) throw new IllegalArgumentException("Can't call this method if pccard == null");
        int playerPoints = plCard != null ? plCard.getPoints() : 0;
        int pcPoints = pcCard != null ? pcCard.getPoints() : 0;
        int handPoints = playerPoints + pcPoints;
        if (Rules.doesPlayerWin(plCard, pcCard, brSuit, !isPcFirst)) {
            return -handPoints;
        } else return handPoints;
    }

    public int getMyUTC() {
        if (!hasChilds()) return getPoints();
        int UTC = getPoints();
        for (Node child : childs) UTC += child.getMyUTC();
        return UTC;
    }

    public Node getBest() {
        Node selected = null;
        int maxValue = Integer.MIN_VALUE;
        for (Node child : childs) {
            if (child.getMyUTC() > maxValue) {
                selected = child;
                maxValue = child.getMyUTC();
            }
        }
        return selected;
    }

    public Card getPcCard() {
        return pcCard;
    }
}
