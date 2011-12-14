package de.knapsack;
import java.util.Arrays;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rucksack {
	
	List<Gegenstand> Gegenstaende;
	int laenge; //Laenge der Liste der Gegensta�nde, also Anazahl der Gegenst�nde
	int Kapazitaet; //Kapazit�t des Rucksacks
	
	
	
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
	//interner Konstruktor f�r clone_()
	private Rucksack(int n, int k, ArrayList<Gegenstand> G_Liste) {
		
		this.laenge=n;
		this.Kapazitaet=k;
		this.Gegenstaende =G_Liste;
		
	}
	
	//Ausgabe des Rucksacks auf der Konsole
	public void print() {
		System.out.println("Rucksack mit Kapazit�t "+this.Kapazitaet+" und n="+laenge);
		
		for(int i=0;i<laenge;i++) {
			System.out.println(this.Gegenstaende.get(i));
			
		}
		
		
		
	}
	
	//sortieren der Gegenst�nde, sortierungsvorschrift in Gegenstand.java
	public void sort() {
		
		Collections.sort(this.Gegenstaende);
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
	
	//Kapazit�t definiteren(sollte vom ALgorithmus benutzt werden)
	public void setKapazitaet( int k) {
		
		this.Kapazitaet = k;
	}
	
	//ersten Gegenstand aus dem Rucksack entfernen(sollte vom ALgorithmus benutzt werden)
	public void removeFirst() {
		this.Gegenstaende.remove(0);
	}
	
	
	

}
