package com.javarush.task.task35.task3513;

import java.util.ArrayList;
import java.util.List;

public class Model {
    int score = 0;

    int maxTile = 0;

    static final int FIELD_WIDTH = 4;

    private final Tile[][] gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
    private int[][] flippedGameField = new int[FIELD_WIDTH][FIELD_WIDTH];

    public Model() {
        resetGameTiles();
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    public void resetGameTiles() {
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[i].length; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        addTile(); addTile();
    }

    private void addTile() {
        List<Tile> tiles = getEmptyTiles();
        if (!tiles.isEmpty()) {
            int randomTile = (int) (Math.random() * tiles.size());
            tiles.get(randomTile).value = Math.random() < 0.9 ? 2 : 4;
        }
    }

    private List<Tile> getEmptyTiles() {
        List<Tile> tiles = new ArrayList<>();
        for (Tile[] gameTile : gameTiles) {
            for (Tile tile : gameTile) {
                if (tile.isEmpty()) tiles.add(tile);
            }
        }
        return tiles;
    }

    private boolean compressTiles(Tile[] tiles) {
        boolean isChanged = false;
        int j = 0;
        for (int i = 0; i < tiles.length; i++) {
            if (!tiles[i].isEmpty()) {
                if (i != j) {
                    tiles[j] = tiles[i];
                    tiles[i] = new Tile();
                    isChanged = true;
                }
                j++;
            }
        }
        return isChanged;
    }

    private boolean mergeTiles(Tile[] tiles) {
        boolean isCharged = false;
        for (int i = 0; i < tiles.length; i++) {
            if((i + 1) < tiles.length && tiles[i].value == tiles[i + 1].value && !tiles[i].isEmpty()) {
                tiles[i].value *= 2;
                tiles[i + 1].value = 0;
                score += tiles[i].value;
                maxTile = Math.max(maxTile, tiles[i].value);
                isCharged = true;
                compressTiles(tiles);
            }
        }
        return isCharged;
    }

//    public static void main(String[] args) {
//        Model model = new Model();
//        model.gameTiles[0] = new Tile[]{new Tile(4), new Tile(8), new Tile(4), new Tile(8)};
//        model.gameTiles[1] = new Tile[]{new Tile(2), new Tile(4), new Tile(8), new Tile(4)};
//        model.gameTiles[2] = new Tile[]{new Tile(8), new Tile(5), new Tile(5), new Tile(8)};
//        model.gameTiles[3] = new Tile[]{new Tile(4), new Tile(2), new Tile(3), new Tile(4)};
//        System.out.println(model);
//        System.out.println("*****************************************");
//
//
//        System.out.println(model.canMove());
//        model.down();
//        System.out.println(model);
//    }

    void left() {
        boolean check = false;
        for (Tile[] gameTile : gameTiles) {
            boolean wasCompressTiles = compressTiles(gameTile);
            boolean wasMergeTiles = mergeTiles(gameTile);
            if (wasMergeTiles || wasCompressTiles) check = true;
        }
        if (check) addTile();
    }

    void right() {
        flipField(); flipField(); left(); flipField(); flipField();
    }

    void down() {
        flipField(); left(); flipField(); flipField(); flipField();
    }

    void up() {
        flipField(); flipField(); flipField(); left(); flipField();
    }

    private void flipField() {
        for (int i = 0; i < FIELD_WIDTH; i++)
            for (int j = 0; j < FIELD_WIDTH; j++)
                flippedGameField[j][(FIELD_WIDTH - 1) - i] = gameTiles[i][j].value;
        for (int i = 0; i < FIELD_WIDTH; i++)
            for (int j = 0; j < FIELD_WIDTH; j++)
                gameTiles[i][j].value = flippedGameField[i][j];
    }

    boolean canMove() {
        for (int i = 0; i < FIELD_WIDTH; i++)
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameTiles[i][j].isEmpty()) return true;
                if (i > 0 && gameTiles[i][j].value == gameTiles[i - 1][j].value) return true;
                if (i < FIELD_WIDTH - 1 && gameTiles[i][j].value == gameTiles[i + 1][j].value) return true;
                if (j > 0 && gameTiles[i][j].value == gameTiles[i][j - 1].value) return true;
                if (j < FIELD_WIDTH - 1 && gameTiles[i][j].value == gameTiles[i][j + 1].value) return  true;
            }
        return false;
    }

//    @Override
//    public String toString() {
//        StringBuilder builder = new StringBuilder();
//        for (Tile[] tiles : gameTiles) {
//            for (Tile tile : tiles)
//                builder.append(tile.value).append(" ");
//            builder.append('\n');
//        }
//        return builder.toString().trim();
//    }
}
