package com.gustavhaavik.game.entity;

import com.gustavhaavik.game.GamePanel;
import com.gustavhaavik.game.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void update() {
        if (keyHandler.up) {
            direction = "up";
            y -= speed;
        }
        if (keyHandler.down) {
            direction = "down";
            y += speed;
        }
        if (keyHandler.left) {
            direction = "left";
            x -= speed;
        }
        if (keyHandler.right) {
            direction = "right";
            x += speed;
        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.fillRect(x, y, gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);

//        BufferedImage image = switch (direction) {
//            case "up" -> up1;
//            case "down" -> down1;
//            case "left" -> left1;
//            case "right" -> right1;
//            default -> null;
//        };
//
//        g2.drawImage(image, x, y, gamePanel.TILE_SIZE, gamePanel.TILE_SIZE, null);
    }

    public void getPlayerImage() {
    }
}
