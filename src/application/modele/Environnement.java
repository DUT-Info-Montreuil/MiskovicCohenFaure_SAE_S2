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
		for (Personnage p : this.persos) {
			p.action();
			if (!p.collisionBas()) {
				if(p.getDirY() < 5)
					p.additionnerDirY(0.5);	
			}
		}
		
		//Mouvement + Gravité Joueur
		joueur.action();
		if (!joueur.collisionBas()) {
			if(joueur.getDirY() < 5) {
				joueur.additionnerDirY(1);
			}
		}
	}


}

