package it.floydjohn.briscola;

import it.floydjohn.briscola.players.Player;
import it.floydjohn.briscola.players.RandomPlayer;
import it.floydjohn.briscola.players.RealPlayer;
import it.floydjohn.briscola.model.Game;

/**
 * Author: alessandro
 * Date:   5/12/17.
 */
public class Main {

    public static void main(String[] args) {
        Player p1 = new RandomPlayer();
        Player p2 = new RealPlayer();

        Game game = new Game(p1, p2);

        game.run();
    }
}
