import java.util.*;

public class MineSweeper {
    // Class Variables
    public static int rowNumber;
    public static int colNumber;
    public int mineNum;
    private char[][] board;
    private boolean[][] mines;
    private boolean[][] revealedCells;

    // Constructor
    public MineSweeper(int rowNumber, int colNumber) {
        this.rowNumber = rowNumber;
        this.colNumber = colNumber;
        mineNum = (rowNumber * colNumber) / 4;
        boardInitialization();
        mineInitialization(mineNum);
        revealedCells = new boolean[rowNumber][colNumber];
    }

    // Separator String
    String separator = "============================";

    // Initialize the Game Board
    public void boardInitialization() {
        board = new char[rowNumber][colNumber];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = '-';
            }
        }
    }

    // Print the Game Board
    public void printBoard() {
        System.out.println(separator);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Print the locations of mines (for debugging purposes)
    public void printMines() {
        System.out.println("Mine Locations:");
        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < colNumber; j++) {
                if (mines[i][j]) {
                    System.out.print("* ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }

    // Get User Input for row and column entries
    public static int[] gettingUserInput() {
        Scanner scanner = new Scanner(System.in);
        int[] move = new int[2];
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Enter row: ");
            move[0] = scanner.nextInt();
            System.out.print("Enter column: ");
            move[1] = scanner.nextInt();
            validInput = (move[0] >= 0 && move[0] < rowNumber) && (move[1] >= 0 && move[1] < colNumber);
            if (!validInput) {
                System.out.println("Invalid input. Please enter a row and column within the bounds of the board.");
            }
        }
        return move;
    }

    // Initialize mines randomly on the Game Board
    public void mineInitialization(int mineNum) {
        mines = new boolean[rowNumber][colNumber];
        Random random = new Random();
        int placedMines = 0;
        while (placedMines < mineNum) {
            int row = random.nextInt(rowNumber);
            int col = random.nextInt(colNumber);
            if (!mines[row][col]) {
                mines[row][col] = true;
                placedMines++;
            }
        }
    }

    // Start the Game
    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        boolean isGameOver = false;
        int win = 0, loss = 0;
        System.out.println("Welcome to MineSweeper Game!");
        printBoard();
        while (!isGameOver) {
            //printBoard();
            int[] move = gettingUserInput();
            int row = move[0];
            int col = move[1];
            if (mines[row][col]) {
                System.out.println("Game Over! You lost!");
                loss += 1;
                isGameOver = true;
            } else {
                if (revealedCells[row][col]) {
                    System.out.println("This cell has already been revealed. Please enter a different cell.");
                    continue;
                }
                revealedCells[row][col] = true;
                int mineCount = countMines(row, col);
                board[row][col] = (char) (mineCount + '0');
                printBoard();
                if (checkWin()) {
                    System.out.println("Congratulations! You won the game!");
                    win += 1;
                    isGameOver = true;
                }
            }
        }
        System.out.println(separator);
        if ((win == 1 )|| (loss == 1)) {
            System.out.println("Do you want to play again (y/n)?");
            String playAgain = scanner.nextLine();
            playAgain(playAgain);
        }
    }

    // Play Again Based on User Decision
    public void playAgain(String decision) {
        if (decision.equalsIgnoreCase("y")) {
            resetGame();
            printMines();
            playGame();
        } else {
            System.out.println("Exiting the game...");
        }
    }

    // Count adjacent mines for a given cell
    public int countMines(int row, int col) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if ((i >= 0) && (i < rowNumber) && (j >= 0) && (j < colNumber) && (mines[i][j])) {
                    count++;
                }
            }
        }
        return count;
    }

    // Check if all non-mine cell have been revealed (win condition)
    public boolean checkWin() {
        int revealedCellsCount = 0;
        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < colNumber; j++) {
                if (board[i][j] != '-') {
                    revealedCellsCount++;
                }
            }
        }
        return revealedCellsCount == (rowNumber * colNumber) - mineNum;
    }

    // Reset the Game for playing again
    public void resetGame() {
        boardInitialization();
        mineInitialization(mineNum);
        revealedCells = new boolean[rowNumber][colNumber];
    }
}
