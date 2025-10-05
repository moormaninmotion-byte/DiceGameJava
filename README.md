
Pig Dice Game - Java Edition
This is a simple, text-based implementation of the dice game "Pig," written in Java. It's a game of luck and strategy where you face off against a simple computer AI to be the first to reach 100 points.

Game Rules
The goal is to accumulate a score of 100 or more.

On each turn, a player can:

Roll: Roll a standard six-sided die. The number rolled is added to a temporary "turn total." The player can roll again.

Hold: Stop rolling for the turn. The entire "turn total" is added to the player's main score. It is now the next player's turn.

The Catch!
If a player rolls a 1, their turn is a "bust!" The "turn total" is immediately lost (reset to 0), and their turn ends. The main score is unaffected.

How to Play
Prerequisites
Java Development Kit (JDK) installed (version 8 or higher).

Compile and Run
Save the Code: Save the PigDiceGame.java file to your computer.

Open a Terminal: Navigate to the directory where you saved the file.

Compile: Run the Java compiler:

javac PigDiceGame.java

Run: Execute the compiled code:

java PigDiceGame

Follow the on-screen prompts. Enter r to roll the die and h to hold your score.
