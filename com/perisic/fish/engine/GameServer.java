package com.perisic.fish.engine;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.imageio.ImageIO;

/**
 * GameServer class to fetch game data from an external API and create Game objects.
 */
public class GameServer {
    private static final String API_URL = "https://marcconrad.com/uob/fish/api.php?out=csv&base64=yes";




    /**
     * Reads the content of the URL as a string.
     *
     * @param urlString The URL to fetch data from.
     * @return The content of the URL as a string.
     * @throws IOException if an error occurs during reading.
     */
    private static String readUrl(String urlString) throws IOException {
        try (@SuppressWarnings("deprecation")
		InputStream inputStream = new URL(urlString).openStream();
             ByteArrayOutputStream result = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            return result.toString(StandardCharsets.UTF_8);
        }
    }

    /**
     * Fetches a random game from the API and creates a Game object.
     *
     * @return A new Game object with the image and solution.
     * @throws IOException           if an error occurs while fetching or processing the data.
     * @throws IllegalStateException if the API response is invalid or incomplete.
     */
    public Game getRandomGame() {
        try {
            // Fetch raw data from the API
            String rawData = readUrl(API_URL);
            if (rawData == null || !rawData.contains(",")) {
                throw new IllegalStateException("Invalid API response: " + rawData);
            }

            // Split the data and decode the image
            String[] data = rawData.split(",");
            byte[] decodedImage = Base64.getDecoder().decode(data[0]);
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedImage));

            // Parse the solution
            int solution = Integer.parseInt(data[1].trim());

            // Return the Game object
            return new Game(image, solution);

        } catch (IOException e) {
            throw new RuntimeException("Error while fetching or decoding game data: " + e.getMessage(), e);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new IllegalStateException("Malformed API response: " + e.getMessage(), e);
        }
    }
}