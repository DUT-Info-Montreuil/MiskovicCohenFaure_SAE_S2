package application.modele;

import java.util.Random;

public class Arbre extends Personnage{

	public Arbre(double coordX, double coordY, Environnement e) {
		super(coordX, coordY, 10, e, 120, 30, 86, 0);
	}
	
	public void action() {
		super.action();
		if (this.getPv()<=0) {
			Random r = new Random();
			this.getEnv().retirerArbre(this);
			this.getEnv().getJoueur().getCompteurMateriaux().get(0).ajouterMat(r.nextInt(20 - 10) + 10);
		}
	}
}
