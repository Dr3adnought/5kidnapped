package com.sprints.gui;

import javax.swing.*;
import java.awt.*;

//all the UI things
class Gooey {
    GameController gc;
    JFrame window;
    Container container;
    public JTextArea messageText;
    JPanel gameTitlePanel;
    JLabel gameTitleLabel;
    Font titleFont = new Font("Monospaced", Font.BOLD, 55);
    Font normalFont = new Font("Times New Roman", Font.PLAIN,20);

    public JPanel bgPanel[] = new JPanel[6]; //we have 6 rooms
    public JLabel bgLabel[] = new JLabel[6];

    public Gooey(GameController gc){
        this.gc =gc;
        createMainField();
        createBackground();
        window.setVisible(true); //make it so we can see the window

    }

    //methods
    public void createMainField() {
        //create the overall window that holds everything
        window = new JFrame();
        window.setSize(800,800);  //overall size of the window to hold everything
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //lets us close the window
        window.setLayout(null); //custom layout
        container = window.getContentPane();
        container.setBackground(Color.black); //set background color

//        //Create a panel to hold the Game's title at top
//        gameTitlePanel = new JPanel();
//        gameTitlePanel.setBounds(400, 10, 400, 30);  //measured from upper left corner
//        gameTitlePanel.setBackground(Color.red);
//        container.add(gameTitlePanel);
//
//        //Text for the Game's title
//        gameTitleLabel = new JLabel("5Kidnapped!");
//        gameTitleLabel.setForeground(Color.black);
//        gameTitleLabel.setFont(normalFont);
//        gameTitlePanel.add(gameTitleLabel); //add the Label to the title panel

//        // Code for the main text display area
//        messageText = new JTextArea("Main Game Text is Displayed Here");
//        messageText.setBounds(35,200, 1100, 400);
//        messageText.setBackground(Color.gray);
//        messageText.setForeground(Color.black);
//        messageText.setEditable(false); //display text only? or buttons here instead?
//        messageText.setLineWrap(true);
//        messageText.setWrapStyleWord(true);
//        messageText.setFont(new Font("Arial", Font.PLAIN, 26));
//        container.add(messageText);
    }
    public void createBackground(){
        bgPanel[1] = new JPanel();
        bgPanel[1].setBounds(0,0,800, 800); //size of background
        bgPanel[1].setBackground(null);
        bgPanel[1].setLayout(null);
        container.add(bgPanel[1]);

        bgLabel[1] = new JLabel();
        bgLabel[1].setBounds(0,0,800, 800);

        ImageIcon bgIcon = new ImageIcon(getClass().getClassLoader().getResource("images/Basement.png"));
        bgLabel[1].setIcon(bgIcon);

        bgPanel[1].add(bgLabel[1]);

    }
}