import AI.AI;
import game.Card;
import game.Deck;
import game.Rules;
import utils.IO;

import java.util.ArrayList;
import java.util.List;

public class RealPlayer {

    private static final int CARDS_IN_HAND = 3;
    private Deck gameDeck;
    private Deck playerDeck;
    private Deck playerPoints;
    private Deck pcDeck;
    private Deck pcPoints;
    private Card.Suit briscolaSuit;
    private boolean lastHandWonByPlayer = true;

    public RealPlayer() {
        gameDeck = new Deck();
        playerDeck = new Deck();
        playerPoints = new Deck();
        pcDeck = new Deck();
        pcPoints = new Deck();
        gameDeck.generate(System.currentTimeMillis());
        for (int i = 0; i < CARDS_IN_HAND; i++) draw();
        briscolaSuit = gameDeck.getCards().get(0).getSuit();
    }

    public static void main(String[] args) {
        RealPlayer rp = new RealPlayer();
        rp.loop();
    }

    public void draw() {
        if (gameDeck.size() == 0) return;
        if (lastHandWonByPlayer) {
            playerDeck.add(gameDeck.draw());
            pcDeck.add(gameDeck.draw());
        } else {
            pcDeck.add(gameDeck.draw());
            playerDeck.add(gameDeck.draw());
        }
    }

    public void loop() {
        AI ai = new AI();
        List<String> briscoleViste = new ArrayList<>();
        while (true) {
            Card playerCard, pcCard = null;
            System.out.println(this);
            if (!lastHandWonByPlayer) {
                pcCard = ai.play(playerDeck, pcDeck, gameDeck, null, briscolaSuit);
                System.out.println("\nIl PC ha giocato: "+pcCard);
            }
            Integer choice;
            while (true) {
                System.out.println();
                choice = IO.askNumber("Seleziona la carta da giocare (-1 per uscire): ");
                if (choice == -1) return;
                if (!playerDeck.hasIndex(choice-1)) System.out.println("Numero carta non valido!");
                else break;
            }
            //Card chosen, play it
            playerCard = playerDeck.remove(choice-1);
            System.out.println("\nTu hai giocato: " + playerCard);

            if (pcCard == null) {
                pcCard = ai.play(playerDeck, pcDeck, gameDeck, playerCard, briscolaSuit);
                System.out.println("\nIl PC ha giocato: "+pcCard);
            }
            pcDeck.remove(pcCard);
            if (Rules.doesPlayerWin(playerCard, pcCard, briscolaSuit, lastHandWonByPlayer)){
                System.out.println("\nHai vinto la mano!");
                lastHandWonByPlayer = true;
            } else {
                System.out.println("\nHai perso la mano!");
                lastHandWonByPlayer = false;
            }
            playerPoints.add(playerCard);
            pcPoints.add(pcCard);

            System.out.println();
            draw();
            if (gameDeck.size() > 0 && playerDeck.getCards().get(playerDeck.size()-1).getSuit().equals(briscolaSuit)) briscoleViste.add(playerDeck.getCards().get(playerDeck.size()-1).toString());
            if (playerDeck.size() == 0) {
                //Game ended
                System.out.println("\n\nGioco terminato!");
                if (playerPoints.getPoints() > pcPoints.getPoints()) System.out.print("Hai vinto");
                else if (playerPoints.getPoints() < pcPoints.getPoints()) System.out.print("Hai perso");
                else System.out.print("Hai pareggiato");
                System.out.println(" con "+playerPoints.getPoints()+" punti!\nHai visto le seguenti briscole:");
                briscoleViste.forEach(System.out::println);
                return;
            }
        }
    }



    @Override
    public String toString() {
        String out = "--------------------------" + gameDeck.size() + " carte rimanenti--------------------------------------------\n";
        out += String.format("Le tue carte:\n%s", playerDeck);
        //out += String.format("Le carte del PC:\n%s", pcDeck);
        out += "\nLa briscola e': "+briscolaSuit.name();
        return out;
    }

}
