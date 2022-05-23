package com.sprints.gui.util;


import com.sprints.gui.Gooey;

import javax.swing.*;
import java.util.TimerTask;

import static com.sprints.gui.Gooey.bgPanel;
import static com.sprints.gui.util.CountdownTimer.eyes;

public class Task extends TimerTask {
    public void run() {

        eyes.eyesClosed();
        System.exit(0);
        Gooey.window.dispose();
    }
}