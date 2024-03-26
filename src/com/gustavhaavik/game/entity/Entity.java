package com.gustavhaavik.game.entity;

import com.gustavhaavik.game.animator.Animation;

import java.awt.image.BufferedImage;

public class Entity {
    public int x, y;
    public int speed;

    public Animation walkUp;
    public Animation walkDown;
    public Animation walkLeft;
    public Animation walkRight;

    public String direction;
}

