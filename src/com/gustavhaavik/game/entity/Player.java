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

        setDefaultValues();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void update() {
        if (keyHandler.up) {
            y -= speed;
            direction = "up";
            walkUp.update();
        } else if (keyHandler.down) {
            y += speed;
            direction = "down";
            walkDown.update();
        } else if (keyHandler.left) {
            x -= speed;
            direction = "left";
            walkLeft.update();
        } else if (keyHandler.right) {
            x += speed;
            direction = "right";
            walkRight.update();
        }

    }

    public void draw(Graphics2D g2) {
        BufferedImage image = switch (direction) {
            case "up" -> walkUp.getCurrentFrame();
            case "down" -> walkDown.getCurrentFrame();
            case "left" -> walkLeft.getCurrentFrame();
            case "right" -> walkRight.getCurrentFrame();
            default -> null;
        };

        g2.drawImage(image, x, y, gamePanel.TILE_SIZE, gamePanel.TILE_SIZE, null);
    }
}
