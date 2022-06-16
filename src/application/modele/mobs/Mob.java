package application.modele.mobs;

import application.modele.Environnement;
import application.modele.Joueur;
import application.modele.Personnage;

public abstract class Mob extends Personnage{


	public Mob(double coordX, double coordY, int pvMax,Environnement e,int h, int d, int b, int g) {
		super ( coordX,  coordY,  pvMax, e, h,  d,  b,  g) ;
	}

	public abstract void detection();
	
	public void attaque() {
		//SYSTEME DE HITBOX
		Joueur j=this.getEnv().getJoueur();
		if ((this.getX()-this.gettGauche()<j.getX()+j.gettDroite()&&this.getX()+this.gettDroite()>j.getX()-j.gettGauche()
			&& this.getY()+this.gettBas()>j.getY()-j.getThaut()&&this.getY()-this.getThaut()<j.getY()+j.gettBas())) {
			j.perdrePV(1,this.getX()<j.getX());
		}
	}
	
	//Getter
	public Joueur getJoueur() {
		return this.getEnv().getJoueur();
	}
	
	public void action() {
		super.action();
		this.detection();
	}
	
}


	




