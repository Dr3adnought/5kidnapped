package com.sprints;

public class Restart {
    private static boolean restart = false;

    public static boolean getRestart(){
        return restart;
    }

    public static void setRestart(boolean restart){
        Restart.restart = restart;
    }
}
