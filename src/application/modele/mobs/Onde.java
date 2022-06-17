package application.modele.mobs;

import application.modele.Environnement;
import application.modele.Outils;

public class Onde extends Mob {
	private int temps;
	private boolean versDroite;

	public Onde(double coordX, double coordY, Environnement e,boolean versDroite) {
		super(coordX, coordY , 1, e,10,10,0,0);
		this.temps=5;
		this.versDroite=versDroite;
		System.out.println("jexiste");
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

			if (this.checkCollision(Outils.coordToTile(this.getX(), this.getY()+5), getEnv())
				&&!this.checkCollision(Outils.coordToTile(this.getX()+d, this.getY()), getEnv())) {
				this.getEnv().creerOnde(this.getX()+d, this.getY(), versDroite);
			}
			this.perdrePV(1, versDroite);
		}
	}

	public void action () {
		this.detection();
		this.meurt();
	}
}
