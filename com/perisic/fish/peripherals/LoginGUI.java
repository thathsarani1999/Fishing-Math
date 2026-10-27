
package com.perisic.fish.peripherals;

import javax.swing.*;
import java.awt.*;

public class LoginGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private final JButton blogin = new JButton("Login");
    private final JButton bregister = new JButton("Register");

    private final JTextField txuser = new JTextField(15);
    private final JPasswordField pass = new JPasswordField(15);

    private final JLabel titleLabel =
            new JLabel("Welcome to the Fishing Math");

    private final JLabel userLabel = new JLabel("Username:");
    private final JLabel passLabel = new JLabel("Password:");

    public LoginGUI() {
        super("Login Authentication");

        setSize(1080, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Gradient background
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

        // Title and mermaid image
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 30));
        titleLabel.setForeground(new Color(60, 60, 150));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon originalIcon = new ImageIcon("Icons/mermaid.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(
                200, 200, Image.SCALE_SMOOTH
        );

        titleLabel.setIcon(new ImageIcon(scaledImage));
        titleLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        titleLabel.setVerticalTextPosition(SwingConstants.BOTTOM);

        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(20));

        // Username
        userLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        userLabel.setForeground(new Color(60, 60, 150));
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(userLabel);
        panel.add(Box.createVerticalStrut(5));

        styleTextField(txuser);
        panel.add(txuser);
        panel.add(Box.createVerticalStrut(15));

        // Password
        passLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        passLabel.setForeground(new Color(60, 60, 150));
        passLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(passLabel);
        panel.add(Box.createVerticalStrut(15));

        styleTextField(pass);
        panel.add(pass);
        panel.add(Box.createVerticalStrut(10));

        // Login and Register buttons
        JPanel buttonPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 20, 0)
        );
        buttonPanel.setOpaque(false);

        styleButton(blogin);
        styleButton(bregister);

        buttonPanel.add(blogin);
        buttonPanel.add(bregister);

        panel.add(buttonPanel);

        add(panel, BorderLayout.CENTER);

        // Login opens MainGUI
        blogin.addActionListener(e -> {
            MainGUI mainGUI = new MainGUI();
            mainGUI.setVisible(true);
            dispose();
        });

        // Register opens RegisterGUI
        bregister.addActionListener(e -> {
            new RegisterGUI();
            dispose();
        });

        setVisible(true);
    }

    private void styleTextField(JComponent field) {
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        field.setFont(new Font("SansSerif", Font.PLAIN, 16));

        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE, 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        field.setForeground(Color.BLACK);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("SansSerif", Font.BOLD, 30));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(60, 120, 255));
        button.setFocusPainted(false);

        button.setBorder(BorderFactory.createLineBorder(
                new Color(50, 100, 200), 2
        ));

        button.setOpaque(true);
        button.setPreferredSize(new Dimension(250, 60));
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
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginGUI::new);
    }
}
