package application.modele;

import java.util.ArrayList;

public class Environnement {

	private Joueur joueur;
	private Terrain terrain;
	private ArrayList<Personnage> persos;
	private ArrayList<Mob> mobs;

	public Environnement() {
		terrain = new Terrain();
		persos = new ArrayList<>();
		joueur = new Joueur(960,-64, this);
		mobs = new ArrayList<Mob>();

		//Temporaire
		Mob slime = new Slime(0,64, joueur, this);
//		Mob slime2 = new Slime(0,85, joueur, this);
//		ajouterMob(slime2);
//		Mob slime3 = new Slime(0,85, joueur, this);
//		ajouterMob(slime3);
		ajouterMob(slime);
	}

	//Gestion Liste
	public void ajouterMob(Mob m) { 
		mobs.add(m);
		ajouterPerso(m);
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
		System.out.println(this.mobs);
		System.out.println("removed");
		System.out.println(this.mobs);
		System.out.println(this.persos);
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

