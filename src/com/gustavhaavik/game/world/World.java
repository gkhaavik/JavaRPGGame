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

        tiles = WorldGeneration.generateWorld(maxColumns, maxRows);
    }

    public int getTile(int x, int y) {
        return tiles[x][y];
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < maxWorldColumns && worldRow < maxWorldRows) {
            int tileIndex = tiles[worldCol][worldRow];
            Tile tile = tileManager.getTile(tileIndex, TileType.WORLD);

            int worldX = worldCol * GamePanel.TILE_SIZE;
            int worldY = worldRow * GamePanel.TILE_SIZE;

            int screenX = worldX - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().screenX;
            int screenY = worldY - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().screenY;

            if (worldX + GamePanel.TILE_SIZE > gamePanel.getPlayer().getWorldX() - gamePanel.getPlayer().screenX &&
                    worldX - GamePanel.TILE_SIZE < gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().screenX &&
                    worldY + GamePanel.TILE_SIZE > gamePanel.getPlayer().getWorldY() - gamePanel.getPlayer().screenY &&
                    worldY - GamePanel.TILE_SIZE < gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().screenY
            ) {
                g2.drawImage(tile.image,
                        screenX,
                        screenY,
                        GamePanel.TILE_SIZE,
                        GamePanel.TILE_SIZE,
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
