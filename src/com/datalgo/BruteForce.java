package com.datalgo;

import java.io.PrintWriter;
import java.util.LinkedList;

public class BruteForce {

    public static LinkedList<String> bruteForce(Line[] lines) {
        LinkedList<String> intersections = new LinkedList<>();

        for (int i = 0; i < lines.length; i++) {
            for (int j = i + 1; j < lines.length; j++) {
                if (lines[i].isHoriz() ^ lines[j].isHoriz()) {
                    if (lines[i].isHoriz()) {
                        if(lines[i].start().x() <= lines[j].start().x()
                                && lines[i].end().x() >= lines[j].start().x()
                                && lines[i].start().y() <= lines[j].start().y()
                                && lines[i].start().y() >= lines[j].end().y()) {
                            intersections.add(
                                    lines[i] + " intersects with " + lines [j]);
                        }
                    }
                    else if (lines[j].start().x() <= lines[i].start().x()
                            && lines[j].end().x() >= lines[i].start().x()
                            && lines[j].start().y() <= lines[i].start().y()
                            && lines[j].start().y() >= lines[i].end().y()) {
                        intersections.add(
                                lines[j] + " intersects with " + lines [i]);
                    }
                }
            }
        }

        return intersections;
    }

}
