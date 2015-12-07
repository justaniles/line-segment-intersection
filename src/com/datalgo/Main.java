package com.datalgo;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        //generate graph of speeds for  n = {10, 100, 500, 1000, 2000, 10000, 20000}
        int[] nValues = {10, 100, 500, 1000, 2000, 10000, 20000};
        PrintWriter bf = new PrintWriter("brute_force.txt", "UTF-8");
        

        for(int n : nValues) {
            bf.println("\nn = " + n);
            Line[] lines = GenerateLines.genLines(n);
            BruteForce.bruteForce(lines, bf);

        }
        bf.close();
    }

}
