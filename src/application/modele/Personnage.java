package application.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Personnage {

	private Environnement env;
	private IntegerProperty coordXProperty, coordYProperty;
	private int dirVitGauche,dirVitDroite, dirY;
	private IntegerProperty pvProperty;
	private int pvMax;

	public Personnage (int coordX, int coordY, int pvMax, Environnement env) {
		this.coordXProperty = new SimpleIntegerProperty(coordX);
		this.coordYProperty = new SimpleIntegerProperty(coordY);
		this.dirVitGauche = 0;
		this.dirVitDroite = 0; 
		this.dirY = 0;
		this.pvMax = pvMax;
		this.pvProperty = new SimpleIntegerProperty(pvMax);
		this.env = env;
	}


	//Direction
	public void move () {
		this.coordXProperty.set(coordXProperty.get() + this.dirVitDroite - this.dirVitGauche);
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
		return this.dirVitGauche;
	}
	public void setDirGauche(int dirGauche) {
		this.dirVitGauche=dirGauche;
	}
	public int getDirDroite() {
		return this.dirVitDroite;
	}
	public void setDirDroite(int dirDroite) {
		this.dirVitDroite=dirDroite;
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

	public void gestionCollision () {
		int x = this.coordXProperty.get();
		int y = this.coordYProperty.get();
		collisionDroite(x,y);
		collisionGauche(x,y);
		collisionHaut(x,y);
		collisionBas(x,y);

	}

	public void collisionDroite (int x,int y) {
		if (checkCollision(Outils.coordToTile(x+20, y-25))||checkCollision(Outils.coordToTile(x+20, y-1))) {
			this.coordXProperty.set(x-this.dirVitDroite);
		}
	}

	public void collisionGauche (int x,int y) {
		if (checkCollision(Outils.coordToTile(x+10, y-25))||checkCollision(Outils.coordToTile(x+10, y-1))) {
			this.coordXProperty.set(x+this.dirVitGauche);
		}
	}

	public void collisionHaut (int x,int y) {
		if (checkCollision(Outils.coordToTile(x+17, y-32))||checkCollision(Outils.coordToTile(x+12, y-32))) {
			this.setDirY(1);
		}
	}

	public boolean collisionBas (int x,int y) {
		if (checkCollision(Outils.coordToTile(x+17 ,y+1))||checkCollision(Outils.coordToTile(x+12, y+1))) {
			if (checkCollision(Outils.coordToTile(x+17 ,y))||checkCollision(Outils.coordToTile(x+12, y))) {
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

	private boolean checkCollision (int x) {
		return (x<0 || env.getTerrain().getTable()[x]>0);
	}
}
