package com.javarush.task.task22.task2213;

/*
    we write the code "from top to bottom". First, the classes, then the methods, then the implementation of the methods
 */

public class Tetris {
    private Field field;
    private Figure figure;
    static Tetris game;

    public static void main(String[] args) {
        game = new Tetris();
        game.run();
    }

    public Field getField() {
        return field;
    }

    public Figure getFigure() {
        return figure;
    }

    public void run() {

    }

    public void step() {

    }
}
