package com.perisic.fish.engine;

import javax.sound.sampled.*;


import java.io.File;
import java.io.IOException;

public class MusicPlayer {
    private static MusicPlayer instance;  
    private Clip clip;

    private MusicPlayer(String filePath) {
        try {
            File audioFile = new File(filePath);
            if (!audioFile.exists()) {
                System.out.println("File not found: " + filePath);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static MusicPlayer getInstance(String filePath) {
        if (instance == null) {
            instance = new MusicPlayer(filePath);
        }
        return instance;
    }

    public void playMusic(boolean loop) {
        if (clip != null && !clip.isRunning()) {  
            clip.start();
            if (loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            System.out.println("background music is playing");
        } else {
            System.out.println("background music is playing already");
        }
    }

    public void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            System.out.println("background music has stopped.");
        }
    }
}
