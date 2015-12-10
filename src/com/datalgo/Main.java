package com.datalgo;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

    private static final boolean EXECUTE_BRUTE_FORCE = true;
    private static final boolean EXECUTE_RANGE_ALGORITHM = true;

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        int[] nValues = {1, 10, 100, 500, 1000, 2000, 10000, 20000, 30000};

        ArrayList<Line[]> lines = new ArrayList<>(nValues.length);
        PrintWriter bf = new PrintWriter("brute_force.txt", "UTF-8");
        PrintWriter rf = new PrintWriter("range_algorithm.txt", "UTF-8");
        long startTime, endTime;
        LinkedList<String> result;

        // Generate random lines:
        for (int n : nValues) {
            lines.add(GenerateLines.genLines(n));
        }

        // Run brute force algorithm:
        if (EXECUTE_BRUTE_FORCE) {
            writeOut("----Executing Brute Force\n", bf);
            for(int i = 0; i < nValues.length; i++) {
                writeOut("n = " + nValues[i] + " - ", bf);

                startTime = System.nanoTime();
                result = BruteForce.bruteForce(lines.get(i));
                endTime = System.nanoTime();

                writeOut("Time elapsed: "
                        + (endTime - startTime) / 1000000
                        + " (" + result.size() + " intersections)\n", bf);

                // Print resulting intersections
                for (String intersection : result) {
                    bf.println(intersection);
                }
            }
        }

        // Run range algorithm:
        if (EXECUTE_RANGE_ALGORITHM) {
            writeOut("----Executing Range Algorithm\n", rf);
            RangeAlgorithm algorithm;
            for(int i = 0; i < nValues.length; i++) {
                Line[] curLines = lines.get(i);

                // Convert curLines array to endpoints
                EndPoint[] endPoints = new EndPoint[2*curLines.length];
                for (int j = 0; j < curLines.length; j++) {
                    endPoints[2*j] = curLines[j].start();
                    endPoints[2*j+1] = curLines[j].end();
                }
                algorithm = new RangeAlgorithm(endPoints);

                // Time and execute algorithm
                writeOut("n = " + nValues[i] + " - ", rf);

                startTime = System.nanoTime();
                result = algorithm.run();
                endTime = System.nanoTime();

                writeOut("Time elapsed: "
                        + (endTime - startTime) / 1000000
                        + " (" + result.size() + " intersections)\n", rf);

                // Print resulting intersections
                for (String intersection : result) {
                    rf.println(intersection);
                }
            }
        }

        bf.close();
        rf.close();
    }

    private static void writeOut(String s, PrintWriter pw) {
        System.out.print(s);
        pw.print(s);
    }

}
