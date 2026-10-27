package com.perisic.fish.peripherals;

import java.awt.*;



import java.awt.event.*;
import javax.swing.*;


import com.perisic.fish.engine.GameServer;
import com.perisic.fish.engine.Game;

public class GameGUI extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    // UI Components
    private JLabel questArea;
    private JTextField answerField;
    private JLabel messageLabel, timerLabel, scoreLabel;
    private JButton submitButton;
    private Timer gameTimer;
    private JPanel buttonPanel;

    private GameServer gameServer;
    private Game currentGame;
    private int score = 0;
    private int timeLeft = 120;
   

    private final String currentPlayer;

    public GameGUI(String player) {
        super("What is the Missing Value? ");
        this.currentPlayer = player;
        initializeGame();
    }

    private void initializeGame() {
        setSize(1080, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        gameServer = new GameServer();

        JPanel mainPanel = new JPanel(new BorderLayout()) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(
                    0, 0, new Color(100, 150, 255),
                    0, getHeight(), new Color(255, 200, 200)
                );

                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        
        // Create top panel with navbar + game information
        JPanel topPanel = new JPanel(new BorderLayout());

        // Score & Timer Panel
        JPanel gameInfoPanel = new JPanel(new GridLayout(1, 3));
        timerLabel = new JLabel("Time Left: " + timeLeft + "s", JLabel.CENTER);
        scoreLabel = new JLabel("Score: " + score, JLabel.CENTER);
        gameInfoPanel.add(timerLabel);
        gameInfoPanel.add(new JLabel("Count the fish", JLabel.CENTER));
        gameInfoPanel.add(scoreLabel);

        // Add navbar and game info to top panel
        topPanel.add(createNavBar(), BorderLayout.NORTH);
        topPanel.add(gameInfoPanel, BorderLayout.CENTER);

        // Add top panel to main layout
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Center Question Area
        questArea = new JLabel("", JLabel.CENTER);
        questArea.setOpaque(false);
        mainPanel.add(questArea, BorderLayout.CENTER);

        // Bottom panel (answer input)
        JPanel bottomPanel = new JPanel(new FlowLayout());
        answerField = new JTextField(5);
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        messageLabel = new JLabel("");

        bottomPanel.add(new JLabel("Enter your answer: "));
        bottomPanel.add(answerField);
        bottomPanel.add(submitButton);
        bottomPanel.add(messageLabel);

        buttonPanel = createButtonPanel();
        bottomPanel.add(buttonPanel);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        add(mainPanel);

        startTimer();
        loadNextGame();
    }

    /**
     * Creates the navigation bar with action buttons.
     */
    private JPanel createNavBar() {
        JPanel navBar = new JPanel(new FlowLayout(FlowLayout.CENTER));
        navBar.setBackground(new Color(102, 105, 238));
        navBar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        navBar.add(createNavButton("Skip", "Skip"));
        navBar.add(createNavButton("Leaderboard", "Leaderboard"));
        navBar.add(createNavButton("Quit", "Quit"));

        return navBar;
    }

    private JButton createNavButton(String text, String actionCommand) {
        JButton button = new JButton(text);
        button.setActionCommand(actionCommand);
        button.addActionListener(this);
        return button;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.WHITE);

        for (int i = 0; i < 10; i++) {
            JButton btn = new JButton(String.valueOf(i));
            btn.setFont(new Font("Roboto", Font.BOLD, 25));
            btn.setFocusPainted(false);
            btn.setBackground(new Color(200, 230, 250)); // Soft blue
            btn.setForeground(Color.DARK_GRAY);
            btn.setBorder(BorderFactory.createLineBorder(new Color(100, 150, 200), 2));
            btn.setOpaque(true);
            btn.setPreferredSize(new Dimension(60, 60));
            btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            // Hover effect
            btn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    btn.setBackground(new Color(180, 220, 240)); // Lighten on hover
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    btn.setBackground(new Color(200, 230, 250)); // Original soft blue
                }
            });

            // Click event to highlight selected button
            btn.addActionListener(e -> {
                for (Component c : buttonPanel.getComponents()) {
                    if (c instanceof JButton) {
                        ((JButton) c).setBorder(BorderFactory.createLineBorder(new Color(100, 150, 200), 2)); // Reset border
                    }
                }
                btn.setBorder(BorderFactory.createLineBorder(Color.RED, 3)); // Set red border on selected button
                answerField.setText(btn.getText()); // Set answer field text
            });

            buttonPanel.add(btn);
        }
        return buttonPanel;
    }

    private void startTimer() {
        gameTimer = new Timer(1000, e -> {
            timeLeft--;
            timerLabel.setText("Time Left: " + timeLeft + "s");
            if (timeLeft <= 0) {
                gameTimer.stop();
                JOptionPane.showMessageDialog(this, "Time's up! Your final score: " + score);
                
                setVisible(false);
                new MainGUI().setVisible(true);
            }
        });
        gameTimer.start();
    }


    private void loadNextGame() {
        currentGame = gameServer.getRandomGame();
        if (currentGame != null) {
            questArea.setIcon(new ImageIcon(currentGame.getImage()));
            messageLabel.setText("");
        } else {
            questArea.setText("No game available!");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command == null) return;

        switch (command) {
            case "Submit":
                checkAnswer();
                break;
            case "Skip":
                loadNextGame();
                break;
            case "Leaderboard":
                showLeaderboard();
                break;
            case "Quit":
                int option = JOptionPane.showConfirmDialog(this, 
                    "Are you sure you want to quit?", 
                    "Confirm Quit", 
                    JOptionPane.OK_CANCEL_OPTION, 
                    JOptionPane.QUESTION_MESSAGE);
                if (option == JOptionPane.OK_OPTION) {
                    this.setVisible(false);
                    new MainGUI().setVisible(true); 
                }
                break;
            default:
                break;
        }
    }

    private void checkAnswer() {
        try {
            int userAnswer = Integer.parseInt(answerField.getText().trim());
            if (currentGame != null && userAnswer == currentGame.getSolution()) {
                score += 10;
                scoreLabel.setText("Score: " + score);
                messageLabel.setText("✅ Correct! Next question...");

                // Update leaderboard
                
                loadNextGame();
            } else {
                messageLabel.setText("❌ Wrong! Try again.");
            }
        } catch (NumberFormatException ex) {
            messageLabel.setText("⚠ Enter a valid number!");
        }
    }

    private void showLeaderboard() {
        SwingUtilities.invokeLater(() -> {
            new LeaderboardGUI();
        });
    }





    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameGUI("Player1").setVisible(true));
        

    }

}