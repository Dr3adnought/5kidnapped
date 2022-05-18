package com.sprints.gui.util;

import com.sprints.gui.GameController;

public class SceneChanger {

    GameController gc;

    public SceneChanger (GameController gc){
        this.gc = gc;
    };

    //business methods
    public void showScene1(){ //basement
        gc.gooey.bgPanel[0].setVisible(false);
        gc.gooey.bgPanel[1].setVisible(true); //show the basement
        gc.gooey.bgPanel[2].setVisible(false); //don't show the parlor
        gc.gooey.bgPanel[3].setVisible(false);
        gc.gooey.bgPanel[4].setVisible(false);
        gc.gooey.bgPanel[5].setVisible(false);
        gc.gooey.bgPanel[6].setVisible(false);
    }
    public void showScene2(){ //parlor
        gc.gooey.bgPanel[0].setVisible(false);
        gc.gooey.bgPanel[1].setVisible(false); //don't show the basement
        gc.gooey.bgPanel[2].setVisible(true); //show the parlor
        gc.gooey.bgPanel[3].setVisible(false);
        gc.gooey.bgPanel[4].setVisible(false);
        gc.gooey.bgPanel[5].setVisible(false);
        gc.gooey.bgPanel[6].setVisible(false);
    }
    public void showScene3(){ //kitchen
        gc.gooey.bgPanel[0].setVisible(false);
        gc.gooey.bgPanel[1].setVisible(false);
        gc.gooey.bgPanel[2].setVisible(false);
        gc.gooey.bgPanel[3].setVisible(true); //show the kitchen
        gc.gooey.bgPanel[4].setVisible(false);
        gc.gooey.bgPanel[5].setVisible(false);
        gc.gooey.bgPanel[6].setVisible(false);
    }
    public void showScene4(){ // east hall
        gc.gooey.bgPanel[0].setVisible(false);
        gc.gooey.bgPanel[1].setVisible(false);
        gc.gooey.bgPanel[2].setVisible(false);
        gc.gooey.bgPanel[3].setVisible(false);
        gc.gooey.bgPanel[4].setVisible(true); //show the east hall
        gc.gooey.bgPanel[5].setVisible(false);
        gc.gooey.bgPanel[6].setVisible(false);

    }
    public void showScene5(){ // east room
        gc.gooey.bgPanel[0].setVisible(false);
        gc.gooey.bgPanel[1].setVisible(false);
        gc.gooey.bgPanel[2].setVisible(false);
        gc.gooey.bgPanel[3].setVisible(false);
        gc.gooey.bgPanel[4].setVisible(false);
        gc.gooey.bgPanel[5].setVisible(true); //show the east room
        gc.gooey.bgPanel[6].setVisible(false);

    }
    public void showScene6(){ // west hall
        gc.gooey.bgPanel[0].setVisible(false);
        gc.gooey.bgPanel[1].setVisible(false);
        gc.gooey.bgPanel[2].setVisible(false);
        gc.gooey.bgPanel[3].setVisible(false);
        gc.gooey.bgPanel[4].setVisible(false);
        gc.gooey.bgPanel[5].setVisible(false);
        gc.gooey.bgPanel[6].setVisible(true); //show the west hall
    }
}