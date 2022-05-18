package com.sprints.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

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
    JPanel gameSoundPanel;

    Font titleFont = new Font("Monospaced", Font.BOLD, 55);
    Font normalFont = new Font("Times New Roman", Font.PLAIN,20);
    Font smallerFont = new Font("Times New Roman", Font.PLAIN, 12 );

    public JPanel bgPanel[] = new JPanel[8]; //array to hold panels for rooms
    public JLabel bgLabel[] = new JLabel[8];

    Sound sound = new Sound();
    JSlider slider;
    URL soundURL;
    URL playIMG;
    URL stopIMG;
    URL soundIMG;
    URL muteIMG;

    public Gooey(GameController gc){
        this.gc = gc;
        createMainField();
        showWelcome();
        generateScene();
        window.setVisible(true); //make it so we can see the window

    }

    public void playMusic(URL url) {

        sound.setFile(url);
        sound.play();
        sound.loop();
    }

    //methods
    public void createMainField() {
        //create the overall window that holds everything
        window = new JFrame();
        window.setSize(880,575);  //overall size of the window to hold everything
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //lets us close the window
        window.setLayout(null); //custom layout
        container = window.getContentPane();
        container.setBackground(Color.BLACK); //set background color

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
        gameInventoryPanel.setBounds(50, 435, 125, 35);
        gameInventoryPanel.setBackground(Color.pink);
        container.add(gameInventoryPanel);

        // Text for the Inventory
        gameInventoryLabel = new JLabel("Inventory");
        gameInventoryLabel.setForeground(Color.black);
        gameInventoryLabel.setFont(smallerFont);
        gameInventoryPanel.add(gameInventoryLabel);

        // Create a panel to hold the 'Sound' slider & controls
        gameSoundPanel = new JPanel();
        gameSoundPanel.setBounds(15, 150, 30, 200);
        gameSoundPanel.setBackground(Color.BLACK);
        container.add(gameSoundPanel);

        JButton playB = new JButton();
        // play button image
        playIMG = getClass().getResource("/com/sprints/gui/resources/play-32.png");

        try {
            Image imgPlay = ImageIO.read(playIMG);
            Image scaledPlay = imgPlay.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
            playB.setIcon(new ImageIcon(scaledPlay));
            playB.setBackground(Color.red);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        playB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playMusic(soundURL);
            }
        });
        window.add(playB);

        JButton stopB = new JButton();
        // stop button image
        stopIMG = getClass().getResource(("/com/sprints/gui/resources/stop-32.png"));

        try {
            Image imgStop = ImageIO.read(stopIMG);
            Image scaledStop = imgStop.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
            stopB.setIcon(new ImageIcon(scaledStop));
            stopB.setBackground(Color.red);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        stopB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sound.stop();
            }
        });
        window.add(stopB);

        slider = new JSlider(JSlider.VERTICAL, -30, 6, -12);
        slider.setPreferredSize(new Dimension(20, 100));
