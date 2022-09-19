package com.javarush.task.task25.task2515;

import java.util.List;

public class SpaceShip extends BaseObject {
    private double dx = 0;
    public SpaceShip(double x, double y) {
        super(x, y, 3);
    }

    public void moveLeft() { dx = -1; }

    public void moveRight() { dx = 1; }

    @Override
    public void move() {
        x += dx;
        checkBorders(0, Space.game.getWidth(), 0, Space.game.getHeight());
    }

    @Override
    public void draw(Canvas canvas) {

    }

    public void fire() {
        Rocket r1 = new Rocket(x - 2, y);
        Rocket r2 = new Rocket(x + 2, y);
        List<Rocket> rockets = Space.game.getRockets();
        rockets.add(r1);
        rockets.add(r2);
    }
}
