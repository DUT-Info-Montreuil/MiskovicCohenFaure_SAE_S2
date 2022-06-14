package application.modele;

import java.util.ArrayList;

import application.modele.mobs.Mob;
import application.modele.mobs.Slime;
import application.modele.pnjs.Docteur;
import application.modele.pnjs.Pnj;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Environnement {

	private Joueur joueur;
	private Terrain terrain;
	ObservableList<Pnj> pnjs;
	ObservableList<Mob> mobs;

	public Environnement() {

		terrain = new Terrain();
		pnjs = FXCollections.observableArrayList();
		joueur = new Joueur(960,-64, this);
		
		mobs = FXCollections.observableArrayList();
		
	}

	public void creerSlime() {
		Mob slime = new Slime(100,-64, joueur, this);
		this.ajouterMob(slime);
	}
	
	public void creerDocteur() {
		Docteur doc = new Docteur(1300, 64, this);
		pnjs.add(doc);
	}
	//Gestion Liste
	public void ajouterMob(Mob m) { 
		mobs.add(m);
	}

	public void retirerMob (Personnage p) {
		this.mobs.remove(p);
	}

	public Joueur getJoueur() {
		return this.joueur;
	}

	public Terrain getTerrain() {
		return terrain;
	}
	
	public ObservableList<Mob> getMobs() {
		return mobs;
	}

	public ObservableList<Pnj> getPnjs() {
		return pnjs;
	}

	public void unTour () {
		//Gestion Collision des acteurs
		Mob m;
		int i=0;
		while (i<this.mobs.size()) {
			m=this.mobs.get(i);
			m.action();
			i++;
		}
		
		for (Pnj p: pnjs) {
			p.action();
		}
		
		
		

		//Mouvement + Gravité Joueur
		joueur.action();
	}


}

