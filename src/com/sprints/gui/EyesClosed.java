package com.sprints.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class EyesClosed extends JPanel implements ActionListener {

    Timer alphaTimer = new Timer(20, this);
    BufferedImage bImage;
    float alphaValue = 0f;

    public void eyesClosed() {

        JLabel black = new JLabel();
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("images/room/black.png"));
        black.setIcon(icon);
        this.add(black);

        loadBufferedImage();
        alphaTimer.start();

    }

    public void loadBufferedImage() {

        bImage = null;

        try {
            bImage = ImageIO.read(getClass().getClassLoader().getResource("images/rooms/black.png"));

        }catch(IOException e) {

        }
    }

    public void paint(Graphics g) {

        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
        g2d.drawImage(bImage, 50, 20, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        alphaValue = alphaValue + 0.01f;

        if(alphaValue < 1) {
            alphaValue = 1;
            alphaTimer.stop();
        }

        repaint();

    }
}