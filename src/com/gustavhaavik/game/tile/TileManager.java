package com.gustavhaavik.game.tile;

import com.gustavhaavik.game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Objects;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tiles;
    int tilesIndex = 0;

    int[][] mapTileNum;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[10];

        mapTileNum = new int[gamePanel.MAX_SCREEN_ROW][gamePanel.MAX_SCREEN_COL];

        loadTileMap("/tiles/Grass.png");

        loadMap("/maps/map01.txt");
    }

    private void loadMap(String path) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(path)))) {
            for (int row = 0; row < gamePanel.MAX_SCREEN_ROW; row++) {
                String line = br.readLine();
                String[] tokens = line.split(" ");
                for (int col = 0; col < gamePanel.MAX_SCREEN_COL; col++) {
                    mapTileNum[row][col] = Integer.parseInt(tokens[col]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void loadTileMap(String path) {
        try {
            BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));

            int rows = image.getHeight() / gamePanel.ORIGINAL_TILE_SIZE;
            int cols = image.getWidth() / gamePanel.ORIGINAL_TILE_SIZE;

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    tiles[tilesIndex] = new Tile(image.getSubimage(col * gamePanel.ORIGINAL_TILE_SIZE, row * gamePanel.ORIGINAL_TILE_SIZE, gamePanel.ORIGINAL_TILE_SIZE, gamePanel.ORIGINAL_TILE_SIZE));
                    tilesIndex++;
                    System.out.println("Tile added: " + tilesIndex);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        for (int row = 0; row < gamePanel.MAX_SCREEN_ROW; row++) {
            for (int col = 0; col < gamePanel.MAX_SCREEN_COL; col++) {
                int tileIndex = mapTileNum[row][col];
                g2.drawImage(tiles[tileIndex].image, col * gamePanel.TILE_SIZE, row * gamePanel.TILE_SIZE, gamePanel.TILE_SIZE, gamePanel.TILE_SIZE, null);
            }
        }
    }
}
