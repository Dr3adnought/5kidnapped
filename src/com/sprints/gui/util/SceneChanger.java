package com.sprints.gui.util;

import com.sprints.gui.GameController;

import javax.swing.*;

import static com.sprints.gui.Gooey.layeredPane;

public class SceneChanger {

    GameController gc;


    public SceneChanger (GameController gc){
        this.gc = gc;
    };

    //business methods
    public void switchScene(JPanel p){
        layeredPane.removeAll();
        layeredPane.add(p);
        layeredPane.repaint();
        layeredPane.revalidate();
    }

}