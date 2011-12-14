package programm.de.knapsack;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Rucksack root = new Rucksack(20, 10, 5, 25);
		BranchAndBound Algo = new BranchAndBound(root);
		Algo.start();
		

	}

}
