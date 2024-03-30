package com.gustavhaavik.game.world;

public class WorldGeneration {
    public static int[][] generateWorld(int width, int height) {
        float[][] noise = Noise.generatePerlinNoise(width, height, 3);

        int[][] world = new int[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (noise[x][y] < 0.3) {
                    world[x][y] = 0;
                } else if (noise[x][y] <= 0.4) {
                    world[x][y] = 2;
                } else {
                    world[x][y] = 1;
                }
            }
        }

        printWorld(world);
        return world;
    }

    private static void printWorld(int[][] world) {
        for (int x = 0; x < world.length; x++) {
            for (int y = 0; y < world[0].length; y++) {
                System.out.print(world[x][y] + " ");
            }
            System.out.println();
        }
    }
}
