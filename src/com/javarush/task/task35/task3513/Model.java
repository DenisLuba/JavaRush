package com.javarush.task.task35.task3513;

import java.util.ArrayList;
import java.util.List;

public class Model {
    int score = 0;

    int maxTile = 0;

    static final int FIELD_WIDTH = 4;
    private final Tile[][] gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];

    public Model() {
        resetGameTiles();
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

    private void compressTiles(Tile[] tiles) {
        int j = 0;
        for (int i = 0; i < tiles.length; i++) {
            if (!tiles[i].isEmpty()) {
                if (i != j) {
                    tiles[j] = tiles[i];
                    tiles[i] = new Tile();
                }
                j++;
            }
        }
    }

     private void mergeTiles(Tile[] tiles) {
        for (int i = 0; i < tiles.length; i++) {
            if((i + 1) < tiles.length && tiles[i].value == tiles[i + 1].value && !tiles[i].isEmpty()) {
                tiles[i].value *= 2;
                tiles[i + 1].value = 0;
                score += tiles[i].value;
                maxTile = Math.max(maxTile, tiles[i].value);
                compressTiles(tiles);
            }
        }
    }

    public static void main(String[] args) {
        Tile[] tiles = new Tile[] {new Tile(), new Tile(8), new Tile(), new Tile(8)};
        new Model().compressTiles(tiles);
        for(Tile title : tiles) System.out.print(title.value + " ");
    }
}
