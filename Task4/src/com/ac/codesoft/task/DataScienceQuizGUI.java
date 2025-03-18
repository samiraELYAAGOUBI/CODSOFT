package com.ac.codesoft.task;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class DataScienceQuizGUI {
    private HashMap<String, List<String>> questions;
    private HashMap<String, String> correctAnswers;
    private Iterator<String> questionIterator;
    private String currentQuestion;
    private Timer timer;
    private JFrame frame;
    private JLabel questionLabel;
    private JLabel timerLabel; // 🔹 Affichage du temps restant
    private JButton[] optionButtons;
    private int score = 0;
    private int timeLeft; // 🔹 Variable pour le compte à rebours

    public DataScienceQuizGUI() {
        DataScienceQuiz quiz = new DataScienceQuiz();
        DataScienceReponses answers = new DataScienceReponses();
        
        questions = quiz.getQuestions();
        correctAnswers = answers.reponses;

        if (questions.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No questions found! Exiting.");
            System.exit(0);
        }

        questionIterator = questions.keySet().iterator();

        frame = new JFrame("Data Science Quiz");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        
        questionLabel = new JLabel("", SwingConstants.CENTER);
        timerLabel = new JLabel("Time left: 60s", SwingConstants.RIGHT); // 🔹 Initialisation du label de temps
        topPanel.add(questionLabel, BorderLayout.CENTER);
        topPanel.add(timerLabel, BorderLayout.EAST);
        
        frame.add(topPanel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1));
        optionButtons = new JButton[4];

        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JButton();
            optionButtons[i].addActionListener(new AnswerListener());
            optionsPanel.add(optionButtons[i]);
        }

        frame.add(optionsPanel, BorderLayout.CENTER);
        frame.setVisible(true);

        showNextQuestion();
    }

    private void showNextQuestion() {
        if (!questionIterator.hasNext()) {
            showResults();
            return;
        }

        currentQuestion = questionIterator.next();
        questionLabel.setText(currentQuestion);
        List<String> options = new ArrayList<>(questions.get(currentQuestion));
        Collections.shuffle(options);

        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText(options.get(i));
            optionButtons[i].setEnabled(true);
        }

        // 🔹 Réinitialisation du temps et affichage du message
        timeLeft = 60;
        timerLabel.setText("Time left: " + timeLeft + "s");

        if (timer != null) {
            timer.cancel();
            timer.purge();
        }

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeLeft--;
                timerLabel.setText("Time left: " + timeLeft + "s");

                if (timeLeft <= 0) {
                    timer.cancel();
                    timerLabel.setText("Time is up! ⏰");
                    for (JButton button : optionButtons) {
                        button.setEnabled(false);
                    }
                    // 🔹 Passage automatique à la prochaine question après 2 secondes
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            showNextQuestion();
                        }
                    }, 2000);
                }
            }
        }, 1000, 1000); // Exécute la tâche chaque seconde (1000 ms)
    }

    private void showResults() {
        JOptionPane.showMessageDialog(frame, "Quiz completed! Your final score: " + score);
        frame.dispose();
    }

    private class AnswerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton) e.getSource();
            String userAnswer = clickedButton.getText();
            String correctAnswer = correctAnswers.getOrDefault(currentQuestion, "Unknown Answer");

            if (userAnswer.equals(correctAnswer)) {
                score++;
            }

            showNextQuestion();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DataScienceQuizGUI::new);
    }
}
