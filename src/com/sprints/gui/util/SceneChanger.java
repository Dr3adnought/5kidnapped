package com.sprints.gui.util;

import com.sprints.gui.GameController;

public class SceneChanger {

    GameController gc;

    public SceneChanger (GameController gc){
        this.gc = gc;
    };

    //business methods
    public void showScene1(){
        gc.gooey.bgPanel[1].setVisible(true); //show the basement
        gc.gooey.bgPanel[2].setVisible(false); //don't show the parlor
    }
    public void showScene2(){
        gc.gooey.bgPanel[1].setVisible(false); //don't show the basement
        gc.gooey.bgPanel[2].setVisible(true); //show the parlor
    }
}