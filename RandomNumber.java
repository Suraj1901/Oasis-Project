import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

class GuessTheNumber extends JFrame {
    private int generatedNumber;
    private int attemptsLeft;
    private JTextField guessField;
    private JButton guessButton;
    private JLabel messageLabel;
    private JLabel attemptsLabel;
    private JButton restartButton;
    private int score;

    public GuessTheNumber() {
        super("Guess the Number");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null); // Center the frame on the screen

        generatedNumber = generateRandomNumber();
        attemptsLeft = 5;
        score = 0;

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        messageLabel = new JLabel("Guess a number between 1 and 100:");
        panel.add(messageLabel);

        guessField = new JTextField(10);
        panel.add(guessField);

        guessButton = new JButton("Guess");
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });
        panel.add(guessButton);

        attemptsLabel = new JLabel("Attempts left: " + attemptsLeft);
        panel.add(attemptsLabel);

        restartButton = new JButton("Restart");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });
        restartButton.setEnabled(false); // Enable only when the game is over
        panel.add(restartButton);

        add(panel);
    }

    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(100) + 1; // Generates a random number between 1 and 100
    }

    private void checkGuess() {
        String guessText = guessField.getText();
        if (!guessText.isEmpty()) {
            int guess = Integer.parseInt(guessText);
            if (guess == generatedNumber) {
                messageLabel.setText("Congratulations! You guessed the number.");
                score += attemptsLeft * 10; // Increase score based on attempts left
                restartButton.setEnabled(true);
                guessButton.setEnabled(false);
            } else {
                attemptsLeft--;
                if (attemptsLeft == 0) {
                    messageLabel.setText("Sorry, you have run out of attempts. The number was: " + generatedNumber);
                    restartButton.setEnabled(true);
                    guessButton.setEnabled(false);
                } else {
                    messageLabel.setText(guess + " is " + (guess < generatedNumber ? "too low." : "too high.") +
                            " Guess again.");
                    attemptsLabel.setText("Attempts left: " + attemptsLeft);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please enter a number.");
        }
        guessField.setText("");
    }

    private void restartGame() {
        generatedNumber = generateRandomNumber();
        attemptsLeft = 5;
        messageLabel.setText("Guess a number between 1 and 100:");
        attemptsLabel.setText("Attempts left: " + attemptsLeft);
        guessButton.setEnabled(true);
        restartButton.setEnabled(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GuessTheNumber().setVisible(true);
            }
        });
    }
}
