package com.perisic.fish.engine;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Game {
    private BufferedImage image; 
    private int solution;

    /**
     * Constructs a Game object with a BufferedImage and solution.
     *
     * @param image    The visual representation of the game.
     * @param solution The solution or answer for the game.
     * @throws IllegalArgumentException if the image is null or solution is invalid.
     */
    public Game(BufferedImage image, int solution) {
        if (image == null) {
            throw new IllegalArgumentException("Image cannot be null.");
        }
        if (solution < 0) {
            throw new IllegalArgumentException("Solution must be a non-negative integer.");
        }
        this.image = image;
        this.solution = solution;
    }

    /**
     * Gets the image associated with the game.
     *
     * @return The BufferedImage of the game.
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Gets the solution associated with the game.
     *
     * @return The solution as an integer.
     */
    public int getSolution() {
        return solution;
    }

    /**
     * Loads an image from an InputStream.
     *
     * @param inputStream The input stream of the image.
     * @return A BufferedImage object.
     * @throws IOException if the image cannot be read.
     */
    public static BufferedImage loadImage(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException("Input stream cannot be null.");
        }
        return ImageIO.read(inputStream);
    }

    /**
     * Sets a new solution for the game.
     *
     * @param solution The new solution.
     * @throws IllegalArgumentException if the solution is invalid.
     */
    public void setSolution(int solution) {
        if (solution < 0) {
            throw new IllegalArgumentException("Solution must be a non-negative integer.");
        }
        this.solution = solution;
    }

    /**
     * Sets a new image for the game.
     *
     * @param image The new BufferedImage.
     * @throws IllegalArgumentException if the image is null.
     */
    public void setImage(BufferedImage image) {
        if (image == null) {
            throw new IllegalArgumentException("Image cannot be null.");
        }
        this.image = image;
    }
}