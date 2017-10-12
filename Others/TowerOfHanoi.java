class TowerOfHanoi {

	public static void printInstructions(int height, String fromPole, String toPole, String withPole) {
		if(height == 0)
			return;

		printInstructions(height - 1, fromPole, withPole, toPole);
		System.out.println("Move disk from " + fromPole + " to " + toPole);
		printInstructions(height - 1, withPole, toPole, fromPole);
	}

	public static void main(String[] args) {
		int height = 3;

		// Assuming pegs' names are:
		// "A" : Source/Starting peg
		// "B" : Destination/Target peg
		// "C" : Temporary/Middle peg
		printInstructions(height, "A", "B", "C");
	}
}