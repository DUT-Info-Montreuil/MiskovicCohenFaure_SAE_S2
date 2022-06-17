package application.modele.mobs;

import application.modele.Environnement;

public class BouleBas extends Fleche{

	public BouleBas(double coordX, double coordY, Environnement e, boolean droite) {
		super(coordX, coordY, e, droite);
		this.setDirDroite(0);
		this.setDirGauche(0);
		this.setDirY(2);
	}
	
	public void detection() {
		if (this.collisionBas()){
			this.perdrePV(1, true);
		}
	}

}
