
package apps;

import adts.BinarySearchTree;

public class LeaderBoard {

	public static void main(String[] args) {

		BinarySearchTree<Golfer> golfers = new BinarySearchTree<Golfer>();

		golfers.add(new Golfer("Hideki", 74));
		golfers.add(new Golfer("Cheyenne", 73));
		golfers.add(new Golfer("Matt", 77));
		golfers.add(new Golfer("Belén", 76));
		golfers.add(new Golfer("Brooks", 75));
		golfers.add(new Golfer("Natalie", 68));
		golfers.add(new Golfer("Xander", 71));
		golfers.add(new Golfer("Lexi", 79));
		golfers.add(new Golfer("Rory", 70));

		// display order of finish:
		int sum = 0;
		int count = 0;
		int median = 0;
		int midPt = 1 + golfers.size() / 2;
		System.out.println("Leader Board\n------------");
		golfers.setTraversalType("in");
		for (Golfer g : golfers) {
			System.out.println(g);
			sum += g.getScore();
			count++;
			if (count == midPt) {
				median = g.getScore();
			}
		}
		System.out.println("\nAverage score: " + (double) sum / golfers.size());
		System.out.println(" Median score: " + median);
	}
}
