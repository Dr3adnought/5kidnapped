package com.sprints.gui.util;

import com.sprints.gui.GameController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionHandler implements ActionListener {

    GameController gc;

    public ActionHandler(GameController gc){
        this.gc = gc;
    }

    @Override //may put in event class
    public void actionPerformed(ActionEvent e) {
        String playerChoice = e.getActionCommand();

        switch(playerChoice)   {
            //case "go" : gc.event.goSomewhere(); break;
            //case "look": gc.event.lookObject(); break;

            //change scenes
            case "go parlor" : gc.sChanger.showScene2(); break;
            case "go basement" : gc.sChanger.showScene1(); break;

        }
    }
}