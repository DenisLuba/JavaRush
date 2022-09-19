package com.javarush.task.task25.task2515;

import javax.swing.plaf.PanelUI;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Space {
    public static Space game;
    private int width;
    private int height;
    private SpaceShip ship;
    private List<Ufo> ufos = new ArrayList<>();
    private List<Rocket> rockets = new ArrayList<>();
    private List<Bomb> bombs = new ArrayList<>();

    public Space(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public static void main(String[] args) {
        game = new Space(20, 20);
        game.setShip(new SpaceShip(10, 18));
        game.run();
    }

    public void run() {
        Canvas canvas = new Canvas(width, height);

        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();

        while (ship.isAlive()) {
            if (keyboardObserver.hasKeyEvents()) {
                KeyEvent event = keyboardObserver.getEventFromTop();
                System.out.println(event.getKeyCode());
                if (event.getKeyCode() == KeyEvent.VK_LEFT)
                    ship.moveLeft();
                else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
                    ship.moveRight();
                else if (event.getKeyCode() == KeyEvent.VK_SPACE)
                    ship.fire();
            }

            moveAllItems();

            checkBombs();
            checkRockets();
            removeDead();
            createUfo();
            canvas.clear();
            draw(canvas);
            canvas.print();
            Space.sleep(300);
        }
        System.out.println("Game Over!");
    }

    public void moveAllItems() {
        for (BaseObject baseObject : getAllItems())
            baseObject.move();
    }

    public List<BaseObject> getAllItems() {
        List<BaseObject> baseObjects = new ArrayList<>();
        baseObjects.addAll(Space.game.getBombs());
        baseObjects.addAll(Space.game.getUfos());
        baseObjects.addAll(Space.game.getRockets());
        baseObjects.add(Space.game.getShip());
        return baseObjects;
    }

    public void createUfo() {

    }

    public void checkBombs() {

    }

    public void checkRockets() {

    }

    public void removeDead() {

    }

    public void draw(Canvas canvas) {}

    public static void sleep(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException ignored) {}
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public SpaceShip getShip() {
        return ship;
    }

    public List<Ufo> getUfos() {
        return ufos;
    }

    public List<Rocket> getRockets() {
        return rockets;
    }

    public List<Bomb> getBombs() {
        return bombs;
    }

    public void setShip(SpaceShip ship) {
        this.ship = ship;
    }


}
