import java.util.Random;
import java.util.Scanner;

/**
 * A simple, single-file console implementation of the dice game "Pig".
 *
 * --- GAME RULES ---
 * - The game is played by two players: one human and one computer.
 * - The goal is to be the first player to reach 100 points.
 * - On your turn, you can "roll" a six-sided die as many times as you want.
 * - The results of each roll are added to your "turn total".
 * - If you decide to "hold", your turn total is added to your main score, and it becomes the next player's turn.
 * - HOWEVER, if you roll a 1, your turn total is lost (reset to 0), and your turn ends immediately.
 */
public class PigDiceGame {

    // --- Class Members ---
    private static final int WINNING_SCORE = 100;
    private final Random dieRoller;
    private static final int COMPUTER_HOLD_THRESHOLD = 20;
    private static final int TURN_PAUSE_MS = 1000;
    private final Scanner scanner;

    private int humanScore;

    private int computerScore;
    private boolean isHumanTurn;

    // --- Constructor ---
    public PigDiceGame() {
        this.dieRoller = new Random();
        this.scanner = new Scanner(System.in);
        this.humanScore = 0;
        this.computerScore = 0;
        // Human player always starts first
        this.isHumanTurn = true;
    }

    /**
     * The main method to start and run the game.
     */
    public static void main(String[] args) {
        PigDiceGame game = new PigDiceGame();
        game.play();
    }

    /**
     * Contains the main game loop, which continues until a player wins.
     */
    public void play() {
        printWelcomeMessage();

        while (humanScore < WINNING_SCORE && computerScore < WINNING_SCORE) {
            printCurrentScores();

            if (isHumanTurn) {
                humanTurn();
            } else {
                computerTurn();
            }
            // Switch turns after a turn is completed
            isHumanTurn = !isHumanTurn;
            System.out.println("\n-------------------------------------------------\n");
        }

        printFinalWinner();
        scanner.close();
    }

    /**
     * Handles the logic for a single human player's turn.
     */
    private void humanTurn() {
        System.out.println("It's your turn!");
        int turnTotal = 0;
        boolean turnOver = false;

        while (!turnOver) {
            System.out.print("Enter 'r' to roll or 'h' to hold: ");
            String input = scanner.nextLine().trim().toLowerCase();

             if (input.isEmpty()) {
                System.out.println("Invalid input. Please enter 'r' or 'h'.");
                continue;
            }
            char choice = input.charAt(0);

            switch (choice) {
                case 'r':
                    int roll = rollDie();
                    System.out.println("You rolled a " + roll);
                    if (roll == 1) {
                        System.out.println("Bust! You lose your turn total.");
                        turnOver = true; // Turn ends
                    } else {
                        turnTotal += roll;
                        System.out.println("Your turn total is now: " + turnTotal);
                        System.out.println("Your total score would be: " + (humanScore + turnTotal));
                    }
                    break;
                case 'h':
                    humanScore += turnTotal;
                    System.out.println("You hold. Your turn is over.");
                    turnOver = true;

               break;
               default:
                System.out.println("Invalid input. Please enter 'r' or 'h'.");
            }
        }
    }

    /**
     * Handles the logic for the computer's turn.
     * The AI strategy is simple: it will try to score at least 20 points
     * on its turn before holding.
     */
    private void computerTurn() {
        System.out.println("It's the computer's turn!");
        int turnTotal = 0;
        while (true) {
            // Simple AI: keep rolling until turn total is 20 or more
            if (turnTotal < COMPUTER_HOLD_THRESHOLD && (computerScore + turnTotal) < WINNING_SCORE) {
                pause(TURN_PAUSE_MS);
                int roll = rollDie();
                System.out.println("Computer rolled a " + roll);

                if (roll == 1) {
                    System.out.println("Bust! Computer loses its turn total.");
                    break; // End turn
                } else {
                    turnTotal += roll;
                    System.out.println("Computer's turn total is now: " + turnTotal);
                }
            } else {
                computerScore += turnTotal;
                System.out.println("Computer holds. Its turn is over.");
                break; // End turn
            }
        }
    }

    /**
     * Simulates rolling a six-sided die.
     * @return An integer between 1 and 6.
     */
    private int rollDie() {
        return dieRoller.nextInt(6) + 1;
    }

    /**
     * Pauses the execution for a given number of milliseconds.
     * @param milliseconds The time to pause in ms.
     */
    private void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // --- Printing Methods ---
    private void printWelcomeMessage() {
        System.out.println("=========================================");
        System.out.println("=== Welcome to the Game of Pig! ===");
        System.out.println("=========================================");
        System.out.println("The first player to reach 100 points wins.");
    }

    private void printCurrentScores() {
        System.out.println("Current Scores:");
        System.out.println("  - You: " + humanScore);
        System.out.println("  - Computer: " + computerScore);
    }

    private void printFinalWinner() {
        printCurrentScores();
        if (humanScore >= WINNING_SCORE) {
            System.out.println("Congratulations! You won!");
        } else {
            System.out.println("The computer won. Better luck next time!");
        }
    }
}
