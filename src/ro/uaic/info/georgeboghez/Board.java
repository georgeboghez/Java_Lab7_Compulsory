package ro.uaic.info.georgeboghez;

import java.util.*;
import java.util.stream.IntStream;

/**
 * An instance of this class will contain n tokens (you may consider the numbers from 1 to n). It is a singleton class having only one object.
 */
public class Board {
    private List<Token> tokens;
    private static Board board = null;

    /**
     * private constructor only to be used inside this class
     * @param numberOfTokens specifies the number of tokens the board will have
     */
    private Board(int numberOfTokens) {
        tokens = new ArrayList<>();
        IntStream.rangeClosed(0, numberOfTokens)
                .boxed()
                .forEach(
                        (elem) -> tokens.add(new Token(elem))
                );
    }

    /**
     * will be called from outside of the class in order to get the unique Board object
     * @param numberOfTokens specifies the number of tokens the board will have
     * @return the unique Board object
     */
    public static Board getInstance(int numberOfTokens) {
        if (board == null) {
            board = new Board(numberOfTokens);
        }
        return board;
    }

    /**
     * will be called from outside from the player class in order to get the unique Board object
     * @return
     */
    public static Board getInstance() {
        return board;
    }

    /**
     * gets all the tokens
     * @return an ArrayList of tokens
     */
    public List<Token> getTokens() {
        return tokens;
    }

    /**
     * gets the number of tokens which are still on the board
     * @return the number of tokens on the board
     */
    public int getNumberOfTokens() {
        return tokens.size();
    }

    /**
     * checks if the board is empty
     * @return a boolean stating whether the board is empty or not
     */
    public boolean isEmpty() {
        return tokens.size() == 0;
    }

    /**
     * gets a token from the board
     * @param index the index of the token on the board
     * @return a Token object
     */
    public Token getToken(int index) {
        return tokens.get(index);
    }

    /**
     * removes a token from the Board
     * @param board the board to remove the token from
     * @param token the token to be removed
     * @return the modified board
     */
    public static Board removeFromBoard(Board board, Token token) {
        board.tokens.remove(token);
        return board;
    }
}
