package application.modele.mobs;

import application.modele.Environnement;

public class Squelette extends Mob{
	private boolean versDroite;

	public Squelette(double coordX, double coordY, Environnement e) {
		super(coordX, coordY, 7, e, 20,15,0,0);
		this.setDirDroite(1);
		this.versDroite=true;
	}

	public void detection() {
		double xJoueur = this.getJoueur().getX();
		double yJoueur = this.getJoueur().getY();
		if (xJoueur-this.getX() < 200 && xJoueur-this.getX() >0  && yJoueur-this.getY() < 100 && yJoueur-this.getY() > -100) {
			this.deplacementAgressif(true);
		}
		else if (xJoueur-this.getX() > -200 && xJoueur-this.getX() <0  && yJoueur-this.getY() < 100 && yJoueur-this.getY() > -100) { 
			this.deplacementAgressif(false);
		}
		else {
			this.deplacementPassif();
		}
	}
	
	public void deplacementAgressif(boolean versDroite) {
		if (versDroite) {
			this.setDirDroite(2);
		}
		else {
			this.setDirGauche(2);
		}
		if ((this.collisionDroite(this.getX(), this.getY()) 
				|| this.collisionGauche(this.getX(), this.getY()))
				&&this.collisionBas()){
			this.setDirY(-6);
		}
	}
	
	public void deplacementPassif() {
		if (this.collisionDroite(this.getX(), this.getY()) 
				|| this.collisionGauche(this.getX(), this.getY())){
			this.versDroite=!this.versDroite;
		}
		if (this.versDroite) {
			this.setDirDroite(1);
		}
		else {
			this.setDirGauche(1);
		}
	}
	public void action () {
		super.action();
		this.attaque();
	}

	
}
