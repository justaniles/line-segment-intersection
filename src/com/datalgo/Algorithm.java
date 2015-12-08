package com.datalgo;

import java.util.Arrays;
import java.util.Comparator;

public class Algorithm {

    public static void efficientAlgorithm(EndPoint[] endPoints) {
        // Sort endPoints in ascending y-coord
        Arrays.sort(endPoints, new SortVertical());

        AVL<Integer, EndPoint> statusLine = new AVL<>();
        Line parentLine;
        for (EndPoint currentEndPt : endPoints) {
            parentLine = currentEndPt.getParentLine();

            // Horizontal line:
            // Perform range search and print out intersecting vertical lines.
            if (parentLine.isHoriz()) {
                
            }
            // Vertical line:
            // If this is the starting (top) endpt, add to statusLine,
            // otherwise delete.
            else {
                if (currentEndPt.equals(parentLine.start())) {
                    statusLine.insert(currentEndPt.x(), currentEndPt);
                }
                else {
                    statusLine.delete(currentEndPt.x(), currentEndPt);
                }
            }
        }



    }

}

class SortVertical implements Comparator<EndPoint> {

    /**
     * This function will be used to sort the endPoint array with the largest
     * y-coordinate first. Doing so will enable us to traverse the endPoints
     * in a top-to-bottom manner.
     * @param a
     * @param b
     * @return
     */
    @Override
    public int compare(EndPoint a, EndPoint b) {
        if (a.y() < b.y()) {
            return 1;
        }
        else if (a.y() == b.y()) {
            return 0;
        }
        else {
            return -1;
        }
    }
}
