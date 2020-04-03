package ro.uaic.info.georgeboghez;

import java.util.*;

/**
 *  This class implements the Runnable interface. In the run method the player will repeatedly extract one token from the board.
 */
public class Player implements Runnable {
    private String name;
    private Stack<Token> tokensInHand;
    /**
     * the unique Board object
     */
    private static Board board = Board.getInstance();
    private int position;

    public Player(String name) {
        this.name = name;
        this.tokensInHand = new Stack<Token>();
    }

    /**
     * gets the name of the player
     * @return a string representing the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * sets the name of the player
     * @param name a string representing the player's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets the tokens
     * @return a Stack of Token objects
     */
    public Stack<Token> getTokensInHand() {
        return tokensInHand;
    }

    /**
     * sets the tokens
     * @param tokensInHand a Stack of Token objects
     */
    public void setTokensInHand(Stack<Token> tokensInHand) {
        this.tokensInHand = tokensInHand;
    }

    /**
     * checks wheter the board is empty or not
     * @return
     */
    public boolean isBoardEmpty() {
        return board.isEmpty();
    }

    /**
     * the implemented method run from Runnable which will wait (making the thread to give up the lock and go to sleep until
     * other thread enters the monitor and calls notify, then reacquiring the lock and continuing the execution) to be notified, then
     * it will generate the position of the Token from the board and will notify the other threads about it
     */
    @Override
    public synchronized void run() {
        while (!board.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (board.getNumberOfTokens() != 0) {
                position = new Random().nextInt(board.getNumberOfTokens());
            }
            System.out.println(name + " ... position was generated: " + position);
            notify();
        }
    }

    /**
     * firstly, this will allow the thread other to generate the position of the token on the board (token
     * which will be taken by the player), then wait for it to be generated and then removing it from the board and
     * adding it to the stack of tokens each player has
     * @return
     * @throws InterruptedException
     */
    public synchronized boolean getToken() throws InterruptedException {
        if (!isBoardEmpty()) {
            System.out.println(name + " ... now can generate the position of the token");
            notify();
            System.out.println(name + " ... wait until the position is generated");
            wait();

            Token token = board.getToken(position);
            board = Board.removeFromBoard(board, token);
            tokensInHand.add(token);
            System.out.println(name + " ... token found: " + token.getNumber());
            return true;
        }
        return false;
    }

    /**
     * checks if the last k tokens have created an arithmetic progression (I tried other versions with a list and a set,
     * but I got to this implementation eventually) by getting the first k elements from the stack (last k added) and checking if the middle
     * elements are equal to the mean of its neighbours.
     * @param k the size of the progression
     * @return a boolean stating if the player has won the game
     * @throws InterruptedException
     */
    public synchronized boolean checkWinner(int k) throws InterruptedException {
        if (tokensInHand.size() < k) {
            return false;
        }

        if (k <= 2) {
            return true;
        }

        Stack<Token> stack = (Stack<Token>) tokensInHand.clone();

        Token token1 = stack.peek();
        stack.pop();
        Token token2 = stack.peek();
        stack.pop();

        for (int i = 0; i < k - 2; i++) {
            Token token3 = stack.peek();
            if((double)token2.getNumber() != (double)(token3.getNumber() + token1.getNumber()) / 2.0) {
                return false;
            }
            token1 = token2;
            token2 = token3;
        }

        return true;
    }
}
