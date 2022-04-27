package com.sprints.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class App {
    public static String currentRoom = "Basement";
    private boolean tutorial = true;
    private boolean gameOver = false;

    Scanner myObj = new Scanner(System.in);

    public void execute() {
        //displays splash screen
        welcome();
        start();
    }

    private void start() {

        // possible start tutorial here where player prompted to look, get, and move
        // standard gave play starts after tutorial portion ends
        tutorial = false;

        while (!gameOver) {
            System.out.println(">");
            String playerCommand = myObj.nextLine(); //changed variable name from "command" to "playerCommand" for better readability

            //if player inputs "quit" it will break out of the while loop and exit the game----
            // we can integrate the "start over" logic with this, if the group decides
            if (playerCommand.equals("quit")) {
                break;
            }

            if (playerCommand.equals("restart")) {
                start();
            }
        }
    }

    private void welcome() {
        //read from txt later
        System.out.println("Kidnapped");
        //read from txt later
        System.out.println("You awake to find yourself in a twisted escape game.\n Can you gather all the clues and escape with your life in tack before time runs out?");


    }

    private void parseInput(List<String> input) {
        String noun;
        String verb;
//        List<String> actions = new ArrayList<>(Arrays.asList("look", "get"));
//        List<String> items = new ArrayList<>(Arrays.asList("note", "torch"));

        if (input.size() > 2) {
            System.out.println("Please enter a two word command");
        } else {
            verb = input.get(0);
            noun = input.get(1);

            JSONParser parser = new JSONParser();
            try {
                FileReader reader = new FileReader(""); //
                FileReader reader2 = new FileReader("");
                JSONObject obj = (JSONObject) parser.parse(reader);
                JSONObject obj2 = (JSONObject) obj.get(verb);
                JSONObject obj3 = (JSONObject) parser.parse(reader2);
                JSONObject obj4 = (JSONObject) obj3.get(noun);
                String syn = (String) obj2.get("synonyms");
                if (!obj.containsKey(verb) || (!obj2.containsValue(syn))) {
                    System.out.println(verb + " is not recognized verb");
                }
                if (!obj3.containsKey(noun)) {
                    System.out.println(noun + " is not a recognized noun!");
                }
            } catch (IOException | ParseException e) {
                System.out.println(e);
            }
        }
    }

        private static void showStatus () {
            System.out.println("---------------------------");
            System.out.println("You are in the" + currentRoom);
            System.out.println("-----------------------------");
        }
    }
