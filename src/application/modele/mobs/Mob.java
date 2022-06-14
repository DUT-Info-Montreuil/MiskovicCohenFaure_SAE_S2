package application.modele.mobs;

import application.modele.Environnement;
import application.modele.Joueur;
import application.modele.Personnage;

public abstract class Mob extends Personnage{


	public Mob(double coordX, double coordY,int pv, Environnement e,int l,int h) {
		super(coordX, coordY,pv,e,l,h);
		this.getEnv().ajouterMob(this); 
	}

	public abstract void detection();
	
	public void attaque() {
		//SYSTEME DE HITBOX
		Joueur j=this.getEnv().getJoueur();
		if ((this.getX()<j.getX()+j.getLargeur()&&this.getX()+this.getLargeur()>j.getX()
			&& this.getY()+this.getHauteur()>j.getY()&&this.getY()<j.getY()+j.getHauteur())) {
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
		this.attaque();
	}
	
}


	




