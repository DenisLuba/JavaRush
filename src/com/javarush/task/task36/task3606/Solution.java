package com.javarush.task.task36.task3606;

/*
Осваиваем classLoader и Reflection
 */

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "com/javarush/task/task36/task3606/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("secondhiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("firsthiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
        ClassLoader loader = Solution.class.getClassLoader();
    }

    public void scanFileSystem() throws ClassNotFoundException {
        File[] files = new File(packageName)
                .listFiles(pathName -> String.valueOf(pathName).endsWith(".class"));

        String excessPath = this.getClass()
                .getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .getPath()
                .substring(1)
                .replaceAll("/", ".");
        System.out.println(excessPath);


        ClassLoader loader = this.getClass().getClassLoader();

        assert files != null;
        for (File file : files)
            try {
                String fileName = String.valueOf(file)
                        .replaceAll("\\\\", ".")
                        .replaceFirst(excessPath, "");

                hiddenClasses.add(loader.loadClass(fileName.substring(0, fileName.lastIndexOf("."))));

            } catch (ClassNotFoundException ignored) {
            }
    }

    public HiddenClass getHiddenClassObjectByKey(String key) {
        for (Class<?> clazz : hiddenClasses)
            if (key.equalsIgnoreCase(clazz.getSimpleName()))
                try {
                    Constructor<?> constructor = clazz.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    return (HiddenClass) constructor.newInstance();
                } catch (IllegalAccessException | InstantiationException | InvocationTargetException ignored) {
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
        return null;
    }


//    Решение для валидатора (у меня не работает):

//    public void scanFileSystem() throws ClassNotFoundException {
//        File dir = new File(packageName);
//        ClassLoader classLoader = Solution.class.getClassLoader();
//        for (String fileName : Objects.requireNonNull(dir.list())) {
//            if (fileName.endsWith(".class")) {
//    не понятно, откуда взялась папка ru/, откуда я должен о ней знать
//                String className = packageName.replaceAll("[/\\\\]", ".").substring(packageName.lastIndexOf("ru/")) + "." + fileName.substring(0, fileName.length() - 6);
//                Class<?> aClass = classLoader.loadClass(className);
//                hiddenClasses.add(aClass);
//            }
//        }
//    }

//    public HiddenClass getHiddenClassObjectByKey(String key) {
//        String lowerKey = key.toLowerCase();
//        try {
//            Class resultClass = null;
//            for (Class aClass : hiddenClasses) {
//                // оказывается, key может содержать не все имя класса, а только его начало
//                if (aClass.getSimpleName().toLowerCase().startsWith(lowerKey)) {
//                    resultClass = aClass;
//                    Constructor<?> declaredConstructor = resultClass.getDeclaredConstructor();
//                    declaredConstructor.setAccessible(true);
//                    return (HiddenClass) declaredConstructor.newInstance();
//                }
//            }
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
