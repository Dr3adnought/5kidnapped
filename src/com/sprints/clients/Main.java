package com.sprints.clients;

import com.sprints.controller.App;
import com.sprints.gui.GameController;
import org.json.simple.parser.ParseException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;


class Main {
    public static void main(String[] args) throws IOException, InterruptedException, java.text.ParseException, UnsupportedAudioFileException, LineUnavailableException {
        new GameController(); //call game controller to launch our GUI/window
        App app = new App();
        app.execute();
    }
}