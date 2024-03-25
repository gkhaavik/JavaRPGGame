package com.gustavhaavik.game.tile;

import java.awt.image.BufferedImage;

public class Tile {
    public BufferedImage image;
    public boolean isSolid = false;

    public Tile(BufferedImage image) {
        this.image = image;
    }
}
