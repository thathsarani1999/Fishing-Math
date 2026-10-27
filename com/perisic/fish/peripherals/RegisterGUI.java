
package com.perisic.fish.peripherals;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RegisterGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private final JTextField txuser = new JTextField(15);
    private final JPasswordField pass = new JPasswordField(15);
    private final JTextField txemail = new JTextField(15);

    public RegisterGUI() {
        super("Register New Account");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080, 720);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Gradient Background Panel
        JPanel backgroundPanel = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;

                Color startColor = new Color(100, 150, 255);
                Color endColor = new Color(255, 200, 200);

                GradientPaint gradient = new GradientPaint(
                        0, 0, startColor,
                        0, getHeight(), endColor
                );

                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        backgroundPanel.setLayout(
                new BoxLayout(backgroundPanel, BoxLayout.Y_AXIS)
        );

        backgroundPanel.setBorder(
                new EmptyBorder(20, 50, 20, 50)
        );

        // Back Button
        JPanel topPanel = new JPanel(
                new FlowLayout(FlowLayout.RIGHT)
        );
        topPanel.setOpaque(false);

        JButton backToHomeButton = new JButton("Back");
        styleButton(backToHomeButton);

        backToHomeButton.addActionListener(e -> {
            new LoginGUI();
            dispose();
        });

        topPanel.add(backToHomeButton);
        backgroundPanel.add(topPanel);

        // Title
        JLabel titleLabel = new JLabel(
                "Create a New Account",
                JLabel.CENTER
        );

        titleLabel.setFont(new Font("Verdana", Font.BOLD, 30));
        titleLabel.setForeground(new Color(60, 60, 150));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        backgroundPanel.add(titleLabel);
        backgroundPanel.add(Box.createVerticalStrut(30));

        // Form Panel
        JPanel formPanel = new JPanel(
                new GridLayout(6, 1, 10, 10)
        );
        formPanel.setOpaque(false);

        formPanel.add(createLabeledField("Username:", txuser));
        formPanel.add(createLabeledField("Password:", pass));
        formPanel.add(createLabeledField("Email:", txemail));

        // Register Button
        JButton bregister = new JButton("Register");
        styleButton(bregister);
        bregister.setAlignmentX(Component.CENTER_ALIGNMENT);

        // UI demonstration only. No account is created.
        formPanel.add(bregister);

        backgroundPanel.add(formPanel);

        add(backgroundPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    // Label and Input Field
    private JPanel createLabeledField(
            String labelText,
            JComponent field
    ) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Verdana", Font.BOLD, 18));
        label.setForeground(new Color(60, 60, 150));

        panel.add(label, BorderLayout.NORTH);

        field.setPreferredSize(new Dimension(300, 30));
        field.setFont(new Font("SansSerif", Font.PLAIN, 16));

        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE, 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        panel.add(field, BorderLayout.CENTER);

        return panel;
    }

    // Button Styling and Hover Effect
    private void styleButton(JButton button) {
        button.setFont(new Font("SansSerif", Font.BOLD, 30));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(60, 120, 255));
        button.setFocusPainted(false);

        button.setBorder(BorderFactory.createLineBorder(
                new Color(50, 100, 200), 2
        ));

        button.setOpaque(true);
        button.setPreferredSize(new Dimension(200, 50));
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
        SwingUtilities.invokeLater(RegisterGUI::new);
    }
}
