package ro.uaic.info.georgeboghez;


public class Game {

    /**
     * creates two new threads t1 and t2 having player1 and player2 as targets (given the fact that they implement Runnable)
     * while the board is not empty each player will take a token from the board and it will be checked if he has won
     * @param numberOfTokens
     * @param nameOfPlayer1
     * @param nameOfPlayer2
     * @param size
     * @throws InterruptedException
     */
    public Game(int numberOfTokens, String nameOfPlayer1, String nameOfPlayer2, int size) throws InterruptedException {
        Board board = Board.getInstance(numberOfTokens);
        Player player1 = new Player(nameOfPlayer1);
        Player player2 = new Player(nameOfPlayer2);
        int sizeOfTheArithProgr = size;

        Thread t1 = new Thread(player1);
        Thread t2 = new Thread(player2);

        t1.start();
        t2.start();

        System.out.println("Jocul Incepe!");
        int roundCounter = 0;
        while (!player1.isBoardEmpty()) {
            System.out.println("Round " + ++roundCounter);
            Token token1;
            Token token2;

            if(player1.getToken()) {
                if(player1.checkWinner(sizeOfTheArithProgr)) {
                    printTokensOfPlayer(player1);
                    printTokensOfPlayer(player2);
                    System.out.println(player1.getName() + " Has Won");
                    break;
                }
            }

            if(player2.getToken()) {
                if(player2.checkWinner(sizeOfTheArithProgr)){
                    printTokensOfPlayer(player1);
                    printTokensOfPlayer(player2);
                    System.out.println(player2.getName() + " Has Won");
                    break;
                }
            }

            System.out.println();
            printTokensOfPlayer(player1);
            printTokensOfPlayer(player2);
            System.out.println();
        }
        t1.join();
        t2.join();
    }

    /**
     * prints all the tokens a player has
     * @param player a Player object
     */
    public void printTokensOfPlayer(Player player) {
        System.out.print(player.getName() + " tokens: ");
        for (Token token : player.getTokensInHand()) {
            System.out.print(token.getNumber() + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        try {
            Game game = new Game( 100, "PLAYER A", "PLAYER B", 3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
