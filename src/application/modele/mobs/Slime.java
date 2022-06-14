package application.modele.mobs;

import application.modele.Environnement;
import application.modele.Joueur;

public class Slime extends Mob{
	
	private int temps;

	public Slime(double coordX, double coordY,Environnement e) {
		super(coordX, coordY, 3, e,25,15);
		this.temps=0;
	}

	@Override
	public void detection() {
		double xJoueur = this.getJoueur().getX();
		double yJoueur = this.getJoueur().getY();
		if (xJoueur-this.getX() < 250 && xJoueur-this.getX() >0  && yJoueur-this.getY() < 100 && yJoueur-this.getY() > -100) 
			this.deplacement(true);

		if (xJoueur-this.getX() > -250 && xJoueur-this.getX() <0  && yJoueur-this.getY() < 100 && yJoueur-this.getY() > -100) 
			this.deplacement(false);
	}

	public void deplacement(boolean versDroite) {
		temps++;
		if (this.temps==100) {
			if (versDroite) {
				this.setDirDroite(3);
			}
			else {
				this.setDirGauche(3);
			}
			this.setDirY(-7);
			this.temps=0;
		}
	}
	
	public void inertie() {
		if (this.collisionBas()){
			if (this.getDirDroite()>0) {
				this.setDirDroite(this.getDirDroite()-0.2);
			}
			if (this.getDirGauche()>0) {
				this.setDirGauche(this.getDirGauche()-0.2);
			}
		}
	}



}