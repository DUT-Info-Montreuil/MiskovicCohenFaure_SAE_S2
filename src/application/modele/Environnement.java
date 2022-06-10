package application.modele;

import java.util.ArrayList;

import application.controleur.Controleur;

public class Environnement {

	private Joueur joueur;
	private Terrain terrain;
	private ArrayList<Personnage> persos;
	private ArrayList<Mob> mobs;
	private Controleur controleur;

	public Environnement(Controleur c) {
		
		this.controleur = c;
		terrain = new Terrain();
		persos = new ArrayList<>();
		joueur = new Joueur(960,-64, this);
		mobs = new ArrayList<Mob>();
	
		

		//Temporaire
		Mob slime = new Slime(0,64, joueur, this);
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

	public Controleur getControleur() {
		return controleur;
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
	public ArrayList<Mob> getMobs() {
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

