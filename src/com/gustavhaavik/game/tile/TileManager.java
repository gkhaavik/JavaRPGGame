package com.gustavhaavik.game.tile;

import com.gustavhaavik.game.GamePanel;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TileManager {
    private final GamePanel gamePanel;
    Tile[] groundTiles = new Tile[256];

    Tile[] swordsmanTiles;

    int tilesIndex = 0;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        loadTileMap("/tiles/Grass.png");
        loadTileMap("/tiles/Trees.png");
        loadTileMap("/tiles/Rocks.png");

        System.out.println("Loaded " + tilesIndex + " tiles");

        swordsmanTiles = getTiles("/player/SwordsmanPurple.png");
        System.out.println("Loaded " + swordsmanTiles.length + " player tiles");
    }

    public Tile[] getTiles(String path) {
        List<Tile> tileList = new ArrayList<>();

        try {
            BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));

            int rows = image.getHeight() / gamePanel.ORIGINAL_TILE_SIZE;
            int cols = image.getWidth() / gamePanel.ORIGINAL_TILE_SIZE;

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    Tile tile = new Tile(image.getSubimage(col * gamePanel.ORIGINAL_TILE_SIZE, row * gamePanel.ORIGINAL_TILE_SIZE, gamePanel.ORIGINAL_TILE_SIZE, gamePanel.ORIGINAL_TILE_SIZE));
                    tileList.add(tile);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tileList.toArray(new Tile[0]);
    }

    public Tile getTile(int index, TileType tileType) {
        switch (tileType) {
            case WORLD -> {
                return groundTiles[index];
            }
            case PLAYER -> {
                return swordsmanTiles[index];
            }
            default -> {
                return null;
            }
        }
    }

    private void loadTileMap(String path) {
        try {
            BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));

            int rows = image.getHeight() / gamePanel.ORIGINAL_TILE_SIZE;
            int cols = image.getWidth() / gamePanel.ORIGINAL_TILE_SIZE;

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    groundTiles[tilesIndex] = new Tile(image.getSubimage(col * gamePanel.ORIGINAL_TILE_SIZE, row * gamePanel.ORIGINAL_TILE_SIZE, gamePanel.ORIGINAL_TILE_SIZE, gamePanel.ORIGINAL_TILE_SIZE));
                    tilesIndex++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        for (int row = 0; row < gamePanel.getMaxScreenRows(); row++) {
            for (int col = 0; col < gamePanel.getMaxScreenColumns(); col++) {
                g2.drawImage(groundTiles[1].image, col * gamePanel.getTileSize(), row * gamePanel.getTileSize(), gamePanel.getTileSize(), gamePanel.getTileSize(), null);
            }
        }
    }
}
