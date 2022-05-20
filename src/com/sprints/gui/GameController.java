package com.sprints.gui;

import com.sprints.OurJSONParser;
import com.sprints.TextParser;
import com.sprints.gui.util.ActionHandler;
import com.sprints.gui.util.Event;
import com.sprints.gui.util.SceneChanger;


public class GameController {


    ActionHandler aHandler = new ActionHandler(this);
    public Gooey gooey = new Gooey(this);
    public SceneChanger sChanger = new SceneChanger(this);
    public Event event = new Event(this);
    public TextParser textParser = new TextParser(this);

    public GameController(){
        //empty ctor

    }

}