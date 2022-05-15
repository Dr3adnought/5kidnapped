package com.sprints.gui;

import com.sprints.MusicPlayer;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//all the UI things
public class Gooey {
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
        this.gc = gc;
        createMainField();
        generateScene();
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
//        messageText.setBounds(35,200, 1100, 400); (TODO need to resize smaller and adjust placement if we use this)
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
        container.add(bgPanel[bgNum]);

        bgLabel[bgNum] = new JLabel();
        bgLabel[bgNum].setBounds(0,0,750, 375);

        ImageIcon bgIcon = new ImageIcon(getClass().getClassLoader().getResource(bgFileName));
        bgLabel[bgNum].setIcon(bgIcon);



    }
    public void createArrowButton(int bgNum, int x, int y, int width, int height, String arrowFileName, String command){
        ImageIcon arrowIcon = new ImageIcon(getClass().getClassLoader().getResource(arrowFileName));

        JButton arrowButton = new JButton();
        arrowButton.setBounds(x, y, width, height); //place arrow in correct location on background
        arrowButton.setBackground(null);
        arrowButton.setContentAreaFilled(false);
        arrowButton.setFocusPainted(false);
        arrowButton.setIcon(arrowIcon);
        arrowButton.addActionListener(gc.aHandler);
        arrowButton.setActionCommand(command);
        arrowButton.setBorderPainted(false);

        bgPanel[bgNum].add(arrowButton);

    }
    //may not need this method depending on how we do objects??
    public void createObject(int bgNum, int xObj, int yObj, int widthObj,  int heightObj, String objName,
                             String choice1, String choice2, String choice1Command, String choice2Command){
        //create popupmenu
        JPopupMenu popUpMenu = new JPopupMenu();
        //create popup menu items
        JMenuItem menuItem[] = new JMenuItem[3]; //number of choices
        menuItem[1] = new JMenuItem(choice1);
        menuItem[1].addActionListener(gc.aHandler);
        menuItem[1].setActionCommand(choice1Command); //look
        popUpMenu.add(menuItem[1]);

        menuItem[2] = new JMenuItem(choice2);
        menuItem[2].addActionListener(gc.aHandler);
        menuItem[2].setActionCommand(choice2Command); //get
        popUpMenu.add(menuItem[2]);

        //create objects
        JLabel objectLabel = new JLabel();
        objectLabel.setBounds(xObj, yObj, widthObj, heightObj);

        ImageIcon objectIcon = new ImageIcon(getClass().getClassLoader().getResource(objName));
        objectLabel.setIcon(objectIcon);

        objectLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) { }
            @Override
            public void mousePressed(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)){
                    popUpMenu.show(objectLabel, e.getX(), e.getY());
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) { }
            @Override
            public void mouseExited(MouseEvent e) { }
        });

        bgPanel[bgNum].add(objectLabel);
        bgPanel[bgNum].add(bgLabel[bgNum]);
    }


    public void generateScene() {

        // Scene 1 (Basement)
        createBackground(1, "images/basement.png");
        createArrowButton(1, 325, 0, 50, 50, "images/arrow_up.png", "go parlor"); //arrow needs to be added before label in order for us to see it
        bgPanel[1].add(bgLabel[1]); //last thing added to panel goes on the 'bottom'

        // Scene 2 (Parlour)
        createBackground(2, "images/parlor.png");
        createArrowButton(2, 325, 325, 50, 50, "images/arrow_down.png", "go basement"); //arrow needs to be added before label in order for us to see it
        bgPanel[2].add(bgLabel[2]); //last thing added to panel goes on the 'bottom'

    }
}