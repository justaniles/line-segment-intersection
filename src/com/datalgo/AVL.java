package com.datalgo;

// On my honor:
//
// - I have not discussed the Java language code in my program with
// anyone other than my instructor or the teaching assistants
// assigned to this course.
//
// - I have not used Java language code obtained from another student,
// or any other unauthorized source, either modified or unmodified.
//
// - If any Java language code or documentation used in my program
// was obtained from another source, such as a text book or course
// notes, that has been clearly noted with a proper citation in
// the comments of my program.
//
// - I have not designed this program in such a way as to defeat or
// interfere with the normal operation of the Curator System.
//
// John Myhre

/**
 *  Implementation of a generic AVL tree. Does not support deletion.
 *  @param <T>
 *
 *  @author johncmyh
 *  @version Jun 20, 2014
 */
public class AVL<T extends Comparable<? super T>>
{
    // -------------------------------------------------------------------------
    /**
     * BinaryNode class
     *
     * @author johncmyh
     * @version Jun 2, 2014
     */
    class BinaryNode
    {
        // Initialize a childless binary node.
        // Pre: elem is not null
        // Post: (in the new node)
        // element == elem
        // left == right == null
        public BinaryNode(T elem)
        {
            element = elem;
            left = right = null;
            height = 1;
        }


        // Initialize a binary node with children.
        // Pre: elem is not null
        // Post: (in the new node)
        // element == elem
        // left == lt, right == rt
        public BinaryNode(T elem, BinaryNode lt, BinaryNode rt)
        {
            element = elem;
            left = lt;
            right = rt;
            height = 1;
        }

        T          element; // the data in the node
        BinaryNode left;   // pointer to the left child
        BinaryNode right;  // pointer to the right child
        int        height; // the height of the subtree of that node
    }

    BinaryNode root;    // pointer to root node, if present
    boolean    success; //boolean that stores whether the operation was successful



    // ----------------------------------------------------------
    /**
     * Create a new AVL object.
     * Initialize empty AVL
     * Pre: none
     * Post: (in the new tree)
     * root == null
     */
    public AVL()
    {
        root = null;
    }

    private static final int ALLOWED_IMBALANCE = 1;



    /**
     * private method taken from the course textbook that performs a right
     * rotation
     * @param k2 the BinaryNode level that is imbalanced.
     */
    private BinaryNode rotateWithLeftChild(BinaryNode k2)
    {
        //rotates right
        BinaryNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;

        //reassigns the heights of the 2 nodes changed
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;
        return k1;
    }

    /**
     * private method taken from the course textbook that performs a left
     * rotation
     * @param k2 BinaryNode level that has become imbalanced
     */
    private BinaryNode rotateWithRightChild(BinaryNode k2)
    {
        //rotates left
        BinaryNode k1 = k2.right;
        k2.right = k1.left;
        k1.left = k2;

        //reassigns the heights of the 2 nodes changed
        k2.height = Math.max(height(k2.right), height(k2.left)) + 1;
        k1.height = Math.max(height(k1.right), k2.height) + 1;
        return k1;
    }

