package com.javarush.task.task35.task3507;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) throws IOException {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
//        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) throws IOException {
        Set<Animal> allAnimals = new HashSet<>();
        Path path = new File(pathToAnimals).toPath();
        if(Files.isDirectory(path)) {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Animal animal = allAnimals(file);
                    if(animal != null) allAnimals.add(animal);

                    return FileVisitResult.CONTINUE;
                }
            });
        } else if (Files.isRegularFile(path)) {
            Animal animal = allAnimals(path);
            if (animal != null) allAnimals.add(animal);
        }
        return allAnimals;
    }

    private static Animal allAnimals(Path file) {
        if(file.toString().endsWith(".class")) {
            try {
                ClassLoader loader = new MyClassLoader();
                Class<?> clazz =loader.loadClass(file.toString());

                for (Constructor<?> constructor : clazz.getDeclaredConstructors())
                    if (constructor.getModifiers() == Modifier.PUBLIC &&
                            constructor.getParameterCount() == 0 &&
                            Animal.class.isAssignableFrom(clazz))

                        return (Animal) clazz.newInstance();

            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static class MyClassLoader extends ClassLoader {
        @Override
        protected Class<?> findClass(String name) {
            try{
                Path path = Paths.get(name);
                byte[] bytes = Files.readAllBytes(path);
                return defineClass(null, bytes, 0, bytes.length);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
