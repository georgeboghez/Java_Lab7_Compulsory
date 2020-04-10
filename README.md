# Java_Lab7_Compulsory

## Concurrency

Write a program that simulates a positional game, such as the arithmetic progression game, between a given number of players.

At the beginning of the game the board contains n tokens, each token having a distinct value from 1 to m.
Each player extracts tokens successively from the board and must create with them a a complete arithmetic progression of a given sizek.
The game ends when either a player makes a complete arithmetic progression (in this case the winner receives n points and the others 0) or when all tokens have been removed from the board (in this case each player receives a number of points equal to the their largest arithmetic progression).
The players might take turns (or not...) and a time limit might be imposed (or not...).

The main specifications of the application are:

## Compulsory

- Create the class Token. An instance of this class will hold a number from 1 to m. Consider the case when a token may be blank, meaning that it can take the place of any number.
- Create the class Board. An instance of this class will contain n tokens (you may consider the numbers from 1 to n).
- Create the class Player. Each player will have a name. This class will implement the Runnable interface. In the run method the player will repeatedly extract one token from the board.
- Create the Game class. Simulate the game using a thread for each player.
Pay attention to the synchronization of the threads when extracting tokens from the board.

![Screenshot](https://raw.githubusercontent.com/georgeboghez/Java_Lab7_Compulsory/master/Screenshot%20(2).png)
