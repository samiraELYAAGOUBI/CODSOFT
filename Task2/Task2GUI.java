package task.codesoft.com.Task2GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Task2GUI {
    public static void main(String[] args) {
        // Création de la fenêtre principale
        JFrame frame = new JFrame("Calcul de Moyenne Pondérée");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JLabel lblNbrSubjects = new JLabel("Nombre de matières:");
        JTextField txtNbrSubjects = new JTextField(5);
        JButton btnSubmit = new JButton("Valider");
        JTextArea txtResult = new JTextArea(12, 40);
        txtResult.setEditable(false);

        frame.add(lblNbrSubjects);
        frame.add(txtNbrSubjects);
        frame.add(btnSubmit);
        frame.add(new JScrollPane(txtResult));

        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int nbrSubject = Integer.parseInt(txtNbrSubjects.getText());
                    double sumMarks = 0, sumCoefficients = 0;
                    StringBuilder details = new StringBuilder();

                    for (int i = 1; i <= nbrSubject; i++) {
                        String subjectName = JOptionPane.showInputDialog("Nom de la matière " + i + " :");
                        double marks;
                        int coefficient;

                        do {
                            String inputMarks = JOptionPane.showInputDialog("Note pour " + subjectName + " (max 100) :");
                            marks = Double.parseDouble(inputMarks);
                        } while (marks > 100);

                        do {
                            String inputCoefficient = JOptionPane.showInputDialog("Coefficient pour " + subjectName + " :");
                            coefficient = Integer.parseInt(inputCoefficient);
                        } while (coefficient <= 0);

                        sumMarks += marks * coefficient;
                        sumCoefficients += coefficient;
                        details.append(subjectName).append(" : Note = ").append(marks).append(", Coeff = ").append(coefficient).append("\n");
                    }

                    double avgMarks = sumMarks / sumCoefficients;
                    String appreciation;

                    if (avgMarks >= 90) {
                        appreciation = "Excellent";
                    } else if (avgMarks >= 80) {
                        appreciation = "Très bien";
                    } else if (avgMarks >= 70) {
                        appreciation = "Bien";
                    } else if (avgMarks >= 60) {
                        appreciation = "Assez bien";
                    } else if (avgMarks >= 50) {
                        appreciation = "Passable";
                    } else if (avgMarks >= 40) {
                        appreciation = "Faible";
                    } else {
                        appreciation = "Échec";
                    }

                    txtResult.setText("Détails des notes :\n" + details +
                            "\nMoyenne pondérée : " + String.format("%.2f", avgMarks) +
                            "\nAppréciation : " + appreciation);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Veuillez entrer un nombre valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.setVisible(true);
    }
}
