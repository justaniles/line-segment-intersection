package com.datalgo;

import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) {
        //generate graph of speeds for  n = {10, 100, 500, 1000, 2000, 10000, 20000}
        int[] nValues = {10, 100, 500, 1000, 2000, 10000, 20000};
        PrintWriter bf = new PrintWriter("brute_force.txt", "UTF-8");
        

        for(int n : nValues) {
            Line[] lines = GenerateLines.genLines(n);
            BruteForce.bruteForce(lines);

        }

    }

}
