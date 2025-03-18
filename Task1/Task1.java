package com.ac.codesoft.task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

interface Game {
    void startGame();
}

public class Task1 extends JFrame implements Game {
    private Random random = new Random();
    private int targetNumber;
    private int attemptsLeft;
    private final int MAX_ATTEMPTS = 10;
    private int roundsWon = 0; //  Compteur de manches gagnées

    // Composants de l'interface graphique
    private JTextField guessField;
    private JLabel messageLabel;
    private JButton checkButton;
    private JButton replayButton;
    private JButton quitButton; //  Nouveau bouton pour quitter

    // Constructeur
    public Task1() {
        // Configuration de la fenêtre
        setTitle("Guess the Number Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        // Champ de saisie pour entrer le nombre
        guessField = new JTextField();
        add(new JLabel("Enter your guess (1-100):"));
        add(guessField);

        // Label pour afficher les messages
        messageLabel = new JLabel("", SwingConstants.CENTER);
        add(messageLabel);

        // Bouton "Vérifier"
        checkButton = new JButton("Check");
        add(checkButton);

        // Bouton "Rejouer" (invisible au début)
        replayButton = new JButton("Play Again");
        replayButton.setVisible(false);
        add(replayButton);

        //  Bouton "Quitter" (invisible au début)
        quitButton = new JButton("Quit");
        quitButton.setVisible(false);
        add(quitButton);

        // Action du bouton "Vérifier"
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        // Action du bouton "Rejouer"
        replayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        // Action du bouton "Quitter"
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Game Over! Rounds won: " + roundsWon);
                System.exit(0);
            }
        });

        //  Maintenant que `messageLabel` est initialisé, on peut appeler `startGame()`
        startGame();

        setVisible(true);
    }

    // Démarrer une nouvelle partie
    @Override
    public void startGame() {
        targetNumber = random.nextInt(100) + 1; // Génère un nombre entre 1 et 100
        attemptsLeft = MAX_ATTEMPTS;
        messageLabel.setText("You have " + MAX_ATTEMPTS + " attempts left.");
        guessField.setText("");
        guessField.setEnabled(true);
        checkButton.setEnabled(true);
        replayButton.setVisible(false);
        quitButton.setVisible(false); //  Cacher le bouton "Quitter" au début
    }

    // Vérifier la tentative de l'utilisateur
    private void checkGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText());

            if (guess < 1 || guess > 100) {
                messageLabel.setText("Enter a number between 1 and 100!");
                return;
            }

            attemptsLeft--;

            if (guess == targetNumber) {
                messageLabel.setText("Correct! The number was " + targetNumber + ".");
                roundsWon++; //  Incrémenter les manches gagnées
                endGame();
            } else if (guess < targetNumber) {
                messageLabel.setText("Too low! Attempts left: " + attemptsLeft);
            } else {
                messageLabel.setText("Too high! Attempts left: " + attemptsLeft);
            }

            if (attemptsLeft == 0) {
                messageLabel.setText("Game Over! The number was " + targetNumber + ".");
                endGame();
            }

        } catch (NumberFormatException e) {
            messageLabel.setText("Invalid input! Enter a number.");
        }
    }

    // Fin du jeu, désactiver les entrées et afficher "Rejouer" et "Quitter"
    private void endGame() {
        guessField.setEnabled(false);
        checkButton.setEnabled(false);
        replayButton.setVisible(true);
        quitButton.setVisible(true); // Afficher "Quitter"
    }

    // Méthode principale
    public static void main(String[] args) {
        new Task1();
    }
}
