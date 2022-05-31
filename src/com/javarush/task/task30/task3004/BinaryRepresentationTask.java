package com.javarush.task.task30.task3004;

import java.util.concurrent.RecursiveTask;

public class BinaryRepresentationTask extends RecursiveTask<String> {
    int x;
    public BinaryRepresentationTask(int x) {
        this.x = x;
    }

    @Override
    protected String compute() {
        if(x / 2 > 0) {
            BinaryRepresentationTask binaryRepresentationTask = new BinaryRepresentationTask(x / 2);
            binaryRepresentationTask.fork();
            return binaryRepresentationTask.join() + x % 2;
        } else return String.valueOf(x % 2);
    }
}
