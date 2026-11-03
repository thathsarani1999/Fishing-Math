package com.perisic.fish.trivia;

import javax.swing.*;
import java.awt.*;

public class TriviaGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private TriviaQuestion triviaQuestion;
    private JButton[] answerButtons;
    private JLabel messageLabel;
    private JButton continueButton;

    public TriviaGUI() {

        super("Fishing Math - Trivia Bonus");

        setSize(1080, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        showLoadingScreen();

        // Fetch the question without freezing the GUI
        SwingWorker<TriviaQuestion, Void> worker =
            new SwingWorker<TriviaQuestion, Void>() {

            @Override
            protected TriviaQuestion doInBackground() throws Exception {
                return TriviaAPI.getRandomQuestion();
            }

            @Override
            protected void done() {

                try {
                    triviaQuestion = get();
                    displayQuestion();

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(
                        TriviaGUI.this,
                        "Unable to load a trivia question.\nPlease check your internet connection.",
                        "Trivia Error",
                        JOptionPane.ERROR_MESSAGE
                    );

                    returnToMainMenu();
                }
            }
        };

        worker.execute();

        setVisible(true);
    }

    private void showLoadingScreen() {

        JLabel loadingLabel = new JLabel(
            "Loading your trivia question...",
            JLabel.CENTER
        );

        loadingLabel.setFont(new Font("Arial", Font.BOLD, 25));

        add(loadingLabel);
    }

    private void displayQuestion() {

        getContentPane().removeAll();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.setBackground(new Color(220, 230, 255));
        mainPanel.setBorder(
            BorderFactory.createEmptyBorder(50, 100, 50, 100)
        );

        JLabel titleLabel = new JLabel(
            "TRIVIA BONUS QUESTION",
            JLabel.CENTER
        );

        titleLabel.setFont(new Font("Verdana", Font.BOLD, 30));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel questionLabel = new JLabel(
            "<html><div style='text-align:center; width:650px;'>"
            + triviaQuestion.getQuestion()
            + "</div></html>",
            JLabel.CENTER
        );

        questionLabel.setFont(new Font("Arial", Font.BOLD, 23));
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(40));
        mainPanel.add(questionLabel);
        mainPanel.add(Box.createVerticalStrut(40));

        answerButtons = new JButton[4];

        java.util.List<String> options = triviaQuestion.getOptions();

        for (int i = 0; i < 4; i++) {

            String answer = options.get(i);

            JButton button = new JButton(answer);

            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.setAlignmentX(Component.CENTER_ALIGNMENT);

            button.setMaximumSize(new Dimension(600, 50));
            button.setPreferredSize(new Dimension(600, 50));

            button.addActionListener(e -> checkAnswer(answer));

            answerButtons[i] = button;

            mainPanel.add(button);
            mainPanel.add(Box.createVerticalStrut(12));
        }

        messageLabel = new JLabel("Choose your answer!");
        messageLabel.setFont(new Font("Arial", Font.BOLD, 20));
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(messageLabel);

        continueButton = new JButton("Back to Main Menu");
        continueButton.setFont(new Font("Arial", Font.BOLD, 18));
        continueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        continueButton.setVisible(false);

        continueButton.addActionListener(e -> returnToMainMenu());

        mainPanel.add(Box.createVerticalStrut(25));
        mainPanel.add(continueButton);

        add(mainPanel);

        revalidate();
        repaint();
    }

    private void checkAnswer(String selectedAnswer) {

        boolean correct = selectedAnswer.equals(
            triviaQuestion.getCorrectAnswer()
        );

        if (correct) {
            messageLabel.setText("Correct! Well done!");
            messageLabel.setForeground(new Color(0, 130, 0));

        } else {
            messageLabel.setText(
                "Wrong! Correct answer: "
                + triviaQuestion.getCorrectAnswer()
            );

            messageLabel.setForeground(Color.RED);
        }

        for (JButton button : answerButtons) {
            button.setEnabled(false);
        }

        continueButton.setVisible(true);
    }

    private void returnToMainMenu() {

        dispose();
        new com.perisic.fish.peripherals.MainGUI().setVisible(true);
    }
}