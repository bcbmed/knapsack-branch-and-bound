package programm.de.knapsack;

import java.util.*;

public class BranchAndBound {

	
	Rucksack beste_Loesung ;
	Queue<Rucksack> MyQueue;
	
	
	public BranchAndBound(Rucksack root) {
		
		MyQueue = new LinkedList<Rucksack>();
		MyQueue.offer(root);
		beste_Loesung=root;
		
		
		
		
	}
	
	public void start(){
		
		while(!MyQueue.isEmpty()) {
			branch_bound_algo(MyQueue.poll());
			
		}
		
		System.out.println("Optimallösung gefunden:");
		System.out.println("OS="+beste_Loesung.obereSchranke() + " US="+beste_Loesung.untereSchranke());
		beste_Loesung.print();
		
		
	}
	
	private void branch_bound_algo( Rucksack root) {
		
		
		Rucksack r_links = root.clone();
		Rucksack r_rechts = root.clone();
		
		int ObereSchrankeRoot = root.obereSchranke();
		int UntereSchrankeRoot = root.untereSchranke();
		
		
		
		
		//linker Knoten: Gegenstand in Rucksack packen
		
		//Kapazität entsprechend anpassen
		//auskommentiert, wird im Rucsack selbst geregelt, sobald ein Ding eingepackt wird
		//r_links.setKapazitaet(root.Kapazitaet - root.Gegenstaende.get(0).Gewicht);
		
		//Gegenstand in den Rucksack packen
		r_links.addGegenstand(root.Gegenstaende.get(0));
		
		//Gegenstand aus Liste möglicher Gegenstände netfernen
		r_links.removeFirst();
		
		
		
		//rechter Knoten: Gegenstand aus Liste verfügbarer Gegenstände entfernen, sonst nichts ändern
		
		r_rechts.removeFirst();
		
		
		//Schranken berechnen - Bounding
		
		
		int ObereSchrankeLinks = r_links.obereSchranke();
		int UntereSchrankeLinks = r_links.untereSchranke();
		
		if(UntereSchrankeLinks == ObereSchrankeLinks) {
			
			if (UntereSchrankeLinks > beste_Loesung.untereSchranke() ) { beste_Loesung = r_links;}
			//neu, Speicher sparen...
		
			
		}
		else if (ObereSchrankeLinks > UntereSchrankeRoot) MyQueue.offer(r_links);
		
		
		
		
		
		
		int ObereSchrankeRechts = r_rechts.obereSchranke();
		int UntereSchrankeRechts = r_rechts.untereSchranke();
		
		if(UntereSchrankeRechts == ObereSchrankeRechts) {
			
			if (UntereSchrankeRechts > beste_Loesung.untereSchranke() )   { beste_Loesung = r_rechts; }
			
			
		}
		else if (ObereSchrankeRechts > UntereSchrankeRoot) MyQueue.offer(r_rechts);
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
}
