package com.javarush.task.task34.task3410.model;

import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LevelLoader {
    private Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level) {
        Player player = new Player(250, 250);
        Set<Home> homes = Stream.of(new Home(10, 10)).collect(Collectors.toSet());
        Set<Box> boxes = Stream.of(new Box(250, 270)).collect(Collectors.toSet());
        Set<Wall> walls = Stream.of(new Wall(10, 30), new Wall(20, 30), new Wall(250, 290)).collect(Collectors.toSet());
        return new GameObjects(walls, boxes, homes, player);
    }
}
