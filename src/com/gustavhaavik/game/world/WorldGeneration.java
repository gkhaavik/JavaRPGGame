package com.gustavhaavik.game.world;

public class WorldGeneration {
    public static int[][] generateWorld(int width, int height) {
        int[][] noise = Noise.generatePerlinNoise(width, height, 3);

        int[][] world = new int[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                world[x][y] = switch (noise[x][y]) {
                    case 0 -> 0; // water
                    case 1 -> 1; // sand
                    case 2 -> 2; // grass
                    default -> 0;
                };
            }
        }

        return world;
    }
}
