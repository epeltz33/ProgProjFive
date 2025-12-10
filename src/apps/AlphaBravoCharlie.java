package apps;

import adts.*;

public class AlphaBravoCharlie {

	public static void main(String[] args) {

		BinarySearchTree<String> abcTree = new BinarySearchTree<String>();

		abcTree.add("November");
		abcTree.add("Bravo");
		abcTree.add("Sierra");
		abcTree.add("Alpha");
		abcTree.add("Echo");
		abcTree.add("Romeo");
		abcTree.add("Tango");
		abcTree.add("India");
		abcTree.add("Yankee");

		System.out.println("PREORDER: ");
		abcTree.setTraversalType("pre");
		abcTree.setTraversalType("pre");
		for (String s : abcTree) {
			System.out.print(s + " ");
		}
		System.out.println();

		System.out.println("INORDER: ");
		abcTree.setTraversalType("in");
		for (String s : abcTree) {
			System.out.print(s + " ");
		}
		System.out.println();

		System.out.println("POSTORDER: ");
		abcTree.setTraversalType("post");
		for (String s : abcTree) {
			System.out.print(s + " ");
		}
		System.out.println();
	}

}
