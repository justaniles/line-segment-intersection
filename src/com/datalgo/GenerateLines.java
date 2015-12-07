package com.datalgo;

import java.util.Random;

/**
 * Created by justinniles on 12/6/15.
 */
public class GenerateLines {

    private static int upperBound = 1000000;

    public static Line[] genLines(int n) {
        System.out.println("PRINTING");
        Line[] lines = new Line[2*n];
        Random randomGenerator = new Random();
        //generate n horizontal lines
        for(int i = 0; i < n; i++) {
            int startX = randomGenerator.nextInt(upperBound);
            int maxLen = Math.min(upperBound-startX, 25);
            int endX = startX + randomGenerator.nextInt(maxLen);
            int y = randomGenerator.nextInt(upperBound);
            lines[i] = new Line(new EndPoint(startX, y), new EndPoint(endX, y), true);
            System.out.println(lines[i]);
        }

        //generate n vertical lines
        for(int j = n; j < 2*n; j++) {
            int startY = randomGenerator.nextInt(upperBound);
            int maxLen = Math.min(startY, 25);
            int endY = startY - randomGenerator.nextInt(maxLen);
            int x = randomGenerator.nextInt(upperBound);
            lines[j] = new Line(new EndPoint(x, startY), new EndPoint(x, endY), false);
            System.out.println(lines[j]);
        }

        return lines;
    }

}
