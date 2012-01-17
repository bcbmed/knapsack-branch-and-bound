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

	Rucksack beste_Loesung; //speichert die beste gefundene L�sung
	Queue<Rucksack> MyQueue; //im Laufe des Verfahres erzeugte, zu pr�fende Rucks�cke

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

	/**Gibt die ermittelte beste L�sung auf der Konsole aus
	 * 
	 */
	public void print_loesung() {
		System.out.println("Optimall�sung gefunden:");
		System.out.println("OS=" + beste_Loesung.obereSchranke() + " US="
				+ beste_Loesung.untereSchranke());
		beste_Loesung.print();
	}

	/** Implementierung der Branch-and-Bound-Methode unter Verwendung der Tiefensuche
	 * Zu pr�fende Rucks�cke werden in eine Warteschlange gelegt und nacheinander abgearbeitet
	 * Wird eine sichere Optimall�sung gefunden(identisch mit oberer Schranke) wird das Verfahren beendet
	 * Ermittelt wird die insgesamt beste gefundene L�sung
	 * 
	 * 
	 * @param root - Urpsrungsrucksack, f�r den das Rucksackproblem gel�st werden soll
	 */
	private void branch_bound_algo(Rucksack root) {

		Rucksack r_links = root.clone();
		Rucksack r_rechts = root.clone();

		int ObereSchrankeRoot = root.obereSchranke();
		int UntereSchrankeRoot = root.untereSchranke();

		// linker Knoten: Gegenstand in Rucksack packen

		// Kapazit�t entsprechend anpassen
		// auskommentiert, wird im Rucsack selbst geregelt, sobald ein Ding
		// eingepackt wird
		

		// Gegenstand in den Rucksack packen
		r_links.addGegenstand(root.Gegenstaende.get(0));

		// Gegenstand aus Liste m�glicher Gegenst�nde netfernen
		r_links.removeFirst();

		// rechter Knoten: Gegenstand aus Liste verf�gbarer Gegenst�nde
		// entfernen, sonst nichts �ndern

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
