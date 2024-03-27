package com.gustavhaavik.game.world;

import java.util.Random;

public class Noise {
    public static int[][] offsetNoise(int[][] noise, int xOffset, int yOffset) {
        int width = noise.length;
        int height = noise[0].length;
        int[][] offsetNoise = new int[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                offsetNoise[x][y] = noise[(x + xOffset) % width][(y + yOffset) % height];
            }
        }

        return offsetNoise;
    }

    public static int[][] generatePerlinNoise(int width, int height, int octaveCount, float persistance, long seed) {
        int[][] baseNoise = generateWhiteNoise(width, height, seed);
        int[][][] smoothNoise = new int[octaveCount][][];
        float amplitude = 1.0f;
        float totalAmplitude = 0.0f;

        for (int i = 0; i < octaveCount; i++) {
            smoothNoise[i] = generateSmoothNoise(baseNoise, i);
            totalAmplitude += amplitude;
            amplitude *= persistance;
        }

        int[][] perlinNoise = new int[width][height];

        for (int i = 0; i < octaveCount; i++) {
            amplitude = (float) Math.pow(persistance, i) / totalAmplitude;
            perlinNoise = addNoise(perlinNoise, smoothNoise[i], amplitude);
        }

        return offsetNoise(perlinNoise, 5, 5);
    }

    public static int[][] generatePerlinNoise(int width, int height, int octaveCount) {
        return generatePerlinNoise(width, height, octaveCount, 0.2f, System.currentTimeMillis());
    }

    private static int[][] addNoise(int[][] baseNoise, int[][] noise, float amplitude) {
        int width = baseNoise.length;
        int height = baseNoise[0].length;

        int[][] result = new int[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                result[x][y] = baseNoise[x][y] + (int) (noise[x][y] * amplitude);
            }
        }

        return result;
    }

    private static int[][] generateWhiteNoise(int width, int height, long seed) {
        int[][] noise = new int[width][height];
        Random random = new Random(seed);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                noise[x][y] = random.nextInt(Integer.MAX_VALUE);
            }
        }

        return noise;
    }

    private static int[][] generateSmoothNoise(int[][] baseNoise, int octave) {
        int width = baseNoise.length;
        int height = baseNoise[0].length;
        int[][] smoothNoise = new int[width][height];
        int samplePeriod = 1 << octave;
        float sampleFrequency = 1.0f / samplePeriod;


        for (int x = 0; x < width; x++) {
            int sample_i0 = (x / samplePeriod) * samplePeriod;
            int sample_i1 = (sample_i0 + samplePeriod) % width;
            float horizontal_blend = (x - sample_i0) * sampleFrequency;

            for (int y = 0; y < height; y++) {
                int sample_j0 = (y / samplePeriod) * samplePeriod;
                int sample_j1 = (sample_j0 + samplePeriod) % height;
                float vertical_blend = (y - sample_j0) * sampleFrequency;

                float top = interpolate(baseNoise[sample_i0][sample_j0], baseNoise[sample_i1][sample_j0], horizontal_blend);
                float bottom = interpolate(baseNoise[sample_i0][sample_j1], baseNoise[sample_i1][sample_j1], horizontal_blend);

                smoothNoise[x][y] = (int) interpolate(top, bottom, vertical_blend);
            }
        }

        return smoothNoise;
    }

    private static float interpolate(float x0, float x1, float alpha) {
        return x0 * (1 - alpha) + alpha * x1;
    }
}
