package application.modele.pnjs;

import application.modele.Environnement;
import application.modele.Joueur;

public class Docteur extends Pnj {


	public Docteur(int coordX, int coordY, Environnement e) {
		super(coordX, coordY, e);
		// TODO Auto-generated constructor stub
	}
	
	public void soigne() {
		Joueur j = this.getEnv().getJoueur();
		j.ajouterPV(j.getPvMax());
	}
}
