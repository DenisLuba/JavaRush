package com.javarush.task.task36.task3602;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws NoSuchMethodException, InstantiationException, IllegalAccessException {

        System.out.println(getExpectedClass());
    }

    public static Class<?> getExpectedClass() {
        for (Class clazz : Collections.class.getDeclaredClasses()) {
            if (clazz.getSimpleName().equals("EmptyList")) {
                return clazz;
            }
        }
        return null;
    }

//    public static Class<?> getExpectedClass() throws NoSuchMethodException, InstantiationException, IllegalAccessException {
//        Class<?>[] classes = Collections.class.getDeclaredClasses();
//        for (Class<?> c : classes) {
//            if (List.class.isAssignableFrom(c) || List.class.isAssignableFrom(c.getSuperclass())) {
//                if (Modifier.isPrivate(c.getModifiers()) && Modifier.isStatic(c.getModifiers())) {
//                        Constructor<?>[] constructors = c.getDeclaredConstructors();
//                        for (Constructor<?> constructor : constructors) {
//                            if (constructor.getParameterCount() == 0) constructor.setAccessible(true);
//                            try {
//                                Object object = constructor.newInstance();
//                                Method method = c.getDeclaredMethod("get", int.class);
//                                method.setAccessible(true);
//                                method.invoke(object, 0);
//                            } catch (InvocationTargetException e) {
//                                if (e.getCause().toString().contains(IndexOutOfBoundsException.class.getName()))
//                                    return c;
//                            }
//                        }
//                }
//            }
//        }
//        return null;
//    }
}
