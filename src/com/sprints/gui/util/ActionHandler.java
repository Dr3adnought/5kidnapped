package com.sprints.gui.util;

import com.sprints.Player;
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

            case "go basement" : gc.sChanger.showScene1(); Player.getInstance().setCurrentRoom("basement"); break;
            case "go parlor" : gc.sChanger.showScene2(); Player.getInstance().setCurrentRoom("parlor"); break;
            case "go kitchen" : gc.sChanger.showScene3(); Player.getInstance().setCurrentRoom("kitchen"); break;
            case "go east hall" : gc.sChanger.showScene4(); Player.getInstance().setCurrentRoom("east hall"); break;
            case "go east room" : gc.sChanger.showScene5(); Player.getInstance().setCurrentRoom("east rooom"); break;
            case "go west hall" : gc.sChanger.showScene6(); Player.getInstance().setCurrentRoom("west hall"); break;
        }
    }
}