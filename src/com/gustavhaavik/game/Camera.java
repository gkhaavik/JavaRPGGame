package com.gustavhaavik.game;

public class Camera {
    private int xOffset, yOffset;

    public Camera() {
        xOffset = 5;
        yOffset = 5;
    }

    public void move(int x, int y) {
        xOffset += x;
        yOffset += y;
    }

    public int getxOffset() {
        return xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }
}
