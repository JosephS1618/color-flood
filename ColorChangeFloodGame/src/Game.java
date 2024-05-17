import java.util.Scanner;

public class Game {
    private static final int MAX_NUM = 4;
    private static final int MIN_NUM = 1;

    private Scanner input = new Scanner(System.in);
    private int tries;
    private int choice;
    private Integer[][] board;

    public Game() {
        board = new Integer[20][10];
        tries = 0;
        makeBoard();
        playGame();
    }

    private void playGame() {
        while (!checkWin()) {
            printBoard();
            System.out.println("Number of tries: " + tries);
            System.out.println("Choice: ");
            choice = input.nextInt();
            floodBoard(0,0, choice);
            tries++;
        }
    }

    private void floodBoard(int startR, int startC, int choice) {
        int currentNum = board[startR][startC];
        board[startR][startC] = choice;

        // check adj cells
        if (!outOfBounds(startR-1, startC)
                && board[startR-1][startC] == currentNum) { //top
            floodBoard(startR-1, startC, choice);
        } else if (!outOfBounds(startR, startC-1)
                && board[startR][startC-1] == currentNum) { //left
            floodBoard(startR, startC-1, choice);
        } else if (!outOfBounds(startR, startC+1)
                && board[startR][startC+1] == currentNum) { //right
            floodBoard(startR, startC+1, choice);
        } else if (!outOfBounds(startR+1, startC)
                && board[startR+1][startC] == currentNum) { // bottom
            floodBoard(startR+1, startC, choice);
        }

        // Check corner cells
        if (!outOfBounds(startR-1, startC-1)
                && board[startR-1][startC-1] == currentNum) { // top left
            floodBoard(startR-1, startC-1, choice);
        }
        if (!outOfBounds(startR-1, startC+1)
                && board[startR-1][startC+1] == currentNum) { // top right
            floodBoard(startR-1, startC+1, choice);
        }
        if (!outOfBounds(startR+1, startC-1)
                && board[startR+1][startC-1] == currentNum) { // bottom left
            floodBoard(startR+1, startC-1, choice);
        }
        if (!outOfBounds(startR+1, startC+1)
                && board[startR+1][startC+1] == currentNum) { // bottom right
            floodBoard(startR+1, startC+1, choice);
        }
    }

    private boolean outOfBounds(int row, int col) {
        return row < 0 || row >= board.length || col < 0 || col >= board[row].length;
    }

    private boolean checkWin() {
        int reference = board[0][0];

        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                if (board[row][column] != reference) {
                    return false;
                }
            }
        }

        printBoard();
        System.out.println("Win");
        return true;
    }

    private void makeBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                board[row][column] = (int) Math.floor(Math.random() * (MAX_NUM - MIN_NUM + 1) + MIN_NUM);
            }
        }
    }

    private void printBoard() {
        for (Integer[] ints : board) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }




}
