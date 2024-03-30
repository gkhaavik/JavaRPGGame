package com.gustavhaavik.game.entity;

import com.gustavhaavik.game.GamePanel;
import com.gustavhaavik.game.KeyHandler;
import com.gustavhaavik.game.components.animator.Animation;
import com.gustavhaavik.game.components.animator.Animator;
import com.gustavhaavik.game.gameobjects.GameObject;
import com.gustavhaavik.game.tile.TileType;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;

    Animator animator;

    public final int screenX, screenY;

    public Player(GamePanel gamePanel, KeyHandler keyHandler, GameObject gameObject) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        this.animator = gameObject.getComponent(Animator.class);

        screenX = GamePanel.SCREEN_WIDTH / 2 - GamePanel.TILE_SIZE / 2;
        screenY = GamePanel.SCREEN_HEIGHT / 2 - GamePanel.TILE_SIZE / 2;

        setDefaultValues();
    }

    public void setDefaultValues() {
        worldX = 10 * GamePanel.TILE_SIZE;
        worldY = 10 * GamePanel.TILE_SIZE;
        speed = 2;
        direction = "down";
    }

    public void update() {
        if (keyHandler.up) {
            worldY -= speed;
            direction = "up";
            animator.setAnimation(1);
        } else if (keyHandler.down) {
            worldY += speed;
            direction = "down";
            animator.setAnimation(0);
        } else if (keyHandler.left) {
            worldX -= speed;
            direction = "left";
            animator.setAnimation(3);
        } else if (keyHandler.right) {
            worldX += speed;
            direction = "right";
            animator.setAnimation(2);
        }

        if (keyHandler.isMoving()) {
            animator.update();
        }

//        Check if player is out of bounds
        if (worldX < 0) {
            worldX = 0;
        } else if (worldX > gamePanel.getWorld().getTiles().length * GamePanel.TILE_SIZE - GamePanel.TILE_SIZE) {
            worldX = gamePanel.getWorld().getTiles().length * GamePanel.TILE_SIZE - GamePanel.TILE_SIZE;
        }

        if (worldY < 0) {
            worldY = 0;
        } else if (worldY > gamePanel.getWorld().getTiles()[0].length * GamePanel.TILE_SIZE - GamePanel.TILE_SIZE) {
            worldY = gamePanel.getWorld().getTiles()[0].length * GamePanel.TILE_SIZE - GamePanel.TILE_SIZE;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = animator.getCurrentAnimation().getCurrentFrame();
        g2.drawImage(image, screenX, screenY, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }
}
