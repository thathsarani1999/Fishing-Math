
package com.perisic.fish.peripherals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private JButton startButton, creditsButton, quitButton;

    public MainGUI() {
        super("Main Menu");
        initializeUI();
    }

    private void initializeUI() {
        setSize(1080, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Gradient background matching LoginGUI
        JPanel mainPanel = new JPanel() {
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

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(
            BorderFactory.createEmptyBorder(20, 50, 20, 50)
        );
        mainPanel.setOpaque(false);

        // Title
        JLabel titleLabel = new JLabel(
            "Welcome to the Fishing Math",
            JLabel.CENTER
        );

        titleLabel.setFont(new Font("Verdana", Font.BOLD, 30));
        titleLabel.setForeground(new Color(60, 60, 150));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Mermaid image
        JLabel iconLabel = new JLabel();
        java.net.URL imageURL = getClass().getResource("/Icons/mermaid.png");

        if (imageURL != null) {
            ImageIcon originalIcon = new ImageIcon(imageURL);
            Image scaledImage = originalIcon.getImage().getScaledInstance(
                200, 200, Image.SCALE_SMOOTH
            );
            iconLabel.setIcon(new ImageIcon(scaledImage));
        }

        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Buttons
        startButton = createStyledButton("Start Game");
        creditsButton = createStyledButton("Credits");
        quitButton = createStyledButton("Quit Game");

        startButton.addActionListener(this);
        creditsButton.addActionListener(this);
        quitButton.addActionListener(this);

        // Add components
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(iconLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        mainPanel.add(startButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(creditsButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(quitButton);
        mainPanel.add(Box.createVerticalGlue());

        add(mainPanel);
        setVisible(true);
    }

    // Styled buttons with hover effects
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);

        button.setFont(new Font("SansSerif", Font.BOLD, 30));
        button.setFocusPainted(false);
        button.setBackground(new Color(60, 120, 255));
        button.setForeground(Color.WHITE);

        button.setBorder(BorderFactory.createLineBorder(
            new Color(50, 100, 200), 2
        ));

        button.setOpaque(true);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(200, 50));
        button.setMaximumSize(new Dimension(200, 50));
        button.setMinimumSize(new Dimension(200, 50));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setBackground(new Color(80, 140, 255));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBackground(new Color(60, 120, 255));
            }
        });

        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("Start Game".equals(command)) {
            new GameGUI("Player1").setVisible(true);
            dispose();

        } else if ("Credits".equals(command)) {
            new CreditsGUI().setVisible(true);
            dispose();

        } else if ("Quit Game".equals(command)) {
            int result = JOptionPane.showConfirmDialog(
                this,
                "Return to the login screen?",
                "Confirm",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );

            if (result == JOptionPane.YES_OPTION) {
                new LoginGUI();
                dispose();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainGUI::new);
    }
}
