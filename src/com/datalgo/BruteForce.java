package com.datalgo;

/**
 * Created by justinniles on 12/6/15.
 */
public class BruteForce {

    public static void bruteForce(Line[] lines) {
        for (int i = 0; i < lines.length; i++) {
            for (int j = i; j < lines.length; j++) {
                if (lines[i].isHoriz() ^ lines[j].isHoriz()) {
                    if (lines[i].isHoriz()) {
                        if(lines[i].start().x() <= lines[j].start().x()
                                && lines[i].end().x() >= lines[j].start().x()) {
                            System.out.println(lines[i] + " intersects with " + lines [j]);
                        }
                    }
                    else if (lines[j].start().x() <= lines[i].start().x()
                            && lines[j].end().x() >= lines[i].start().x()) {
                        System.out.println(lines[j] + " intersects with " + lines [i]);
                    }
                }
            }
        }
    }

}
