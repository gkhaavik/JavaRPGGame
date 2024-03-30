package com.gustavhaavik.game.components.animator;

import com.gustavhaavik.game.tile.TileManager;

import java.awt.image.BufferedImage;

public class Frame {
    public final String path;
    public final int x, y;

    public Frame(String path, int x, int y) {
        this.path = path;
        this.x = x;
        this.y = y;
    }

    public BufferedImage image(String path, int x, int y) {
        return TileManager.getImage(path, x, y);
    }
}
