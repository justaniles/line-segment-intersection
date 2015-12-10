package com.datalgo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

public class RangeAlgorithm {

    private EndPoint[] endPoints;
    private LinkedList<String> intersections;

    public RangeAlgorithm(EndPoint[] endPoints) {
        this.endPoints = endPoints;
        intersections = new LinkedList<>();
    }

    public LinkedList<String> run() {
        // Sort endPoints in ascending y-coord
        Arrays.sort(endPoints, new SortVertically());

        Line parentLine;
        AVL<Integer, EndPoint> statusLine = new AVL<>();
        // Iterate over the endpoints, top to bottom
        for (EndPoint currentEndPt : endPoints) {
            parentLine = currentEndPt.getParentLine();

            // Horizontal line:
            // Perform range search and print out intersecting vertical lines.
            if (parentLine.isHoriz()) {
                if (currentEndPt.equals(parentLine.start())) {
                    findIntersections(statusLine.getRoot(), parentLine);
                }
            }
            // Vertical line:
            // If this is the starting (top) endpt, add to statusLine,
            // otherwise delete.
            else {
                if (currentEndPt.equals(parentLine.start())) {
                    statusLine.insert(currentEndPt.x(), parentLine.start());
                }
                else {
                    statusLine.delete(currentEndPt.x(), parentLine.start());
                }
            }
        }

        return intersections;
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
                reportIntersection(horizLine, curPoint.getParentLine());
            }
            findIntersections(node.left, horizLine);
            findIntersections(node.right, horizLine);
        }
    }

    private void reportIntersection(Line l1, Line l2) {
        // This fucker is taking a long time for whatever reason:
        intersections.add(l1 + " intersects with " + l2);
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
            Line aLine = a.getParentLine();
            Line bLine = b.getParentLine();
            // If a is horiz and b is vertical, or vice versa, order matters
            if (aLine.isHoriz() ^ bLine.isHoriz()) {
                if (aLine.isHoriz() && bLine.isStartPoint(b)) {
                    return 1;
                }
                else if (aLine.isHoriz() && !bLine.isStartPoint(b)) {
                    return -1;
                }
                else if (aLine.isStartPoint(b) && bLine.isHoriz()) {
                    return -1;
                }
                else if (!aLine.isStartPoint(b) && bLine.isHoriz()) {
                    return 1;
                }
            }

            return 0;
        }
        else {
            return -1;
        }
    }
}
