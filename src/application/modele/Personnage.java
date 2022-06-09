package application.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Personnage {

	private IntegerProperty coordXProperty, coordYProperty;
	private double dirGauche;
	private double dirDroite; 

	private double dirY;
	private IntegerProperty pvProperty;
	private int pvMax;
	
	private int hauteur , largeur;
	
	private Environnement env;

	public Personnage (int coordX, int coordY, int pvMax,Environnement e,int l, int h) {
		this.coordXProperty = new SimpleIntegerProperty(coordX);
		this.coordYProperty = new SimpleIntegerProperty(coordY);
		this.dirGauche=0;
		this.dirDroite=0;
		dirY = 0;
		this.pvMax = pvMax;
		pvProperty = new SimpleIntegerProperty(pvMax);
		this.env=e;
		this.hauteur=h;
		this.largeur=l;

	}

	public Environnement getEnv() {
		return env;
	}

	//Direction
	public void move () {
		this.coordXProperty.set((int) (coordXProperty.get() + this.dirDroite - this.dirGauche));
		this.coordYProperty.set(coordYProperty.get() + (int)dirY);
	}
	public void additionnerDirY(double d) {
		this.dirY += d;
	}

	//PV
	public void perdrePV(int valeur,boolean versDroite) {
		if (pvProperty.get() - valeur <= 0) {
			pvProperty.set(0);
//			this.env.retirerMob(this);
			System.out.println("mort");
			
		}
		else 
			pvProperty.set(pvProperty.get() - valeur);
		if (versDroite)
			this.setDirDroite(4);
		else 
			this.setDirGauche(4);
		this.setDirY(-5);
	}
	public void ajouterPV(int valeur) {
		if (pvProperty.get() + valeur > pvMax) 
			pvProperty.set(pvMax);
		else
			pvProperty.set(pvProperty.get() + valeur);
	}
	public boolean estMort() {
		return pvProperty.get() <= 0;
	}

	//Setter & Getter//				listenTerrainXProperty();
//	listenMobProperty(m);
	public int getPv() {
		return pvProperty.get();
	}
	public IntegerProperty pvProperty() {
		return pvProperty;
	}
	public int getPvMax() {
		return pvMax;
	}
	public double getDirGauche() {
		return this.dirGauche;
	}
	public void setDirGauche(double d) {
		this.dirGauche=d;
	}
	public double getDirDroite() {
		return this.dirDroite;
	}
	public void setDirDroite(double d) {
		this.dirDroite=d;
	}
	public IntegerProperty xProperty() {
		return coordXProperty;
	}
	public IntegerProperty yProperty() {
		return coordYProperty;
	}
	public int getX () {
		return this.coordXProperty.getValue();
	}
	public int getY () {
		return this.coordYProperty.getValue();
	}
	public double getDirY() {
		return dirY;
	}
	
	public void setDirY(int dirY) {
		this.dirY = dirY;
	}
	public int getHauteur() {
		return hauteur;
	}

	public int getLargeur() {
		return largeur;
	}
	
	//Gestion de l'inertie
	public void inertie() {
		if (this.dirDroite>0) {
			this.setDirDroite(this.dirDroite-0.2);
		}
		if (this.dirGauche>0) {
			this.setDirGauche(this.dirGauche-0.2);
		}
	}
	


	//Collisions
	public void gestionCollision () {
		int x = this.coordXProperty.get();
		int y = this.coordYProperty.get();
		collisionDroite(x,y);
		collisionGauche(x,y);
		collisionHaut(x,y);
		collisionBas();

	}
	public void collisionDroite (int x,int y) {
		if (checkCollision(Outils.coordToTile(x+this.largeur, y-this.hauteur), this.env)||checkCollision(Outils.coordToTile(x+this.largeur, y), this.env)) {
			if (checkCollision(Outils.coordToTile(x+this.largeur-1, y-this.hauteur), this.env)||checkCollision(Outils.coordToTile(x+this.largeur-1, y), this.env)) {
				this.coordXProperty.set((int) (x-this.dirDroite)-1);
			}
			else {
				this.coordXProperty.set((int) (x-this.dirDroite));
			}
		}
	}
	public void collisionGauche (int x,int y) {
		if (checkCollision(Outils.coordToTile(x+9, y-this.hauteur), this.env)||checkCollision(Outils.coordToTile(x+9, y), this.env)) {
			if (checkCollision(Outils.coordToTile(x+10, y-this.hauteur), this.env)||checkCollision(Outils.coordToTile(x+10, y), this.env)) {
				this.coordXProperty.set((int) (x+this.dirGauche)+1);
			}
			else {
				this.coordXProperty.set((int) (x+this.dirGauche));
			}
		}
	}
	public void collisionHaut (int x,int y) {
		if (checkCollision(Outils.coordToTile(x+17, y-this.hauteur-10), this.env)||checkCollision(Outils.coordToTile(x+12, y-this.hauteur-10), this.env)) {
			this.setDirY(1);
		}
	}
	public boolean collisionBas () {
		int x=this.coordXProperty.get();
		int y=this.coordYProperty.get();
		if (checkCollision(Outils.coordToTile(x+17 ,y+1), this.env)||checkCollision(Outils.coordToTile(x+12, y+1), this.env)) {
			if (checkCollision(Outils.coordToTile(x+17 ,y), this.env)||checkCollision(Outils.coordToTile(x+12, y), this.env)) {
				this.coordYProperty.set(this.coordYProperty.get()-1);
			}
			if (this.getDirY()>0 || this.getDirY()==-1) {
				this.setDirY(0);
			}
			return true;
		}
		else {
			return false;
		}
	}
	private boolean checkCollision (int x,Environnement e) {
		return (x<0 || e.getTerrain().getTable()[x]>0);
	}
	
	public void action() {
		this.gestionCollision();
		this.inertie();
		this.move();
	}
	
}
