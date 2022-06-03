package application.modele;

import application.modele.inventaire.Inventaire;
import application.modele.utilitaires.Pioche;

public class Joueur extends Personnage{
	
	Inventaire inventaire;
	
	public Joueur(int coordX, int coordY, Environnement env) {
		super(coordX, coordY, 5, env);
		inventaire = new Inventaire();
		
		inventaire.ajouterItem(new Pioche(0));
	}
	
	public void saut() {
		if (this.collisionBas(getX(), getY())) {
			super.additionnerDirY(-10);
		}
	}
	
	public Inventaire getInventaire() {
		return inventaire;
	}
}
