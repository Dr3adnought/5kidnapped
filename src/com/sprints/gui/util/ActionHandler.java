package com.sprints.gui.util;

import com.sprints.TextParser;
import com.sprints.Player;
import com.sprints.gui.EyesOpen;
import com.sprints.gui.GameController;
import com.sprints.gui.Gooey;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static com.sprints.gui.Gooey.bgPanel;

public class ActionHandler implements ActionListener {

    GameController gc;

    public ActionHandler(GameController gc){
        this.gc = gc;
    }

    @Override //may put in event class
    public void actionPerformed(ActionEvent e) {
        String playerChoice = e.getActionCommand();

        try {
            gc.textParser.playerInput(playerChoice);
        } catch (UnsupportedAudioFileException ex) {
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        switch(playerChoice)   {

            //interact with objects
            // east room objects
            case "look sword" :
                gc.gooey.messageText.setText("Rusted but sharp. Dried blood covers the tip.");
                break;
            case "get sword" :
                gc.gooey.messageText.setText("You got the sword!");
                break;
            case "look vase" :
                gc.gooey.messageText.setText("Like everything else in the house this vase is old and time worn. Upon closer investigation you see a clue");
                break;
            case "get vase" :
                gc.gooey.messageText.setText("You got the vase!");
                break; //TODO add to inventory

            // basement objects
            case "look note" :
                gc.gooey.messageText.setText("Welcome to my game! I've been awaiting a player such as yourself. Sadly, it's still a work in progress,\nbut I'd love to hear what you think...if you survive.\nTwo pieces of friendly advice:\nfind my clues and hurry, hurry, hurry.\nYou have less than 10 minutes before the poison you've been injected you with claims your life. The only\nway to survive is to escape and the only way to escape is to play. I hope you're a fast reader.");
                break;
            case "get note" :
                gc.gooey.messageText.setText("You got the note!");
                gc.gooey.inventoryText.setText(Player.getInstance().getCurrentInventory().toString());
                break;
            case "look torch" :
                gc.gooey.messageText.setText("The flame shows no signs of giving out, it may be useful");
                break;
            case "get torch" :
                gc.gooey.messageText.setText("You got the torch!");
                gc.gooey.inventoryText.setText(Player.getInstance().getCurrentInventory().toString());
                break;
            case "look needle" :
                gc.gooey.messageText.setText("A small needle protrudes from a crack in the ground where you awoke");
                break;

            // kitchen objects
            case "look cabinets" :
                gc.gooey.messageText.setText("The cabinets are time worn, hanging loosely by their hinges. The rats appear to have made a meal of them. Upon closer investigation you see a clue (3).");
                break;
            case "get cabinets" :
                gc.gooey.messageText.setText("You'll need some tools to take it off the wall. Maybe worry about escaping instead.");
                break;
            case "look window" :
                gc.gooey.messageText.setText("The window is barred from the outside, There is no way to get out.");
                break;
            case "get window" :
                gc.gooey.messageText.setText("That's going to be too much work.");
                break;
            case "look sink" :
                gc.gooey.messageText.setText("The spoiled remains of food fill the sink.");
                break;
            case "get sink" :
                gc.gooey.messageText.setText("...and how do you propose to do that?");
                break;
            case "look clue3" :
                gc.gooey.messageText.setText("Clue: Reading is fundamental, but WHAT you read is key (2).");
                break;
            case "get clue3" :
                gc.gooey.messageText.setText("Okay, you've added the clue to your inventory.");
                gc.gooey.inventoryText.setText(Player.getInstance().getCurrentInventory().toString());
                break;

            // west hall objects
            case "look bookcase" :
                gc.gooey.messageText.setText("Bookcase: There are many books here. Perhaps you should try to look at the books, or maybe read one? Some appear to have been recently read. Upon closer investigation you see a clue (4)");
                break;
            case "get bookcase" :
                gc.gooey.messageText.setText("You can't carry a bookcase.");
                break;
            case "look Frankenstein" :
                gc.gooey.messageText.setText("Frankenstein (book): The book tells the story of Victor Frankenstein, a Swiss student of natural science who creates an artificial man from pieces of corpses and brings his creature to life.");
                break;
            case "get Frankenstein" :
                gc.gooey.messageText.setText("You took Mary Shelley's 'Frankenstein'.");
                gc.gooey.inventoryText.setText(Player.getInstance().getCurrentInventory().toString());
                break;
            case "look Something Wicked" :
                gc.gooey.messageText.setText("Something Wicked This Way Comes (book): This book is about two 13-year-old best friends, and their nightmarish experience with a traveling carnival that comes to their Midwestern home.");
                break;
            case "get Something Wicked" :
                gc.gooey.messageText.setText("You took Ray Bradbury's 'Something Wicked This Way Comes'.");
                gc.gooey.inventoryText.setText(Player.getInstance().getCurrentInventory().toString());
                break;
            case "look It" :
                gc.gooey.messageText.setText("It (book): The story follows the experiences of seven children as they are terrorized by an evil entity that exploits the fears of its victims to disguise itself while hunting its prey.");
                break;
            case "get It" :
                gc.gooey.messageText.setText("You took Stephen King's 'It'.");
                gc.gooey.inventoryText.setText(Player.getInstance().getCurrentInventory().toString());
                // TODO : CheckWin() from Game Class
                break;
            case "look Reprieve" :
                gc.gooey.messageText.setText("Reprieve (book): Seems to be a novel about otherness, loneliness, racism, and identity wrapped in a gory tale about a full-contact escape room attraction.");
                break;
            case "get Reprieve" :
                gc.gooey.messageText.setText("You took James Ham Mattson's 'Reprieve'.");
                gc.gooey.inventoryText.setText(Player.getInstance().getCurrentInventory().toString());
                break;
            case "look clue4" :
                gc.gooey.messageText.setText("Clue: I'm every nightmare you've ever had (good luck)");
                break;
            case "get clue4" :
                gc.gooey.messageText.setText("You've retrieved this clue #4");
                gc.gooey.inventoryText.setText(Player.getInstance().getCurrentInventory().toString());
                break;

            //parlor objects
            case "look streamers" :
                gc.gooey.messageText.setText("Time has not treated these well");
                break;
            case "look candle holders" :
                gc.gooey.messageText.setText("Merely decorative. Covered in webs and dust, they appear to have not been used in some time");
                break;
            case "look corpse":
                gc.gooey.messageText.setText("Looks like they have the same wrist clamp as me...guess they didn't make it out in time.");
                break;
            case "look portrait":
                gc.gooey.messageText.setText("Eerily the eyes of the portrait seem to follow you around the room");
                break;
            case "look clue1" :
                gc.gooey.messageText.setText("Heavy is the head that wears the crown (you get)");
                break;
            case "get clue1" :
                gc.gooey.messageText.setText("You got clue 1!");
                gc.gooey.inventoryText.setText(Player.getInstance().getCurrentInventory().toString());
                break;

            case "look clue2" :
                gc.gooey.messageText.setText("Pale skin and bright red lips curled in a grin can terrify (strikes is)");
                break;
            case "get clue2" :
                gc.gooey.messageText.setText("You got clue 2!");
                gc.gooey.inventoryText.setText(Player.getInstance().getCurrentInventory().toString());
                break;


            case "start":
                gc.sChanger.switchScene(bgPanel[1]); Player.getInstance().setCurrentRoom("basement");
                CountdownTimer.timer.start();
                EyesOpen eyes = new EyesOpen();
                eyes.eyesOpen();
                break;
            case "go basement" :
                gc.sChanger.switchScene(bgPanel[1]);
                Player.getInstance().setCurrentRoom("basement");
                break;
            case "go parlor" :
                gc.sChanger.switchScene(bgPanel[2]);
                Player.getInstance().setCurrentRoom("parlor");
                break;
            case "go kitchen" :
                gc.sChanger.switchScene(bgPanel[3]);
                Player.getInstance().setCurrentRoom("kitchen");
                break;
            case "go east hall" :
                gc.sChanger.switchScene(bgPanel[4]);
                Player.getInstance().setCurrentRoom("east hall");
                break;
            case "go east room" :
                gc.sChanger.switchScene(bgPanel[5]);
                Player.getInstance().setCurrentRoom("east rooom");
                break;
            case "go west hall" :
                gc.sChanger.switchScene(bgPanel[6]);
                Player.getInstance().setCurrentRoom("west hall");
                break;
            case "go west room" :
                gc.sChanger.switchScene(bgPanel[7]);
                Player.getInstance().setCurrentRoom("west room");
                break;
        }
    }
}