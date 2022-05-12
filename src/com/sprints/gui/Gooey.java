package com.sprints.gui;

import com.sprints.MusicPlayer;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//all the UI things
class Gooey {
    GameController gc;
    JFrame window;
    Container container;
    public JTextArea messageText;
    JPanel gameTitlePanel;
    JLabel gameTitleLabel;
    JPanel gameInventoryPanel;
    JLabel gameInventoryLabel;

    Font titleFont = new Font("Monospaced", Font.BOLD, 55);
    Font normalFont = new Font("Times New Roman", Font.PLAIN,20);
    Font smallerFont = new Font("Times New Roman", Font.PLAIN, 12 );

    public JPanel bgPanel[] = new JPanel[6]; //we have 6 rooms
    public JLabel bgLabel[] = new JLabel[6];

    public Gooey(GameController gc){
        this.gc =gc;
        createMainField();
        generateScreen();
        window.setVisible(true); //make it so we can see the window

    }

    //methods
    public void createMainField() {
        //create the overall window that holds everything
        window = new JFrame();
        window.setSize(880,575);  //overall size of the window to hold everything
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //lets us close the window
        window.setLayout(null); //custom layout
        container = window.getContentPane();
        container.setBackground(Color.black); //set background color

        //Create a panel to hold the Game's title at top
        gameTitlePanel = new JPanel();
        gameTitlePanel.setBounds(230, 10, 400, 30);  //measured from upper left corner
        gameTitlePanel.setBackground(Color.red);
        container.add(gameTitlePanel);

        //Text for the Game's title
        gameTitleLabel = new JLabel("5Kidnapped!");
        gameTitleLabel.setForeground(Color.black);
        gameTitleLabel.setFont(normalFont);
        gameTitlePanel.add(gameTitleLabel); //add the Label to the title panel

        //Create a panel to hold the 'Inventory' clickable item
        gameInventoryPanel = new JPanel();
        gameInventoryPanel.setBounds(50, 435, 125, 20);
        gameInventoryPanel.setBackground(Color.pink);
        container.add(gameInventoryPanel);

        // Text for the Inventory
        gameInventoryLabel = new JLabel("Inventory");
        gameInventoryLabel.setForeground(Color.black);
        gameInventoryLabel.setFont(smallerFont);
        gameInventoryPanel.add(gameInventoryLabel);



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
    public void createBackground(int bgNum, String bgFileName){
        bgPanel[bgNum] = new JPanel();
        bgPanel[bgNum].setBounds(50,50,750, 375); //size of background
        bgPanel[bgNum].setBackground(null);
        bgPanel[bgNum].setLayout(null);
        container.add(bgPanel[1]);

        bgLabel[bgNum] = new JLabel();
        bgLabel[bgNum].setBounds(0,0,750, 375);

        ImageIcon bgIcon = new ImageIcon(getClass().getClassLoader().getResource(bgFileName));
        bgLabel[bgNum].setIcon(bgIcon);

        bgPanel[bgNum].add(bgLabel[1]);

    }

    public void generateScreen() {
        // Screen 1
        createBackground(1, "./images/Basement2.png");
    }
}