package application.modele;

import application.modele.mobs.Mob;

public class Fleche extends Mob{
	private boolean versDroite;


	public Fleche(double coordX, double coordY, Environnement e, boolean droite) {
		super(coordX, coordY,1, e, 30, 10);
		if (droite) {
			this.setDirDroite(5);
		}
		else {
			this.setDirGauche(5);
		}
		this.versDroite=droite;
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
			if (this.getX()+this.getLargeur()<e.getX()+e.getLargeur()
					&& this.getX()+this.getLargeur()>e.getX()
					&& this.getY()<e.getY()+e.getHauteur()
					&& this.getY()+this.getHauteur()>e.getY() ) {
				e.perdrePV(1, versDroite);
				this.perdrePV(1, versDroite);
			}
			i++;
		}
	}
}

