package com.javarush.task.task39.task3905;

public class PhotoPaint {
    public boolean paintFill(Color[][] image, int x, int y, Color desiredColor) {
        if (x < 0 || x >= image[0].length || y < 0 || y >= image.length) return false;
        if (image[y][x].equals(desiredColor)) return false;

        Color currentColor = image[y][x];
        paintFill(image, x, y, desiredColor, currentColor);
        return true;
    }

    public boolean paintFill(Color[][] image, int x, int y, Color desiredColor, Color currentColor) {
        if (x < 0 || x >= image[0].length || y < 0 || y >= image.length) return false;
        if (!image[y][x].equals(currentColor) ) return false;

        image[y][x] = desiredColor;

        paintFill(image, x - 1, y, desiredColor, currentColor);
        paintFill(image, x + 1, y, desiredColor, currentColor);
        paintFill(image, x, y - 1, desiredColor, currentColor);
        paintFill(image, x, y + 1, desiredColor, currentColor);

        return true;
    }
}
