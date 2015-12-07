package com.datalgo;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by justinniles on 12/6/15.
 */
public class Algorithm {

    public static void efficientAlgorithm(EndPoint[] endPoints) {
        // Sort endPoints in descending y-coord
        Arrays.sort(endPoints, new YCoordComparator());

        BPTree<Integer, EndPoint> bptree =
                new BPTree<Integer, EndPoint>(new XCoordComparator(), 3, 1);

    }

}

class YCoordComparator implements Comparator<EndPoint> {
    @Override
    public int compare(EndPoint a, EndPoint b) {
        if (a.y() < b.y()) {
            return -1;
        }
        else if (a.y() == b.y()) {
            return 0;
        }
        else {
            return 1;
        }
    }
}
class XCoordComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer a, Integer b) {
        if (a < b) {
            return -1;
        }
        else if (a == b) {
            return 0;
        }
        else {
            return 1;
        }
    }
}
