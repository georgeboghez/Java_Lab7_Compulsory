package ro.uaic.info.georgeboghez;

/**
 * An instance of this class will hold a number from 1 to m
 */
public class Token {
    private static int id = 0;
    private int number;

    public Token(int number) {
        this.number = number;
        id++;
    }

    public static int getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
