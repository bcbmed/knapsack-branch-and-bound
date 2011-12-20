package programm.de.knapsack;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		final int kapazitaet = 35;
		final int maxWert=10;
		final int maxGewicht=10;
		final int anzahl=45;
		int[] gewichte = {1, 4, 7, 11, 6, 2, 8};
		int[] werte = { 2, 7, 6, 8, 4, 1, 4};
	
		
		//Rucksack def = new Rucksack(werte, gewichte, Kapazitaet);
		
		Rucksack def = new Rucksack(anzahl, maxWert, maxGewicht, kapazitaet);
		
		System.out.println("Ausgangszustand Rucksackinhalte:");
		def.print();
		System.out.println("OpbereSchranke ="+def.obereSchranke());
		System.out.println("UntereSchranke ="+def.untereSchranke());
		
		
		
		
		BranchAndBound Algo = new BranchAndBound(def);
		Algo.start();
		

	}

}
