package application.modele.mobs;

import application.modele.Environnement;
import application.modele.mobs.boss.Mob;

public class Archer extends Mob {
	private int cooldown;
	private boolean versDroite;

	public Archer(double coordX, double coordY, Environnement e) {
		super(coordX, coordY, 3, e, 20,15,0,0);
		this.cooldown=100;
	}

	@Override
	public void detection() {
		double xJoueur = this.getJoueur().getX();
		double yJoueur = this.getJoueur().getY();
		if (xJoueur-this.getX() < 650 && xJoueur-this.getX() >0  && yJoueur-this.getY() < 100 && yJoueur-this.getY() > -100) { 
			if (xJoueur-this.getX() < 450 && xJoueur-this.getX() >0  && yJoueur-this.getY() < 100 && yJoueur-this.getY() > -100) { 
				this.versDroite=false;
				this.deplacement();	
				this.attaque();
			}
			else {
				this.setDirDroite(1);
				if ((this.collisionDroite(this.getX(), this.getY())||
						this.collisionGauche(this.getX(), this.getY()))
						&&this.collisionBas()){
					this.setDirY(-6);
				}
			}
		}
		if (xJoueur-this.getX() > -650 && xJoueur-this.getX() <0  && yJoueur-this.getY() < 100 && yJoueur-this.getY() > -100) { 
			if (xJoueur-this.getX() > -450 && xJoueur-this.getX() <0  && yJoueur-this.getY() < 100 && yJoueur-this.getY() > -100) { 
				this.versDroite=true;
				this.deplacement();
				this.attaque();
			}
			else {
				this.setDirGauche(1);
				if ((this.collisionDroite(this.getX(), this.getY())||
						this.collisionGauche(this.getX(), this.getY()))
						&&this.collisionBas()){
					this.setDirY(-6);
				}
			}
		}
	}
	
	public void deplacement() {
		if (versDroite) {
			this.setDirDroite(1);
		}
		else {
			this.setDirGauche(1);
		}
		if ((this.collisionDroite(this.getX(), this.getY())||
				this.collisionGauche(this.getX(), this.getY()))
				&&this.collisionBas()){
			this.setDirY(-6);
		}
		this.attaque();
		
	}
	
	public void attaque() {
		cooldown++;
		if (cooldown==250) {
			if (versDroite) {
				this.getEnv().creerFleche(this.getX()-35, this.getY(), !versDroite);
			}
			else {
				this.getEnv().creerFleche(this.getX()+30, this.getY(), !versDroite);
			}
			cooldown=0;
		}
	}
}
