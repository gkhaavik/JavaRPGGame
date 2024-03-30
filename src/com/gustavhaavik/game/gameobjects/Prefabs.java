package com.gustavhaavik.game.gameobjects;

import com.gustavhaavik.game.components.animator.Animation;
import com.gustavhaavik.game.components.animator.Animator;
import com.gustavhaavik.game.components.PlayerController;
import com.gustavhaavik.game.components.animator.Frame;
import com.gustavhaavik.game.tile.TileManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public final class Prefabs {
    private static List<GameObject> GameObjects = new ArrayList<>();

    public static GameObject createPlayer() {
        GameObject player = new GameObject();

        Animator animator = new Animator(
                new Animation(120, new BufferedImage[]{
                        TileManager.getImage("/player/SwordsmanPurple.png", 1, 0),
                        TileManager.getImage("/player/SwordsmanPurple.png", 2, 0),
                        TileManager.getImage("/player/SwordsmanPurple.png", 3, 0),
                        TileManager.getImage("/player/SwordsmanPurple.png", 4, 0)
                }),
                new Animation(120, new BufferedImage[]{
                        TileManager.getImage("/player/SwordsmanPurple.png", 1, 1),
                        TileManager.getImage("/player/SwordsmanPurple.png", 2, 1),
                        TileManager.getImage("/player/SwordsmanPurple.png", 3, 1),
                        TileManager.getImage("/player/SwordsmanPurple.png", 4, 1)
                }),
                new Animation(120, new BufferedImage[]{
                        TileManager.getImage("/player/SwordsmanPurple.png", 1, 2),
                        TileManager.getImage("/player/SwordsmanPurple.png", 2, 2),
                        TileManager.getImage("/player/SwordsmanPurple.png", 3, 2),
                        TileManager.getImage("/player/SwordsmanPurple.png", 4, 2)
                }),
                new Animation(120, new BufferedImage[]{
                        TileManager.getImage("/player/SwordsmanPurple.png", 1, 3),
                        TileManager.getImage("/player/SwordsmanPurple.png", 2, 3),
                        TileManager.getImage("/player/SwordsmanPurple.png", 3, 3),
                        TileManager.getImage("/player/SwordsmanPurple.png", 4, 3)
                })
        );

        player.addComponent(animator);

        GameObjects.add(player);
        return player;
    }

    public void render(Graphics2D g2) {
        for (GameObject gameObject : GameObjects) {
            gameObject.render();
        }
    }

    public void update() {
        for (GameObject gameObject : GameObjects) {
            gameObject.update();
        }
    }
}
