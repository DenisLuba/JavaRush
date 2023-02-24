package com.javarush.task.task34.task3410.model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LevelLoader {
    private Path levels;

    private int width = 500;
    private int height = 500;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level) {

        String[] levelsFile = new String[0];
        try (Stream<String> fileStream = Files.lines(levels)){
            levelsFile = fileStream.collect(Collectors.joining("/")).split("\\*{20,}"); // объединяем все строки из файла в одну, затем делим ее на строки по границам мз звездочек
        } catch (IOException ignore) {}

        String levelString = Arrays.stream(levelsFile).filter(line -> line.matches(".*Maze: " + level + "/.*")).collect(Collectors.joining()); // находим строку с нужным уровнем
        String[] strings = levelString.replaceFirst("(.+)(//)(.+)(//)", "$3").split("/"); // выделяем из нее массив строк из элементов для заполнения поля игры
        char[][] chars = Arrays.stream(strings).map(String::toCharArray).toArray(char[][]::new); // превращаем массив строк в двойной массив символов

        width = Integer.parseInt(levelString.replaceFirst("(.*)(Size X: )(\\d+)(.*)", "$3")); // находим в метаданных для уровня ширину игрового поля
        height = Integer.parseInt(levelString.replaceFirst("(.*)(Size Y: )(\\d+)(.*)", "$3")); // и его высоту

        return getGameObjectFromArrayOfCharacters(chars);
    }

//    вспомогательный метод для метода getLevel(int level),
//    который из массива символов возвращает объект класса GameObjects,
//    содержащий необходимые объекты для этого уровня
    private GameObjects getGameObjectFromArrayOfCharacters(char[][] chars) {
        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = null;

        int S = Model.FIELD_CELL_SIZE;

        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars[i].length; j++) {
                switch (chars[i][j]) {
                    case 'X': walls.add(new Wall(j * S + S / 2, i * S + S / 2)); break;
                    case '*': boxes.add(new Box(j * S + S / 2, i * S + S / 2)); break;
                    case '.': homes.add(new Home(j * S + S / 2, i * S + S / 2)); break;
                    case '@': player = new Player(j * S + S / 2, i * S + S / 2);
                }
            }
        }
        return new GameObjects(walls, boxes, homes, player);
    }
}
