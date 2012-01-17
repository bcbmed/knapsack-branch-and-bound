package programm.de.knapsack;

import java.util.*;

/**Implementierung des Branch and Bound-Verfahrens
 * Es wird die Tiefensuche verwendet
 * 
 * 
 * 
 *
 */
public class BranchAndBound {

	Rucksack beste_Loesung; //speichert die beste gefundene Lösung
	Queue<Rucksack> MyQueue; //im Laufe des Verfahres erzeugte, zu prüfende Rucksäcke

	public BranchAndBound(Rucksack root) {

		MyQueue = new LinkedList<Rucksack>();
		MyQueue.offer(root);
		beste_Loesung = root;

	}

	/**Startet das Programm
	 * 
	 */
	public void start() {

		while (!MyQueue.isEmpty()) {
			branch_bound_algo(MyQueue.poll());

		}

	}

	/**Gibt die ermittelte beste Lösung auf der Konsole aus
	 * 
	 */
	public void print_loesung() {
		System.out.println("Optimallösung gefunden:");
		System.out.println("OS=" + beste_Loesung.obereSchranke() + " US="
				+ beste_Loesung.untereSchranke());
		beste_Loesung.print();
	}

	/** Implementierung der Branch-and-Bound-Methode unter Verwendung der Tiefensuche
	 * Zu prüfende Rucksäcke werden in eine Warteschlange gelegt und nacheinander abgearbeitet
	 * Wird eine sichere Optimallösung gefunden(identisch mit oberer Schranke) wird das Verfahren beendet
	 * Ermittelt wird die insgesamt beste gefundene Lösung
	 * 
	 * 
	 * @param root - Urpsrungsrucksack, für den das Rucksackproblem gelöst werden soll
	 */
	private void branch_bound_algo(Rucksack root) {

		Rucksack r_links = root.clone();
		Rucksack r_rechts = root.clone();

		int ObereSchrankeRoot = root.obereSchranke();
		int UntereSchrankeRoot = root.untereSchranke();

		// linker Knoten: Gegenstand in Rucksack packen

		// Kapazität entsprechend anpassen
		// auskommentiert, wird im Rucsack selbst geregelt, sobald ein Ding
		// eingepackt wird
		

		// Gegenstand in den Rucksack packen
		r_links.addGegenstand(root.Gegenstaende.get(0));

		// Gegenstand aus Liste möglicher Gegenstände netfernen
		r_links.removeFirst();

		// rechter Knoten: Gegenstand aus Liste verfügbarer Gegenstände
		// entfernen, sonst nichts ändern

		r_rechts.removeFirst();

		// Schranken berechnen - Bounding

		int ObereSchrankeLinks = r_links.obereSchranke();
		int UntereSchrankeLinks = r_links.untereSchranke();

		if (UntereSchrankeLinks == ObereSchrankeLinks
				&& r_links.getKapazitaet() >= 0) {

			if (UntereSchrankeLinks > beste_Loesung.untereSchranke()) {
				beste_Loesung = r_links;
			}
			

		} else if (ObereSchrankeLinks > UntereSchrankeRoot
				&& r_links.getKapazitaet() >= 0)
			MyQueue.offer(r_links);

		int ObereSchrankeRechts = r_rechts.obereSchranke();
		int UntereSchrankeRechts = r_rechts.untereSchranke();

		if (UntereSchrankeRechts == ObereSchrankeRechts) {

			if (UntereSchrankeRechts > beste_Loesung.untereSchranke()) {
				beste_Loesung = r_rechts;
			}

		} else if (ObereSchrankeRechts > UntereSchrankeRoot)
			MyQueue.offer(r_rechts);

	}

}
