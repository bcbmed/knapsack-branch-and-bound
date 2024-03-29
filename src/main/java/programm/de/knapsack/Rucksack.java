package programm.de.knapsack;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**Diese Klasse beschreibt den Aufbau eines Rucksacks
 * 
 * 
 * 
 * 
 * @author Cha0z
 *
 */
public class Rucksack {

	ArrayList<Gegenstand> Gegenstaende; //Liste der Gegenst�nde, die in den Rucksack gelegt werden k�nnen
	int laenge; // Laenge der Liste der Gegensta�nde, also Anazahl der Gegenst�nde
	int Kapazitaet; // Kapazit�t des Rucksacks
	ArrayList<Gegenstand> Inhalt; //Gegenst�nde, die sich bereits im Rucksack befinden
	int Wert; // gibt den Wert des Rucksacks an, ergibst sich aus enthaltenen
				// Gegenst�nden

	// Konstruktor mit definierten Gegenst�nden
	public Rucksack(int[] werte, int[] gewichte, int kapazitaet) {

		this.Gegenstaende = new ArrayList<Gegenstand>();

		for (int i = 0; i < werte.length; i++) {

			this.Gegenstaende.add(new Gegenstand(gewichte[i], werte[i]));
		}

		this.laenge = gewichte.length;
		this.Kapazitaet = kapazitaet;
		this.Inhalt = new ArrayList<Gegenstand>();
		this.Wert = 0;

		this.sort();
	}

	// Konstruktor mit Zufallswerten
	public Rucksack(int n, int max_w, int max_g, int k) {
		this.Gegenstaende = new ArrayList<Gegenstand>();
		this.laenge = n;
		this.Kapazitaet = k;
		this.Inhalt = new ArrayList<Gegenstand>();
		this.Wert = 0;

		Random MyRand = new Random();
		int temp = -1;

		for (int i = 0; i < n; i++) {
			this.Gegenstaende.add(new Gegenstand(MyRand.nextInt(max_g - 1) + 1,
					MyRand.nextInt(max_w - 1) + 1));
		}
		this.sort();
	}

	// interner Konstruktor f�r clone_()
	private Rucksack(int n, int k, ArrayList<Gegenstand> G_Liste,
			ArrayList<Gegenstand> I_Liste, int wert) {
		this.laenge = n;
		this.Kapazitaet = k;
		this.Gegenstaende = G_Liste;
		this.Inhalt = I_Liste;
		this.Wert = wert;
	}

	/**Methode zur formatierten Ausgabe des Rucksacks auf der Konsole
	 * 
	 * 
	 */
	public void print() {

		DecimalFormat f = new DecimalFormat("#0.00");
		System.out.println("Rucksack mit Kapazit�t " + this.Kapazitaet
				+ " und n=" + laenge);

		System.out.print("G:\t");
		for (int i = 0; i < this.Gegenstaende.size(); i++) {
			System.out.print(this.Gegenstaende.get(i).getGewicht() + "\t");
		}
		System.out.println();
		System.out.print("W:\t");
		for (int i = 0; i < this.Gegenstaende.size(); i++) {
			System.out.print(this.Gegenstaende.get(i).getWert() + "\t");
		}
		System.out.println();
		System.out.print("N:\t");
		for (int i = 0; i < this.Gegenstaende.size(); i++) {
			System.out.print(f.format(this.Gegenstaende.get(i).getNutzen())
					+ "\t");
		}
		System.out.println();
		if (!this.Inhalt.isEmpty()) {

			System.out.println("Inhalt des Rucksacks:");

			for (int i = 0; i < this.Inhalt.size(); i++) {

				System.out.println(this.Inhalt.get(i) + " im Rucksack!!");
			}
		}
	}

