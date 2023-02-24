package com.javarush.task.task34.task3410.controller;

import com.javarush.task.task34.task3410.model.Direction;
import com.javarush.task.task34.task3410.model.GameObjects;
import com.javarush.task.task34.task3410.model.Model;
import com.javarush.task.task34.task3410.view.View;

public class Controller implements EventListener {

    private View view;
    private Model model;
    private int level;

    public Controller() {
        model = new Model();
        view = new View(this);
        model.restart();
        view.setWidth(model.getWidth());
        view.setHeight(model.getHeight());
        view.setLevel(model.getCurrentLevel());
        view.init();
        model.setEventListener(this);
        view.setEventListener(this);
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
    }



    public GameObjects getGameObjects() {
        return model.getGameObjects();
    }

    @Override
    public void move(Direction direction) {
        model.move(direction);
        view.update();
    }

    @Override
    public void restart() {
        model.restart();
        view.update();
    }

    @Override
    public void startNextLevel() {
        model.startNextLevel();
        view.setWidth(model.getWidth());
        view.setHeight(model.getHeight());
        view.setLevel(model.getCurrentLevel());
        view.update();
    }

    @Override
    public void levelCompleted(int level) {
        view.completed(level);
    }

    @Override
    public void setWin() {
        model.setAllBoxesInHomes();
    }
}
