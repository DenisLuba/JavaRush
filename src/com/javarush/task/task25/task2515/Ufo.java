package com.javarush.task.task25.task2515;

import java.util.Random;

public class Ufo extends BaseObject {
    private double dx = 0, dy = 0;
    private static int[][] matrix = {
            {0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0},
            {1, 1, 1, 1, 1},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0}
    };

    public Ufo(double x, double y) {
        super(x, y, 3);
    }

    @Override
    public void move() {
        dx = Math.random()*2 - 1;
        x += dx;
        dy = Math.random()*2 - 1;
        y += dy;
        checkBorders(radius, Space.game.getWidth() - radius + 1, radius - 1,  Space.game.getHeight() / 2 - 1 );
        Random random = new Random();
        int probability = random.nextInt(100);
        if (probability < 10) fire();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawMatrix(x - radius + 1, y - radius + 1, matrix, 'U');
    }

    public void fire() {
        Space.game.getBombs().add(new Bomb(x, y + radius));
    }
}
