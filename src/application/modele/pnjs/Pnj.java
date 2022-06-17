package application.modele.pnjs;

import application.modele.Environnement;
import application.modele.Personnage;

public abstract class Pnj extends Personnage{

	private boolean versDroite;
	private int temps;

	public Pnj(int coordX, int coordY, Environnement e, int h, int  d, int  b, int  g) {
		super ( coordX,  coordY,  0, e, h,  d,  b,  g) ;
		this.temps=0;
		versDroite = false;
	}

	public void deplacement() {
		this.additionnerDirY(1);
		temps++;
		switch (temps) {
		case 100:
			if (versDroite) {
				this.setDirDroite(2);
			}
			else {
				this.setDirGauche(2);
			}
			break;
		case 200:
			if (versDroite) {
				this.setDirDroite(2);
			}
			else {
				this.setDirGauche(2);
			}
			break;
		case 300:
			if (versDroite) {
				this.setDirDroite(2);
				versDroite = false;
			}
			else {
				this.setDirGauche(2);
				versDroite = true;
			}

			temps = 0;
			break;
		}
	}

	public void action() {
		super.action();
		deplacement();
	}

}
