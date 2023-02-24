package com.javarush.task.task34.task3410.model;

import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 16.1. Открой файл levels.txt и внимательно изучи структуру файла. Символ 'X' - означает стену, '*' - ящик, '.' - дом, '&' - ящик который стоит в доме, а '@' - игрока. Всего в файле 60 уровней.
 * 16.2. Реализуй метод GameObjects getLevel(int level). Он должен:
 * 16.2.1. Вычитывать из файла данные уровня level. Уровни должны повторяться циклически, если пользователь прошел все 60 уровней и попал на 61 уровень, то ему нужно вернуть 1, вместо 62 уровня вернуть 2 и т.д.
 * 16.2.2. Создать все игровые объекты, описанные в указанном уровне. Координаты каждого игрового объекта должны быть рассчитаны согласно следующей логике:
 * 16.2.2.1. Самый верхний левый объект (если такой есть) должен иметь координаты x = x0 = FIELD_CELL_SIZE / 2 и y = y0 = FIELD_CELL_SIZE / 2.
 * 16.2.2.2. Объект, который находится на одну позицию правее от него должен иметь координаты x = x0 + FIELD_CELL_SIZE и y = y0.
 * 16.2.2.3. Объект, который находится на одну позицию ниже от самого верхнего левого должен иметь координаты x = x0 и y = y0 + FIELD_CELL_SIZE.
 * 16.2.2.4. Аналогично должны рассчитываться координаты любого объекта на поле.
 * 16.2.3. Создать новое хранилище объектов GameObjects и поместить в него все объекты.
 * 16.2.4. Вернуть созданное хранилище.
 *
 * Игра должна быть полностью рабочей. Проверь, если есть какие-то проблемы исправь их.
 */

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
