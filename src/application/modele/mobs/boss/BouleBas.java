package application.modele.mobs.boss;

import application.modele.Environnement;
import application.modele.mobs.Fleche;

public class BouleBas extends Fleche{

	public BouleBas(double coordX, double coordY, Environnement e, boolean droite) {
		super(coordX, coordY, e, droite);
		this.setDirDroite(0);
		this.setDirGauche(0);
		this.setDirY(2);
	}
	
	public void detection() {
		if (this.collisionBas()){
			this.getEnv().creerOnde(this.getX()-50, this.getY()+5, false,6);
			this.getEnv().creerOnde(this.getX()+50, this.getY()+5, true,6);
			this.perdrePV(1, true);
		}
	}

}
