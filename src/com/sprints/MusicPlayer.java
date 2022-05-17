package com.sprints;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MusicPlayer {
    public static Clip clip;
    public static FloatControl fc;
    public static float previousVolume = 6;
    public static float currentVolume = -17;
    private static boolean isSound = true;     // music is ON by default

    // get the audio clip used for sound
    static {
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }


    MusicPlayer() throws LineUnavailableException {
    }


    // start in game music
    static void playSound(String fileName) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        InputStream is = MusicPlayer.class.getResourceAsStream(fileName);
        AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
        clip.open(ais);
        clip.start();
        clip.loop(-1);
    }

    // change in game music audible / non-audible

    public static void soundOn() {
        if (!isSound) {
            clip.start();
            isSound = true;
            // fc.setValue(previousVolume);
        }
    }

    public static void soundOff() {
        if (isSound) {
            clip.stop();
            isSound = false;
           // fc.setValue(currentVolume - 23);
        }
    }

    // lower music volume
    public static void lowerSoundVolume() {
        if(!isSound) {
            System.out.println("It doesn't go lower than off.");
        }
        else {
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            fc.setValue(-20.0f);
        }
    }

    // increase music volume
    public static void raiseSoundVolume() {
        if (!isSound) {
            soundOn();
        }
        fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
        fc.setValue(+6.0f);
    }
}