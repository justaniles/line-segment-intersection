package com.datalgo;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

    private static final boolean EXECUTE_BRUTE_FORCE = false;
    private static final boolean EXECUTE_RANGE_ALGORITHM = true;

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        int[] nValues = {1, 10, 100, 500, 1000, 2000, 10000, 20000};
        ArrayList<Line[]> lines = new ArrayList<>(nValues.length);
        PrintWriter bf = new PrintWriter("brute_force.txt", "UTF-8");
        PrintWriter rf = new PrintWriter("range_algorithm.txt", "UTF-8");
        long startTime, endTime;

        // Generate random lines:
        for (int n : nValues) {
            lines.add(GenerateLines.genLines(n));
        }

        // Run brute force algorithm:
        if (EXECUTE_BRUTE_FORCE) {
            for(int i = 0; i < nValues.length; i++) {
                System.out.print("n = " + nValues[i] + " - ");
                bf.println("n = " + nValues[i]);

                startTime = System.nanoTime();
                BruteForce.bruteForce(lines.get(i), bf);
                endTime = System.nanoTime();

                String elapsed = "Time elapsed: "
                        + (endTime - startTime) / 1000000;
                System.out.println(elapsed);
                bf.println(elapsed);
            }
        }

        // Run range algorithm:
        if (EXECUTE_RANGE_ALGORITHM) {
            RangeAlgorithm algorithm;
            LinkedList<String> result;
            for(int i = 0; i < nValues.length; i++) {
                Line[] curLines = lines.get(i);

                // Convert curLines array to endpoints
                EndPoint[] endPoints = new EndPoint[2*curLines.length];
                for (int j = 0; j < curLines.length; j++) {
                    endPoints[2*j] = curLines[j].start();
                    endPoints[2*j+1] = curLines[j].end();
                }
                algorithm = new RangeAlgorithm(endPoints);

                System.out.print("n = " + nValues[i] + " - ");
                rf.print("n = " + nValues[i] + " - ");

                startTime = System.nanoTime();
                result = algorithm.run();
                endTime = System.nanoTime();

                String elapsed = "Time elapsed: "
                        + (endTime - startTime) / 1000000;
                System.out.println(elapsed);
                rf.println(elapsed);

                // Print resulting intersections
                for (String intersection : result) {
                    rf.println(intersection);
                }
            }
        }

        bf.close();
        rf.close();
    }

}
