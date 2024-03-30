package com.gustavhaavik.game.gameobjects;

import com.gustavhaavik.game.components.animator.Animator;

public class Prefab {
    private final String name;
    private final String path;
    private final int x, y;
    private final Animator animator;

    public Prefab(String name, String path, int x, int y, Animator animator) {
        this.name = name;
        this.path = path;
        this.x = x;
        this.y = y;
        this.animator = animator;
    }

    public String getName() {
        return name;
    }
}
