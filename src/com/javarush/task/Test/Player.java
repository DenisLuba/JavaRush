package com.javarush.task.Test;

import java.util.Scanner;

public class Player {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int width = in.nextInt();
        int height = in.nextInt();
        if (in.hasNextLine()) in.nextLine();

        char[][] nodes = new char[height][width];

        for (int i = 0; i < height; i++)
            nodes[i] = in.nextLine().toCharArray();

        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++) {
                if (nodes[i][j] == '.') continue;

                String node = j + " " + i;
                String rightNode = "", bottomNode = "";

                for (int k = j + 1; k < width; k++)
                    if (nodes[i][k] == '0') {
                        rightNode = k + " " + i;
                        break;
                    }
                if (rightNode.length() == 0) rightNode = "-1 -1";

                for (int k = i + 1; k < height; k++)
                    if (nodes[k][j] == '0') {
                        bottomNode = j + " " + k;
                        break;
                    }
                if (bottomNode.length() == 0) bottomNode = "-1 -1";

                System.out.printf("%s %s %s%n", node, rightNode, bottomNode);
            }
    }
}