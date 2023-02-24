package com.javarush.task.task34.task3410.model;

import static com.javarush.task.task34.task3410.model.Model.FIELD_CELL_SIZE;

public abstract class CollisionObject extends GameObject {

    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction) {
        int gameObjectX = gameObject.getX();
        int gameObjectY = gameObject.getY();
        return switch (direction) {
            case LEFT -> gameObjectX == x - FIELD_CELL_SIZE && gameObjectY == y;
            case RIGHT -> gameObjectX == x + FIELD_CELL_SIZE && gameObjectY == y;
            case UP -> gameObjectX == x && gameObjectY == y - FIELD_CELL_SIZE;
            case DOWN -> gameObjectX == x && gameObjectY == y + FIELD_CELL_SIZE;
        };
    }
}
