package com.DamageeZ.Snake;

import java.awt.*;

/**
 * @Author: DamageeZ
 * @Create: 05-07-2021 19:12
 */
public class Node {
    int row,col;
    Node prev,next;

    public Node(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void paint(Graphics g) {
        int x = Field.x + col*Field.NodeSize;
        int y = Field.x + row*Field.NodeSize;

        Color c = g.getColor();
        if(prev == null) g.setColor(Color.GREEN);
        else if(next == null) g.setColor(Color.ORANGE);
        else g.setColor(Color.YELLOW);
        g.fillRect(x,y,Field.NodeSize,Field.NodeSize);
        g.setColor(c);
    }
}
