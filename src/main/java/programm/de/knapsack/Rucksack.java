package programm.de.knapsack;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Rucksack {

	List<Gegenstand> Gegenstaende;
	int laenge; // Laenge der Liste der Gegenstaände, also Anazahl der
				// Gegenstände
	int Kapazitaet; // Kapazität des Rucksacks
	List<Gegenstand> Inhalt;
	int Wert; //gibt den Wert des Rucksacks an, ergibst sich aus enthaltenen Gegenständen
	
	//Konstruktor mit definierten Gegenständen
	public Rucksack(int[] werte, int[]gewichte, int kapazitaet) {
		
		this.Gegenstaende = new ArrayList<Gegenstand>();
		
		for(int i=0;i<werte.length;i++) {
			
			this.Gegenstaende.add(new Gegenstand(gewichte[i], werte[i]));
		}
		
		this.laenge = gewichte.length;
		this.Kapazitaet = kapazitaet;
		this.Inhalt = new ArrayList<Gegenstand>();
		this.Wert=0;
		
		this.sort();
	}
	
	
	// Konstruktor mit Zufallswerten
	public Rucksack(int n, int max_w, int max_g, int k) {
		this.Gegenstaende = new ArrayList<Gegenstand>();
		this.laenge = n;
		this.Kapazitaet = k;
		this.Inhalt = new ArrayList<Gegenstand>();
		this.Wert=0;

		Random MyRand = new Random();
		int temp = -1;

		for (int i = 0; i < n; i++) {
			this.Gegenstaende.add(new Gegenstand(MyRand.nextInt(max_g - 1) + 1,
					MyRand.nextInt(max_w - 1) + 1));
		}
		this.sort();
	}

	// interner Konstruktor für clone_()
	private Rucksack(int n, int k, ArrayList<Gegenstand> G_Liste,
			ArrayList<Gegenstand> I_Liste, int wert) {
		this.laenge = n;
		this.Kapazitaet = k;
		this.Gegenstaende = G_Liste;
		this.Inhalt = I_Liste;
		this.Wert=wert;
	}

	// Ausgabe des Rucksacks auf der Konsole
	public void print() {
		
		DecimalFormat f = new DecimalFormat("#0.00");
		System.out.println("Rucksack mit Kapazität " + this.Kapazitaet
				+ " und n=" + laenge);
		
		
		System.out.print("G:\t");
		for (int i = 0; i < this.Gegenstaende.size(); i++) {
			System.out.print(this.Gegenstaende.get(i).getGewicht()+"\t");
		}System.out.println();
		System.out.print("W:\t");
		for (int i = 0; i < this.Gegenstaende.size(); i++) {
			System.out.print(this.Gegenstaende.get(i).getWert()+"\t");
		}System.out.println();
		System.out.print("N:\t");
		for (int i = 0; i < this.Gegenstaende.size(); i++) {
			System.out.print(f.format(this.Gegenstaende.get(i).getNutzen())+"\t");
		}
		System.out.println();
		if (!this.Inhalt.isEmpty()) {
			
			System.out.println("Inhalt des Rucksacks:");
			
			for(int i=0;i<this.Inhalt.size();i++) {
				
				System.out.println(this.Inhalt.get(i) + " im Rucksack!!");
			}
		}
	}

	/**gibt den Inahlt des Rucksacks aus
	 * (veraltet) - print() benutzen 
	 */
	 
	@Deprecated
	public void printInahlt() {
		System.out.println("Rucksack mit RestKapazität " + this.Kapazitaet
				+ " und n=" + laenge);
		for (int i = 0; i < Inhalt.size(); i++) {
			System.out.println(this.Inhalt.get(i));
		}

	}

	public void addGegenstand(Gegenstand g) {

		this.Inhalt.add(g);
		this.Wert+=g.getWert();
		this.Kapazitaet=this.Kapazitaet - g.getGewicht();
		//if (this.Kapazitaet< 0) System.err.println("Rucksack mit Kapazitaet keliner 0 erzeugt!");
		
	}

	// sortieren der Gegenstände, sortierungsvorschrift in Gegenstand.java
	@SuppressWarnings("unchecked")
	public void sort() {
		Collections.sort(this.Gegenstaende);
	}

	/**
	 * Diese Methode berechnet Obereschranke für Branch and Bound Verfahren
	 */
//	public int getObereSchranke() {
//		float obSchranke = 0;
//		int akGewicht = 0;
//		int akWert = 0;
//		for (int index = 0; index < this.Gegenstaende.size(); index++) {
//			akGewicht = akGewicht + this.Gegenstaende.get(index).getGewicht();
//			akWert = this.Gegenstaende.get(index).getWert();
//			if (akGewicht < this.Kapazitaet) {
//				obSchranke = obSchranke
//						+ this.Gegenstaende.get(index).getWert();
//			} else
//				obSchranke = (obSchranke + (this.Kapazitaet - akGewicht)
//						/ akGewicht * akWert);
//			if (akGewicht > this.Kapazitaet) {
//				return (int) Math.floor(obSchranke);
//			}
//		}
//		return 0;
//
//	}
	
	/** Berechnet die obere Schranke des aktuellen Rucksacks
	 * 
	 * @return obere Schranke
	 */
	public int obereSchranke() {
		
		int tempGewicht=0;
		double tempWert=0;
		int counter=-1;
		
		//erster Schritt: so viele ganze Gegenstände in den RUcksack wie möglich
		for(int i=0;i<Gegenstaende.size();i++) {
			
			if ( ( tempGewicht + Gegenstaende.get(i).getGewicht() )< this.Kapazitaet) {
			tempGewicht+= Gegenstaende.get(i).getGewicht();
			tempWert+=Gegenstaende.get(i).getWert();
			counter=i;
		}
			else break;
	}
		//zweiter Schritt: den nächsten Ggenstände teilweise in den RUcksak legen, um die Kapazitaet zu füllen
		
		
		if (counter+1< Gegenstaende.size()) tempWert+= Gegenstaende.get(counter+1).getWert() * ( (double)( this.Kapazitaet - tempGewicht)/ Gegenstaende.get(counter+1).getGewicht() ) ;
		
		
	//	System.out.println("Test obere Schranke neu = "+tempWert);
		
		
		//Schrakenberechnung abgeschlossen, Inhalt des Rucksack draufaddieren:
		
		
			
		
		return (int)  Math.floor(tempWert) + this.Wert;
		
		
		
		
		
	}
	
	/** Berechnet die untere Schranke des aktuellen Rucksacks
	 * 
	 * @return untere Schranke
	 */
	public int untereSchranke() {
		
		int tempGewicht=0;
		double tempWert=0;
		
		
		//erster Schritt: so viele ganze Gegenstände in den RUcksack wie möglich
		for(int i=0;i<Gegenstaende.size();i++) {
			
			if ( ( tempGewicht + Gegenstaende.get(i).getGewicht() )< this.Kapazitaet) {
			tempGewicht+= Gegenstaende.get(i).getGewicht();
			tempWert+=Gegenstaende.get(i).getWert();
			
		}
			else break;
	}
		
		//Schrakenberechnung abgeschlossen, Inhalt des Rucksack draufaddieren:
		
		return (int)  Math.floor(tempWert) + this.Wert;
		
		
		
		
		
	}

	/**
	 * Diese Methode berechnet Untereschranke für Branch and Bound Verfahren
	 */
//	public int untereSchranke() {
//		float unSchranke = 0;
//		int akGewicht = 0;
//		for (int index = 0; index < this.Gegenstaende.size(); index++) {
//			akGewicht = akGewicht + this.Gegenstaende.get(index).getGewicht();
//			if (akGewicht < this.Kapazitaet) {
//				unSchranke = unSchranke
//						+ this.Gegenstaende.get(index).getWert();
//			}
//			if (akGewicht > this.Kapazitaet) {
//				return (int) Math.floor(unSchranke);
//			}
//		}
//		return 0;
//	}

	// Clone-Methode zum DUpliziere des Rucksacks
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

	// Kapazität definiteren(sollte vom ALgorithmus benutzt werden)
	public void setKapazitaet(int k) {
		this.Kapazitaet = k;
	}

	// ersten Gegenstand aus dem Rucksack entfernen(sollte vom ALgorithmus
	// benutzt werden)
	public void removeFirst() {
		this.Gegenstaende.remove(0);
		this.laenge--;
	}

}
