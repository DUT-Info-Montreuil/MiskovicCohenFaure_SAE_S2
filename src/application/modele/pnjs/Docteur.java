package application.modele.pnjs;

import application.modele.Environnement;
import application.modele.Joueur;

public class Docteur extends Pnj {

	public Docteur(int coordX, int coordY, Environnement e) {
		super(coordX, coordY, e, 10,10,10,10);
	}
	
	public void soigne() {
		Joueur j = this.getEnv().getJoueur();
		j.ajouterPV(j.getPvMax());
	}
}
