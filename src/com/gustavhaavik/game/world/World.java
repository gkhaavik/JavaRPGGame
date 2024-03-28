package com.gustavhaavik.game.world;

import com.gustavhaavik.game.GamePanel;
import com.gustavhaavik.game.tile.Tile;
import com.gustavhaavik.game.tile.TileManager;
import com.gustavhaavik.game.tile.TileType;

import java.awt.*;

public class World {
    GamePanel gamePanel;
    TileManager tileManager;

    private final int maxWorldColumns;
    private final int maxWorldRows;
    private int[][] tiles;

    public World(GamePanel gamePanel, int maxColumns, int maxRows) {
        this.gamePanel = gamePanel;
        this.tileManager = gamePanel.getTileManager();

        this.maxWorldColumns = maxColumns;
        this.maxWorldRows = maxRows;
//        this.width = maxColumns * gamePanel.getTileSize();
//        this.height = maxRows * gamePanel.getTileSize();

        tiles = WorldGeneration.generateWorld(maxColumns, maxRows);
    }

    public int getTile(int x, int y) {
        return tiles[x][y];
    }

    public void update() {

    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < maxWorldColumns && worldRow < maxWorldRows) {
            int tileIndex = tiles[worldCol][worldRow];
            Tile tile = tileManager.getTile(tileIndex, TileType.WORLD);

            int worldX = worldCol * gamePanel.getTileSize();
            int worldY = worldRow * gamePanel.getTileSize();

            int screenX = worldX - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().screenX;
            int screenY = worldY - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().screenY;

            if (worldX + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldX() - gamePanel.getPlayer().screenX &&
                    worldX - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().screenX &&
                    worldY + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldY() - gamePanel.getPlayer().screenY &&
                    worldY - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().screenY
            ) {
                g2.drawImage(tile.image,
                        screenX,
                        screenY,
                        gamePanel.getTileSize(),
                        gamePanel.getTileSize(),
                        null);
            }


            worldCol++;
            if (worldCol >= maxWorldColumns) {
                worldCol = 0;
                worldRow++;
            }
        }
    }

    public int[][] getTiles() {
        return tiles;
    }
}
