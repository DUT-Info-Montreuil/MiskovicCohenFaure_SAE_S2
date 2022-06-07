
package application.modele;

public abstract class Mob extends Personnage{


	public Mob(int coordX, int coordY,Environnement e,int l,int h) {
		super(coordX, coordY,3,e,l,h);
	}

	public abstract void detectionJoueur();
	public abstract void deplacement(boolean versDroite);
	public void attaque() {
		// FAIRE SYSTEME DE HITBOX
		Joueur j=this.getEnv().getJoueur();
		if ((this.getX()>j.getX()&&this.getX()<j.getX()+j.getLargeur()
			|| this.getX()+this.getLargeur()>j.getX()&&this.getX()+this.getLargeur()<j.getX()+j.getLargeur())
			&& (this.getY()>j.getY()&&this.getY()<j.getY()+j.getHauteur()
			||this.getY()+this.getHauteur()>j.getY()&&this.getY()+this.getHauteur()<j.getY()+j.getHauteur())) {
			j.perdrePV(1,this.getX()<j.getX());
		}
	}
	
	//Getter
	public Joueur getJoueur() {
		return this.getEnv().getJoueur();
	}
	
	public void action() {
		super.action();
		this.detectionJoueur();
		this.attaque();
	}
	
}



