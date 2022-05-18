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

            //interact with objects
            case "look sword" : gc.gooey.messageText.setText("Rusted but sharp. Dried blood covers the tip."); break;
            case "get sword" : gc.gooey.messageText.setText("You got the sword!"); break; //TODO add to inventory
            case "look vase" : gc.gooey.messageText.setText("Like everything else in the house this vase is old and time worn. Upon closer investigation you see a clue"); break;
            case "get vase" : gc.gooey.messageText.setText("You got the vase!"); break; //TODO add to inventory

            case "look note" : gc.gooey.messageText.setText("Welcome to my game! I've been awaiting a player such as yourself. Sadly, it's still a work in progress,\nbut I'd love to hear what you think...if you survive.\nTwo pieces of friendly advice:\nfind my clues and hurry, hurry, hurry.\nYou have less than 10 minutes before the poison you've been injected you with claims your life. The only\nway to survive is to escape and the only way to escape is to play. I hope you're a fast reader."); break;
            case "get note" : gc.gooey.messageText.setText("You got the note!"); break; //TODO add to inventory
            case "look torch" : gc.gooey.messageText.setText("The flame shows no signs of giving out, it may be useful"); break;
            case "get torch" : gc.gooey.messageText.setText("You got the torch!"); break; //TODO add to inventory
            case "look needle" : gc.gooey.messageText.setText("A small needle protrudes from a crack in the ground where you awoke"); break;

            case "look streamers" : gc.gooey.messageText.setText("Time has not treated these well"); break;
            case "look candle holders" : gc.gooey.messageText.setText("Merely decorative. Covered in webs and dust, they appear to have not been used in some time"); break;
            case "look corpse": gc.gooey.messageText.setText("Looks like they have the same wrist clamp as me...guess they didn't make it out in time.");break;
            case "look portrait": gc.gooey.messageText.setText("Eerily the eyes of the portrait seem to follow you around the room, upon closer evaluation you find clue (1)\"");break;

            case "look clue2" : gc.gooey.messageText.setText("Pale skin and bright red lips curled in a grin can terrify (strikes is)"); break;
            case "get clue2" : gc.gooey.messageText.setText("You got clue 2!"); break;


            //change scenes
            case "start": gc.sChanger.showScene1(); Player.getInstance().setCurrentRoom("basement"); break;
            case "go basement" : gc.sChanger.showScene1(); Player.getInstance().setCurrentRoom("basement"); break;
            case "go parlor" : gc.sChanger.showScene2(); Player.getInstance().setCurrentRoom("parlor"); break;
            case "go kitchen" : gc.sChanger.showScene3(); Player.getInstance().setCurrentRoom("kitchen"); break;
            case "go east hall" : gc.sChanger.showScene4(); Player.getInstance().setCurrentRoom("east hall"); break;
            case "go east room" : gc.sChanger.showScene5(); Player.getInstance().setCurrentRoom("east rooom"); break;
            case "go west hall" : gc.sChanger.showScene6(); Player.getInstance().setCurrentRoom("west hall"); break;
            case "go west room" : gc.sChanger.showScene7(); Player.getInstance().setCurrentRoom("west room"); break;
        }
    }
}