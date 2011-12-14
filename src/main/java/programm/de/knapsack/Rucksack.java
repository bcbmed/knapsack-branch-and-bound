package programm.de.knapsack;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Rucksack {
	
	List<Gegenstand> Gegenstaende;
	int laenge; //Laenge der Liste der Gegenstaände, also Anazahl der Gegenstände
	int Kapazitaet; //Kapazität des Rucksacks
	
	
	
	//Konstruktor mit Zufallswerten
	public Rucksack(int n, int max_w, int max_g, int k){
		this.Gegenstaende = new ArrayList<Gegenstand>();
		this.laenge=n;
		this.Kapazitaet = k;

		Random MyRand = new Random();
		int temp=-1;
		
		for(int i=0;i<n;i++) {
			this.Gegenstaende.add(new Gegenstand(MyRand.nextInt(max_g-1) +1, MyRand.nextInt(max_w-1) +1));
		}
	}

	//interner Konstruktor für clone_()
	private Rucksack(int n, int k, ArrayList<Gegenstand> G_Liste) {
		this.laenge=n;
		this.Kapazitaet=k;
		this.Gegenstaende =G_Liste;
	}
	
	//Ausgabe des Rucksacks auf der Konsole
	public void print() {
		System.out.println("Rucksack mit Kapazität "+this.Kapazitaet+" und n="+laenge);
		for(int i=0;i<laenge;i++) {
			System.out.println(this.Gegenstaende.get(i));
		}
	}
	
	//sortieren der Gegenstände, sortierungsvorschrift in Gegenstand.java
	@SuppressWarnings("unchecked")
	public void sort() {
		Collections.sort(this.Gegenstaende);
	}
	
	/**
	 * Diese Methode berechnet Obereschranke für Branch and Bound Verfahren*/
	public void obereSchranke(){
		double obSchranke = 0.0;
		int akGewicht = 0;
		int akWert = 0;
		for(int index = 0; index<this.Gegenstaende.size(); index++){
		akGewicht = akGewicht + this.Gegenstaende.get(index).getGewicht();
		akWert = this.Gegenstaende.get(index).getWert();
		if(akGewicht<this.Kapazitaet){
			obSchranke =  obSchranke + this.Gegenstaende.get(index).getWert();
		}
		else obSchranke = (obSchranke + (this.Kapazitaet-akGewicht)/akGewicht * akWert); 
		if(akGewicht>this.Kapazitaet){
			break;
		}
		}
		
	}
	
	/**
	 * Diese Methode berechnet Untereschranke für Branch and Bound Verfahren*/
	public void untereSchranke(){
		
	}
	//Clone-Methode zum DUpliziere des Rucksacks
	@Override
	public Rucksack clone() {
		ArrayList<Gegenstand> temp_G_Liste = new ArrayList<Gegenstand>();
		for(int i=0;i<laenge;i++) {
			temp_G_Liste.add (this.Gegenstaende.get(i).clone());
		}
		return new Rucksack(this.laenge, this.Kapazitaet, temp_G_Liste);
	}
	
	//Kapazität definiteren(sollte vom ALgorithmus benutzt werden)
	public void setKapazitaet( int k) {
		this.Kapazitaet = k;
	}
	
	//ersten Gegenstand aus dem Rucksack entfernen(sollte vom ALgorithmus benutzt werden)
	public void removeFirst() {
		this.Gegenstaende.remove(0);
	}

}
