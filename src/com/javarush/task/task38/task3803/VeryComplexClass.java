package com.javarush.task.task38.task3803;

public class VeryComplexClass {
    public void methodThrowsClassCastException() throws ClassCastException {
        getClassCastException();
    }

    public void methodThrowsNullPointerException() throws NullPointerException {
        getNullPointerException();
    }

    public static void main(String[] args) {

    }

    private void getClassCastException() throws ClassCastException {
        throw new ClassCastException();
    }

    private void getNullPointerException() {
        throw new NullPointerException();
    }
}
