import java.awt.Font;
import java.awt.GridLayout;
import java.util.Random;
import javax.swing.*;

public class GuessTheNumberGame {
    public static void main(String[] args) {
        // Game settings
        int min = 1;
        int max = 100;
        int attemptsAllowed = 10;
        int score = 0;
        int rounds = 3;  // Number of rounds in the game

        // Create an instance of Random to generate numbers
        Random rand = new Random();

        // Loop for multiple rounds
        for (int round = 1; round <= rounds; round++) {
            int numberToGuess = rand.nextInt(max - min + 1) + min;  // Random number between min and max
            int attemptsLeft = attemptsAllowed;
            boolean correctGuess = false;

            // Start a new round
            showMessage("Round " + round + ": Guess the number between " + min + " and " + max + "!");
            
            // Start guessing loop
            while (attemptsLeft > 0 && !correctGuess) {
                String userInput = showInputDialog("Attempt " + (attemptsAllowed - attemptsLeft + 1) + ": Enter your guess (1-100):");
                
                // Check if the user closed the dialog or pressed cancel
                if (userInput == null) {
                    showMessage("You exited the game. Goodbye!");
                    System.exit(0);  // Exit the application
                }

                // Check if input is valid
                if (userInput.isEmpty()) {
                    showMessage("Please enter a valid number!");
                    continue;
                }

                int userGuess;
                try {
                    userGuess = Integer.parseInt(userInput);
                } catch (NumberFormatException e) {
                    showMessage("Invalid input. Please enter a number.");
                    continue;
                }

                // Check if the guess is within the valid range
                if (userGuess < min || userGuess > max) {
                    showMessage("Please guess a number between " + min + " and " + max + ".");
                    continue;
                }

                // Compare the guess with the generated number
                if (userGuess < numberToGuess) {
                    showMessage("Too low! You have " + (attemptsLeft - 1) + " attempts left.");
                } else if (userGuess > numberToGuess) {
                    showMessage("Too high! You have " + (attemptsLeft - 1) + " attempts left.");
                } else {
                    showMessage("Congratulations! You guessed the number!");
                    correctGuess = true;
                    score += (attemptsLeft * 10);  // Add points based on remaining attempts
                }

                attemptsLeft--;
            }

            // If user runs out of attempts
            if (!correctGuess) {
                showMessage("Sorry, you ran out of attempts. The correct number was: " + numberToGuess);
            }

            // Display score after each round
            showMessage("Round " + round + " complete! Your current score: " + score);
        }

        // Final score after all rounds
        showMessage("Game Over! Your total score is: " + score);
        System.exit(0);  // Close the application after the game ends
    }

    // Method to show a message dialog with larger font and vertical layout
    private static void showMessage(String message) {
        JPanel panel = new JPanel(new GridLayout(0, 1));  // Vertically arranged components
        JLabel label = new JLabel("<html><div style='text-align: center;'>" + message + "</div></html>");
        label.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(label);

        JOptionPane.showMessageDialog(null, panel, "Guess The Number Game", JOptionPane.INFORMATION_MESSAGE);
    }

    // Method to show an input dialog with a custom "GUESS" button and larger size
    private static String showInputDialog(String message) {
        JPanel panel = new JPanel(new GridLayout(0, 1));  // Vertically arranged components
        JLabel label = new JLabel("<html><div style='text-align: center;'>" + message + "</div></html>");
        label.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(label);

        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(textField);

        int option = JOptionPane.showOptionDialog(
            null,
            panel,
            "Guess The Number Game",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            new String[]{"GUESS"},
            "GUESS"
        );

        if (option == JOptionPane.CLOSED_OPTION) {
            return null;  // User clicked the close button
        }

        return textField.getText();
    }
}

