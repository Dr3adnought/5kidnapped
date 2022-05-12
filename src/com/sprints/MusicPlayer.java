package com.sprints;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

class MusicPlayer {
    static Clip clip;
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

    static void soundOn() {
        if (!isSound) {
            clip.start();
            isSound = true;
        }
    }

    static void soundOff() {
        if (isSound) {
            clip.stop();
            isSound = false;
        }
    }

    // lower music volume
    public static void lowerSoundVolume() {
        if(!isSound) {
            System.out.println("It doesn't go lower than off.");
        }
        else {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-20.0f);
        }
    }

    // increase music volume
    public static void raiseSoundVolume() {
        if (!isSound) {
            soundOn();
        }
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(+6.0f);
    }
}