//        slider.setForeground(Color.red);
        slider.setBackground(Color.BLACK);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                sound.currentVolume = slider.getValue();
                if(sound.currentVolume == -30) {
                    sound.currentVolume = -80;
                }
                System.out.println("volume:" + sound.currentVolume);
                sound.fc.setValue(sound.currentVolume);
            }
        });

        JButton muteB = new JButton();

        muteIMG = getClass().getResource(("/com/sprints/gui/resources/mute-32.png"));

        try {

            Image imgMute = ImageIO.read(muteIMG);
            Image scaledMute = imgMute.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
            muteB.setIcon(new ImageIcon(scaledMute));
            muteB.setBackground(Color.red);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        muteB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sound.volumeMute(slider);
            }
        });
        window.add(muteB);


        window.add(slider);

        //window.pack(); //commented these out to fix gui not loading correct size
        //window.setVisible(true);

        // Sound File
        soundURL = getClass().getResource("/com/sprints/gui/resources/Sound.wav");

        gameSoundPanel.add(playB);
        gameSoundPanel.add(stopB);
        gameSoundPanel.add(muteB);
        gameSoundPanel.add(slider);


        // Code for area to display text at bottom
        messageText = new JTextArea(); //text can go here
        messageText.setBounds(360,475, 400, 150); //resized smaller and placed under image
        messageText.setBackground(Color.black);
        messageText.setForeground(Color.white);
        messageText.setEditable(false); //display text only? or buttons here instead?
        messageText.setLineWrap(true);
        messageText.setWrapStyleWord(true);
        messageText.setFont(new Font("Arial", Font.PLAIN, 12));
        container.add(messageText);
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

    private void createStartButton(int bgNum, int x, int y, int width, int height, String command){

        JPanel startButtonPanel = new JPanel();
        startButtonPanel.setBounds(x,y,width,height);
        startButtonPanel.setBackground(Color.black);

        JButton startButton = new JButton("START");
        startButton.setBackground(Color.white);
        startButton.setForeground(Color.red);
        startButton.addActionListener(gc.aHandler);
        startButton.setActionCommand(command);
        startButtonPanel.add(startButton);
        bgPanel[bgNum].add(startButtonPanel);

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

        //display popup menu with right click on object
        objectLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) { }
            @Override
            public void mousePressed(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)){
                    popUpMenu.show(objectLabel, e.getX(), e.getY()); //current cords (x, y) of mouse cursor
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

    public void showWelcome() {
        // Scene 0 (Start)
        createBackground(0, "images/rooms/welcomeScreen.png");
        createStartButton(0,315,325,120,50,"start");
        bgPanel[0].add(bgLabel[0]);

    }

    public void generateScene() {

//        // Scene 0 (Start) //created a show welcome method instead
//        createBackground(0,"images/welcomeScreen.png");
//        createStartButton(0,315,325,120,50,"start");
//        bgPanel[0].add(bgLabel[0]);

        // Scene 1 (Basement)
        createBackground(1, "images/rooms/basement.png");
        createArrowButton(1, 325, 0, 50, 50, "images/arrows/arrow_up.png", "go parlor"); //arrow needs to be added before label in order for us to see it
        createObject(1,568,248,50,50,"images/objects/note.png","Look","Get","look note","get note");
        createObject(1,325,310,100,100,"images/objects/vaccine.png","Look","","look needle","");
        createObject(1,475,120,100,100, "images/objects/walltorch.png","Look","Get","look torch","get torch");
        bgPanel[1].add(bgLabel[1]); //last thing added to panel goes on the 'bottom'

        // Scene 2 (Parlor)
        createBackground(2, "images/rooms/Parlor.png");
        createArrowButton(2, 325, 0, 50, 50, "images/arrows/arrow_up.png", "go kitchen");
        createArrowButton(2, 325, 325, 50, 50, "images/arrows/arrow_down.png", "go basement");
        createArrowButton(2, 700, 140, 50, 50, "images/arrows/arrow_right.png", "go east hall");
        createArrowButton(2, 0, 140, 50, 50, "images/arrows/arrow_left.png", "go west hall");
        createObject(2,85,47,198,165,"images/objects/streamers1.png","Look","","look streamers","");
        createObject(2,80,265,50,50,"images/objects/candleholders.png","Look","","look candle holders","");
        createObject(2,390,265,317,105,"images/objects/bones.png","Look","","look corpse","");
        createObject(2,360,110,50,65,"images/objects/portrait.png","Look","","look portrait","");
        bgPanel[2].add(bgLabel[2]);

        // Scene 3 (Kitchen)
        createBackground(3, "images/rooms/kitchen.png");
        createArrowButton(3, 325, 325, 50, 50, "images/arrows/arrow_down.png", "go parlor");
        bgPanel[3].add(bgLabel[3]);

        // Scene 4 (East Hall)
        createBackground(4, "images/rooms/eastHall.png");
        createArrowButton(4, 700, 140, 50, 50, "images/arrows/arrow_right.png", "go east room");
        createArrowButton(4, 0, 140, 50, 50, "images/arrows/arrow_left.png", "go parlor");
        bgPanel[4].add(bgLabel[4]);

        // Scene 5 (East Room)
        createBackground(5, "images/rooms/eastRoom.png");
        createObject(5, 520, 165, 100, 100, "images/objects/vase.png", "Look", "Get", "look vase", "get vase");
        createObject(5, 200, 275, 100, 100, "images/objects/sword.png", "Look", "Get", "look sword", "get sword");
        createObject(5, 375, 295, 50, 50, "images/objects/clue.png", "Look", "Get", "look clue2", "get clue2");
        createArrowButton(5, 0, 140, 50, 50, "images/arrows/arrow_left.png", "go east hall");
        bgPanel[5].add(bgLabel[5]);

        //Scene 6 (West Hall)
        createBackground(6, "images/rooms/westHall.png");
        createArrowButton(6, 700, 140, 50, 50, "images/arrows/arrow_right.png", "go parlor");
        createArrowButton(6, 0, 140, 50, 50, "images/arrows/arrow_left.png", "go west room");
        bgPanel[6].add(bgLabel[6]);

        //Scene 7 (West Room)
        createBackground(7, "images/rooms/westRoom.png");
        createArrowButton(7, 700, 140, 50, 50, "images/arrows/arrow_right.png", "go west hall");
        bgPanel[7].add(bgLabel[7]);
    }
}