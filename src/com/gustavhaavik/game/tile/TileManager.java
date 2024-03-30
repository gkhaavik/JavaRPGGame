package com.gustavhaavik.game.tile;

import com.gustavhaavik.game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class TileManager {
    private static TileManager instance;

    Tile[] groundTiles = new Tile[256];

    int tilesIndex = 0;

    public TileManager() {
        // Water = 0
        loadTile("/tiles/Grass.png", 0, 0, true);
        // Grass = 1
        loadTile("/tiles/Grass.png", 2, 0);
        // Sand = 2
        loadTile("/tiles/Grass.png", 4, 0);

        System.out.println("Loaded " + tilesIndex + " tiles");
    }

    public static BufferedImage getImage(String path, int x, int y) {
        try {
            BufferedImage image = ImageIO.read(Objects.requireNonNull(TileManager.class.getResourceAsStream(path)));
            return image.getSubimage(
                    x * GamePanel.ORIGINAL_TILE_SIZE,
                    y * GamePanel.ORIGINAL_TILE_SIZE,
                    GamePanel.ORIGINAL_TILE_SIZE,
                    GamePanel.ORIGINAL_TILE_SIZE
            );
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void loadTile(String path, int x, int y, boolean solid) {
        BufferedImage image = getImage(path, x, y);
        groundTiles[tilesIndex] = new Tile(image, solid);
        tilesIndex++;
    }

    public void loadTile(String path, int x, int y) {
        loadTile(path, x, y, false);
    }

    public Tile getTile(int index, TileType tileType) {
        if (Objects.requireNonNull(tileType) == TileType.WORLD) {
            return groundTiles[index];
        }
        return null;
    }
}
