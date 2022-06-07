package application.modele;

import java.util.ArrayList;

public class Environnement {

	private Joueur joueur;
	private Terrain terrain;
	private ArrayList<Personnage> persos; //liste observable pour gérer mort et nouveaux nés
	private ArrayList<Mob> mobs;

	public Environnement() {
		terrain = new Terrain();
		persos = new ArrayList<>();
		joueur = new Joueur(0,-64);
		mobs = new ArrayList<Mob>(); // retourner les mobs / métyhode action

		//Temporaire
		Mob slime = new Slime(0,64, joueur);
		Mob slime2 = new Slime(0,85, joueur);
		ajouterMob(slime);
		ajouterPerso(slime);
		ajouterMob(slime2);
		ajouterPerso(slime2);
	}

	//Gestion Liste
	public void ajouterMob(Mob m) {
		mobs.add(m);
	}

	public void ajouterPerso(Personnage p) {
		if (!(p instanceof Joueur))
			persos.add(p);
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




}

