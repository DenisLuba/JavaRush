package com.javarush.task.task35.task3513;

import java.util.*;

public class Model {
    int score = 0;

    int maxTile = 0;

    static final int FIELD_WIDTH = 4;

    private boolean isSaveNeeded = true;

    private Tile[][] gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
    private int[][] flippedGameField = new int[FIELD_WIDTH][FIELD_WIDTH];

    private Stack<Tile[][]> previousStates = new Stack();
    private Stack<Integer> previousScores = new Stack();

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

    void left() {
        if (isSaveNeeded) saveState(gameTiles);
        boolean check = false;
        for (Tile[] gameTile : gameTiles) {
            boolean wasCompressTiles = compressTiles(gameTile);
            boolean wasMergeTiles = mergeTiles(gameTile);
            if (wasMergeTiles || wasCompressTiles) check = true;
        }
        if (check) addTile();
        isSaveNeeded = true;
    }

    void right() {
        saveState(gameTiles);
        flipField(); flipField(); left(); flipField(); flipField();
    }

    void down() {
        saveState(gameTiles);
        flipField(); left(); flipField(); flipField(); flipField();
    }

    void up() {
        saveState(gameTiles);
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

    private void saveState(Tile[][] tiles) {
        Tile[][] saveTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++)
            for (int j = 0; j < FIELD_WIDTH; j++)
                saveTiles[i][j] = new Tile(tiles[i][j].value);
        previousStates.push(saveTiles);
        previousScores.push(score);
        isSaveNeeded = false;
    }

    public void rollback() {
        if (!previousStates.empty() && !previousScores.empty()) {
            gameTiles = previousStates.pop();
            score = previousScores.pop();
        }
    }

    void randomMove() {
        int n = ((int) (Math.random() * 100)) % 4;
        switch (n) {
            case 0 :
                left();
                break;
            case 1 :
                right();
                break;
            case 2 :
                up();
                break;
            case 3 :
                down();
        }
    }

    boolean hasBoardChanged() {
        Tile[][] previousGameTiles = previousStates.peek();
        for (int i = 0; i < FIELD_WIDTH; i++)
            for (int j = 0; j < FIELD_WIDTH; j++)
                if (previousGameTiles[i][j].value != gameTiles[i][j].value)
                    return true;
        return false;
    }

    MoveEfficiency getMoveEfficiency(Move move) {
        MoveEfficiency moveEfficiency;
        move.move();
        if (hasBoardChanged()) {
            int numberOfEmptyTiles = 0;
            for (Tile[] tiles : gameTiles)
                for (Tile tile : tiles)
                    if (tile.isEmpty())
                        numberOfEmptyTiles ++;
            moveEfficiency = new MoveEfficiency(numberOfEmptyTiles, score, move);
        } else moveEfficiency = new MoveEfficiency(-1, 0, move);
        rollback();
        return moveEfficiency;
    }

    void autoMove() {
        PriorityQueue<MoveEfficiency> queue = new PriorityQueue<>(4, Collections.reverseOrder());

//        1 способ - через внутренний анонимный класс
        queue.add(getMoveEfficiency(new Move() {
            @Override
            public void move() {
                left();
            }
        }));
//        2 способ - через lambda
        queue.add(getMoveEfficiency(() -> right()));
//        3 способ - через reference method (ссылочный метод)
        queue.add(getMoveEfficiency(this::up));
        queue.add(getMoveEfficiency(this::down));
        assert queue.peek() != null;
        queue.peek().getMove().move();
    }
}
