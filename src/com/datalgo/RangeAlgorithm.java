package com.datalgo;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class RangeAlgorithm {

    private EndPoint[] endPoints;
    private PrintWriter out;

    public RangeAlgorithm(EndPoint[] endPoints, PrintWriter out) {
        this.endPoints = endPoints;
        this.out = out;
    }

    public void run() {
        // Sort endPoints in ascending y-coord
        Arrays.sort(endPoints, new SortVertically());

        AVL<Integer, EndPoint> statusLine = new AVL<>();
        Line parentLine;
        int startVal, endVal;
        AVL.Node curNode;
        ArrayList<EndPoint> nodeList;

        for (EndPoint currentEndPt : endPoints) {
            parentLine = currentEndPt.getParentLine();

            // Horizontal line:
            // Perform range search and print out intersecting vertical lines.
            if (parentLine.isHoriz()) {
                findIntersections(statusLine.getRoot(), parentLine);
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

    @SuppressWarnings("unchecked")
    private void findIntersections(AVL.Node node, Line horizLine) {
        if (node == null) {
            return;
        }

        int startVal = horizLine.start().x();
        int endVal = horizLine.end().x();
        int keyValue = (int) node.key();

        if (keyValue < startVal) {
            // Node is to the left of horiz line
            findIntersections(node.right, horizLine);
        }
        else if (keyValue > endVal) {
            // Node is to the right of horiz line
            findIntersections(node.left, horizLine);
        }
        else {
            // This node intersects, report all vertical lines at this point
            ArrayList<EndPoint> values = (ArrayList<EndPoint>) node.valueList();
            for (EndPoint curPoint : values) {
                if (curPoint.getParentLine().isHoriz()) {
                    continue;
                }
                reportIntersection(curPoint.getParentLine(), horizLine);
            }
        }
    }

    private void reportIntersection(Line l1, Line l2) {
        out.println(l1 + " intersects with " + l2);
    }
}

class SortVertically implements Comparator<EndPoint> {

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
