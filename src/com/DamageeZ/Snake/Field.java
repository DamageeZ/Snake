package com.DamageeZ.Snake;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @Author: DamageeZ
 * @Create: 05-07-2021 19:13
 */
public class Field extends Frame {
    public static final int NodeSize = 15;
    public static final int NodeCount = 30;

    public static final int AreaSize = NodeSize*NodeCount;
    public static int x = AreaSize/2;
    public static int y = AreaSize/2;

    Egg e = new Egg(10,10);
    Snake s = new Snake();

    public static void main(String[] args) {
        new Field();
    }

    Field() {
        this.setSize(2*AreaSize,2*AreaSize);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                s.keyPressed(e);
            }
        });
        while(true) {
            try {
                Thread.sleep(70);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.repaint();
        }
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0,0,this.getWidth(),this.getHeight());

        g.setColor(c);
        for (int i =0;i<=NodeCount;i++) {
            g.drawLine(x,y+NodeSize*i,x+AreaSize,y+NodeSize*i);
            g.drawLine(x+NodeSize*i,y,x+NodeSize*i,y+AreaSize);
        }
        e.paint(g);
        s.paint(g);

        s.eat(e);
        
    }

    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if(offScreenImage == null) {
            offScreenImage = this.createImage(this.getWidth(),this.getHeight());
        }
        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage,0,0,null);
    }
}
