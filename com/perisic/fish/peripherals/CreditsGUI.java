
package com.perisic.fish.peripherals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CreditsGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    public CreditsGUI() {
        super("Credits");
        initializeUI();
    }

    private void initializeUI() {
        setSize(1080, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Gradient background matching LoginGUI and MainGUI
        JPanel panel = new JPanel() {
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

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(
            BorderFactory.createEmptyBorder(20, 50, 20, 50)
        );
        panel.setOpaque(false);

        // Title
        JLabel titleLabel = new JLabel("Credits");
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 36));
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

        // Credit text
        JLabel creditsText1 = new JLabel(
            "Created by Thathsarani",
            JLabel.CENTER
        );
        creditsText1.setFont(new Font("Verdana", Font.BOLD, 30));
        creditsText1.setForeground(new Color(60, 60, 150));
        creditsText1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel creditsText2 = new JLabel(
            "UOB ID: 2625674",
            JLabel.CENTER
        );
        creditsText2.setFont(new Font("Verdana", Font.BOLD, 30));
        creditsText2.setForeground(new Color(60, 60, 150));
        creditsText2.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("SansSerif", Font.BOLD, 30));
        backButton.setFocusPainted(false);
        backButton.setBackground(new Color(60, 120, 255));
        backButton.setForeground(Color.WHITE);

        backButton.setBorder(BorderFactory.createLineBorder(
            new Color(50, 100, 200), 2
        ));

        backButton.setOpaque(true);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setPreferredSize(new Dimension(250, 60));
        backButton.setMaximumSize(new Dimension(250, 60));
        backButton.setMinimumSize(new Dimension(250, 60));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Button hover effect
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                backButton.setBackground(new Color(80, 140, 255));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                backButton.setBackground(new Color(60, 120, 255));
            }
        });

        // Return to MainGUI
        backButton.addActionListener(this::onBackButtonClicked);

        // Add components
        panel.add(Box.createVerticalGlue());
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(iconLabel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(creditsText1);
        panel.add(Box.createVerticalStrut(10));
        panel.add(creditsText2);
        panel.add(Box.createVerticalStrut(25));
        panel.add(backButton);
        panel.add(Box.createVerticalGlue());

        add(panel);
        setVisible(true);
    }

    private void onBackButtonClicked(ActionEvent e) {
        new MainGUI();
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CreditsGUI::new);
    }
}
