package programm.de.knapsack;

import java.util.ArrayList;
import java.util.LinkedList;

/**Diese Klasse implementiert ein vollst�ndiges Enumerationverfahren zur L�sung des Rucksackproblems
 * Dazu werden alle m�glichen Kombinationen von Gegenst�nden f�r den Rucksack gebildet und gepr�ft
 * A
 * 
 * 
 * @author Cha0z
 *
 */
public class BruteForce {

	ArrayList<Gegenstand> G_Liste = null;

	int OS;
	int kap;

	public BruteForce() {

	}

	/**Initialisiert die Klasse mit einem zu l�senden Rucksack.
	 * 
	 * 
	 * @param r - zu l�sender Rucksack
	 */
	public void init(Rucksack r) {

		OS = r.obereSchranke();
		kap = r.getKapazitaet();

		this.G_Liste = r.clone().Gegenstaende;

	}

	

	
	/**Methode zum Ermitteln alle m�glichen Kombinationen von Gegenst�nden in einem Rucksack
	 * Es wird ein externer Kombinationsgenerator verwendet
	 * 
	 * @param n - L�nge der zu pr�fenden Kombinationen
	 * @return beste gefundene Kombination mit Ergebnis und Inhalten als String
	 */
	public String comb_gen(int n) {

		int gewicht;
		int wert;
		String best = "";
		int best_wert = 0;

		for (int j = 1; j < n; j++) {

			// String[] elements = {"a", "b", "c", "d", "e", "f", "g"};
			int[] indices;
			CombinationGenerator x = new CombinationGenerator(G_Liste.size(), j);
			StringBuffer combination;
			while (x.hasMore()) {
				gewicht = 0;
				wert = 0;
				combination = new StringBuffer();
				indices = x.getNext();
				for (int i = 0; i < indices.length; i++) {
					combination.append(indices[i] + "_");
				}
				for (int i = 0; i < indices.length; i++) {
					gewicht += G_Liste.get(indices[i]).getGewicht();
					wert += G_Liste.get(indices[i]).getWert();
				}

				combination.append("WERT=" + wert + " GEWICHT=" + gewicht);
				if (gewicht <= this.kap) {

					// pr�fen, obneue beste L�sung vorliegt
					if (wert > best_wert) {
						best_wert = wert;
						best = combination.toString();
					}

					// Pr�fe, ob Optimall�sung vorliegt
					if (best_wert == this.OS)
						return combination.toString();

					// System.out.println(combination.toString());
				}

			}

		}
		return best;

	}

}
