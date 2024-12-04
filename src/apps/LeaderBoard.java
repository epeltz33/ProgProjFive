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

	}
}