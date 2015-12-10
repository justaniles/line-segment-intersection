Project Phase II
Axis Parallel Line Segment Intersection Problem
Julia Binger, John Myhre, Justin Niles
-------------------------------------------------

Random Lines Generation:
We use the java Random util to generate n horizontal and n vertical lines within the given bounds.
We have a Line class that holds the two end points of the line and a boolean indicator of
whether the line is vertical or horizontal. The end points come from our EndPoint class, which
just holds and x and a y integer corresponding to that point's coordinates and a reference to that
EndPoint's parent line. These lines are stored in an array of Lines that our algorithms use to find 
intersections.

Brute Force Algorithm:
We use the BruteForce class to find all of the intersections in our graph using a brute force method.
It essentially iterates through the array of Lines and tests every combination of lines to see if they
intersect and then reports them to its output file.

Range Algorithm:
We use the RangeAlgorithm class to instantiate and run a range algorithm to report intersections.
Instead of using a B+ tree, with degree of 1, as outlined in phase 1, we opted to use a balanced
BST, in the form of an AVL tree. We found an AVL tree implementation online and modified it to support
duplicate nodes by having each node maintain an ArrayList of values, at the end of which duplicate
values would be appended.
The AVL tree maintained the status line of our algorithm, as outlined in phase 1, and whenever we
encountered a horizontal line from the event queue, we performed a recursive range query over the
AVL tree to report all intersecting vertical lines.

How to run:
Our algorithm executes from the Main class. The Main class takes the given sequence of n values,
generates the random lines, finds intersections using brute force, and then finds intersections
using the range algorithm. Main outputs a list of intersections found for each n to brute_force.txt
and range_algorithm.txt, accordingly. It also keeps track of the execution time at each iteration
of n and outputs them to the console. These are the values used to construct our performance graph.

Graph:
The performance graph for each algorithm is found in datalgo.png. It shows the run times for
the algorithms when they are not spending time printing the output to files. Thus when the
program is run in the version submitted, the running time may be a bit slower. This can be 
changed by commenting out the lines pertaining to printing the output. 
