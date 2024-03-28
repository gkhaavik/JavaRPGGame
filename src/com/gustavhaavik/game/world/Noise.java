package com.gustavhaavik.game.world;

import java.util.Random;

public class Noise {
    public static float[][] offsetNoise(float[][] noise, int xOffset, int yOffset) {
        int width = noise.length;
        int height = noise[0].length;
        float[][] offsetNoise = new float[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                offsetNoise[x][y] = noise[(x + xOffset) % width][(y + yOffset) % height];
            }
        }

        return offsetNoise;
    }

    //    generate perlinnoise
    public static float[][] generatePerlinNoise(int width, int height, int octaveCount) {
        float[][] baseNoise = generateWhiteNoise(width, height);
        float[][][] smoothNoise = new float[octaveCount][][];

        float persistance = 0.5f;

        for (int i = 0; i < octaveCount; i++) {
            smoothNoise[i] = generateSmoothNoise(baseNoise, i);
        }

        float[][] perlinNoise = new float[width][height];
        float amplitude = 1.0f;
        float totalAmplitude = 0.0f;

        for (int octave = octaveCount - 1; octave >= 0; octave--) {
            amplitude *= persistance;
            totalAmplitude += amplitude;

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    perlinNoise[x][y] += smoothNoise[octave][x][y] * amplitude;
                }
            }
        }

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                perlinNoise[x][y] /= totalAmplitude;
            }
        }

        return perlinNoise;
    }

    public static float[][] generateWhiteNoise(int width, int height) {
        Random random = new Random(0);
        float[][] noise = new float[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                noise[x][y] = random.nextFloat() % 1;
            }
        }

        return noise;
    }

    public static float[][] generateSmoothNoise(float[][] baseNoise, int octave) {
        int width = baseNoise.length;
        int height = baseNoise[0].length;
        float[][] smoothNoise = new float[width][height];

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

                smoothNoise[x][y] = interpolate(top, bottom, vertical_blend);
            }
        }

        return smoothNoise;
    }

    public static float interpolate(float x0, float x1, float alpha) {
        return x0 * (1 - alpha) + alpha * x1;
    }
}
