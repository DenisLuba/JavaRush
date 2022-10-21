package com.javarush.task.task37.task3702.female;

import com.javarush.task.task37.task3702.AbstractFactory;
import com.javarush.task.task37.task3702.Human;

public class FemaleFactory implements AbstractFactory {
    public Human getPerson(int age) {
        return age <= KidGirl.MAX_AGE ? new KidGirl() : age <= TeenGirl.MAX_AGE ? new TeenGirl() : new Woman();
    }
}
