package application.modele.mobs.boss;

import application.modele.Environnement;
import application.modele.Outils;

public class Onde extends Mob {
	private int temps;
	private boolean versDroite;
	private int vitesse;

	public Onde(double coordX, double coordY, Environnement e,boolean versDroite,int vitesse) {
		super(coordX, coordY , 1, e,10,5,0,5);
		this.temps=vitesse;
		this.versDroite=versDroite;
		this.vitesse=vitesse;
	}

	@Override
	public void detection() {
		temps--;
		int d;
		this.attaque();
		if (this.temps==0) {
			if (this.versDroite ) {
				d=20;
			}
			else {
				d=-20;

			}

			if (this.checkCollision(Outils.coordToTile(this.getX(), this.getY()), getEnv())
				&&!this.checkCollision(Outils.coordToTile(this.getX()+d, this.getY()-10), getEnv())) {
				this.getEnv().creerOnde(this.getX()+d, this.getY(), versDroite,this.vitesse);
			}
			this.perdrePV(1, versDroite);
		}
	}

	public void action () {
		this.detection();
		this.meurt();
	}
}
