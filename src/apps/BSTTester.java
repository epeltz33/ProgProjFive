package apps;

import adts.BinarySearchTree;

/**
 * Test program for the enhanced BinarySearchTree implementation.
 * Tests both original functionality and new methods using multiple test trees.
 */
public class BSTTester {
    public static void main(String[] args) {
        // Test Perfect Tree
        System.out.println("=== Testing Perfect Binary Search Tree ===");
        BinarySearchTree<Integer> perfectTree = new BinarySearchTree<>();
        // Creating a perfect BST with 7 nodes
        perfectTree.add(50);
        perfectTree.add(30);
        perfectTree.add(70);
        perfectTree.add(20);
        perfectTree.add(40);
        perfectTree.add(60);
        perfectTree.add(80);

        testTreeProperties(perfectTree, "Perfect Tree");
        testTraversals(perfectTree, "Perfect Tree");

        // Test Unbalanced Tree
        System.out.println("\n=== Testing Unbalanced Binary Search Tree ===");
        BinarySearchTree<Integer> unbalancedTree = new BinarySearchTree<>();
        // Creating an unbalanced BST (right-heavy)
        unbalancedTree.add(10);
        unbalancedTree.add(20);
        unbalancedTree.add(30);
        unbalancedTree.add(40);
        unbalancedTree.add(50);
        unbalancedTree.add(60);
        unbalancedTree.add(70);

        testTreeProperties(unbalancedTree, "Unbalanced Tree");
        testTraversals(unbalancedTree, "Unbalanced Tree");

        // Test String-based tree
        System.out.println("\n=== Testing String Binary Search Tree ===");
        BinarySearchTree<String> stringTree = new BinarySearchTree<>();
        stringTree.add("Mercury");
        stringTree.add("Venus");
        stringTree.add("Earth");
        stringTree.add("Mars");
        stringTree.add("Jupiter");
        stringTree.add("Saturn");
        stringTree.add("Uranus");

        testTreeProperties(stringTree, "String Tree");
        testTraversals(stringTree, "String Tree");

        // Test operations
        System.out.println("\n=== Testing Tree Operations ===");
        testOperations(perfectTree);
    }

    private static void testTreeProperties(BinarySearchTree<?> tree, String treeName) {
        System.out.println("\nTesting properties of " + treeName + ":");
        System.out.println("Size: " + tree.size());
        System.out.println("Is Empty: " + tree.isEmpty());
        System.out.println("Height (recursive): " + tree.treeHeight());
        System.out.println("Height (iterative): " + tree.treeHeight2());
        System.out.println("Is Perfect (recursive): " + tree.isPerfect());
        System.out.println("Is Perfect (iterative): " + tree.isPerfect2());
        System.out.println("Balance Score 1: " + tree.balanceScore1());
        System.out.println("Balance Score 2: " + tree.balanceScore2());
    }

    private static void testTraversals(BinarySearchTree<?> tree, String treeName) {
        System.out.println("\nTesting traversals of " + treeName + ":");

        // Test in-order traversal
        tree.setTraversalType("in");
        System.out.print("In-order traversal:    ");
        for (Object value : tree) {
            System.out.print(value + " ");
        }
        System.out.println();

        // Test pre-order traversal
        tree.setTraversalType("pre");
        System.out.print("Pre-order traversal:   ");
        for (Object value : tree) {
            System.out.print(value + " ");
        }
        System.out.println();

        // Test post-order traversal
        tree.setTraversalType("post");
        System.out.print("Post-order traversal:  ");
        for (Object value : tree) {
            System.out.print(value + " ");
        }
        System.out.println();

        // Test random traversal
        tree.setTraversalType("random");
        System.out.print("Random traversal 1:    ");
        for (Object value : tree) {
            System.out.print(value + " ");
        }
        System.out.println();
        System.out.print("Random traversal 2:    ");
        for (Object value : tree) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    private static void testOperations(BinarySearchTree<Integer> tree) {
        // Test contains
        System.out.println("Contains 30: " + tree.contains(30));
        System.out.println("Contains 45: " + tree.contains(45));

        // Test get
        System.out.println("Get 50: " + tree.get(50));
        System.out.println("Get 90: " + tree.get(90));

        // Test remove
        System.out.println("\nRemoving elements:");
        System.out.println("Remove 30: " + tree.remove(30));
        System.out.println("Tree after removing 30:");
        tree.setTraversalType("in");
        for (Integer value : tree) {
            System.out.print(value + " ");
        }
        System.out.println("\nRemove 90 (non-existent): " + tree.remove(90));

        // Test rebalancing
        System.out.println("\nRebalancing tree...");
        tree.rebalance();
        System.out.println("Tree after rebalancing:");
        for (Integer value : tree) {
            System.out.print(value + " ");
        }
        System.out.println("\nNew height after rebalancing: " + tree.treeHeight());
    }
}