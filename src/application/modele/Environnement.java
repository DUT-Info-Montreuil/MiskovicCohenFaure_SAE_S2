package application.modele;

import java.util.ArrayList;

import application.modele.mobs.Archer;
import application.modele.mobs.Fleche;
import application.modele.mobs.Mob;
import application.modele.mobs.Slime;
import application.modele.mobs.Squelette;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Environnement {

	private Joueur joueur;
	private Terrain terrain;
	private ArrayList<Personnage> persos;
	ObservableList<Mob> mobs;

	public Environnement() {

		terrain = new Terrain();
		persos = new ArrayList<>();
		joueur = new Joueur(960,-64, this);
		mobs = FXCollections.observableArrayList();
		
	}

	public void creerSlime(double x,double y) {
		this.ajouterMob(new Slime(x,y, this));
	}
	
	public void creerFleche(double x,double y,boolean versDroite) {
		this.ajouterMob(new Fleche(x,y, this,versDroite));
	}
	
	public void creerSquelette(double x,double y) {
		this.ajouterMob(new Squelette(x,y, this));
	}	
	
	public void creerArcher(double x,double y) {
		this.ajouterMob(new Archer(x,y, this));
	}	
	
	//Gestion Liste
	public void ajouterMob(Mob m) { 
		mobs.add(m);
	}

	public void ajouterPerso(Personnage p) {
		if (!(p instanceof Joueur))
			persos.add(p);
	}

	public void retirerPerso (Personnage p) {
		this.persos.remove(p);
	}

	public void retirerMob (Personnage p) {
		retirerPerso(p);
		this.mobs.remove(p);
	}

	public Joueur getJoueur() {
		return this.joueur;
	}

	public Terrain getTerrain() {
		return terrain;
	}

	public ArrayList<Personnage> getPersos() {
		return persos;
	}
	public ObservableList<Mob> getMobs() {
		return mobs;
	}

	public void unTour () {
		//Gestion Collision des acteurs
		Personnage p;
		int i=0;
		while (i<this.persos.size()) {
			p=this.mobs.get(i);
			p.action();
			i++;
		}

		//Mouvement + Gravité Joueur
		joueur.action();
	}


}

