package com.datalgo;

import java.io.PrintWriter;

public class BruteForce {

    public static void bruteForce(Line[] lines, PrintWriter out) {
        for (int i = 0; i < lines.length; i++) {
            for (int j = i + 1; j < lines.length; j++) {
                if (lines[i].isHoriz() ^ lines[j].isHoriz()) {
                    if (lines[i].isHoriz()) {
                        if(lines[i].start().x() <= lines[j].start().x()
                                && lines[i].end().x() >= lines[j].start().x()
                                && lines[i].start().y() <= lines[j].start().y()
                                && lines[i].start().y() >= lines[j].end().y()) {
                            out.println(lines[i] + " intersects with " + lines [j]);
                        }
                    }
                    else if (lines[j].start().x() <= lines[i].start().x()
                            && lines[j].end().x() >= lines[i].start().x()
                            && lines[j].start().y() <= lines[i].start().y()
                            && lines[j].start().y() >= lines[i].end().y()) {
                        out.println(lines[j] + " intersects with " + lines [i]);
                    }
                }
            }
        }
    }

}
