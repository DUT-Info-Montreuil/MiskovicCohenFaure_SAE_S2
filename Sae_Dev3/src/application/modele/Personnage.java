package application.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Personnage {

	private IntegerProperty coordXProperty, coordYProperty;
	private int dirGauche,dirDroite, dirY;
	private IntegerProperty pvProperty;
	private int pvMax;

	public Personnage (int coordX, int coordY, int pvMax) {
		this.coordXProperty = new SimpleIntegerProperty(coordX);
		this.coordYProperty = new SimpleIntegerProperty(coordY);
		dirGauche = 0;
		dirDroite = 0; 
		dirY = 0;
		this.pvMax = pvMax;
		pvProperty = new SimpleIntegerProperty(pvMax);
	}


	//Direction
	public void move () {
		this.coordXProperty.set(coordXProperty.get() + this.dirDroite - this.dirGauche);
		this.coordYProperty.set(coordYProperty.get() + dirY);
	}
	public void additionnerDirY(int nb) {
		this.dirY += nb;
	}

	//PV
	public void perdrePV(int valeur) {
		if (pvProperty.get() - valeur <= 0)
			pvProperty.set(0);
		else 
			pvProperty.set(pvProperty.get() - valeur);
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


	//Setter & Getter
	public int getPv() {
		return pvProperty.get();
	}
	public IntegerProperty pvProperty() {
		return pvProperty;
	}
	public int getPvMax() {
		return pvMax;
	}
	public int getDirGauche() {
		return this.dirGauche;
	}
	public void setDirGauche(int dirGauche) {
		this.dirGauche=dirGauche;
	}
	public int getDirDroite() {
		return this.dirDroite;
	}
	public void setDirDroite(int dirDroite) {
		this.dirDroite=dirDroite;
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
	public int getDirY() {
		return dirY;
	}

	public void setDirY(int dirY) {
		this.dirY = dirY;
	}

	public void gestionCollision (Environnement e) {
		int x = this.coordXProperty.get();
		int y = this.coordYProperty.get();
		collisionDroite(e,x,y);
		collisionGauche(e,x,y);
		collisionHaut(e,x,y);
		collisionBas(e,x,y);

	}

	public void collisionDroite (Environnement e,int x,int y) {
		if (checkCollision(Outils.coordToTile(x+20, y-25), e)||checkCollision(Outils.coordToTile(x+20, y-1), e)) {
			this.coordXProperty.set(x-this.dirDroite);
		}
	}

	public void collisionGauche (Environnement e,int x,int y) {
		if (checkCollision(Outils.coordToTile(x+10, y-25), e)||checkCollision(Outils.coordToTile(x+10, y-1), e)) {
			this.coordXProperty.set(x+this.dirGauche);
		}
	}

	public void collisionHaut (Environnement e,int x,int y) {
		if (checkCollision(Outils.coordToTile(x+17, y-32), e)||checkCollision(Outils.coordToTile(x+12, y-32), e)) {
			this.setDirY(1);
		}
	}

	public boolean collisionBas (Environnement e,int x,int y) {
		if (checkCollision(Outils.coordToTile(x+17 ,y+1), e)||checkCollision(Outils.coordToTile(x+12, y+1), e)) {
			if (checkCollision(Outils.coordToTile(x+17 ,y), e)||checkCollision(Outils.coordToTile(x+12, y), e)) {
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
}
