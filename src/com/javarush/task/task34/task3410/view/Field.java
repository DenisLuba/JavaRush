package com.javarush.task.task34.task3410.view;

import com.javarush.task.task34.task3410.controller.EventListener;
import com.javarush.task.task34.task3410.model.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Field extends JPanel {

    private final View view;
    private EventListener eventListener;
    private final int width;
    private final int height;

    public Field(View view) {
        this.view = view;
        this.width = view.getWidth();
        this.height = view.getHeight();
        addKeyListener(new KeyHandler());
        setFocusable(true);
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);

        view.getGameObjects().getAll().forEach(gameObject -> gameObject.draw(g));
    }

    public class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT -> eventListener.move(Direction.LEFT);
                case KeyEvent.VK_RIGHT -> eventListener.move(Direction.RIGHT);
                case KeyEvent.VK_UP -> eventListener.move(Direction.UP);
                case KeyEvent.VK_DOWN -> eventListener.move(Direction.DOWN);
                case KeyEvent.VK_R -> eventListener.restart();
                case KeyEvent.VK_Q -> eventListener.setWin();
            }
        }
    }
}
