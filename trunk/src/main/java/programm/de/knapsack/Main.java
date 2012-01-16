package programm.de.knapsack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		final int kapazitaet = 45;
		final int maxWert = 15;
		final int maxGewicht = 15;
		// final int anzahl = 20;

		BufferedReader bin = new BufferedReader(
				new InputStreamReader(System.in));

		System.out.println("Bitte Anzahl eingeben: ");
		int anzahl = Integer.parseInt(bin.readLine());
		System.out.println("Eingegebener Zahl: " + anzahl);
		int[] gewichte = { 1, 4, 7, 11, 6, 2, 8 };
		int[] werte = { 2, 7, 6, 8, 4, 1, 4 };
		long[] laufzeiten;
		long temp_zeit_BaB;
		long temp_zeit_bf;
		long durchschnitt_zeit_BaB = 0;
		long durchschnitt_zeit_bf = 0;
		int durchlaeufe = 8;
		long[][] ergebnisse;

		// Tabelle f�r die Ergebnisse der Lafuzeitmessung

		ergebnisse = new long[durchlaeufe][3];

		String erg_bf;

		// Ein Rucksack kann mit zuf�lligen oder vordefinierten Gegenst�nden
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

			// Ergebnisse in Tabelle eintragen:
			ergebnisse[i][0] = i; // Rucksack_ID
			ergebnisse[i][1] = temp_zeit_BaB; // Zeit BranchAndBound
			ergebnisse[i][2] = temp_zeit_bf; // Zeit BruteForce

		}
		System.out
				.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
		System.out.println("Alle Laufzeitergebnisse:");
		System.out.println("Durchl�ufe=" + durchlaeufe);
		System.out.println("Anzahl der Gegenst�nde=" + anzahl);
		System.out.println("Kapazit�t=" + kapazitaet);
		System.out.print("Ruckack_ID\t\t");
		for (int i = 0; i < durchlaeufe; i++) {
			System.out.print(ergebnisse[i][0] + "\t");
		}
		System.out.println("");
		System.out.print("Zeit BranchAndBound\t");
		for (int i = 0; i < durchlaeufe; i++) {
			System.out.print(ergebnisse[i][1] + "\t");
		}
		System.out.println("");
		System.out.print("Zeit BruteForce\t\t");
		for (int i = 0; i < durchlaeufe; i++) {
			System.out.print(ergebnisse[i][2] + "\t");
		}
		System.out.println("");
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