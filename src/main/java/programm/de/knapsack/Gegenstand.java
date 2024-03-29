package programm.de.knapsack;

/**
 * Ein Gegenstand im Rucksack, der �ber ein Gewicht und einen Wert verf�gt
 */
public class Gegenstand implements Comparable {

	// Konstruktor, Gegenstand durch g und w spezifiziert
	public Gegenstand(int g, int w) {

		this.Gewicht = g;
		this.Wert = w;
		this.Nutzen = (float) Wert / (float) Gewicht;
	}

	public int getGewicht() {
		return this.Gewicht;
		// this.
	}

	public int getWert() {

		return this.Wert;
	}

	public float getNutzen() {

		return this.Nutzen;
	}

	int Gewicht;
	int Wert;
	float Nutzen;


/** Vergleicht Gegenst�nde �ber ihren Nutzen (Wert pro Gewichtseinheit)
 * 
 * 
 */
	public int compareTo(Object arg0) {

		Gegenstand g2 = (Gegenstand) arg0;

		if (this.Nutzen > g2.Nutzen)
			return -1;
		if (this.Nutzen < g2.Nutzen)
			return 1;

		if (this.Gewicht == g2.Gewicht && this.Wert == g2.Wert)
			return 0;

		return 42;
	}

	public String toString() {

		String Ausgabe = "Gewicht=" + Gewicht + " Wert=" + Wert + " Nutzen="
				+ Nutzen;
		return Ausgabe;

	}

	// Gegenstand duplizieren
	@Override
	public Gegenstand clone() {

		return new Gegenstand(this.Gewicht, this.Wert);
	}

}