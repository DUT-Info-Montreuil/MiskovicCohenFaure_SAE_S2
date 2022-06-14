package application.modele.mobs;

import application.modele.Environnement;
import application.modele.Personnage;

public class Fleche extends Mob{
	private boolean versDroite;


	public Fleche(double coordX, double coordY, Environnement e, boolean droite) {
		//modifier souris clique et attaque de archer si l'on modifie la largeur
		super(coordX, coordY,1, e, 30, 10);
		this.versDroite=droite;
		if (droite) {
			this.setDirDroite(5);
		}
		else {
			this.setDirGauche(5);
		}
	}

	@Override
	public void detection() {
		if (this.collisionDroite(this.getX(), this.getY()) 
				|| this.collisionGauche(this.getX(), this.getY())){
			this.perdrePV(1, versDroite);
		}
	}
	
	public void action () {
		this.move();
		this.detection();
		this.attaque();
		this.meurt();
	}
	
	public void attaque() {
		//SYSTEME DE HITBOX
		Personnage e;
		int i=0;
		while (i<this.getEnv().getMobs().size()) {
			e=this.getEnv().getMobs().get(i);
			if (e!=this) {
				this.touchePerso(e);
			}
			i++;
		}
		this.touchePerso(this.getJoueur());
	}
	
	public void touchePerso (Personnage e) {
		if (this.getX()<e.getX()+e.getLargeur()
				&& this.getX()+this.getLargeur()>e.getX()
				&& this.getY()<e.getY()+e.getHauteur()
				&& this.getY()+this.getHauteur()>e.getY() ) {
			e.perdrePV(1, versDroite);
			this.perdrePV(1, versDroite);
		}
	}

	public boolean isVersDroite() {
		return versDroite;
	}

}

