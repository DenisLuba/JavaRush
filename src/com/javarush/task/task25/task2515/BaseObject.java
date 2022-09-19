package com.javarush.task.task25.task2515;

public abstract class BaseObject {
    protected double x, y, radius;
    private boolean isAlive;

    public BaseObject(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        isAlive = true;
    }

    public void draw(Canvas canvas) {}

    public void move() {}

    public void die() {isAlive = false;}

    public boolean isIntersect(BaseObject o) {
        double distanse = Math.sqrt((Math.pow(x - o.getX(), 2) + Math.pow(y - o.getY(), 2)));
        return distanse <= Math.max(radius, o.getRadius());
    }

    public void checkBorders(double minx, double maxx, double miny, double maxy) {
        if (x < minx) x = minx;
        if (x > maxx) x = maxx;
        if (y < miny) y = miny;
        if (y > maxy) y = maxy;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
