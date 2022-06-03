package application.modele;

import application.modele.items.utilitaires.Arc;
import application.modele.items.utilitaires.Epee;
import application.modele.items.utilitaires.Hache;
import application.modele.items.utilitaires.Pioche;

public class Joueur extends Personnage{
	
	Inventaire inventaire;
	
	public Joueur(int coordX, int coordY, Environnement env) {
		super(coordX, coordY, 5, env);
		inventaire = new Inventaire();
		
		inventaire.ajouterItem(new Pioche(0));
		inventaire.ajouterItem(new Epee(0));
		inventaire.ajouterItem(new Hache(0));
		inventaire.ajouterItem(new Arc(0));
	}
	
	public void saut(Environnement e) {
		if (this.collisionBas( getX(), getY())) {
			super.additionnerDirY(-10);
		}
	}
	
	public Inventaire getInventaire() {
		return inventaire;
	}
}
