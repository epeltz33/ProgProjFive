package adts;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.Random;

import interfaces.*;
import iterators.BSTIterator;
import nodes.BSTNode;


enum TraversalType {
    PREORDER, INORDER, POSTORDER, REVORDER, RANDOM
}


public class BinarySearchTree<T extends Comparable<T>>
        implements BSTInterface<T>, Iterable<T> {

    protected BSTNode<T> root;

    boolean found;   // used by remove

    T[] rebalanceArray;  // for rebalancing the tree
    int rebalanceIndex;  //           "

    // for traversals
    protected TraversalType travType;
    ArrayList<T> travList;


    public BinarySearchTree() {
        root = null;
        travType = TraversalType.INORDER;
    }


    public void add (T element) {
        root = recAdd(element, root);
    }

    private BSTNode<T> recAdd(T element, BSTNode<T> tree) {
        if (tree == null) {
            tree = new BSTNode<T>(element);
        }
        else {
            if (element.compareTo(tree.getData()) <= 0) {
                tree.setLeft(recAdd(element, tree.getLeft()));  // add to left subtree

            }
            else {
                tree.setRight(recAdd(element, tree.getRight()));  // add to right subtree
            }
        }
        return tree;
    }


    public boolean remove (T element) {
        root = recRemove(element, root);
        return found;
    }

    private BSTNode<T> recRemove(T element, BSTNode<T> tree) {
        if (tree == null) {
            found = false;
        }
        else {
            if (element.compareTo(tree.getData()) < 0) {
                tree.setLeft(recRemove(element, tree.getLeft()));
            }
            else {
                if (element.compareTo(tree.getData()) > 0) {
                    tree.setRight(recRemove(element, tree.getRight()));
                }
                else {
                    tree = removeNode(tree);
                    found = true;
                }
            }
        }
        return tree;
    }

    private BSTNode<T> removeNode(BSTNode<T> tree) {

        T payload;

        if (tree.getLeft() == null) {
            return tree.getRight();
        }
        else {
            if (tree.getRight() == null) {
                return tree.getLeft();
            }
            else {
                payload = getPredecessor(tree.getLeft());
                tree.setData(payload);
                tree.setLeft(recRemove(payload, tree.getLeft()));
                return tree;
            }
        }
    }

    private T getPredecessor(BSTNode<T> tree) {
        while (tree.getRight() != null) {
            tree = tree.getRight();
        }
        return tree.getData();
    }


    public int size() {
        return recSize(root);
    }

    private int recSize(BSTNode<T> tree) {
        if (tree == null) {
            return 0;
        }
        else {
            return recSize(tree.getLeft()) + recSize(tree.getRight()) + 1;
        }
    }


    public boolean isEmpty() {
        return (root == null);
    }


    public boolean contains (T element) {
        return recContains(element, root);
    }

    private boolean recContains(T element, BSTNode<T> tree) {
        if (tree == null) {
            return false;
        }
        else {
            if (element.compareTo(tree.getData()) < 0) {
                return recContains(element, tree.getLeft());  // search left subtree
            }
            else {
                if (element.compareTo(tree.getData()) > 0) {
                    return recContains(element, tree.getRight());  // search right subtree
                }
                else {
                    return true;  // element.compareTo(tree, the subtree's root) == 0
                }
            }
        }
    }


    public T get(T element) {
        return recGet(element, root);
    }

    private T recGet(T element, BSTNode<T> tree) {
        if (tree == null) {
            return null;
        }
        else {
            if (element.compareTo(tree.getData()) < 0) {
                return recGet(element, tree.getLeft());  // get from left subtree
            }
            else {
                if (element.compareTo(tree.getData()) > 0) {
                    return recGet(element, tree.getRight());  // get from right subtree
                }
                else {
                    return tree.getData();  // element is found!
                }
            }
        }
    }

    // New method: Calculate tree height recursively
    public int treeHeight() {
        return recTreeHeight(root);
    }

    private int recTreeHeight(BSTNode<T> tree) {
        if (tree == null) {
            return -1;
        }
        int leftHeight = recTreeHeight(tree.getLeft());
        int rightHeight = recTreeHeight(tree.getRight());
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // New method: Calculate tree height iteratively
    public int treeHeight2() {
        if (root == null) {
            return -1;
        }

        LLQ<BSTNode<T>> queue = new LLQ<>();
        queue.enqueue(root);
        int height = -1;

        while (!queue.isEmpty()) {
            int levelSize = getQueueSize(queue);
            height++;

            while (levelSize > 0) {
                BSTNode<T> current = queue.dequeue();
                if (current.getLeft() != null) {
                    queue.enqueue(current.getLeft());
                }
                if (current.getRight() != null) {
                    queue.enqueue(current.getRight());
                }
                levelSize--;
            }
        }
        return height;
    }

    private int getQueueSize(LLQ<BSTNode<T>> queue) {
        int count = 0;
        LLQ<BSTNode<T>> tempQueue = new LLQ<>();
        while (!queue.isEmpty()) {
            tempQueue.enqueue(queue.dequeue());
            count++;
        }
        while (!tempQueue.isEmpty()) {
            queue.enqueue(tempQueue.dequeue());
        }
        return count;
    }

    // New method: Check if tree is perfect recursively
    public boolean isPerfect() {
        return recIsPerfect(root);
    }

    private boolean recIsPerfect(BSTNode<T> tree) {
        if (tree == null) {
            return true;
        }
        int leftHeight = recTreeHeight(tree.getLeft());
        int rightHeight = recTreeHeight(tree.getRight());

        if (leftHeight != rightHeight) {
            return false;
        }
        return recIsPerfect(tree.getLeft()) && recIsPerfect(tree.getRight());
    }

    // New method: Check if tree is perfect iteratively
    public boolean isPerfect2() {
        if (root == null) {
            return true;
        }
        int height = treeHeight2();
        int expectedSize = (1 << (height + 1)) - 1; // 2^(h+1) - 1
        return size() == expectedSize;
    }

    // New method: Calculate balance score based on subtree sizes
    public double balanceScore1() {
        if (root == null) {
            return 0.0;
        }
        int leftSize = (root.getLeft() != null) ? recSize(root.getLeft()) : 0;
        int rightSize = (root.getRight() != null) ? recSize(root.getRight()) : 0;
        return Math.abs(leftSize - rightSize) / (double)size();
    }

    // New method: Calculate balance score based on height difference
    public int balanceScore2() {
        if (root == null) {
            return 0;
        }
        int actualHeight = treeHeight();
        int size = size();
        int minHeight = (int)(Math.log(size + 1) / Math.log(2)) - 1;
        return actualHeight - minHeight;
    }

    // Modified to support RANDOM traversal
    public void setTraversalType(String order) {
        if (order.equalsIgnoreCase("pre")) {
            travType = TraversalType.PREORDER;
        }
        else if (order.equalsIgnoreCase("in")) {
            travType = TraversalType.INORDER;
        }
        else if (order.equalsIgnoreCase("post")) {
            travType = TraversalType.POSTORDER;
        }
        else if (order.equalsIgnoreCase("random")) {
            travType = TraversalType.RANDOM;
        }
        else {
            travType = TraversalType.INORDER;
        }
    }

    // Modified to support RANDOM traversal
    public Iterator<T> iterator() {
        travList = new ArrayList<>(size());

        switch (travType) {
            case INORDER:
                inOrder(root);
                break;
            case PREORDER:
                preOrder(root);
                break;
            case POSTORDER:
                postOrder(root);
                break;
            case RANDOM:
                randomOrder(root);
                break;
        }

        return new BSTIterator(travList);
    }

    private void inOrder(BSTNode<T> tree) {
        if (tree != null) {
            inOrder(tree.getLeft());
            travList.add(tree.getData());
            inOrder(tree.getRight());
        }
    }

    private void preOrder(BSTNode<T> tree) {
        if (tree != null) {
            travList.add(tree.getData());
            preOrder(tree.getLeft());
            preOrder(tree.getRight());
        }
    }

    private void postOrder(BSTNode<T> tree) {
        if (tree != null) {
            postOrder(tree.getLeft());
            postOrder(tree.getRight());
            travList.add(tree.getData());
        }
    }

    // New private method to support RANDOM traversal
    private void randomOrder(BSTNode<T> tree) {
        if (tree == null) {
            return;
        }
        ArrayList<T> elements = new ArrayList<>();
        collectElements(tree, elements);
        Random rand = new Random();
        while (!elements.isEmpty()) {
            int index = rand.nextInt(elements.size());
            travList.add(elements.remove(index));
        }
    }

    private void collectElements(BSTNode<T> tree, ArrayList<T> elements) {
        if (tree != null) {
            elements.add(tree.getData());
            collectElements(tree.getLeft(), elements);
            collectElements(tree.getRight(), elements);
        }
    }

    public void rebalance() {
        rebalanceArray = (T[]) new Comparable[size()];
        rebalanceIndex = -1;
        fillRebalanceArray(root);
        root = null;
        recRebalance(0, rebalanceArray.length - 1);
    }

    private void fillRebalanceArray(BSTNode<T> tree) {
        if (tree != null) {
            fillRebalanceArray(tree.getLeft());
            rebalanceArray[++rebalanceIndex] = tree.getData();
            fillRebalanceArray(tree.getRight());
        }
    }

    private void recRebalance(int first, int last) {
        if (first <= last) {
            int mid = first + (last - first) / 2;
            root = recAdd(rebalanceArray[mid], root);
            recRebalance(first, mid-1);
            recRebalance(mid+1, last);
        }
    }
}