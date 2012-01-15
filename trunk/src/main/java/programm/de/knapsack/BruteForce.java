package programm.de.knapsack;

import java.util.ArrayList;
import java.util.LinkedList;

public class BruteForce {

	ArrayList<Gegenstand> G_Liste = null;

	int OS;
	int kap;

	public BruteForce() {

	}

	public void init(Rucksack r) {

		OS = r.obereSchranke();
		kap = r.getKapazitaet();

		this.G_Liste = r.clone().Gegenstaende;

	}

	public void start(int n) {

		ArrayList<String> ergebnis = kombinieren(n);

		for (int i = 0; i < ergebnis.size(); i++) {
			System.out.println(ergebnis.get(i));
		}
	}

	/**
	 * a b c d e f g h
	 * 
	 * l=1:
	 * 
	 * a b c d e f g h
	 * 
	 * 
	 * l=2:
	 * 
	 * ab ac ad ae af ag ah
	 * 
	 * bc bd be bf bg bh ......
	 * 
	 * 
	 * l=3:
	 * 
	 * 
	 * abc abd abe abf abg abh
	 * 
	 * acd ace acf acg ach
	 * 
	 * ade adf adg adh
	 * 
	 * aef aeg aeh
	 * 
	 * afg afh
	 * 
	 * agh
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @param laenge
	 * @return
	 */

	public ArrayList<String> kombinieren(int n) {

		if (n == 0)
			return null;
		if (n == 1) {

			ArrayList<String> erg = new ArrayList<String>();

			for (int i = 0; i < G_Liste.size(); i++) {

				erg.add(Integer.toString(i));

			}
			return erg;
		}

		if (n > 1) {
			int count_start = n - 1;
			int grenze = G_Liste.size() - (n - 1);

			ArrayList<String> temp = kombinieren(n - 1);

			ArrayList<String> erg = new ArrayList<String>();

			for (int j = 0; j < temp.size(); j++) {

				for (int i = count_start + (j / grenze) + j % grenze; i < G_Liste
						.size() && j != grenze; i++) {

					erg.add(temp.get(j) + "_" + Integer.toString(i));

				}

			}
			return erg;

		}
		return null;
	}

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

					// prüfen, obneue beste Lösung vorliegt
					if (wert > best_wert) {
						best_wert = wert;
						best = combination.toString();
					}

					// Prüfe, ob Optimallösung vorliegt
					if (best_wert == this.OS)
						return combination.toString();

					// System.out.println(combination.toString());
				}

			}

		}
		return best;

	}

}
