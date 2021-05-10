package com.DamageeZ.Snake;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @Author: DamageeZ
 * @Create: 05-07-2021 19:12
 */
public class Snake {
    Node head, tail;
    Dir dir = Dir.L;
    boolean acc = false;

    Snake() {
        head = new Node(15, 15);
        tail = head;
    }

    public void addToHead() {
        Node n = null;
        switch (dir) {
            case L:
                n = new Node(head.row, head.col - 1);
                break;
            case U:
                n = new Node(head.row - 1, head.col);
                break;
            case R:
                n = new Node(head.row, head.col + 1);
                break;
            case D:
                n = new Node(head.row + 1, head.col);
                break;
        }
        head.prev = n;
        n.next = head;
        head = n;
    }

    public void paint(Graphics g) {
        Node n = head;
        while (n != null) {
            n.paint(g);
            n = n.next;
        }

        if(acc == false)move();
        else {
            move();move();
        }

    }

    private void move() {
        addToHead();
        deleteTail();
        boundaryCheck();
    }

    private void boundaryCheck() {
        if(head.col < 0) head.col = Field.NodeCount-1;
        if(head.row < 0) head.row = Field.NodeCount-1;
        if(head.row > Field.NodeCount-1) head.row = 0;
        if(head.col > Field.NodeCount-1) head.col = 0;
    }

    private void deleteTail() {
        if (tail == null) return;
        tail = tail.prev;
        tail.next.prev = null;
        tail.next = null;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                if(dir != Dir.R) dir = Dir.L;
                break;
            case KeyEvent.VK_UP:
                if(dir != Dir.D) dir = Dir.U;
                break;
            case KeyEvent.VK_RIGHT:
                if(dir != Dir.L) dir = Dir.R;
                break;
            case KeyEvent.VK_DOWN:
                if(dir != Dir.U) dir = Dir.D;
                break;
            case KeyEvent.VK_SHIFT:
                if(acc == false)acc = true;
                else acc = false;
                break;
        }
    }

    public void eat(Egg e) {
        if(head.row == e.row && head.col == e.col) {
            addToHead();
            e.reAppear();
        }
    }
}
