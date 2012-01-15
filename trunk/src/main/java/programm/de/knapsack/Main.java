package programm.de.knapsack;

import java.util.Date;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		final int kapazitaet = 35;
		final int maxWert = 10;
		final int maxGewicht = 10;
		final int anzahl = 20;
		int[] gewichte = { 1, 4, 7, 11, 6, 2, 8 };
		int[] werte = { 2, 7, 6, 8, 4, 1, 4 };
		long[] laufzeiten;
		long temp_zeit_BaB;
		long temp_zeit_bf;
		long durchschnitt_zeit_BaB = 0;
		long durchschnitt_zeit_bf = 0;
		int durchlaeufe = 30;
		String erg_bf;

		// Ein Rucksack kann mit zufälligen oder vordefinierten Gegenständen
		// erzeugt werden
		// Rucksack def = new Rucksack(werte, gewichte, Kapazitaet);
		Date date1;
		Date date2;
		Date date3;
		Date date4;
		laufzeiten = new long[durchlaeufe];
		// Laufzeitmessung
		Rucksack def;

		for (int i = 0; i < durchlaeufe; i++) {
			def = new Rucksack(anzahl, maxWert, maxGewicht, kapazitaet);

			System.out.println("Ausgangszustand Rucksackinhalte Rucksack Nr:"
					+ i + " Anfang----------------");
			def.print();
			System.out.println("OpbereSchranke =" + def.obereSchranke());
			System.out.println("UntereSchranke =" + def.untereSchranke());

			BranchAndBound Algo = new BranchAndBound(def);
			date1 = new Date();
			Algo.start();
			date2 = new Date();
			temp_zeit_BaB = date2.getTime() - date1.getTime();
			Algo.print_loesung();
			System.out.println("BaB-Laufzeit Rucksack Nr" + i + ": "
					+ temp_zeit_BaB + "ms");
			durchschnitt_zeit_BaB += temp_zeit_BaB;

			BruteForce bf = new BruteForce();
			bf.init(def);
			date3 = new Date();
			erg_bf = bf.comb_gen(def.Gegenstaende.size());
			date4 = new Date();
			temp_zeit_bf = date4.getTime() - date3.getTime();
			System.out.println(erg_bf);
			System.out.println("BF-Laufzeit Rucksack Nr" + i + ": "
					+ temp_zeit_bf + "ms");
			durchschnitt_zeit_bf += temp_zeit_bf;

			System.out.println("-------Ausgabe Rucksack Nr:" + i
					+ " Ende-----------");
		}
		durchschnitt_zeit_BaB = durchschnitt_zeit_BaB / durchlaeufe;
		durchschnitt_zeit_bf = durchschnitt_zeit_bf / durchlaeufe;

		System.out.println("BandB Durcschnittliche Laufzeit:"
				+ durchschnitt_zeit_BaB + "ms");
		System.out.println("BF Durcschnittliche Laufzeit:"
				+ durchschnitt_zeit_bf + "ms");

		// def = new Rucksack(anzahl, maxWert, maxGewicht, kapazitaet);
		// BruteForce bf = new BruteForce();
		// bf.init(def);
		// erg_bf= bf.comb_gen(def.Gegenstaende.size());
		// System.out.println(erg_bf);
		// bf.start(2);
		// System.out.println("---");
		// bf.start(3);

	}

}