    /**
     * private method taken from the course textbook that performs a left
     * rotation, and then a right rotation
     * @param k3 the node that has become imbalanced
     */
    private BinaryNode doubleWithLeftChild(BinaryNode k3)
    {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    /**
     * private method taken from the course textbook that performs a right
     * rotation, and then a left rotation
     * @param k4 the node that has become imbalanced
     */
    private BinaryNode doubleWithRightChild(BinaryNode k4)
    {
        k4.right = rotateWithLeftChild(k4.right);
        return rotateWithRightChild(k4);
    }

    /**
     * find helper method
     * @param x the element to find
     * @param sRoot the current node
     */
    private T find(T x, BinaryNode sRoot)
    {
        if (sRoot == null)
        {
            return null;
        }

        int compareResult = x.compareTo(sRoot.element);

        if (compareResult < 0)
        {
            //moves to the left child
            return find(x, sRoot.left);
        }
        else if (compareResult > 0)
        {
            //moves to the right child
            return find(x, sRoot.right);
        }
        else
        {
            //returns the current element
            return sRoot.element;
        }
    }

    /**
     * private method taken from the course textbook that performs an insert
     * and then tries to balance the tree
     * @param x the element to be inserted
     * @param t the root node
     */
    private BinaryNode insert(T x, BinaryNode t)
    {
        if(t == null)
        {
            //indicates a successful insertion
            success = true;

            //returns a new node with the inserted element
            return new BinaryNode(x, null, null);
        }

        int compareResult = x.compareTo(t.element);

        if(compareResult < 0)
        {
            t.left = insert(x, t.left);
        }
        else if( compareResult > 0)
        {
            t.right = insert(x, t.right);
        }
        else
        {
            success = false; // duplicate, return false in public method
        }
        return balance(t);
    }

    /**
     * private method taken from the course textbook that checks if the tree
     * is imbalanced and then performs rotations if needed.
     * @param t the node that would be imbalanced
     */
    private BinaryNode balance(BinaryNode t)
    {
        if(t == null)
        {
            return t;
        }

        if(height(t.left) - height(t.right) > ALLOWED_IMBALANCE)
        {
            if(height(t.left.left) >= height(t.left.right))
            {
                t = rotateWithLeftChild(t);
            }
            else
            {
                t = doubleWithLeftChild(t);
            }
        }
        else
        {
            if(height(t.right) - height(t.left) > ALLOWED_IMBALANCE)
            {
                if(height(t.right.right) >= height(t.right.left))
                {
                    t = rotateWithRightChild(t);
                }
                else
                {
                    t = doubleWithRightChild(t);
                }
            }
        }
        t.height = Math.max(height(t.left), height(t.right)) + 1;
        return t;
    }

    /**
     * private equals helper method
     * @param root1 the first trees root node
     * @param root2 the second trees root node
     */
    private boolean equals(BinaryNode root1, BinaryNode root2)
    {
        boolean left = true;
        boolean right = true;
        boolean b = true;


        if(root1 == null && root2 == null)
        {
            return true;
        }
        else if(root1 == null || root2 == null)
        {
            return false;
        }
        if(root1.element.equals(root2.element))
        {
            if(root1.right != null && root2.right != null)
            {
                right = equals(root1.right, root2.right);
            }
            if(root1.left != null && root2.left != null)
            {
                left = equals(root1.left, root2.left);
            }
            return b && left && right;
        }
        b = false;
        return b;
    }


    /**
     * private height helper method taken from the course textbook
     */
    private int height(BinaryNode sRoot)
    {
        return sRoot == null ? 0 : sRoot.height;
    }


    // Return number of levels in the tree.
    // Pre: tree is a valid AVL<> object
    // Post: the AVL tree is unchanged
    public int height()
    {
        //calls helper method
        return height(root);
    }


    // Return true iff AVL contains no nodes.
    // Pre: none
    // Post: the AVL tree is unchanged
    public boolean isEmpty()
    {
        if (root == null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    // Return pointer to matching data element, or null if no matching
    // element exists in the AVL. "Matching" should be tested using the
    // data object's compareTo() method.
    // Pre: x is null or points to a valid object of type T
    // Post: the binary tree is unchanged
    public T find(T x)
    {
        //passes the element to find and the root node to the private helper
        return find(x, root);
    }


    // Insert element x into AVL, unless it is already stored. Return true
    // if insertion is performed and false otherwise.
    // Pre: x is null or points to a valid object of type T
    // Post: the binary tree contains x
    public boolean insert(T x)
    {
        //resets the boolean
        success = false;

        //passes the element to insert and the root node to the private method
        root = insert(x, root);

        if (success)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    // Return the tree to an empty state.
    // Pre: none
    // Post: the binary tree contains no elements
    public void clear()
    {
        root = null;
    }


    // Return true iff other is a AVL that has the same physical structure
    // and stores equal data values in corresponding nodes. "Equal" should
    // be tested using the data object's equals() method.
    // Pre: other is null or points to a valid AVL<> object, instantiated
    // on the same data type as the tree on which equals() is invoked
    // Post: both binary trees are unchanged
    public boolean equals(Object other)
    {
        if(other instanceof AVL)
        {
            AVL<T> tree2 = (AVL<T>)other;
            if(root == null && tree2.root == null)
            {
                return true;
            }
            return equals(root, tree2.root);
        }
        else
        {
            return false;
        }

    }
}
