package com.datalgo;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        int[] nValues = {10, 100, 500, 1000, 2000, 10000, 20000};
        ArrayList<Line[]> lines = new ArrayList<>(nValues.length);
        PrintWriter bf = new PrintWriter("brute_force.txt", "UTF-8");
        PrintWriter rf = new PrintWriter("range_algorithm.txt", "UTF-8");
        long startTime, endTime;

        // Generate random lines:
        for (int n : nValues) {
            lines.add(GenerateLines.genLines(n));
        }

        // Run brute force algorithm:
        for(int i = 0; i < nValues.length; i++) {
            System.out.print("n = " + nValues[i]);
            bf.println("n = " + nValues[i] + "\n");

            startTime = System.nanoTime();
            BruteForce.bruteForce(lines.get(i), bf);
            endTime = System.nanoTime();

            String elapsed = "Time elapsed: "
                    + (endTime - startTime) / 1000000 + "\n";
            System.out.println(elapsed);
            bf.println(elapsed);
        }

        // Run efficient algorithm:
        RangeAlgorithm ra;
        for(int i = 0; i < nValues.length; i++) {
            Line[] curLines = lines.get(i);

            // Convert curLines array to endpoints
            EndPoint[] endPoints = new EndPoint[2*curLines.length];
            for (int j = 0; j < curLines.length; j++) {
                endPoints[2*j] = curLines[j].start();
                endPoints[2*j+1] = curLines[j].end();
            }
            ra = new RangeAlgorithm(endPoints, rf);

            System.out.print("n = " + nValues[i]);
            bf.println("n = " + nValues[i] + "\n");

            startTime = System.nanoTime();
            ra.run();
            endTime = System.nanoTime();

            String elapsed = "Time elapsed: "
                    + (endTime - startTime) / 1000000 + "\n";
            System.out.println(elapsed);
            bf.println(elapsed);
        }

        bf.close();
        rf.close();
    }

}
