package com.DamageeZ.Snake;

import java.awt.*;
import java.util.Random;

/**
 * @Author: DamageeZ
 * @Create: 05-07-2021 20:23
 */
public class Egg {
    int row, col;
    Random r = new Random();

    public Egg(int row, int col) {
        this.row = row;
        this.col = col;
    }


    public void paint(Graphics g) {
        int x = Field.x + col * Field.NodeSize;
        int y = Field.x + row * Field.NodeSize;

        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x, y, Field.NodeSize, Field.NodeSize);
        g.setColor(c);
    }

    public void reAppear() {
        this.row = r.nextInt(Field.NodeCount);
        this.col = r.nextInt(Field.NodeCount);
    }
}
