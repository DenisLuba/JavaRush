package com.javarush.task.task35.task3513;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter {
    private static final int WINNING_TILE = 2048;
    private Model model;

    private View view;

    public Controller(Model model) {
        this.model = model;
        view = new View(this);
    }

    public View getView() {
        return view;
    }

    public Tile[][] getGameTiles() {
        return model.getGameTiles();
    }

    public int getScore() {
        return model.score;
    }

    public void resetGame() {
        model.score = 0;
        view.isGameLost = false;
        view.isGameWon = false;
        model.resetGameTiles();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == 27) resetGame(); // 27 - ESCAPE
        if (!model.canMove()) view.isGameLost = true;

        if (!view.isGameLost && !view.isGameWon) {
            switch (keyCode) {
//              37 - VK_LEFT
                case 37 :
                    model.left();
                    break;
//              39 - VK_RIGHT
                case 39 :
                    model.right();
                    break;
//              38 - UP
                case 38 :
                    model.up();
                    break;
//              40 - DOWN
                case 40 :
                    model.down();
                    break;
            }
        }
        if (model.maxTile == WINNING_TILE) view.isGameWon = true;
        view.repaint();
    }
}
