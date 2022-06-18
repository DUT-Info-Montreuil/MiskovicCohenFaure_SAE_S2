package application.modele.mobs.boss;

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
		if ((this.getX()-this.gettGauche()<j.getX()+j.getTDroite()&&this.getX()+this.getTDroite()>j.getX()-j.gettGauche()
			&& this.getY()+this.getTBas()>j.getY()-j.getTHaut()&&this.getY()-this.getTHaut()<j.getY()+j.getTBas())) {
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


	




