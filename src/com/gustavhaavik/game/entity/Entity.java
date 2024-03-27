package com.gustavhaavik.game.entity;

import com.gustavhaavik.game.animator.Animation;

public class Entity {
    public int worldX, worldY;
    public int speed;

    public Animation walkUp;
    public Animation walkDown;
    public Animation walkLeft;
    public Animation walkRight;

    public String direction;
}

