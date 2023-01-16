package com.javarush.task.task39.task3905;

import org.junit.Test;
import org.junit.Assert;

public class Solution {
    Color[][] image = {
            {Color.RED, Color.YELLOW, Color.GREEN, Color.GREEN, Color.YELLOW},
            {Color.GREEN, Color.INDIGO, Color.VIOLET, Color.GREEN, Color.GREEN},
            {Color.INDIGO, Color.INDIGO, Color.GREEN, Color.GREEN, Color.RED},
            {Color.VIOLET, Color.GREEN, Color.VIOLET, Color.GREEN, Color.GREEN},
            {Color.RED, Color.INDIGO, Color.RED, Color.RED, Color.INDIGO}
    };

    Color[][] expected = {
            {Color.RED, Color.YELLOW, Color.RED, Color.RED, Color.YELLOW},
            {Color.GREEN, Color.INDIGO, Color.VIOLET, Color.RED, Color.RED},
            {Color.INDIGO, Color.INDIGO, Color.RED, Color.RED, Color.RED},
            {Color.VIOLET, Color.GREEN, Color.VIOLET, Color.RED, Color.RED},
            {Color.RED, Color.INDIGO, Color.RED, Color.RED, Color.INDIGO}
    };

    PhotoPaint paint;
    public static void main(String[] args) {

    }

    @Test
    public void testPaintFill() {
        paint = new PhotoPaint();
        paint.paintFill(image, 2, 2, Color.RED);
        StringBuilder imageBuilder = new StringBuilder();
        for (Color[] colors : image) {
            for (Color color : colors)
                imageBuilder.append(color).append(" - ");
            imageBuilder.delete(imageBuilder.length() - 3, imageBuilder.length()).append("\n");
        }

        StringBuilder expectedBuilder = new StringBuilder();
        for (Color[] colors : expected) {
            for (Color color : colors)
                expectedBuilder.append(color).append(" - ");
            expectedBuilder.delete(expectedBuilder.length() - 3, expectedBuilder.length()).append("\n");
        }

        System.out.println(imageBuilder + "\n\n" + expectedBuilder);
        Assert.assertEquals(imageBuilder.toString(), expectedBuilder.toString());
    }
}
