package org.example;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static int player_win = 0;
    static int computer_win = 0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to Tic Tac Toe");

        for (int round = 1; round <= 3; round++) {
            System.out.println("\nRound " + round);

            char[][] board = {
                    {' ', ' ', ' '},
                    {' ', ' ', ' '},
                    {' ', ' ', ' '}};

            while (true) {
                displayBoard(board);
                playerTurn(board, input);
                if (isGameOver(board)) break;

                computerTurn(board);
                if (isGameOver(board)) break;
            }

            if (player_win == 2 || computer_win == 2) {
                break;
            }
        }

        System.out.println("\n===== FINAL RESULT =====");
        System.out.println("You: " + player_win + " | Computer: " + computer_win);

        if (player_win > computer_win)
            System.out.println("The final winner is: YOU!");
        else if (computer_win > player_win)
            System.out.println("The final winner is: COMPUTER!");
        else
            System.out.println("The result is a TIE!");
    }

    public static void displayBoard(char[][] board) {
        for (int i = 0; i < 3; i++) {
            System.out.println(" " + board[i][0] + " | " + board[i][1] + " | " + board[i][2]);
            if (i < 2) {
                System.out.println("-----------");
            }
        }
    }

    public static void playerTurn(char[][] board, Scanner input) {
        int row, col;
        while (true) {
            System.out.print("Enter row (0-2): ");
            row = input.nextInt();
            System.out.print("Enter col (0-2): ");
            col = input.nextInt();

            if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
                board[row][col] = 'X';
                break;
            } else {
                System.out.println("Invalid move, try again.");
            }
        }
    }

    public static void computerTurn(char[][] board) {
        Random rand = new Random();
        int row, col;
        while (true) {
            row = rand.nextInt(3);
            col = rand.nextInt(3);
            if (board[row][col] == ' ') {
                board[row][col] = 'O';
                System.out.println("Computer chose: " + row + " " + col);
                break;
            }
        }
    }

    public static boolean isGameOver(char[][] board) {
        if (checkWinner(board, 'X')) {
            displayBoard(board);
            System.out.println("You won this round!");
            player_win++;
            return true;
        }
        if (checkWinner(board, 'O')) {
            displayBoard(board);
            System.out.println("Computer won this round!");
            computer_win++;
            return true;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') return false;
            }
        }
        displayBoard(board);
        System.out.println("It's a tie!");
        return true;
    }

    public static boolean checkWinner(char[][] board, char symbol) {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) ||
                    (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol))
                return true;
        }
        return (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
                (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol);
    }
}



