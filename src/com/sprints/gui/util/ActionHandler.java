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

            //interact with objects //TODO implment clue logic
            case "look sword" : gc.gooey.messageText.setText("Rusted but sharp. Dried blood covers the tip."); break;
            case "get sword" : gc.gooey.messageText.setText("You got the sword!"); break; //TODO add to inventory
            case "look vase" : gc.gooey.messageText.setText("Like everything else in the house this vase is old and time worn. Upon closer investigation you see a clue"); break;
            case "get vase" : gc.gooey.messageText.setText("You got the vase!"); break; //TODO add to inventory

            //change scenes
            case "start":
            case "go basement" :
                gc.sChanger.showScene1(); Player.getInstance().setCurrentRoom("basement"); break;
            case "go parlor" : gc.sChanger.showScene2(); Player.getInstance().setCurrentRoom("parlor"); break;
            case "go kitchen" : gc.sChanger.showScene3(); Player.getInstance().setCurrentRoom("kitchen"); break;
            case "go east hall" : gc.sChanger.showScene4(); Player.getInstance().setCurrentRoom("east hall"); break;
            case "go east room" : gc.sChanger.showScene5(); Player.getInstance().setCurrentRoom("east rooom"); break;
            case "go west hall" : gc.sChanger.showScene6(); Player.getInstance().setCurrentRoom("west hall"); break;
        }
    }
}