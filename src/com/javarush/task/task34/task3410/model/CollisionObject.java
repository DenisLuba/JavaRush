package com.javarush.task.task34.task3410.model;

public abstract class CollisionObject extends GameObject {

    private int x, y;

    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction) {
        int X = gameObject.getX();
        int Y = gameObject.getY();
        switch (direction) {
            case LEFT: if (X == x - 1 && Y == y) return true;
            case RIGHT: if (X == x + 1 && Y == y) return true;
            case UP: if (X == x && Y == y - 1) return true;
            case DOWN: if (X == x && Y == y + 1) return true;
        }
        return false;
    }
}
