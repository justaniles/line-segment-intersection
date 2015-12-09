Project Phase II
Axis Parallel Line Segment Intersection Problem
Julia Binger, John Myhre, Justin Niles
-------------------------------------------------

Random Lines Generation:
We use the java Random util to generate n horizontal and n vertical lines within the given bounds.
We have a Line class that holds the two end points of the line and a boolean indicator of
whether the line is vertical or horizontal. The end points come from our EndPoint class, which
just holds and x and a y integer corresponding to that point's coordinates. These lines are
stored in an array of Lines that our algorithms use to find intersections.

Brute Force Algorithm:
We use the BruteForce class to find all of the intersections in our graph using a brute force method.
It essentially iterates through the array of Lines and tests every combinationof lines to see if they
intersect and then reports them to its output file.

Range Algorithm:
We use the RangeAlgorithm class to find all of the intersections in our graph using an AVL tree with
linked leaf nodes, which is behaves like a B+ tree of degree 1, as we described in phase 1.

How to run:
Our algorithm executes from the Main class. The Main class takes the given sequence of n values,
generates the random lines, finds intersections using brute force, and then finds intersections
using the range algorithm. Main outputs a list of intersections found for each n to brute_force.txt
and range_algorithm.txt, accordingly. It also keeps track of the execution time at each iteration
of n and outputs them to the console. These are the values used to construct our performance graph.

Speed up notes:
In general, we have achieved a speed up of 2X with this algorithm. We believe a limiting factor in the
range algorithm is the time it takes to balance the tree. Because of this balancing, the brute force search
is sometimes faster than the range algorithm search when n = 20000. Since there are so many lines and
so many intersections, balancing the tree becomes cumbersome. 