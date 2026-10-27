
package com.perisic.fish.peripherals;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LeaderboardGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTable leaderboardTable;

    public LeaderboardGUI() {
        setTitle("Leaderboard");
        setSize(1000, 360);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main Panel with Gradient Background
        JPanel mainPanel = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;

                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(58, 123, 213),
                        getWidth(), getHeight(), new Color(18, 33, 69)
                );

                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Title
        JLabel title = new JLabel("🏆 Leaderboard", JLabel.CENTER);
        title.setFont(new Font("Roboto", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        title.setBorder(new EmptyBorder(10, 10, 20, 10));

        mainPanel.add(title, BorderLayout.NORTH);

        // Sample Leaderboard Data
        String[] columnNames = {"Rank", "Player", "Score"};

        Object[][] data = {
            {1, "OceanMaster", 950},
            {2, "FishHunter", 870},
            {3, "AquaStar", 790},
            {4, "SeaExplorer", 720},
            {5, "WaveRider", 650},
            {6, "CoralKing", 580},
            {7, "BlueFin", 510},
            {8, "ReefRunner", 430},
            {9, "DeepDiver", 360},
            {10, "FishingPro", 290}
        };

        DefaultTableModel model = new DefaultTableModel(
                data, columnNames
        ) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        leaderboardTable = new JTable(model);

        leaderboardTable.setFont(
                new Font("Roboto", Font.PLAIN, 16)
        );

        leaderboardTable.setRowHeight(30);
        leaderboardTable.setShowGrid(false);
        leaderboardTable.setIntercellSpacing(new Dimension(0, 0));
        leaderboardTable.setFillsViewportHeight(true);

        // Table Header Styling
        leaderboardTable.getTableHeader().setFont(
                new Font("Roboto", Font.BOLD, 18)
        );

        leaderboardTable.getTableHeader().setBackground(
                new Color(255, 195, 0)
        );

        leaderboardTable.getTableHeader().setForeground(Color.BLACK);

        // Center Table Content
        DefaultTableCellRenderer centerRenderer =
                new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < leaderboardTable.getColumnCount(); i++) {
            leaderboardTable.getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(centerRenderer);
        }

        // Scroll Pane
        JScrollPane scrollPane = new JScrollPane(leaderboardTable);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LeaderboardGUI::new);
    }
}
