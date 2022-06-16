package application.modele.mobs;

import application.modele.Environnement;
import application.modele.Joueur;

public class Boss extends Mob{
	private int temps;
	private boolean versDroite;

	public Boss(double coordX, double coordY, Environnement e) {
		super(coordX, coordY, 30, e, 20,20,0,0);
		this.setDirDroite(1);
		this.temps=400;
		this.versDroite=true;
	}

	public void detection() {
		double xJoueur = this.getJoueur().getX();
		double yJoueur = this.getJoueur().getY();
		if (xJoueur-this.getX() < 750 && xJoueur-this.getX() >0  && yJoueur-this.getY() < 100 && yJoueur-this.getY() > -100) {
			this.gestionDeplacement(true);
		}
		else if (xJoueur-this.getX() > -750 && xJoueur-this.getX() <0  && yJoueur-this.getY() < 100 && yJoueur-this.getY() > -100) { 
			this.gestionDeplacement(false);
		}
		else {
			this.deplacementPassif();
		}
	}
	
	public void gestionDeplacement (boolean versDroite) {
		if (this.getPv()>15) {
			if (this.temps<500) {
				this.phase1V1(versDroite);
			}
			else {
				if (temps==500) {
					this.versDroite=versDroite;
				}
				this.phase1V2();
			}
		}
		else {
			
		}
		if (temps==1000) {
			temps=0;
		}
		temps++;
	}
	
	public void phase1V1 (boolean versDroite) {
		if (versDroite) {
			this.setDirGauche(2);
			if (this.temps%75==0) {
				this.getEnv().creerBouleDeFeu(this.getX()+30, this.getY(), versDroite);
			}
			
		}
		else {
			this.setDirDroite(2);
			if (this.temps%75==0) {
				this.getEnv().creerBouleDeFeu(this.getX()-35, this.getY(), versDroite);
			}
		}
		
		if ((this.collisionDroite(this.getX(), this.getY())||
				this.collisionGauche(this.getX(), this.getY()))
				&&this.collisionBas()){
			this.setDirY(-6);
		}
		
	}
	
	public void phase1V2 () {
		if (this.versDroite) {
			if (!(this.collisionDroite(this.getX(), this.getY()))) {
				if (this.getDirDroite()<5) {
					this.setDirDroite(this.getDirDroite()+0.3);
				}
				this.attaque(2);
			}
		}
		else {
			if (!(this.collisionGauche(this.getX(), this.getY()))) {
				if (this.getDirGauche()<5) {
					this.setDirGauche(this.getDirGauche()+0.3);
				}
				this.attaque(2);
			}
		}
	}
	
	public void phase2V1 (boolean versDroite) {
		//s ecraser
	}
	
	public void phase2V2 (boolean versDroite) {
		//?
	}

	
	public void deplacementPassif() {
		if (this.collisionDroite(this.getX(), this.getY()) 
				|| this.collisionGauche(this.getX(), this.getY())){
			this.versDroite=!this.versDroite;
		}
		if (this.versDroite) {
			this.setDirDroite(1);
		}
		else {
			this.setDirGauche(1);
		}
	}
	
	public void attaque(int pv) {
		//SYSTEME DE HITBOX
		Joueur j=this.getEnv().getJoueur();
		if ((this.getX()-this.gettGauche()<j.getX()+j.gettDroite()&&this.getX()+this.gettDroite()>j.getX()-j.gettGauche()
			&& this.getY()+this.gettBas()>j.getY()-j.getThaut()&&this.getY()-this.getThaut()<j.getY()+j.gettBas())) {
			j.perdrePV(pv,this.getX()<j.getX());
		}
	}
}