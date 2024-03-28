package com.gustavhaavik.game.entity;

import com.gustavhaavik.game.GamePanel;
import com.gustavhaavik.game.KeyHandler;
import com.gustavhaavik.game.animator.Animation;
import com.gustavhaavik.game.tile.TileType;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public final int screenX, screenY;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        walkUp = new Animation(120, new BufferedImage[]{
                gamePanel.getTile(6, TileType.PLAYER).image,
                gamePanel.getTile(7, TileType.PLAYER).image,
                gamePanel.getTile(8, TileType.PLAYER).image,
                gamePanel.getTile(9, TileType.PLAYER).image,
        });
        walkDown = new Animation(120, new BufferedImage[]{
                gamePanel.getTile(1, TileType.PLAYER).image,
                gamePanel.getTile(2, TileType.PLAYER).image,
                gamePanel.getTile(3, TileType.PLAYER).image,
                gamePanel.getTile(4, TileType.PLAYER).image
        });
        walkLeft = new Animation(120, new BufferedImage[]{
                gamePanel.getTile(16, TileType.PLAYER).image,
                gamePanel.getTile(17, TileType.PLAYER).image,
                gamePanel.getTile(18, TileType.PLAYER).image,
                gamePanel.getTile(19, TileType.PLAYER).image
        });
        walkRight = new Animation(120, new BufferedImage[]{
                gamePanel.getTile(11, TileType.PLAYER).image,
                gamePanel.getTile(12, TileType.PLAYER).image,
                gamePanel.getTile(13, TileType.PLAYER).image,
                gamePanel.getTile(14, TileType.PLAYER).image
        });

        screenX = gamePanel.getScreenWidth() / 2 - gamePanel.getTileSize() / 2;
        screenY = gamePanel.getScreenHeight() / 2 - gamePanel.getTileSize() / 2;

        setDefaultValues();
    }

    public void setDefaultValues() {
        worldX = 10 * gamePanel.getTileSize();
        worldY = 10 * gamePanel.getTileSize();
        speed = 2;
        direction = "down";
    }

    public void update() {
        if (keyHandler.up) {
            worldY -= speed;
            direction = "up";
            walkUp.update();
        } else if (keyHandler.down) {
            worldY += speed;
            direction = "down";
            walkDown.update();
        } else if (keyHandler.left) {
            worldX -= speed;
            direction = "left";
            walkLeft.update();
        } else if (keyHandler.right) {
            worldX += speed;
            direction = "right";
            walkRight.update();
        }

        gamePanel.getCamera().move(worldX - gamePanel.getScreenWidth() / 2, worldY - gamePanel.getScreenHeight() / 2);
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = switch (direction) {
            case "up" -> walkUp.getCurrentFrame();
            case "down" -> walkDown.getCurrentFrame();
            case "left" -> walkLeft.getCurrentFrame();
            case "right" -> walkRight.getCurrentFrame();
            default -> null;
        };

        g2.drawImage(image, screenX, screenY, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }
}
