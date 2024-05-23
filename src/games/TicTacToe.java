package games;
import java.util.*;
import rendering.TextScreen;

public class TicTacToe extends ArcadeGame {
    public static final int WIDTH = 7;
    public static final int HEIGHT = 6;
    public static final String PLAYER1 = "O";
    public static final String PLAYER2 = "X";
    public static final String EMPTY = ".";
    public static final String TITLE = "tic_tac_toe.exe";

    private boolean menu = true;
    private boolean turn = false;

    public TicTacToe() {
        
    }

    public void render(TextScreen out) {
        String board = "";

    }

    public void input(Scanner in) {

    }
}