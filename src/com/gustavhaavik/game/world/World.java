package com.gustavhaavik.game.world;

import com.gustavhaavik.game.GamePanel;
import com.gustavhaavik.game.tile.Tile;
import com.gustavhaavik.game.tile.TileManager;
import com.gustavhaavik.game.tile.TileType;

import java.awt.*;

public class World {
    GamePanel gamePanel;
    TileManager tileManager;

    private int width, height;
    private int[][] tiles;

    public World(GamePanel gamePanel, int maxColumns, int maxRows) {
        this.gamePanel = gamePanel;
        this.tileManager = gamePanel.getTileManager();

        this.width = maxColumns * gamePanel.getTileSize();
        this.height = maxRows * gamePanel.getTileSize();

        tiles = WorldGeneration.generateWorld(width, height);
    }

    public int getTile(int x, int y) {
        return tiles[x][y];
    }

    public void update() {

    }

    public void draw(Graphics2D g2) {
        int x = 0;
        int y = 0;

        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles[row].length; col++) {
                int tileType = tiles[row][col];


                x += gamePanel.getTileSize();
            }

            x = 0;
            y += gamePanel.getTileSize();
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[][] getTiles() {
        return tiles;
    }
}