	/**
	 * gibt den Inahlt des Rucksacks aus (veraltet) - print() benutzen
	 */
	@Deprecated
	public void printInahlt() {
		System.out.println("Rucksack mit RestKapazit�t " + this.Kapazitaet
				+ " und n=" + laenge);
		for (int i = 0; i < Inhalt.size(); i++) {
			System.out.println(this.Inhalt.get(i));
		}

	}
	/**Legt einen Gegenstand in den RUcksack
	 * 
	 * @param g - der Gegenstand, der in den Rucksack gelegt wird
	 */
	public void addGegenstand(Gegenstand g) {

		this.Inhalt.add(g);
		this.Wert += g.getWert();
		this.Kapazitaet = this.Kapazitaet - g.getGewicht();
		// if (this.Kapazitaet< 0)
		// System.err.println("Rucksack mit Kapazitaet keliner 0 erzeugt!");

	}

	/** Sortiert die Liste der in den RUcksack legbaren Gegenst�nde nach Nutzen
	 * 
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void sort() {
		Collections.sort(this.Gegenstaende);
	}

	

	/**
	 * Berechnet die obere Schranke des aktuellen Rucksacks
	 * 
	 * @return obere Schranke
	 */
	public int obereSchranke() {

		int tempGewicht = 0;
		double tempWert = 0;
		int counter = -1;

		// erster Schritt: so viele ganze Gegenst�nde in den RUcksack wie
		// m�glich
		for (int i = 0; i < Gegenstaende.size(); i++) {

			if ((tempGewicht + Gegenstaende.get(i).getGewicht()) < this.Kapazitaet) {
				tempGewicht += Gegenstaende.get(i).getGewicht();
				tempWert += Gegenstaende.get(i).getWert();
				counter = i;
			} else
				break;
		}
		// zweiter Schritt: den n�chsten Ggenst�nde teilweise in den RUcksak
		// legen, um die Kapazitaet zu f�llen

		if (counter + 1 < Gegenstaende.size())
			tempWert += Gegenstaende.get(counter + 1).getWert()
					* ((double) (this.Kapazitaet - tempGewicht) / Gegenstaende
							.get(counter + 1).getGewicht());

		// System.out.println("Test obere Schranke neu = "+tempWert);

		// Schrakenberechnung abgeschlossen, Inhalt des Rucksack draufaddieren:

		return (int) Math.floor(tempWert) + this.Wert;

	}

	/**
	 * Berechnet die untere Schranke des aktuellen Rucksacks
	 * Die Gegenst�nde sind nach Nutzen sortiert
	 * 
	 * 
	 * @return untere Schranke
	 */
	public int untereSchranke() {

		int tempGewicht = 0;
		double tempWert = 0;

		// erster Schritt: so viele ganze Gegenst�nde in den RUcksack wie
		// m�glich
		for (int i = 0; i < Gegenstaende.size(); i++) {

			if ((tempGewicht + Gegenstaende.get(i).getGewicht()) < this.Kapazitaet) {
				tempGewicht += Gegenstaende.get(i).getGewicht();
				tempWert += Gegenstaende.get(i).getWert();

			} else
				break;
		}

		// Schrakenberechnung abgeschlossen, Inhalt des Rucksack hinzuddieren:

		return (int) Math.floor(tempWert) + this.Wert;

	}

	

	// Clone-Methode zum Dupliziere des Rucksacks
	@Override
	public Rucksack clone() {
		ArrayList<Gegenstand> temp_G_Liste = new ArrayList<Gegenstand>();
		for (int i = 0; i < this.Gegenstaende.size(); i++) {
			temp_G_Liste.add(this.Gegenstaende.get(i).clone());
		}

		ArrayList<Gegenstand> temp_I_Liste = new ArrayList<Gegenstand>();
		for (int i = 0; i < Inhalt.size(); i++) {
			temp_I_Liste.add(this.Inhalt.get(i).clone());
		}

		return new Rucksack(this.laenge, this.Kapazitaet, temp_G_Liste,
				temp_I_Liste, this.Wert);
	}

	// Kapazit�t definiteren(sollte vom Algorithmus benutzt werden)
	public void setKapazitaet(int k) {
		this.Kapazitaet = k;
	}

	// ersten Gegenstand aus dem Rucksack entfernen(sollte vom ALgorithmus
	// benutzt werden)
	public void removeFirst() {
		this.Gegenstaende.remove(0);
		this.laenge--;
	}

	public int getKapazitaet() {

		return this.Kapazitaet;

	}

}
