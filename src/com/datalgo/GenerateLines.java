package com.datalgo;

import java.util.Random;

public class GenerateLines {

    private static int UPPER_BOUND = 100000;
    private static int MAX_LENGTH = 100;

    public static Line[] genLines(int n) {
        Line curLine;
        Line[] lines = new Line[2*n];
        Random randomGenerator = new Random();

        //generate n horizontal lines
        for(int i = 0; i < n; i++) {
            int startX = randomGenerator.nextInt(UPPER_BOUND);
            int maxLen = Math.min(UPPER_BOUND-startX, MAX_LENGTH);
            int endX = startX + maxLen;
            int y = randomGenerator.nextInt(UPPER_BOUND);
            curLine = new Line();
            curLine.set(
                    new EndPoint(startX, y, curLine),
                    new EndPoint(endX, y, curLine),
                    true);
            lines[i] = curLine;
        }

        //generate n vertical lines
        for(int j = n; j < 2*n; j++) {
            int startY = randomGenerator.nextInt(UPPER_BOUND) + 1;
            int maxLen = Math.min(startY, MAX_LENGTH);
            int endY = startY - maxLen;
            int x = randomGenerator.nextInt(UPPER_BOUND);
            curLine = new Line();
            curLine.set(
                    new EndPoint(x, startY, curLine),
                    new EndPoint(x, endY, curLine),
                    false);
            lines[j] = curLine;
        }

        return lines;
    }

}
