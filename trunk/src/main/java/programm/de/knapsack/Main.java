package programm.de.knapsack;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] werte = { 2, 7, 6, 8, 4, 1, 4};
		int[] gewichte = {1, 4, 7, 11, 6, 2, 8};
		
		Rucksack def = new Rucksack(werte, gewichte, 15);
		
		Rucksack root = new Rucksack(20, 10, 5, 25);
		
		System.out.println("Ausgangszustand Rucksackinhalte:");
		def.print();
		
		BranchAndBound Algo = new BranchAndBound(def);
		Algo.start();
		

	}

}
