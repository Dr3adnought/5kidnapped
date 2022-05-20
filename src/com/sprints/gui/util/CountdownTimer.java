package com.sprints.gui.util;

import com.sprints.gui.EyesClosed;
import com.sprints.gui.GameController;
import com.sprints.gui.Gooey;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import static com.sprints.gui.EyesClosed.*;

public class CountdownTimer {

    public static Timer timer;
    static int second;
    static int minute;
    static DecimalFormat dFormat = new DecimalFormat("00");
    static String ddSecond;
    static String ddMinute;
    static EyesClosed eyes = new EyesClosed();

    public static void countdownTimer(GameController gc) {

        second = 0;
        minute = 10;

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                second--;
                ddSecond = dFormat.format(second);
                ddMinute = dFormat.format(minute);
                Gooey.gameTimerLabel.setText(ddMinute + ":" + ddSecond);

                if(second==-1) {
                    second = 59;
                    minute--;
                    ddSecond = dFormat.format(second);
                    ddMinute = dFormat.format(minute);
                    Gooey.gameTimerLabel.setText(ddMinute + ":" + ddSecond);
                }
                if(minute==0 && second==0) {
                    timer.stop();
                    gc.gooey.messageText.setText("\"Your body begins to stiffen and agony takes the name of each breath. Your world fades to black as you fall to the ground...");
                    eyes.eyesClosed();
                }

            }
        });
    }
}