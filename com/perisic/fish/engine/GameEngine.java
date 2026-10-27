package com.perisic.fish.engine;

import java.awt.image.BufferedImage;

import com.perisic.fish.engine.Game;
import com.perisic.fish.engine.GameServer;

/**
 * GameEngine class to manage game logic, player scores, and game progression.
 */
public class GameEngine {
    private String playerName; 
    private int score = 0;
    private Game currentGame = null; 
    private final GameServer gameServer; 

    /**
     * Constructs a GameEngine for the given player.
     *
     * @param playerName The name of the player.
     * @throws IllegalArgumentException if the playerName is null or empty.
     */
    public GameEngine(String playerName) {
        if (playerName == null || playerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Player name cannot be null or empty.");
        }
        this.playerName = playerName;
        this.gameServer = new GameServer();
    }

    /**
     * Retrieves the next game and returns its associated image.
     *
     * @return The BufferedImage of the next game.
     */
    public BufferedImage getNextGameImage() {
        currentGame = gameServer.getRandomGame();
        return currentGame.getImage();
    }

    /**
     * Retrieves the next game and wraps its image in a JLabel-compatible format.
     *
     * @return The BufferedImage of the next game.
     */
    public BufferedImage nextGame() {
        return getNextGameImage();
    }

    /**
     * Checks if the provided solution is correct for the current game.
     *
     * @param solution The player's solution to check.
     * @return true if the solution is correct, false otherwise.
     * @throws IllegalStateException if there is no current game loaded.
     */
    public boolean checkSolution(int solution) {
        if (currentGame == null) {
            throw new IllegalStateException("No game is currently loaded.");
        }
        boolean isCorrect = solution == currentGame.getSolution();
        if (isCorrect) {
            score++;
        }
        return isCorrect;
    }

    /**
     * Gets the current score of the player.
     *
     * @return The player's score as an integer.
     */
    public int getScore() {
        return score;
    }

    /**
     * Gets the name of the player.
     *
     * @return The player's name as a string.
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Sets a new name for the player.
     *
     * @param playerName The new player name.
     * @throws IllegalArgumentException if the playerName is null or empty.
     */
    public void setPlayerName(String playerName) {
        if (playerName == null || playerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Player name cannot be null or empty.");
        }
        this.playerName = playerName;
    }
}