package com.javarush.task.task34.task3410.model;

import com.javarush.task.task34.task3410.controller.EventListener;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Model {
    public static final int FIELD_CELL_SIZE = 20;
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel;
    private final LevelLoader levelLoader;
    private int width, height;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public Model() {
        currentLevel = 1;
        try {
            levelLoader = new LevelLoader(Paths.get(Objects.requireNonNull(Model.class.getResource("../res/levels.txt")).toURI()));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
        width = levelLoader.getWidth() * FIELD_CELL_SIZE + FIELD_CELL_SIZE;
        height = levelLoader.getHeight() * FIELD_CELL_SIZE + 2 * FIELD_CELL_SIZE;
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        restartLevel(++currentLevel == 61 ? 1 : currentLevel);
    }

    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();
        if (checkWallCollision(player, direction)
                || checkBoxCollisionAndMoveIfAvailable(direction)) return;

        int x = getCoordinatesOfTheNextCell(player, direction)[0];
        int y = getCoordinatesOfTheNextCell(player, direction)[1];

        if ((x - FIELD_CELL_SIZE / 2) < 0
                || (x + FIELD_CELL_SIZE / 2) >= width
                || (y - FIELD_CELL_SIZE / 2) < 0
                || (y + FIELD_CELL_SIZE / 2) >= height) return;

        player.move(x - player.getX(), y - player.getY());

        checkCompletion();
    }

    public void checkCompletion() {
        boolean isCompleted = gameObjects.getHomes().stream()
                .allMatch(home
                        -> gameObjects.getBoxes().stream()
                                .anyMatch(box -> box.getX() == home.getX() && box.getY() == home.getY()));

        if (isCompleted) eventListener.levelCompleted(currentLevel);
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        return gameObjects.getWalls().stream()
                .anyMatch(wall -> gameObject.isCollision(wall, direction));
    }

    public boolean checkBoxCollisionAndMoveIfAvailable(Direction direction) {

        Player player = gameObjects.getPlayer();
        int purposeX = getCoordinatesOfTheNextCell(player, direction)[0];
        int purposeY = getCoordinatesOfTheNextCell(player, direction)[1];

        GameObject objectOnTheWay = getObjectByCoordinates(purposeX, purposeY);

        return objectOnTheWay instanceof Box && ifNextObjectIsBox((Box) objectOnTheWay, direction);
    }

//    ********************************************************************************************

//    вспомогательный метод для метода checkBoxCollisionAndMoveIfAvailable(Direction direction)
//    возвращает координаты следующей ячейки по направлению движения объекта из параметров
    private int[] getCoordinatesOfTheNextCell(GameObject object, Direction direction) {

        int objectX = object.getX();
        int objectY = object.getY();

        switch (direction) {
            case LEFT -> objectX -= FIELD_CELL_SIZE;
            case RIGHT -> objectX += FIELD_CELL_SIZE;
            case UP -> objectY -= FIELD_CELL_SIZE;
            case DOWN -> objectY += FIELD_CELL_SIZE;
        }

        return new int[]{objectX, objectY};
    }

//    вспомогательный метод для метода checkBoxCollisionAndMoveIfAvailable(Direction direction)
//    возвращает объект по координатам:
//    если это граница игрового поля, то вернется стена (Wall);
//    если - пустая клетка, то вернется null;
//    иначе - GameObject
    private GameObject getObjectByCoordinates(int x, int y) {

        if ((x - FIELD_CELL_SIZE / 2) < 0
                || (x + FIELD_CELL_SIZE / 2) >= width
                || (y - FIELD_CELL_SIZE / 2) < 0
                || (y + FIELD_CELL_SIZE / 2) >= height) return new Wall(x, y);

        List<GameObject> objects = gameObjects.getAll().stream()
                .filter(object ->
                        object.getX() == x &&
                                object.getY() == y)
                .toList();

        if (objects.size() == 2)
            return objects.get(0) instanceof Box ? objects.get(0) : objects.get(1);

        return objects.isEmpty() ? null : objects.get(0);
    }

//    вспомогательный метод для метода checkBoxCollisionAndMoveIfAvailable(Direction direction)
//    если на пути у игрока коробка, то он проверяет, может ли ее подвинуть, и, по возможности, двигает
    private boolean ifNextObjectIsBox(Box box, Direction direction) {

        int purposeX = box.getX();
        int purposeY = box.getY();
        int nextX = getCoordinatesOfTheNextCell(box, direction)[0];
        int nextY = getCoordinatesOfTheNextCell(box, direction)[1];

        GameObject nextObject = getObjectByCoordinates(nextX, nextY);

        if (nextObject instanceof Home || nextObject == null) {
            box.move(nextX - purposeX, nextY - purposeY);
            return false;
        }
        return true;
    }

    public void setAllBoxesInHomes() {
        List<Home> homes = gameObjects.getHomes().stream().toList();
        List<Box> boxes = gameObjects.getBoxes().stream().toList();

        for(int i = 0; i < homes.size(); i ++) {
            boxes.get(i).setX(homes.get(i).getX());
            boxes.get(i).setY(homes.get(i).getY());
        }
    }
}
