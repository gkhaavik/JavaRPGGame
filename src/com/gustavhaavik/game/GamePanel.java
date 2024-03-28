package com.gustavhaavik.game;

import com.gustavhaavik.game.entity.Player;
import com.gustavhaavik.game.tile.Tile;
import com.gustavhaavik.game.tile.TileManager;
import com.gustavhaavik.game.tile.TileType;
import com.gustavhaavik.game.world.World;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    public final int ORIGINAL_TILE_SIZE = 16; // 16x16 pixels
    final int SCALE = 3;
    final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; // 48x48 pixels

    final int MAX_SCREEN_COL = 16;
    final int MAX_SCREEN_ROW = 12;
    final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL; // 768 pixels
    final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW; // 576 pixels

    final int FPS = 60;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;

    TileManager tileManager = new TileManager(this);

    Camera camera = new Camera();
    World world = new World(this, 50, 50);
    Player player = new Player(this, keyHandler);


    public GamePanel() {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);

        addKeyListener(keyHandler);
        setFocusable(true);
    }

    public Tile getTile(int index, TileType tileType) {
        return tileManager.getTile(index, tileType);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        world.draw(g2);

        player.draw(g2);

        g2.dispose();
    }

    public void startGame() {
        if (gameThread == null) {
            gameThread = new Thread(this);
            gameThread.start();
        }
    }

    @Override
    public void run() {
        // gameloop using delta time
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000.0 / FPS;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        int ticks = 0;

        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;

            if (delta >= 1) {
                update();
                repaint();
                ticks++;
                delta--;
            }

            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("Ticks: " + ticks + ", Frames: " + frames);
                frames = 0;
                ticks = 0;
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        player.update();
    }

    public void stopGame() {
        if (gameThread != null) {
            gameThread.interrupt();
            gameThread = null;
        }
    }

    public int getOriginalTileSize() {
        return ORIGINAL_TILE_SIZE;
    }

    public int getTileSize() {
        return TILE_SIZE;
    }

    public int getMaxScreenColumns() {
        return MAX_SCREEN_COL;
    }

    public int getMaxScreenRows() {
        return MAX_SCREEN_ROW;
    }

    public int getScreenWidth() {
        return SCREEN_WIDTH;
    }

    public int getScreenHeight() {
        return SCREEN_HEIGHT;
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public Camera getCamera() {
        return camera;
    }

    public World getWorld() {
        return world;
    }

    public Player getPlayer() {
        return player;
    }
}
